package fmi.adii.ecalculator.util;

public class Constants {

	public static final String  LONG_NAME_MAX_MEMBER     = "-max";
	public static final String  SHORT_NAME_MAX_MEMBER    = "-k";
	public static final String  DESCRIPTION_MAX_MEMBER   = "The maximum k.";
	public static final Integer DEFAULT_MAX_MEMBER_VALUE = 1000;

	public static final String  LONG_NAME_PRECISION      = "-precision";
	public static final String  SHORT_NAME_PRECISION     = "-p";
	public static final String  DESCRIPTION_PRECISION    = "The precision of the calculations.";
	public static final Integer DEFAULT_PRECISION_VALUE  = 100;

	public static final String  LONG_NAME_TASKS          = "-tasks";
	public static final String  SHORT_NAME_TASKS         = "-t";
	public static final String  DESCRIPTION_TASKS        = "The maximal number of threads.";
	public static final Integer DEFAULT_TASKS_VALUE      = 8;

	public static final String  LONG_NAME_FILE_NAME      = "-file";
	public static final String  SHORT_NAME_FILE_NAME     = "-o";
	public static final String  DESCRIPTION_FILE_NAME    = "The file that contains the result.";
	public static final String  DEFAULT_FILE_NAME_VALUE  = "result-%s.txt";

	public static final String  LONG_NAME_QUIET          = "-quiet";
	public static final String  SHORT_NAME_QUIET         = "-q";
	public static final String  DESCRIPTION_QUIET        = "Enables quiet behaviour.";
	public static final Boolean DEFAULT_QUIET_VALUE      = Boolean.FALSE;

	public static final String  MESSAGE_COULD_NOT_GET_PARAMETERS       = "Could not get parameters.";
	
	public static final String  MESSAGE_COULD_NOT_WRITE_IN_FILE_FORMAT = "Could not write in file [%s].";
	public static final String  MESSAGE_THREAD_TIME_FORMAT             = "Thread #%s took [%s] milliseconds.";
	public static final String MESSAGE_TOTAL_TIME_FORMAT               = "Total time with %s threads: [%s]";
	public static final String MESSAGE_E_RESULT_FORMAT                 = "Value of e is ~ %s";

}
