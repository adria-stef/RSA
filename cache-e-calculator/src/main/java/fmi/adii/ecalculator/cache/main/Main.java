package fmi.adii.ecalculator.cache.main;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import fmi.adii.ecalculator.cache.Calculator;
import fmi.adii.ecalculator.cache.output.CalculatorOutput;
import fmi.adii.ecalculator.cache.output.CalculatorOutputFactory;
import fmi.adii.ecalculator.cache.util.Parameters;

public class Main {

	private static int precision;
	private static int tasks;
	private static String fileName;
	private static boolean quiet;

	public static void main(String[] args) {
		initParameters(args);

		CalculatorOutput calculatorOutput = CalculatorOutputFactory.createCalculatorOutput(precision, tasks, fileName,
				quiet);
		calculatorOutput.outputPrecision();

		Calculator calculator = new Calculator(precision, tasks, calculatorOutput);
		calculator.execute();
	}

	private static void initParameters(String[] args) {
		Parameters parameters = new Parameters();
		JCommander jCommander = new JCommander(parameters);

		try {
			jCommander.parse(args);

			precision = parameters.getPrecision() + 1;
			tasks = parameters.getTasks();
			quiet = parameters.getQuiet();
			fileName = parameters.getFileName();

		} catch (ParameterException ex) {
			System.out.println(MESSAGE_COULD_NOT_GET_PARAMETERS);
			jCommander.usage();
		}
	}
}
