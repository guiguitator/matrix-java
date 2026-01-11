package com.gmeunier.matrix;

/**
 * Represents a real-valued mathematical matrix.
 * <p>
 * This interface defines common operations and properties for matrices,
 * including arithmetic operations, norms, and structural checks.
 * </p>
 * 
 * <p>
 * Implementations may be mutable or immutable. Unless explicitly stated
 * otherwise, methods returning a {@code Matrix} are expected to return
 * a new matrix instance and must not modify the current matrix.
 * </p>
 * 
 * <p>
 * All indices are zero-based.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public interface Matrix {

	/**
	 * Returns the number of rows in this matrix.
	 * 
	 * @return the row dimension
	 */
	int getRowDimension();
	
	/**
	 * Returns the number of columns in this matrix.
	 * 
	 * @return the column dimension
	 */
	int getColumnDimension();
	
	/**
	 * Returns the value at the specified position.
	 * 
	 * @param row the row index
	 * @param column the column index
	 * @return the value at the specified position
	 */
	double get(int row, int column);
	
	/**
	 * Returns the sum of this matrix and the specified matrix.
	 * 
	 * @param otherMatrix the matrix to add
	 * @return a new matrix representing the sum
	 */
	Matrix add(Matrix otherMatrix);
	
	/**
	 * Returns the difference between this matrix and the specified matrix.
	 * 
	 * @param otherMatrix the matrix to subtract
	 * @return a new matrix representing the difference
	 */
	Matrix subtract(Matrix otherMatrix);
	
	/**
	 * Returns the matrix product of this matrix and the specified matrix.
	 * 
	 * @param otherMatrix the right-hand matrix
	 * @return a new matrix representing the product
	 */
	Matrix multiply(Matrix otherMatrix);
	
	/**
	 * Returns the scalar multiplication of this matrix.
	 * 
	 * @param scalar the scalar value
	 * @return a new matrix scaled by the given scalar
	 */
	Matrix multiply(double scalar);
	
	/**
	 * Indicates whether this matrix is equal to the specified matrix.
	 * 
	 * @param otherMatrix the matrix to compare with
	 * @return {@code true} if the matrices are equal, {@code false} otherwise
	 */
	boolean equals(Matrix otherMatrix);
	
	/**
	 * Indicates whether this matrix has the same dimensions as the specified matrix.
	 * 
	 * @param otherMatrix the matrix to compare with
	 * @return {@code true} if both matrices have the same dimensions, {@code false}
	 * otherwise
	 */
	boolean hasSameDimensions(Matrix otherMatrix);

	/**
	 * Returns the transpose of this matrix.
	 * 
	 * @return a new matrix representing the transpose
	 */
	Matrix getTranspose();
	
	/**
	 * Returns the trace of this matrix.
	 * <p>
	 * The trace is defined as the sum of the elements on the main diagonal.
	 * </p>
	 * 
	 * @return the trace of the matrix
	 */
	double getTrace();
	
	/**
	 * Returns the sum of all elements in this matrix.
	 * 
	 * @return the sum of all matrix elements
	 */
	double getSum();
	
	/**
	 * Returns the arithmetic mean of all elements in this matrix.
	 * 
	 * @return the average value of all matrix elements
	 */
	double getAverage();
	
	/**
	 * Returns the maximum element in this matrix.
	 * 
	 * @return the maximum matrix element
	 */
	double getMaximum();
	
	/**
	 * Returns the minimum element in this matrix.
	 * 
	 * @return the minimum matrix element
	 */
	double getMinimum();
	
	/**
	 * Returns the sum of all elements in the specified row.
	 * 
	 * @param row the index of the row
	 * @return the sum of the elements in the specified row
	 */
	double getRowSum(int row);
	
	// double getRowAverage(int row);
	// double getRowMaximum(int row);
	// double getRowMinimum(int row);
	
	/**
	 * Returns the sum of all elements in the specified column.
	 * 
	 * @param row the index of the row
	 * @return the sum of the elements in the specified column
	 */
	double getColumnSum(int column);
	
	// double getColumnAverage(int column);
	// double getColumnMaximum(int column);
	// double getColumnMinimum(int column);
	
	/**
	 * Returns the L1 norm of this matrix.
	 * <p>
	 * The L1 norm is defined as the maximum absolute column sum.
	 * </p>
	 * 
	 * @return the L1 norm of the matrix
	 */
	double getL1Norm();
	
	/**
	 * Returns the infinity norm of this matrix.
	 * <p>
	 * The infinity norm is defined as the maximum absolute row sum.
	 * </p>
	 * 
	 * @return the infinity norm of the matrix
	 */
	double getInfinityNorm();
	
	/**
	 * Indicates whether this matrix is square.
	 * 
	 * @return {@code true} if the matrix is square, {@code false} otherwise
	 */
	boolean isSquare();
	
	/**
	 * Indicates whether this matrix is a zero matrix.
	 * 
	 * @return {@code true} if all elements are zero, {@code false} otherwise
	 */
	boolean isZero();
	
	/**
	 * Indicates whether this matrix is an identity matrix.
	 * 
	 * @return {@code true} if the matrix is an identity matrix, {@code false}
	 * otherwise
	 */
	boolean isIdentity();
	
	/**
	 * Indicates whether this matrix is a diagonal matrix.
	 * 
	 * @return {@code true} if the matrix is diagonal, {@code false} otherwise
	 */
	boolean isDiagonal();
	
	/**
	 * Indicates whether this matrix is a scalar matrix.
	 * <p>
	 * A scalar matrix is a diagonal matrix whose diagonal elements are all equal.
	 * </p>
	 * 
	 * @return {@code true} if the matrix is scalar, {@code false} otherwise
	 */
	boolean isScalar();
	
	/**
	 * Indicates whether this matrix is symmetric.
	 * 
	 * @return {@code true} if the matrix is symmetric, {@code false} otherwise
	 */
	boolean isSymmetric();
	
	/**
	 * Indicates whether this matrix is a vector.
	 * <p>
	 * A matrix is considered a vector if it has exactly one row or one column.
	 * </p>
	 * 
	 * @return {@code true} if the matrix is a vector, {@code false} otherwise
	 */
	boolean isVector();
	
	/**
	 * Indicates whether this matrix is a row vector.
	 * 
	 * @return {@code true} if the matrix has exactly one row, {@code false}
	 * otherwise
	 */
	boolean isRowVector();
	
	/**
	 * Indicates whether this matrix is a column vector.
	 * 
	 * @return {@code true} if the matrix has exactly one column, {@code false}
	 * otherwise
	 */
	boolean isColumnVector();
	
	/**
	 * Indicates whether this matrix is triangular.
	 * 
	 * @return {@code true} if the matrix is either upper or lower triangular,
	 * {@code false} otherwise
	 */
	boolean isTriangular();
	
	/**
	 * Indicates whether this matrix is upper triangular.
	 * 
	 * @return {@code true} if the matrix is upper triangular, {@code false}
	 * otherwise
	 */
	boolean isUpperTriangular();
	
	/**
	 * Indicates whether this matrix is lower triangular.
	 * 
	 * @return {@code true} if the matrix is lower triangular, {@code false}
	 * otherwise
	 */
	boolean isLowerTriangular();
	
}
