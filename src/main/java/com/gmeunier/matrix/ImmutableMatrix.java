package com.gmeunier.matrix;

/**
 * Immutable implementation of a matrix.
 * <p>
 * This class represents a matrix whose contents cannot be modified after
 * creation. All operations that conceptually modify the matrix return
 * a new {@code ImmutableMatrix} instance instead.
 * </p>
 * 
 * <p>
 * Internally, this class relies on the implementations provided by
 * {@link AbstractMatrix}. The immutability guarantee is ensured by
 * copying input data and by not exposing any mutating methods.
 * </p>
 * 
 * <p>
 * Instances can be created using the {@link #of(double[][])} factory method.
 * </p>
 * 
 * @author Guillaume Meunier
 * @since 1.0.0
 */
public final class ImmutableMatrix extends AbstractMatrix {

	/**
	 * Constructs an immutable matrix backed by a defensive copy
	 * of the given data array.
	 * 
	 * @param data the two-dimensional array containing matrix values
	 */
	private ImmutableMatrix(double[][] data) {
		super(data, true);
	}
	
	/**
	 * Creates a new immutable matrix from the given data array.
	 * <p>
	 * The provided array is defensively copied, so subsequent modifications
	 * to the array will not affect the matrix.
	 * </p>
	 * 
	 * @param data the two-dimensional array containing matrix values
	 * @return a new {@code ImmutableMatrix}
	 */
	public static ImmutableMatrix of(double[][] data) {
		return new ImmutableMatrix(data);
	}
	
	/**
	 * Returns a mutable copy of this matrix.
	 * 
	 * @return a new {@link MutableMatrix} containing the same values
	 */
	public MutableMatrix toMutable() {
		return MutableMatrix.of(this.data);
	}
	
	/**
	 * {@inheritDoc}
	 * <p>
	 * This implementation returns a new {@code ImmutableMatrix}.
	 * </p>
	 */
	@Override
	protected Matrix create(double[][] data) {
		return new ImmutableMatrix(data);
	}
}
