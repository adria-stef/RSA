package fmi.adii.ecalculator.cache;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import org.apfloat.Apfloat;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import fmi.adii.ecalculator.cache.util.IOUtil;
import fmi.adii.ecalculator.cache.util.Parameters;

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

		for (int current = 1; current <= tasks; current += 1) {
			calculator.calculate(maxMember, current);
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

	private void calculate(int maxMember, int tasks) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		CalculatorThread[] calculatorThreads = execute(maxMember, tasks);
		Apfloat e = calculateResult(tasks, calculatorThreads);

		long endTime = System.currentTimeMillis();

		createOutput(e, (endTime - startTime));
	}

	private CalculatorThread[] execute(int maxMember, int tasks) throws InterruptedException {
		CalculatorThread[] calculatorThreads = new CalculatorThread[tasks];
		CalculatorManager calculatorManager = new CalculatorManager();
		FactorialCache factorialCache = new FactorialCache();

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i] = new CalculatorThread(maxMember, calculatorManager, factorialCache, precision, fileName);
		}

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].start();
		}

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].join();
		}

		return calculatorThreads;
	}

	private Apfloat calculateResult(int t, CalculatorThread[] calculatorThreads) {
		Apfloat e = Apfloat.ZERO;

		for (int i = 0; i < t; i++) {
			e = e.add(calculatorThreads[i].getGrandSum());
		}

		return e;
	}

	private void createOutput(Apfloat e, long time) {
		String totalTimeMessage = String.format(MESSAGE_TOTAL_TIME_FORMAT, time);

		if (quiet) {
			System.out.println(totalTimeMessage);
		} else {
			ioUtil.writeWithNewLine(totalTimeMessage, fileName);
			ioUtil.writeWithNewLine(String.format(MESSAGE_E_RESULT_FORMAT, e), fileName);
			ioUtil.writeWithNewLine("", fileName);
		}
	}
}
