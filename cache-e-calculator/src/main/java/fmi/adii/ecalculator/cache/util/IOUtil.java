package fmi.adii.ecalculator.cache.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class IOUtil {

	private static IOUtil INSTANCE = null;

	public static final Boolean APPEND = Boolean.TRUE;
	private String workingDir = System.getProperty("user.dir");

	private IOUtil() {
	}

	public static IOUtil getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new IOUtil();
		}
		return INSTANCE;
	}

	public void write(String content, String fileName) {
		try {

			File file = new File(getFullFilePath(fileName));

			if (!file.exists()) {
				file.createNewFile();
			}

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile(), APPEND));
			bufferedWriter.write(content);
			bufferedWriter.close();

		} catch (IOException e) {
			System.out.println(String.format(Constants.MESSAGE_COULD_NOT_WRITE_IN_FILE_FORMAT, fileName));
		}
	}

	private String getFullFilePath(String filePath) {
		return workingDir + "/" + filePath;
	}

	public void writeWithNewLine(String content, String filePath) {
		StringBuilder newLinedContent = new StringBuilder(System.lineSeparator()).append(content);
		write(newLinedContent.toString(), filePath);
	}

	public void checkAndDeleteIfFileExists(String fileName) {
		File file = new File(getFullFilePath(fileName));

		if (file.exists()) {
			file.delete();
		}
	}

}
