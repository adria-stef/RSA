package fmi.adii.ecalculator.cache.threads;

import static fmi.adii.ecalculator.cache.util.Constants.DEFAULT_PRECISION_VALUE;

import java.util.Map.Entry;

import org.apfloat.Apfloat;
import org.apfloat.Apint;

import fmi.adii.ecalculator.cache.CalculatorManager;
import fmi.adii.ecalculator.cache.FactorialCache;
import fmi.adii.ecalculator.cache.output.CalculatorOutput;

public class CalculatorThread extends Thread {

	private int precision;
	private CalculatorManager calculatorManager;
	private FactorialCache factorialCache;
	private CalculatorOutput calculatorOutput;
	private Apfloat grandSum = new Apfloat(0, DEFAULT_PRECISION_VALUE);

	public CalculatorThread(int precision, CalculatorManager calculatorManager, FactorialCache factorialCache,
			CalculatorOutput calculatorOutput) {

		this.calculatorManager = calculatorManager;
		this.factorialCache = factorialCache;
		this.calculatorOutput = calculatorOutput;
		this.precision = precision;
	}

	@Override
	public void run() {

		long startTime = System.currentTimeMillis();

		int index = calculatorManager.getNextIndex();

		Apfloat lastMember = Apfloat.ZERO;
		do {
			Apfloat denominator = calculateDenominator(index);
			Apfloat nominator = new Apfloat(3 - (4 * index * index));

			lastMember = grandSum;
			grandSum = grandSum.add(nominator.divide(denominator));

			index = calculatorManager.getNextIndex();
		} while (!grandSum.subtract(lastMember).equals(Apfloat.ZERO));

		long endTime = System.currentTimeMillis();

		calculatorOutput.outputThreadTime(endTime - startTime);
	}

	private Apfloat calculateDenominator(int index) {
		int factorialToCount = 2 * index + 1;
		Entry<Integer, Apint> factorialEntry = factorialCache.getHighestFactorial(factorialToCount);
		Apint factorial = calculateFactorial(factorialToCount, factorialEntry);
		factorialCache.addFactorial(factorialToCount, factorial);
		return new Apfloat(factorial.toString(), precision + 2);
	}

	private Apint calculateFactorial(int factorialToCount, Entry<Integer, Apint> factorialEntry) {
		Apint factorial = Apint.ONE;
		for (int i = factorialEntry.getKey() + 1; i <= factorialToCount; i++) {
			factorial = factorial.multiply(new Apint(i));
		}

		factorial = factorial.multiply(factorialEntry.getValue());
		return factorial;
	}

	public Apfloat getGrandSum() {
		return grandSum;
	}
}
