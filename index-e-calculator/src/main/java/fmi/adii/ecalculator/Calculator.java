package fmi.adii.ecalculator;

import static fmi.adii.ecalculator.util.Constants.*;

import org.apfloat.Apfloat;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import fmi.adii.ecalculator.util.Parameters;
import fmi.adii.ecalculator.util.IOUtil;

public class Calculator {

	private static int maxMember;
	private static int tasks;
	private static int precision;
	private static String fileName;
	private static boolean quiet;

	private static IOUtil ioUtil;

	public static void main(String[] args) throws Exception {
		ioUtil = IOUtil.getInstance();

		initParameters(args);

		Calculator calculator = new Calculator();
		long startTime;
		long endTime;
		for (int current = 1; current <= tasks; current += 1) {

			startTime = System.currentTimeMillis();

			Apfloat e = calculator.calculate(maxMember, current);

			endTime = System.currentTimeMillis();

			createOutput(e, (endTime - startTime), current);
		}

	}

	private static void initParameters(String[] args) {
		Parameters parameters = new Parameters();
		JCommander jCommander = new JCommander(parameters);

		try {
			jCommander.parse(args);

			maxMember = parameters.getMaxMember();
			tasks = parameters.getTasks();
			precision = parameters.getPrecision();
			quiet = parameters.getQuiet();
			if (quiet) {
				fileName = null;
			} else {
				fileName = parameters.getFileName();
				ioUtil.checkAndDeleteIfFileExists(fileName);
			}

		} catch (ParameterException ex) {
			System.out.println(MESSAGE_COULD_NOT_GET_PARAMETERS);
			jCommander.usage();
		}
	}

	private Apfloat calculate(int maxMember, int tasks) throws InterruptedException {

		CalculatorThread[] calculatorThreads = execute(maxMember, tasks);
		return calculateResult(tasks, calculatorThreads);
	}

	private CalculatorThread[] execute(int maxMember, int tasks) throws InterruptedException {

		CalculatorThread[] calculatorThreads = new CalculatorThread[tasks];

		for (int threadIndex = 0; threadIndex < tasks; threadIndex++) {
			calculatorThreads[threadIndex] = new CalculatorThread(maxMember, tasks, threadIndex, precision, fileName);
		}

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].start();
		}

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].join();
		}

		return calculatorThreads;
	}

	private Apfloat calculateResult(int tasks, CalculatorThread[] calculatorThreads) {
		Apfloat e = Apfloat.ZERO;

		for (int i = 0; i < tasks; i++) {
			e = e.add(calculatorThreads[i].getGrandSum());
		}

		return e;
	}

	private static void createOutput(Apfloat e, long time, int current) {
		String totalTimeMessage = String.format(MESSAGE_TOTAL_TIME_FORMAT, current, time);

		if (quiet) {
			System.out.println(totalTimeMessage);
		} else {
			ioUtil.writeWithNewLine(totalTimeMessage, fileName);
			ioUtil.writeWithNewLine(String.format(MESSAGE_E_RESULT_FORMAT, e), fileName);
			ioUtil.writeWithNewLine("", fileName);
		}
	}
}