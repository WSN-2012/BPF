package se.kth.ssvl.tslab.wsn.general.systemlib.util;

import se.kth.ssvl.tslab.wsn.general.bps.BPSLogger;

public class Logger {

	/* Singleton instance */
	private static Logger instance;

	/*
	 * Logger callback interface, in order to handle the logging outside our
	 * library
	 */
	private static BPSLogger loggerInterface;

	/* TAG for the logger */
	private final static String TAG = "Logger";

	public static Logger getInstance() {
		if (loggerInterface == null) {
			// Don't return a logger if the loggerinterface hasn't been set
			return null;
		} else if (instance == null) {
			instance = new Logger();
		}

		return instance;
	}

	/* Constructor */
	private Logger() {
		info(TAG, "Logger initialized");
	}

	/* LoggerInterface getter/setter */
	public static BPSLogger getLoggerInterface() {
		return loggerInterface;
	}

	public static void setLoggerInterface(BPSLogger loggerInterface) {
		Logger.loggerInterface = loggerInterface;
	}

	/* Logging methods */

	/**
	 * Method to log messages classed as info
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 */
	public String error(String TAG, String message) {
		return loggerInterface.error(TAG, message);
	}

	/**
	 * Method to log messages classed as debug
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 */
	public String debug(String TAG, String message) {
		return loggerInterface.debug(TAG, message);
	}

	/**
	 * Method to log messages classed as info
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 */
	public String info(String TAG, String message) {
		return loggerInterface.info(TAG, message);
	}

	/**
	 * Method to log messages classed as warning
	 * 
	 * @param TAG
	 *            The TAG to recognize the log message
	 * @param message
	 *            The message to print
	 */
	public String warning(String TAG, String message) {
		return loggerInterface.warning(TAG, message);
	}

}
