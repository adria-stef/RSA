package fmi.adii.ecalculator.cache.output;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import org.apfloat.Apfloat;

import fmi.adii.ecalculator.cache.util.FloatUtil;

public class CalculatorOutputFile implements CalculatorOutput {

	private static FloatUtil floatUtil = FloatUtil.getInstance();

	public int precision;
	public int tasks;
	public String fileName;
	private CalculatorWriter writer;

	public CalculatorOutputFile(int precision, int tasks, String fileName) {
		this.precision = precision;
		this.tasks = tasks;
		this.fileName = fileName;
		this.writer = new CalculatorWriter(fileName);

	}

	public synchronized void outputPrecision() {
		writer.write(String.format(MESSAGE_PERCISION_FORMAT, precision - 1));
		writer.writeWithNewLine("");
	}

	public synchronized void output(Apfloat e, long time) {

		String totalTimeMessage = String.format(MESSAGE_TOTAL_TIME_FORMAT, tasks, time);

		writer.writeWithNewLine(totalTimeMessage);
		writer.writeWithNewLine(String.format(MESSAGE_VALUE_FORMAT, floatUtil.getPrecise(e, precision)));
		writer.writeWithNewLine("");
		writer.close();
	}

	public synchronized void outputThreadTime(long time) {
		writer.writeWithNewLine(String.format(MESSAGE_THREAD_TIME_FORMAT, time));
	}
}