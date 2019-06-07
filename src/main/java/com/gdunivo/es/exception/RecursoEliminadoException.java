package com.gdunivo.es.exception;

public class RecursoEliminadoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RecursoEliminadoException(String message) {
		super(message);
	}

	public RecursoEliminadoException(String message, Throwable cause) {
		super(message, cause);
	}

}
