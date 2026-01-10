package com.gmeunier.matrix;

import java.util.Arrays;
import java.util.Random;

import com.gmeunier.matrix.util.MatrixChecks;

/**
 * Utility class providing factory methods for common matrices.
 * <p>
 * This class contains only static methods and cannot be instantiated.
 * It provides convenient ways to create frequently used immutable matrices.
 * </p>
 * 
 * <p>
 * All matrices returned by this class are instances of 
 * {@link ImmutableMatrix}.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public class Matrices {

	/**
	 * Private constructor to prevent instantiation.
	 */
	private Matrices() {}
	
	/**
	 * Creates a matrix filled with zeros.
	 * 
	 * @param rowDimension the number of rows
	 * @param columnDimension the number of columns
	 * @return a zero matrix with the specified dimensions
	 * @throws IllegalArgumentException if any dimension is not positive
	 */
	public static ImmutableMatrix zeros(int rowDimension, int columnDimension) {
		MatrixChecks.requirePositiveDimensions(rowDimension, columnDimension);
		
		return ImmutableMatrix.of(new double[rowDimension][columnDimension]);
	}
	
	/**
	 * Creates a matrix filled with ones.
	 * 
	 * @param rowDimension the number of rows
	 * @param columnDimension the number of columns
	 * @return a matrix filled with ones with the specified dimensions
	 * @throws IllegalArgumentException if any dimension is not positive
	 */
	public static ImmutableMatrix ones(int rowDimension, int columnDimension) {
		MatrixChecks.requirePositiveDimensions(rowDimension, columnDimension);
		
		double[][] data = new double[rowDimension][columnDimension];
		
		for (int i = 0; i < rowDimension; i++) {
			Arrays.fill(data[i], 1.0);
		}
		
		return ImmutableMatrix.of(data);
	}
	
	/**
	 * Creates an identity matrix of the specified size.
	 * <p>
	 * An identity matrix is a square matrix with ones on the main diagonal
	 * and zeros elsewhere.
	 * </p>
	 * 
	 * @param size the number of rows and columns
	 * @return an identity matrix of the given size
	 * @throws IllegalArgumentException if the size is not positive
	 */
	public static ImmutableMatrix identity(int size) {
		MatrixChecks.requirePositiveDimensions(size, size);
		
		double[][] data = new double[size][size];
		
		for (int i = 0; i < size; i++) {
			data[i][i] = 1.0;
		}
		
		return ImmutableMatrix.of(data);
	}
	
	/**
	 * Creates a matrix filled with random values within a given range.
	 * <p>
	 * Each element of the returned matrix is assigned a pseudo-random value
	 * uniformly distributed in the interval {@code [rangeMin, rangeMax)}.
	 * </p>
	 * 
	 * <p>
	 * The lower bound {@code rangeMin} must be less than {@code rangeMax}.
	 * </p>
	 * 
	 * @param rowDimension the number of rows
	 * @param columnDimension the number of columns
	 * @param rangeMin the inclusive lower bound of the random values
	 * @param rangeMax the exclusive upper bound of the random values
	 * @return a matrix filled with random values in the specified range
	 * @throws IllegalArgumentException if any dimension is not positive
	 * or if {@code rangeMin >= rangeMax}
	 */
	public static ImmutableMatrix random(
			int rowDimension, int columnDimension,
			double rangeMin, double rangeMax) {	
		MatrixChecks.requirePositiveDimensions(rowDimension, columnDimension);
		
		if (rangeMin >= rangeMax) {
			throw new IllegalArgumentException(
					"The rangeMin argument must be less than rangeMax. "
					+ "Provided: rangeMin=" + rangeMin + ", rangeMax=" + rangeMax
			);
		}
		
		double[][] data = new double[rowDimension][columnDimension];
		Random random = new Random();
		
		for (int i = 0; i < rowDimension; i++) {
			for (int j = 0; j < columnDimension; j++) {
				data[i][j] = rangeMin + (rangeMax - rangeMin) * random.nextDouble();
			}
		}
		
		return ImmutableMatrix.of(data);
	}
	
	/**
	 * Creates a diagonal matrix with the specified diagonal values.
	 * <p>
	 * The returned matrix is square, with the provided values placed on the
	 * main diagonal and zeros elsewhere.
	 * </p>
	 * 
	 * <p>
	 * The number of diagonal values determines the size of the matrix.
	 * At least one value must be provided.
	 * <p>
	 * 
	 * @param values the values to place on the main diagonal
	 * @return a diagonal matrix whose diagonal elements are the given values
	 * @throws IllegalArgumentException if no values are provided
	 */
	public static ImmutableMatrix diagonal(double... values) {
		if (values.length == 0) {
			throw new IllegalArgumentException(
					"At least one diagonal value must be provided"
			);
		}
		
		double[][] data = new double[values.length][values.length];
		
		for (int i = 0; i < values.length; i++) {
			data[i][i] = values[i];
		}
		
		return ImmutableMatrix.of(data);
	}
	
	/**
	 * Creates a row vector from the specified values.
	 * <p>
	 * The returned matrix has exactly one row and as many columns as
	 * there are provided values.
	 * </p>
	 * 
	 * @param values the values of the row vector
	 * @return a row vector containing the given values
	 * @throws IllegalArgumentException if no values are provided
	 */
	public static ImmutableMatrix rowVector(double... values) {
		if (values.length == 0) {
			throw new IllegalArgumentException(
					"At least one value must be provided to create a row vector"
			);
		}
		
		double[][] data = new double[1][values.length];
		
		for (int i = 0; i < values.length; i++) {
			data[0][i] = values[i];
		}
		
		return ImmutableMatrix.of(data);
	}
	
	/**
	 * Creates a column vector from the specified values.
	 * <p>
	 * The returned matrix has exactly one column and as many rows as
	 * there are provided values.
	 * </p>
	 * 
	 * @param values the values of the column vector
	 * @return a column vector containing the given values
	 * @throws IllegalArgumentException if no values are provided
	 */
	public static ImmutableMatrix columnVector(double... values) {
		if (values.length == 0) {
			throw new IllegalArgumentException(
					"At least one value must be provided to column a row vector"
			);
		}
		
		double[][] data = new double[values.length][1];
		
		for (int i = 0; i < values.length; i++) {
			data[i][0] = values[i];
		}
		
		return ImmutableMatrix.of(data);
	}	
}
