package fmi.adii.ecalculator.cache.output;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CalculatorWriter {

	public static final Boolean APPEND = Boolean.TRUE;
	private static final String WORKING_DIR = System.getProperty("user.dir");

	private PrintWriter out;

	public CalculatorWriter(String fileName) {
		File file = new File(getFullFilePath(fileName));
		try {
			out = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			throw new IllegalStateException(String.format(MESSAGE_FILE_PATH_IS_ILLEGAL_FORMAT, file.getAbsolutePath()));
		}
	}

	public void write(String content) {
		out.print(content);
	}

	private String getFullFilePath(String filePath) {
		return WORKING_DIR + "/" + filePath;
	}

	public void writeWithNewLine(String content) {
		StringBuilder newLinedContent = new StringBuilder(System.lineSeparator()).append(content);
		write(newLinedContent.toString());
	}

	public void close() {
		out.close();
	}

}
