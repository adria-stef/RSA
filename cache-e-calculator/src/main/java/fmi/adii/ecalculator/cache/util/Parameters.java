package fmi.adii.ecalculator.cache.util;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.beust.jcommander.Parameter;

public class Parameters {

	@Parameter(names = { LONG_NAME_PRECISION, SHORT_NAME_PRECISION }, required = false, description = DESCRIPTION_PRECISION)
	private Integer precision = DEFAULT_PRECISION_VALUE;

	@Parameter(names = { LONG_NAME_TASKS, SHORT_NAME_TASKS }, required = false, description = DESCRIPTION_TASKS)
	private Integer tasks = DEFAULT_TASKS_VALUE;

	@Parameter(names = { LONG_NAME_FILE_NAME, SHORT_NAME_FILE_NAME }, required = false, description = DESCRIPTION_FILE_NAME)
	private String fileName = String.format(DEFAULT_FILE_NAME_VALUE, calculateDateTimeSuffix());

	@Parameter(names = { LONG_NAME_QUIET, SHORT_NAME_QUIET }, required = false, description = DESCRIPTION_QUIET)
	private Boolean quiet = DEFAULT_QUIET_VALUE;

	public Integer getPrecision() {
		return precision;
	}

	public Integer getTasks() {
		return tasks;
	}

	public String getFileName() {
		return fileName;
	}

	public Boolean getQuiet() {
		return quiet;
	}

	private String calculateDateTimeSuffix() {
		return new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss").format(new Date());
	}

}