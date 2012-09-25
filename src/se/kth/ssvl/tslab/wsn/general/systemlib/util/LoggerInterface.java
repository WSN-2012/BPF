package se.kth.ssvl.tslab.wsn.general.systemlib.util;

public interface LoggerInterface {
	
	/**
	 * Method to log messages classed as info
	 * @param TAG The TAG to recognize the log message
	 * @param message The message to print
	 */
	public abstract void error(String TAG, String message);

	/**
	 * Method to log messages classed as debug
	 * @param TAG The TAG to recognize the log message
	 * @param message The message to print
	 */
	public abstract void debug(String TAG, String message);

	/**
	 * Method to log messages classed as info
	 * @param TAG The TAG to recognize the log message
	 * @param message The message to print
	 */
	public abstract void info(String TAG, String message);

	/**
	 * Method to log messages classed as warning
	 * @param TAG The TAG to recognize the log message
	 * @param message The message to print
	 */
	public abstract void warning(String TAG, String message);
}
