package com.gmeunier.matrix.exception;

/**
 * Exception thrown when an operation requires a square matrix, but the
 * provided matrix is not square.
 * <p>
 * An example of such an operation is the calculation of the trace.
 * </p>
 * 
 * <p>
 * This exception extends {@link MatrixException}.
 * </p>
 * 
 * @author Guillaume Meunier
 * @version 1.0.0
 * @see MatrixException
 */
public class NonSquareMatrixException extends MatrixException {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new non-square matrix exception with the specified detail
	 * message.
	 * 
	 * @param message the detail message
	 */
	public NonSquareMatrixException(String message) {
		super(message);
	}
}
