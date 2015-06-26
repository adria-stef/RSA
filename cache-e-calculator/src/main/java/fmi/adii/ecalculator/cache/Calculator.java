package fmi.adii.ecalculator.cache;

import static fmi.adii.ecalculator.cache.util.Constants.MESSAGE_COULD_NOT_GET_PARAMETERS;

import org.apfloat.Apfloat;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import fmi.adii.ecalculator.cache.util.Parameters;

public class Calculator {

	private static int maxMember;
	private static int tasks;
	private static int precision;
	private static String fileName;
	private static boolean quiet;

	public static void main(String[] args) throws Exception {

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
			fileName = parameters.getFileName();
			quiet = parameters.getQuiet();
			
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

		System.out.println("Total time: " + (endTime - startTime));
		System.out.println(e);
	}

	private Apfloat calculateResult(int t, CalculatorThread[] calculatorThreads) {
		Apfloat e = Apfloat.ZERO;

		for (int i = 0; i < t; i++) {
			e = e.add(calculatorThreads[i].getGrandSum());
		}

		return e;
	}

	private CalculatorThread[] execute(int maxMember, int tasks) throws InterruptedException {
		CalculatorThread[] calculatorThreads = new CalculatorThread[tasks];
		CalculatorManager calculatorManager = new CalculatorManager();
		FactorialCache factorialCache = new FactorialCache();

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i] = new CalculatorThread(maxMember, calculatorManager, factorialCache, precision);
		}

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].start();
		}

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].join();
		}

		return calculatorThreads;
	}
}
