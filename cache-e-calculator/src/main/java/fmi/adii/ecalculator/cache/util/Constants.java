package fmi.adii.ecalculator.cache.util;

public class Constants {

	public static final String  LONG_NAME_MAX_MEMBER   = "-max";
	public static final String  SHORT_NAME_MAX_MEMBER  = "-k";
	public static final String  DESCRIPTION_MAX_MEMBER = "The maximum k.";
	public static final Integer DEFAULT_MAX_MEMBER     = 1000;

	public static final String  LONG_NAME_PRECISION    = "-precision";
	public static final String  SHORT_NAME_PRECISION   = "-p";
	public static final String  DESCRIPTION_PRECISION  = "The precision of the calculations.";
	public static final Integer DEFAULT_PRECISION      = 100;

	public static final String  LONG_NAME_TASKS        = "-tasks";
	public static final String  SHORT_NAME_TASKS       = "-t";
	public static final String  DESCRIPTION_TASKS      = "The maximal number of threads.";
	public static final Integer DEFAULT_TASKS          = 8;

	public static final String  LONG_NAME_FILE_NAME    = "-file";
	public static final String  SHORT_NAME_FILE_NAME   = "-o";
	public static final String  DESCRIPTION_FILE_NAME  = "The file that contains the result.";
	public static final String  DEFAULT_FILE_NAME      = "defaultFileName.txt";

	public static final String  LONG_NAME_QUIET        = "-quiet";
	public static final String  SHORT_NAME_QUIET       = "-q";
	public static final String  DESCRIPTION_QUIET      = "Enables quiet behaviour.";
	public static final Boolean DEFAULT_QUIET          = Boolean.FALSE;

	public static final String  MESSAGE_COULD_NOT_GET_PARAMETERS = "Could not get parameters.";

}
