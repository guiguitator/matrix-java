package com.gmeunier.matrix.util;

import com.gmeunier.matrix.Matrix;
import com.gmeunier.matrix.exception.DimensionMismatchException;
import com.gmeunier.matrix.exception.NonSquareMatrixException;

/**
 * Utility class providing validation and precondition checks for matrices.
 * <p>
 * This class centralizes common argument checks related to matrix operations,
 * such as index bounds, dimension compatibility, and square-matrix requirements.
 * </p>
 *
 * <p>
 * All methods in this class are static and throw runtime exceptions when
 * preconditions are not met. This class cannot be instantiated.
 * </p>
 *
 * <p>
 * The purpose of this class is to keep matrix implementations concise
 * while ensuring consistent and meaningful error messages.
 * </p>
 *
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public final class MatrixChecks {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private MatrixChecks() {}
	
	/**
	 * Ensures that the given row and column indices are valid for the specified matrix.
	 * 
	 * @param row the row index
	 * @param column the column index
	 * @param matrix the matrix to check against
	 * @throws IllegalArgumentException if any index is negative or out of bounds
	 */
	public static void requireIndex(int row, int column, Matrix matrix) {
		if (row < 0 || column < 0) {
			throw new IllegalArgumentException(
					"Negative index: (" + row + ", " + column + ")"
			);
		}
		
		int rowDimension = matrix.getRowDimension();
		int columnDimension = matrix.getColumnDimension();
		
		if (row >= rowDimension || column >= columnDimension) {
			throw new IllegalArgumentException(
					"Index (" + row + ", " + column + ") out of bounds for matrix "
					+ rowDimension + "x" + columnDimension
			);
		}
	}
	
	/**
	 * Ensures that both matrix dimensions are strictly positive.
	 * 
	 * @param rowDimension the number of rows
	 * @param columnDimension the number of columns
	 * @throws IllegalArgumentException if any dimension is less than or equal to zero
	 */
	public static void requirePositiveDimensions(int rowDimension, int columnDimension) {
		if (rowDimension <= 0 || columnDimension <= 0) {
			throw new IllegalArgumentException(
					"Matrix dimensions must be strictly positive. "
					+ "Provided: " + rowDimension + "x" + columnDimension
			);
		}
	}
	
	/**
	 * Ensures that two matrices have the same dimensions.
	 * 
	 * @param a the first matrix
	 * @param b the second matrix
	 * @throws DimensionMismatchException if the matrices do not have the same dimensions
	 */
	public static void requireSameDimensions(Matrix a, Matrix b) {
		if (a.getRowDimension() != b.getRowDimension() || a.getColumnDimension() != b.getColumnDimension()) {
			throw new DimensionMismatchException(
					"Matrices must have the same dimensions for this operation: "
					+ a.getRowDimension() + "x" + a.getColumnDimension()
					+ " vs "
					+ b.getRowDimension() + "x" + b.getColumnDimension()
			);
		}
	}
	
	/**
	 * Ensures that the given matrix is square.
	 * 
	 * @param matrix the matrix to check
	 * @throws NonSquareMatrixException if the matrix is not square
	 */
	public static void requireSquareMatrix(Matrix matrix) {
		if (!matrix.isSquare()) {
			throw new NonSquareMatrixException(
					"Operation requires a square matrix, got "
					+ matrix.getRowDimension() + "x" + matrix.getColumnDimension()
			);
		}
	}
}
