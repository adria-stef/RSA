package fmi.adii.ecalculator.cache;

import static fmi.adii.ecalculator.cache.util.Constants.DEFAULT_PRECISION;

import java.util.Map.Entry;

import org.apfloat.Apfloat;
import org.apfloat.Apint;

public class CalculatorThread extends Thread {

	private int maxMember;
	private CalculatorManager calculatorManager;
	private FactorialCache factorialCache;
	private int precision;
	private Apfloat grandSum = new Apfloat(0, DEFAULT_PRECISION);

	public CalculatorThread(int p, CalculatorManager calculatorManager, FactorialCache factorialCache, int precision) {
		this.maxMember = p;
		this.calculatorManager = calculatorManager;
		this.factorialCache = factorialCache;
		this.precision = precision;
	}

	@Override
	public void run() {
		System.out.println("precision is : " + precision);
		long start = System.currentTimeMillis();

		int index = calculatorManager.getNextIndex();

		while (index <= maxMember) {
			int factorialToCount = 2 * index + 1;
			Entry<Integer, Apint> factorialEntry = factorialCache.getHighestFactorial(factorialToCount);
			Apint factorial = Apint.ONE;

			for (int i = factorialEntry.getKey() + 1; i <= factorialToCount; i++) {
				factorial = factorial.multiply(new Apint(i));
			}

			factorial = factorial.multiply(factorialEntry.getValue());
			factorialCache.addFactorial(factorialToCount, factorial);

			Apfloat nominator = new Apfloat(3 - (4 * index * index));
			grandSum = grandSum.add(nominator.divide(new Apfloat(factorial.toString(), precision)));

			index = calculatorManager.getNextIndex();
		}

		long end = System.currentTimeMillis();
		System.out.println("thread " + " time is " + (end - start));
	}

	public Apfloat getGrandSum() {
		return grandSum;
	}
	
}
