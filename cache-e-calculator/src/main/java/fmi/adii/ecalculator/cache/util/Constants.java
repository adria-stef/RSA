package fmi.adii.ecalculator.cache.util;

public interface Constants {

	 String  LONG_NAME_PRECISION      = "-precision";
	 String  SHORT_NAME_PRECISION     = "-p";
	 String  DESCRIPTION_PRECISION    = "The precision of the calculations.";
	 Integer DEFAULT_PRECISION_VALUE  = 100;

	 String  LONG_NAME_TASKS          = "-tasks";
	 String  SHORT_NAME_TASKS         = "-t";
	 String  DESCRIPTION_TASKS        = "The maximal number of threads.";
	 Integer DEFAULT_TASKS_VALUE      = 8;

	 String  LONG_NAME_FILE_NAME      = "-file";
	 String  SHORT_NAME_FILE_NAME     = "-o";
	 String  DESCRIPTION_FILE_NAME    = "The file that contains the result.";
	 String  DEFAULT_FILE_NAME_VALUE  = "result-%s.txt";

	 String  LONG_NAME_QUIET          = "-quiet";
	 String  SHORT_NAME_QUIET         = "-q";
	 String  DESCRIPTION_QUIET        = "Enables quiet behaviour.";
	 Boolean DEFAULT_QUIET_VALUE      = Boolean.FALSE;

	 String  MESSAGE_COULD_NOT_GET_PARAMETERS       = "Could not get parameters.";
	
	 String  MESSAGE_PERCISION_FORMAT               = "Precision: %s";
	 String  MESSAGE_TASKS_FORMAT                   = "Tasks: %s";
	
	 String  MESSAGE_COULD_NOT_WRITE_IN_FILE_FORMAT = "Could not write in file [%s].";
	 String  MESSAGE_THREAD_TIME_FORMAT             = "Thread took [%s] milliseconds.";
	 String  MESSAGE_TOTAL_TIME_FORMAT              = "Total time with %s threads: [%s]";
	 String  MESSAGE_VALUE_FORMAT                   = "Value of e is ~ %s";
	 String  MESSAGE_E_COULD_NOT_BE_CALCULATED      = "e could not be calculated.";
	 String  MESSAGE_FILE_PATH_IS_ILLEGAL_FORMAT    = "File path is illegal: %s";
	
}
