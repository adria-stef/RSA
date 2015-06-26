package fmi.adii.ecalculator.cache;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import java.util.Map.Entry;

import org.apfloat.Apfloat;
import org.apfloat.Apint;

import fmi.adii.ecalculator.cache.util.IOUtil;

public class CalculatorThread extends Thread {

	private int maxMember;
	private CalculatorManager calculatorManager;
	private FactorialCache factorialCache;
	private int precision;
	private String fileName;
	private Apfloat grandSum = new Apfloat(0, DEFAULT_PRECISION_VALUE);

	public CalculatorThread(int maxMember, CalculatorManager calculatorManager, FactorialCache factorialCache,
			int precision, String fileName) {
		this.maxMember = maxMember;
		this.calculatorManager = calculatorManager;
		this.factorialCache = factorialCache;
		this.precision = precision;
		this.fileName = fileName;
	}

	public Apfloat getGrandSum() {
		return grandSum;
	}

	@Override
	public void run() {

		long startTime = System.currentTimeMillis();

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

		long endTime = System.currentTimeMillis();

		writeInFileIfNecessary(endTime - startTime);
	}

	private void writeInFileIfNecessary(long time) {
		if (null != fileName) {
			IOUtil ioUtil = IOUtil.getInstance();
			ioUtil.writeWithNewLine(String.format(MESSAGE_THREAD_TIME_FORMAT, time), fileName);
		}
	}
}
