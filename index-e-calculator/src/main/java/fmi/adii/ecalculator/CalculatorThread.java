package fmi.adii.ecalculator;

import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

public class CalculatorThread extends Thread {

	private static final int PRECISION = 100;

	private int p;
	private int t;
	private int threadIndex;
	private Apfloat grandSum = new Apfloat(0, PRECISION);

	public CalculatorThread(int p, int t, int threadIndex) {
		this.p = p;
		this.t = t;
		this.threadIndex = threadIndex;
	}

	@Override
	public void run() {

		long start = System.currentTimeMillis();

		int factorialDiffCount = 2 * t;
		Apint factorial = ApintMath.factorial(2 * threadIndex + 1);

		for (int i = threadIndex; i <= p; i += t) {
			Apfloat nominator = new Apfloat(3 - (4 * i * i));
			grandSum = grandSum.add(nominator.divide(new Apfloat(factorial.toString(), PRECISION)));

			Apint diff = Apint.ONE;
			for (int j = 0; j < factorialDiffCount; j++) {
				diff = diff.multiply(new Apint(2 * i + 2 + j));
			}

			factorial = factorial.multiply(diff);
		}

		long end = System.currentTimeMillis();
		System.out.println("thread #" + threadIndex + " time is " + (end - start));
	}

	public Apfloat getGrandSum() {
		return grandSum;
	}

}