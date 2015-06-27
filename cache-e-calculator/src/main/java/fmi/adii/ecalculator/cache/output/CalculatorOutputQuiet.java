package fmi.adii.ecalculator.cache.output;

import static fmi.adii.ecalculator.cache.util.Constants.MESSAGE_TOTAL_TIME_FORMAT;

import org.apfloat.Apfloat;

public class CalculatorOutputQuiet implements CalculatorOutput {

	public int tasks;

	public CalculatorOutputQuiet(int tasks) {
		this.tasks = tasks;
	}

	public void outputPrecision() {
	}

	public void output(Apfloat e, long time) {
		String totalTimeMessage = String.format(MESSAGE_TOTAL_TIME_FORMAT, tasks, time);
		System.out.println(totalTimeMessage);
	}

	public void outputThreadTime(long time) {
	}
}