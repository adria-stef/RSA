package fmi.adii.ecalculator.cache;

import java.util.Map.Entry;

import org.apfloat.Apfloat;
import org.apfloat.Apint;

public class CalculatorThread extends Thread {

	private static final int PRECISION = 100;

	private int p;
	private CalculatorManager calculatorManager;
	private FactorialCache factorialCache;
	private Apfloat grandSum = new Apfloat(0, PRECISION);

	public CalculatorThread(int p, CalculatorManager calculatorManager, FactorialCache factorialCache) {
		this.p = p;
		this.calculatorManager = calculatorManager;
		this.factorialCache = factorialCache;
	}

	@Override
	public void run() {
		long start = System.currentTimeMillis();
		int index = calculatorManager.getNextIndex();
		while (index <= p) {
			int factorialToCount = 2 * index + 1;
			Entry<Integer, Apint> factorialEntry = factorialCache.getHighestFactorial(factorialToCount);
			Apint factorial = Apint.ONE;

			for (int i = factorialEntry.getKey() + 1; i <= factorialToCount; i++) {
				factorial = factorial.multiply(new Apint(i));
			}

			factorial = factorial.multiply(factorialEntry.getValue());
			factorialCache.addFactorial(factorialToCount, factorial);

			Apfloat nominator = new Apfloat(3 - (4 * index * index));
			grandSum = grandSum.add(nominator.divide(new Apfloat(factorial.toString(), PRECISION)));

			index = calculatorManager.getNextIndex();
		}

		long end = System.currentTimeMillis();
		System.out.println("thread " + " time is " + (end - start));
	}

	public Apfloat getGrandSum() {
		return grandSum;
	}

}
