package com.zanox.advsdk.util;

// TODO: Auto-generated Javadoc
/**
 * The Class SDKException.
 */
@SuppressWarnings("serial")
public class SDKException extends Exception {

	/** The exception. */
	SDKException exception;
	
	/** The message. */
	String message = null;

	/**
	 * Instantiates a new sDK exception.
	 */
	public SDKException() {
	}

	/**
	 * Instantiates a new sDK exception.
	 *
	 * @param e the e
	 */
	public SDKException(SDKException e) {
		this.exception = e;
	}

	/**
	 * Instantiates a new sDK exception.
	 *
	 * @param message the message
	 */
	public SDKException(String message) {
		super(message);
		this.message = message;
	}

	/**
	 * Instantiates a new sDK exception.
	 *
	 * @param cause the cause
	 */
	public SDKException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new sDK exception.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public SDKException(String message, Throwable cause) {
		super(message, cause);
	}

}
