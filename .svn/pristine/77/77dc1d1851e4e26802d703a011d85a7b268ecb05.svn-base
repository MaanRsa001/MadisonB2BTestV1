package com.maan.common;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.NDC;

public class LogManager {
	private static String strLoggerName = "";

	private static Logger logger = null;

	private static void getAppLogger() {
		if (logger == null && strLoggerName != null
				&& !strLoggerName.equals("")) {
			logger = Logger.getLogger(strLoggerName);
			logger.setLevel(Level.DEBUG);
		}
	}

	public static void setLoggerName(String strLoggerName) {
		if (strLoggerName != null && !strLoggerName.equals("")) {
			if (!strLoggerName.equals(LogManager.strLoggerName)) {
				LogManager.logger = null;
				LogManager.strLoggerName = strLoggerName;
				getAppLogger();
			}
		}
	}

	/**
	 * Logs a message object with the DEBUG level.
	 * 
	 * @param strMessage
	 *            The message to log
	 */
	public static void debug(String strMessage) {
		getAppLogger();
		//logger.debug(strMessage);
		System.out.println(strMessage);
	}
	/**
	 * Logs the stack trace of the Exception with the DEBUG level.
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void debug(Exception exception) {
		getAppLogger();
		//logger.debug("", exception);
		exception.printStackTrace();
		//System.out.println(exception.toString());
	}
	/**
	 * Logs the stack trace of the Exception with the DEBUG level.
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void debug(Throwable throwable) {
		getAppLogger();
		logger.debug("", throwable);
		System.out.println("" + throwable);
	}

	/**
	 * Logs a message object with the INFO level.
	 * 
	 * @param strMessage
	 *            The message to log
	 */
	public static void info(String strMessage) {
		// getAppLogger();
		// logger.info(strMessage);
		System.out.println(strMessage);
	}
	
	public static void info(Object objMessage) {
		getAppLogger();
		logger.info(objMessage);
		System.out.println(objMessage);
	}

	/**
	 * Logs the stack trace of the Exception with the INFO level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void info(Exception exception) {
		getAppLogger();
		logger.info("", exception);
		System.out.println("" + exception);
	}

	/**
	 * Logs the stack trace of the Exception with the INFO level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void info(Throwable throwable) {
		getAppLogger();
		logger.info("", throwable);
		System.out.println("" + throwable);
	}

	/**
	 * Logs a message object with the WARN level.
	 * 
	 * @param strMessage
	 *            The message to log
	 */
	public static void warn(String strMessage) {
		// getAppLogger();
		// logger.warn(strMessage);
		System.out.println("PUSH: " + strMessage);
	}

	/**
	 * Logs the stack trace of the Exception with the WARN level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void warn(Exception exception) {
		// getAppLogger();
		// logger.warn("", exception);
		System.out.println("" + exception);
	}

	/**
	 * Logs the stack trace of the Exception with the WARN level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void warn(Throwable throwable) {
		// getAppLogger();
		// logger.warn("", throwable);
		System.out.println("" + throwable);
	}

	/**
	 * Logs a message object with the ERROR level.
	 * 
	 * @param strMessage
	 *            The message to log
	 */
	public static void error(String strMessage) {
		// getAppLogger();
		// logger.error(strMessage);
		System.out.println("PUSH: " + strMessage);
	}

	/**
	 * Logs the stack trace of the Exception with the ERROR level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void error(Exception exception) {
		// getAppLogger();
		// logger.error("", exception);
		System.out.println("" + exception);
	}

	/**
	 * Logs the stack trace of the Exception with the ERROR level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void error(Throwable throwable) {
		// getAppLogger();
		// logger.error("", throwable);
		System.out.println("" + throwable);
	}

	/**
	 * Logs a message object with the FATAL level.
	 * 
	 * @param strMessage
	 *            The message to log
	 */
	public static void fatal(String strMessage) {
		// getAppLogger();
		// logger.fatal(strMessage);
		System.out.println(strMessage);
	}

	/**
	 * Logs the stack trace of the Exception with the FATAL level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void fatal(Exception exception) {
		// getAppLogger();
		// logger.fatal("", exception);
		System.out.println("" + exception);
	}

	/**
	 * Logs the stack trace of the Exception with the FATAL level. .
	 * 
	 * @param exception
	 *            The exception to log, including its stack trace.
	 * 
	 */
	public static void fatal(Throwable throwable) {
		// getAppLogger();
		// logger.fatal("", throwable);
		System.out.println("" + throwable);
	}

	/**
	 * Push new diagnostic context information for the current thread
	 * 
	 * @param strMessage
	 *            The new diagnostic context information
	 * 
	 */
	public static void push(String strMessage) {
		getAppLogger();
		NDC.push(strMessage);
		System.out.println("PUSH: " + strMessage);
	}

	/**
	 * Pop new diagnostic context information for the current thread
	 * 
	 */
	public static void pop() {
		getAppLogger();
		 NDC.pop();
	}

	/**
	 * clears diagnostic context information memory for the current thread
	 * 
	 * @param strMessage
	 *            The new diagnostic context information
	 * 
	 */
	public static void remove() {
		 getAppLogger();
		 NDC.remove();
	}

	/**
	 * Pop new diagnostic context information for the current thread and clears
	 * the memory
	 * 
	 */
	public static void popRemove() {
		pop();
		remove();
	}
}
