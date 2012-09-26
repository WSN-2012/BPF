package se.kth.ssvl.tslab.wsn.general.systemlib.util;

public interface LoggerInterface {

	/**
	 * Method to log messages classed as info
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String error(String TAG, String message);

	/**
	 * Method to log messages classed as debug
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String debug(String TAG, String message);

	/**
	 * Method to log messages classed as info
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String info(String TAG, String message);

	/**
	 * Method to log messages classed as warning
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 * @return A string representing the TAG and the message. Note: Used by
	 *         asserts
	 */
	public abstract String warning(String TAG, String message);
}
