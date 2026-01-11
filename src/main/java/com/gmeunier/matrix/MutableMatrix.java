package com.gmeunier.matrix;

import java.util.Random;

import com.gmeunier.matrix.exception.DimensionMismatchException;
import com.gmeunier.matrix.util.MatrixChecks;

/**
 * Mutable implementation of a matrix.
 * <p>
 * This class represents a matrix whose contents can be modified after
 * creation. In addition to the standard operations defined by
 * {@link Matrix}, this class provides in-place modification methods.
 * </p>
 * 
 * <p>
 * Instances can be created using the {@link #of(double[][])} factory method.
 * The internal data is initially copied to preserve encapsulation.
 * </p>
 * 
 * <p>
 * Methods ending with {@code Equals} (e.g. {@code addEquals}) modify
 * the current matrix instance and return {@code this} for method chaining.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public class MutableMatrix extends AbstractMatrix {

	/**
	 * Constructs a mutable matrix backed by a defensive copy
	 * of the given data array.
	 * 
	 * @param data the two-dimensional array containing matrix values
	 */
	private MutableMatrix(double[][] data) {
		super(data, true);
	}
	
	/**
	 * Creates a new mutable matrix from the given data array.
	 * <p>
	 * The provided array is defensively copied, so subsequent modifications
	 * to the array will not affect the matrix.
	 * </p>
	 * 
	 * @param data the two-dimensional array containing matrix values
	 * @return a new {@code MutableMatrix}
	 */
	public static MutableMatrix of(double[][] data) {
		return new MutableMatrix(data);
	}
	
	/**
	 * Returns an immutable copy of this matrix.
	 * 
	 * @return a new {@link ImmutableMatrix} containing the same values
	 */
	public ImmutableMatrix toImmutable() {
		return ImmutableMatrix.of(this.data);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation returns a new {@code MutableMatrix}.
	 * </p>
	 */
	@Override
	protected Matrix create(double[][] data) {
		return new MutableMatrix(data);
	}
	
	/**
	 * Sets the value at the specified position.
	 * 
	 * @param row the row index
	 * @param column the column index
	 * @param value the new value to set
	 * @throws IllegalArgumentException if the indices are out of bounds
	 */
	public void set(int row, int column, double value) {
		MatrixChecks.requireIndex(row, column, this);
		this.data[row][column] = value;
	}
	
	/**
	 * Adds the specified matrix to this matrix in place.
	 * 
	 * @param otherMatrix the matrix to add
	 * @return this matrix after modification
	 * @throws DimensionMismatchException if the matrices do not have
	 * the same dimensions
	 */
	public MutableMatrix addEquals(Matrix otherMatrix) {
		MatrixChecks.requireSameDimensions(this, otherMatrix);
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				this.data[i][j] += otherMatrix.get(i, j);
			}
		}
		
		return this;
	}
	
	/**
	 * Subtracts the specified matrix from this matrix in place.
	 * 
	 * @param otherMatrix the matrix to subtract
	 * @return this matrix after modification
	 * @throws DimensionMismatchException if the matrices do not have
	 * the same dimensions
	 */
	public MutableMatrix subtractEquals(Matrix otherMatrix) {
		MatrixChecks.requireSameDimensions(this, otherMatrix);
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				this.data[i][j] -= otherMatrix.get(i, j);
			}
		}
		
		return this;
	}
	
	/**
	 * Multiplies this matrix by the specified scalar in place.
	 * 
	 * @param scalar the scalar value
	 * @return this matrix after modification
	 */
	public MutableMatrix multiplyEquals(double scalar) {
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				this.data[i][j] *= scalar;
			}
		}
		
		return this;
	}
	
	/**
	 * Fills the entire matrix with the specified scalar value.
	 * 
	 * @param value the value to fill the matrix with
	 *  @return this matrix after modification
	 */
	public MutableMatrix fill(double value) {
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				this.data[i][j] = value;
			}
		}
		
		return this;
	}
	
	/**
	 * Randomly shuffles all values in the matrix.
	 * <p>
	 * Each element of the matrix is randomly swapped with another element, 
	 * resulting in a random permutation of all values.
	 * </p>
	 * 
	 * @return this matrix after modification
	 */
	public MutableMatrix shuffle() {
		Random random = new Random();
		
		for (int i = 0; i < this.rowDimension; i++) {
			for (int j = 0; j < this.columnDimension; j++) {
				int r = random.nextInt(this.rowDimension);
				int c = random.nextInt(this.columnDimension);
				
				double temp = this.data[i][j];
				this.data[i][j] = this.data[r][c];
				this.data[r][c] = temp;
			}
		}
		
		return this;
	}
	
	/**
	 * Swaps two rows of this matrix.
	 * <p>
	 * The contents of {@code row1} and {@code row2} are exchanged.
	 * </p>
	 * 
	 * @param row1 the index of the first row
	 * @param row2 the index of the second row
	 * @return this matrix after modification
	 * @throws IllegalArgumentException if either row index is out of bounds
	 */
	public MutableMatrix swapRows(int row1, int row2) {
		if (row1 < 0 || row1 >= this.rowDimension ||
			row2 < 0 || row2 >= this.rowDimension) {
			throw new IllegalArgumentException(
					"Row indices out of bounds: row1=" + row1 +
					", row2=" + row2 + " for matrix with " + 
					this.rowDimension + " rows"
			);
		}
		
		if (row1 != row2) {
			double[] temp = this.data[row1];
			this.data[row1] = this.data[row2];
			this.data[row2] = temp;
		}
		
		return this;
	}
	
	/**
	 * Swaps two columns of this matrix in place.
	 * <p>
	 * The contents of {@code column1} and {@code column2} are exchanged.
	 * </p>
	 * 
	 * @param column1 the index of the first column
	 * @param column2 the index of the second column
	 * @return this matrix after modification
	 * @throws IllegalArgumentException if either column index is out of bounds
	 */
	public MutableMatrix swapColumns(int column1, int column2) {
		if (column1 < 0 || column1 >= this.columnDimension ||
			column2 < 0 || column2 >= this.columnDimension) {
			throw new IllegalArgumentException(
					"Column indices out of bounds: column1=" + column1 +
					", column2=" + column2 + " for matrix with " + 
					this.columnDimension + " columns"
			);
		}
		
		if (column1 != column2) {
			for (int i = 0; i < this.rowDimension; i++) {
				double temp = this.data[i][column1];
				this.data[i][column1] = this.data[i][column2];
				this.data[i][column2] = temp;
			}
		}
		
		return this;
	}
	
	/**
	 * Multiplies all elements of the specified row by a scalar factor.
	 *
	 * @param row the index of the row to scale
	 * @param factor the scalar multiplier
	 * @return this matrix after modification
	 * @throws IllegalArgumentException if the row index is out of bounds
	 */
	public MutableMatrix scaleRow(int row, double factor) {
		if (row < 0 || row >= this.rowDimension) {
			throw new IllegalArgumentException(
					"Row index out of bounds: " + row +
					" for matrix with " + this.rowDimension + " rows"
			);
		}
		
		for (int i = 0; i < this.columnDimension; i++) {
			this.data[row][i] *= factor;
		}
		
		return this;
	}
	
	/**
	 * Multiplies all elements of the specified column by a scalar factor.
	 * 
	 * @param column the index of the column to scale
	 * @param factor the scalar multiplier
	 * @return this matrix after modification
	 * @throws IllegalArgumentException if the column index is out of bounds
	 */
	public MutableMatrix scaleColumn(int column, double factor) {
		if (column < 0 || column >= this.columnDimension) {
			throw new IllegalArgumentException(
					"Column index out of bounds: " + column +
					" for matrix with " + this.columnDimension + " columns"
			);
		}
		
		for (int i = 0; i < this.rowDimension; i++) {
			this.data[i][column] *= factor;
		}
		
		return this;
	}
}
