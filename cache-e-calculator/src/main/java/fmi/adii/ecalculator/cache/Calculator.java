package fmi.adii.ecalculator.cache;

import org.apfloat.Apfloat;

public class Calculator {

	public static void main(String[] args) throws Exception {
		Calculator calculator = new Calculator();

		for (int current = 1; current <= 8; current += 1) {
			calculator.calculate(15000, current);
		}
	}

	private void calculate(int p, int t) throws InterruptedException {
		long startTime = System.currentTimeMillis();

		CalculatorThread[] calculatorThreads = execute(p, t);
		Apfloat e = calculateResult(t, calculatorThreads);

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

	private CalculatorThread[] execute(int p, int t) throws InterruptedException {
		CalculatorThread[] calculatorThreads = new CalculatorThread[t];
		CalculatorManager calculatorManager = new CalculatorManager();
		FactorialCache factorialCacheV4 = new FactorialCache();

		for (int i = 0; i < t; i++) {
			calculatorThreads[i] = new CalculatorThread(p, calculatorManager, factorialCacheV4);
		}

		for (int i = 0; i < t; i++) {
			calculatorThreads[i].start();
		}

		for (int i = 0; i < t; i++) {
			calculatorThreads[i].join();
		}

		return calculatorThreads;
	}

}
