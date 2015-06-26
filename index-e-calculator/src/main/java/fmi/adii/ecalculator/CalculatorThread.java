package fmi.adii.ecalculator;

import static fmi.adii.ecalculator.util.Constants.DEFAULT_PRECISION_VALUE;
import static fmi.adii.ecalculator.util.Constants.MESSAGE_THREAD_TIME_FORMAT;

import org.apfloat.Apfloat;
import org.apfloat.Apint;
import org.apfloat.ApintMath;

import fmi.adii.ecalculator.util.IOUtil;

public class CalculatorThread extends Thread {

	private int maxMember;
	private int tasks;
	private int threadIndex;
	private int precision;
	private String fileName;

	private Apfloat grandSum = new Apfloat(0, DEFAULT_PRECISION_VALUE);

	public CalculatorThread(int maxMember, int tasks, int threadIndex, int precision, String fileName) {
		this.maxMember = maxMember;
		this.tasks = tasks;
		this.threadIndex = threadIndex;
		this.precision = precision;
		this.fileName = fileName;
	}

	@Override
	public void run() {

		long startTime = System.currentTimeMillis();

		int factorialDiffCount = 2 * tasks;
		Apint factorial = ApintMath.factorial(2 * threadIndex + 1);

		for (int i = threadIndex; i <= maxMember; i += tasks) {
			Apfloat nominator = new Apfloat(3 - (4 * i * i));
			grandSum = grandSum.add(nominator.divide(new Apfloat(factorial.toString(), precision)));

			Apint difference = Apint.ONE;
			for (int j = 0; j < factorialDiffCount; j++) {
				difference = difference.multiply(new Apint(2 * i + 2 + j));
			}

			factorial = factorial.multiply(difference);
		}

		long endTime = System.currentTimeMillis();

		writeInFileIfNecessary(endTime - startTime);
	}

	private void writeInFileIfNecessary(long time) {
		if (null != fileName) {
			IOUtil ioUtil = IOUtil.getInstance();
			ioUtil.writeWithNewLine(String.format(MESSAGE_THREAD_TIME_FORMAT, threadIndex, time), fileName);
		}
	}

	public Apfloat getGrandSum() {
		return grandSum;
	}

}