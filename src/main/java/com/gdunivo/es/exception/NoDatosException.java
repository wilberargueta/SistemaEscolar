package com.gdunivo.es.exception;

public class NoDatosException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoDatosException(String message) {
		super(message);
	}
	public NoDatosException(String message, Throwable cause) {
		super(message, cause);
	}
}
