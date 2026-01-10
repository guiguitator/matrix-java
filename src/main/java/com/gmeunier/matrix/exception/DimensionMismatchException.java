package com.gmeunier.matrix.exception;

/**
 * Exception thrown when an operation requires two matrices to have the same
 * dimensions, but the dimensions do not match.
 * <p>
 * For example, this exception is thrown by addition or subtraction operations
 * when the two matrices have different numbers of rows or columns.
 * </p>
 * 
 * <p>
 * This exception extends {@link MatrixException}.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 * @see MatrixException
 */
public class DimensionMismatchException extends MatrixException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new dimension mismatch exception with the specified detail
	 * message.
	 * 
	 * @param message the detail message
	 */
	public DimensionMismatchException(String message) {
		super(message);
	}
}
