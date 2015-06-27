package fmi.adii.ecalculator.cache;

import org.apfloat.Apfloat;

import fmi.adii.ecalculator.cache.output.CalculatorOutput;
import fmi.adii.ecalculator.cache.threads.CalculatorThread;
import fmi.adii.ecalculator.cache.util.Constants;
import fmi.adii.ecalculator.cache.util.FloatUtil;

public class Calculator {

	public int precision;
	public int tasks;
	private CalculatorOutput calculatorOutput;

	private static FloatUtil floatUtil = FloatUtil.getInstance();

	public Calculator(int precision, int tasks, CalculatorOutput calculatorOutput) {
		this.precision = precision;
		this.tasks = tasks;
		this.calculatorOutput = calculatorOutput;
	}

	public void execute() {

		long startTime = System.currentTimeMillis();

		Apfloat e;
		try {
			e = calculate();
		} catch (InterruptedException ex) {
			throw new RuntimeException(Constants.MESSAGE_E_COULD_NOT_BE_CALCULATED);
		}

		long endTime = System.currentTimeMillis();

		calculatorOutput.output(e, (endTime - startTime));
	}

	private Apfloat calculate() throws InterruptedException {

		CalculatorThread[] calculatorThreads = calculateInParallel();
		return calculateResult(calculatorThreads);

	}

	private CalculatorThread[] calculateInParallel() throws InterruptedException {
		CalculatorThread[] calculatorThreads = initThreads();

		startThreads(calculatorThreads);

		joinThreads(calculatorThreads);

		return calculatorThreads;
	}

	private CalculatorThread[] initThreads() throws InterruptedException {
		CalculatorThread[] calculatorThreads = new CalculatorThread[tasks];

		CalculatorManager calculatorManager = new CalculatorManager();
		FactorialCache factorialCache = new FactorialCache();

		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i] = new CalculatorThread(precision, calculatorManager, factorialCache, calculatorOutput);
		}

		return calculatorThreads;
	}

	private void startThreads(CalculatorThread[] calculatorThreads) {
		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].start();
		}
	}

	private void joinThreads(CalculatorThread[] calculatorThreads) throws InterruptedException {
		for (int i = 0; i < tasks; i++) {
			calculatorThreads[i].join();
		}
	}

	private Apfloat calculateResult(CalculatorThread[] calculatorThreads) {
		Apfloat e = Apfloat.ZERO;

		for (int i = 0; i < tasks; i++) {
			e = e.add(calculatorThreads[i].getGrandSum());
		}

		return floatUtil.round(e, precision);
	}

}
