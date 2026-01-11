package com.gmeunier.matrix;

import com.gmeunier.matrix.util.MatrixChecks;
import com.gmeunier.matrix.exception.DimensionMismatchException;

/**
 * Abstract base class for matrix implementations.
 * <p>
 * This class provides a default implementation of most operations defined
 * in the {@link Matrix} interface, including arithmetic operations, norms,
 * structural checks, and common matrix properties.
 * </p>
 * 
 * <p>
 * Concrete subclasses are responsible for implementing the
 * {@link #create(double[][])} factory method, which allows this class to
 * create new matrix instances of the appropriate concrete type.
 * </p>
 * 
 * <p>
 * Matrices are backed by a two-dimensional {@code double} array.
 * </p>
 * 
 * <p>
 * Floating-point comparisons are performed using a tolerance defined
 * by {@link #EPSILON}.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public abstract class AbstractMatrix implements Matrix {
	
	/**
	 * Tolerance used for floating-point comparisons.
	 */
	protected static final double EPSILON = 1e-9;
	
	/**
	 * Number of rows in the matrix.
	 */
	protected final int rowDimension;
	
	/**
	 * Number of columns in the matrix.
	 */
	protected final int columnDimension;
	
	/**
	 * Internal two-dimensional array storing matrix values.
	 * <p>
	 * The array is indexed as {@code data[row][column]}.
	 * </p>
	 */
	protected final double[][] data;
	
	/**
	 * Constructs a matrix backed by the given data array.
	 * 
	 * @param data the two-dimensional array containing matrix values
	 * @param copy whether the provided array should be defensively copied
	 * @throws IllegalArgumentException if the provided array has zero rows
	 * or zeros columns
	 */
	protected AbstractMatrix(double[][] data, boolean copy) {
		if (data.length == 0 || data[0].length == 0) {
			throw new IllegalArgumentException("Matrix dimensions must be positive (at least 1×1)");
		}
		
		this.rowDimension = data.length;
		this.columnDimension = data[0].length;
		
		if (copy) {
			this.data = new double[this.rowDimension][this.columnDimension];
			for (int i = 0; i < this.rowDimension; i++) {
				System.arraycopy(data[i], 0, this.data[i], 0, this.columnDimension);
			}
		} else {
			this.data = data;
		}
	}
	
	/**
	 * Returns a human-readable string representation of this matrix.
	 * <p>
	 * The output includes the matrix dimensions followed by its values,
	 * formatted row by row.
	 * </p>
	 * 
	 * <p>Example:</p>
	 * <pre>
	 * Matrix(2x2)
	 * [  1,000e+00  2,000e+00 ]
	 * [  3,000e+00  4,000e+00 ]
	 * </pre>
	 * 
	 * @return a string representation of this matrix
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Matrix(")
			.append(this.rowDimension)
			.append("x")
			.append(this.columnDimension)
			.append(")\n");
		
		for (int i = 0; i < this.rowDimension; i++) {
			sb.append("[ ");
			for (int j = 0; j < this.columnDimension; j++) {
				
				sb.append(String.format("% .3e", data[i][j]));
				if (j < this.columnDimension - 1) {
					sb.append(" ");
				}
			}
			sb.append(" ]");
			
			if (i < this.rowDimension - 1) {
				sb.append("\n");
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Factory method used to create a new matrix instance of the same
	 * concrete type as this matrix.
	 * 
	 * @param data the data array backing the new matrix
	 * @return a new matrix instance
	 */
	protected abstract Matrix create(double[][] data);
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getRowDimension() {
		return this.rowDimension;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getColumnDimension() {
		return this.columnDimension;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException if the given indices are out of bounds
	 */
	@Override
	public double get(int row, int column) {
		MatrixChecks.requireIndex(row, column, this);
		return data[row][column];
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws DimensionMismatchException if the matrices do not have
	 * the same dimensions
	 */
	@Override
	public Matrix add(Matrix otherMatrix) {
		MatrixChecks.requireSameDimensions(this, otherMatrix);
		
		double[][] result = new double[this.rowDimension][this.columnDimension];
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				result[i][j] = this.get(i, j) + otherMatrix.get(i, j);
			}
		}
		
		return this.create(result);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws DimensionMismatchException if the matrices do not have
	 * the same dimensions
	 */
	@Override
	public Matrix subtract(Matrix otherMatrix) {
		MatrixChecks.requireSameDimensions(this, otherMatrix);
		
		double[][] result = new double[this.rowDimension][this.columnDimension];
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				result[i][j] = this.get(i, j) - otherMatrix.get(i, j);
			}
		}
		
		return this.create(result);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws DimensionMismatchException if the number of columns of this
	 * matrix does not match the number of rows of the other matrix
	 */
	@Override
	public Matrix multiply(Matrix otherMatrix) {
		if (this.getColumnDimension() != otherMatrix.getRowDimension()) {
			throw new DimensionMismatchException(
					"Cannot multiply " + this.getRowDimension() + "x" + this.getColumnDimension() +
					" by " + otherMatrix.getRowDimension() + "x" + otherMatrix.getColumnDimension()
			);
		}
		
		double[][] result = new double[this.getRowDimension()][otherMatrix.getColumnDimension()];
		
		for (int i = 0; i < this.getRowDimension(); i++) {
			for (int j = 0; j < otherMatrix.getColumnDimension(); j++) {
				
				double sum = 0;
				for (int k = 0; k < this.getColumnDimension(); k++) {
					sum += this.get(i, k) * otherMatrix.get(k, j);
				}
				
				result[i][j] = sum;
			}
		}
		
		return this.create(result);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix multiply(double scalar) {
		double[][] result = new double[this.rowDimension][this.columnDimension];
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				result[i][j] = this.get(i, j) * scalar;
			}
		}
		
		return this.create(result);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * Two matrices are considered equal if they have the same dimensions
	 * and all corresponding elements differ by no more than {@link #EPSILON}.
	 * </p>
	 */
	@Override
	public boolean equals(Matrix otherMatrix) {
		boolean result = true;
		
		if (this.hasSameDimensions(otherMatrix)) {
			int i = 0;
			while (i < this.rowDimension && result) {
				
				int j = 0;
				while (j < this.columnDimension && result) {

					if (Math.abs(this.get(i, j) - otherMatrix.get(i, j)) > EPSILON) {
						result = false;
					}
					
					j += 1;
				}
				i += 1;
			}
		} else {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean hasSameDimensions(Matrix otherMatrix) {
		return (
			this.rowDimension == otherMatrix.getRowDimension() &&
			this.columnDimension == otherMatrix.getColumnDimension()
		);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Matrix getTranspose() {
		double[][] result = new double[this.columnDimension][this.rowDimension];
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				result[j][i] = this.get(i, j);
			}
		}
		
		return this.create(result);
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException if the matrix is not square
	 */
	@Override
	public double getTrace() {
		MatrixChecks.requireSquareMatrix(this);
		
		double result = 0;
		for (int i = 0; i < this.rowDimension; i++) {
			result += this.get(i, i);
		}

		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getSum() {
		double result = 0.0;
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				result += this.data[i][j];
			}
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getAverage() {
		return this.getSum() / (this.rowDimension * this.columnDimension);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMaximum() {
		double result = this.data[0][0];
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				if (this.data[i][j] > result) {
					result = this.data[i][j];
				}
			}
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public double getMinimum() {
		double result = this.data[0][0];
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				if (this.data[i][j] < result) {
					result = this.data[i][j];
				}
			}
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException if the row index is out of bounds
	 */
	@Override
	public double getRowSum(int row) {
		MatrixChecks.requireRowIndex(row, this);
		
		double result = 0.0;
		
		for (int i = 0; i < this.columnDimension; i++) {
			result += this.data[row][i];
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException if the row index is out of bounds
	 */
	@Override
	public double getRowAverage(int row) {
		MatrixChecks.requireRowIndex(row, this);
		return this.getRowSum(row) / this.rowDimension;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException if the column index is out of bounds
	 */
	@Override
	public double getColumnSum(int column) {
		MatrixChecks.requireColumnIndex(column, this);
		
		double result = 0.0;
		
		for (int i = 0; i < this.rowDimension; i++) {
			result += this.data[i][column];
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * @throws IllegalArgumentException if the column index is out of bounds
	 */
	@Override
	public double getColumnAverage(int column) {
		MatrixChecks.requireColumnIndex(column, this);
		return this.getColumnSum(column) / this.columnDimension;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The L1 norm is defined as the maximum absolute column sum.
	 * </p>
	 */
	@Override
	public double getL1Norm() {
		double result = 0.0;
		
		for (int i = 0; i < this.rowDimension; i++) {
			
			double columnSum = 0.0;
			for (int j = 0; j < this.columnDimension; j++) {
				columnSum += Math.abs(this.data[j][i]);
			}
			
			if (columnSum > result) {
				result = columnSum;
			}
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 * <p>
	 * The infinity norm is defined as the maximum absolute row sum.
	 * </p>
	 */
	@Override
	public double getInfinityNorm() {
		double result = 0.0;
		
		for (int i = 0; i < this.rowDimension; i++) {
			
			double rowSum = 0.0;
			for (int j = 0; j < this.columnDimension; j++) {
				rowSum += Math.abs(this.data[i][j]);
			}
			
			if (rowSum > result) {
				result = rowSum;
			}
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSquare() {
		return (this.rowDimension == this.columnDimension);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isZero() {
		boolean result = true;
		
		int i = 0;
		while (i < this.rowDimension && result) {
			
			int j = 0;
			while (j < this.columnDimension && result) {

				if (Math.abs(this.data[i][j]) > EPSILON) {
					result = false;
				}
				j += 1;
			}
			i += 1;
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isIdentity() {
		boolean result = true;
		
		if (!this.isSquare()) {
			result = false;
		}
		
		int i = 0;
		while (i < this.rowDimension && result) {
			
			int j = 0;
			while (j < this.columnDimension && result) {

				if (i == j) {
					if (Math.abs(this.data[i][j] - 1.0) > EPSILON) {
						result = false;
					}
					
				} else if (Math.abs(this.data[i][j]) > EPSILON) {
					result = false;
				}
				j += 1;
			}
			i += 1;
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDiagonal() {
		boolean result = true;
		
		if (!this.isSquare()) {
			result = false;
		}
		
		int i = 0;
		while (i < this.rowDimension && result) {
			
			int j = 0;
			while (j < this.columnDimension && result) {
				
				if (i != j && Math.abs(this.data[i][j]) > EPSILON) {
					result = false;
				}
				j += 1;
			}
			i += 1;
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isScalar() {
		boolean result = true;
		
		if (!this.isSquare()) {
			result = false;
		}
		
		final double diagonalConstant = this.data[0][0];
		
		int i = 0;
		while (i < this.rowDimension && result) {
			
			int j = 0;
			while (j < this.columnDimension && result) {
				
				if (i == j) {
					if (Math.abs(this.data[i][j] - diagonalConstant) > EPSILON) {
						result = false;
					}
				} else if (Math.abs(this.data[i][j]) > EPSILON) {
					result = false;
				}
				
				j += 1;
			}
			i += 1;
		}
		
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSymmetric() {
		if (!this.isSquare()) {
			return false;
		}
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = i + 1; j < this.columnDimension; j++) {
				if (Math.abs(data[i][j] - data[j][i]) > EPSILON) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isVector() {
		return (this.isRowVector() || this.isColumnVector());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isRowVector() {
		return (this.rowDimension == 1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isColumnVector() {
		return (this.columnDimension == 1);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isTriangular() {
		return (this.isUpperTriangular() || this.isLowerTriangular());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUpperTriangular() {
		boolean result = true;
		
		if (!this.isSquare()) {
			result = false;
		}
		
		int i = 0;
		while (i < this.rowDimension && result) {
			
			int j = 0;
			while (j < this.columnDimension && result) {
				
				if (i > j && Math.abs(this.data[i][j]) > EPSILON) {
					result = false;
				}
				
				j += 1;
			}
			i += 1;
		}
		
		return result;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isLowerTriangular() {
		boolean result = true;
		
		if (!this.isSquare()) {
			result = false;
		}
		
		int i = 0;
		while (i < this.rowDimension && result) {
			
			int j = 0;
			while (j < this.columnDimension && result) {
				
				if (i < j && Math.abs(this.data[i][j]) > EPSILON) {
					result = false;
				}
				
				j += 1;
			}
			i += 1;
		}
		
		return result;
	}
}
