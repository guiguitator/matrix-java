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
	 * @return
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
}
