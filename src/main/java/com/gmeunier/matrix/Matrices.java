package com.gmeunier.matrix;

import java.util.Arrays;
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
	 * @return identity matrix of the given size
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
}
