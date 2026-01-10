package com.gmeunier.matrix.exception;

/**
 * Base exception class for all matrix-related errors.
 * <p>
 * This exception is unchecked and serves as the superclass for more specific
 * matrix exceptions.
 * </p>
 * 
 * <p>
 * It is typically thrown when an operation on a matrix fails due to
 * invalid arguments or inconsistent state.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public class MatrixException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new matrix exception with the specified detail message.
	 * 
	 * @param message the detail message
	 */
	public MatrixException(String message) {
		super(message);
	}
}
