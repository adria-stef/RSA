package fmi.adii.ecalculator.cache.output;

public class CalculatorOutputFactory {

	public static CalculatorOutput createCalculatorOutput(int precision, int tasks, String fileName, boolean quiet) {
		if (quiet) {
			return new CalculatorOutputQuiet(tasks);
		} else {
			return new CalculatorOutputFile(precision, tasks, fileName);
		}

	}

}
