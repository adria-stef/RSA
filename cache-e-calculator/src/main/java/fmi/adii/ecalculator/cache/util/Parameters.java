package fmi.adii.ecalculator.cache.util;

import static fmi.adii.ecalculator.cache.util.Constants.*;

import com.beust.jcommander.Parameter;

public class Parameters {

	@Parameter(names = { LONG_NAME_MAX_MEMBER, SHORT_NAME_MAX_MEMBER }, required = false, description = DESCRIPTION_MAX_MEMBER)
	private Integer maxMember = DEFAULT_MAX_MEMBER;

	@Parameter(names = { LONG_NAME_PRECISION, SHORT_NAME_PRECISION }, required = false, description = DESCRIPTION_PRECISION)
	private Integer precision = DEFAULT_PRECISION;

	@Parameter(names = { LONG_NAME_TASKS, SHORT_NAME_TASKS }, required = false, description = DESCRIPTION_TASKS)
	private Integer tasks = DEFAULT_TASKS;

	@Parameter(names = { LONG_NAME_FILE_NAME, SHORT_NAME_FILE_NAME }, required = false, description = DESCRIPTION_FILE_NAME)
	private String fileName = DEFAULT_FILE_NAME;

	@Parameter(names = { LONG_NAME_QUIET, SHORT_NAME_QUIET }, required = false, description = DESCRIPTION_QUIET)
	private Boolean quiet = DEFAULT_QUIET;

	public Integer getMaxMember() {
		return maxMember;
	}

	public void setMaxMember(Integer maxMember) {
		this.maxMember = maxMember;
	}

	public Integer getPrecision() {
		return precision;
	}

	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	public Integer getTasks() {
		return tasks;
	}

	public void setTasks(Integer tasks) {
		this.tasks = tasks;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Boolean getQuiet() {
		return quiet;
	}

	public void setQuiet(Boolean quiet) {
		this.quiet = quiet;
	}

}