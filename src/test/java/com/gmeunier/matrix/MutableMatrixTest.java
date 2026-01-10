package com.gmeunier.matrix;

import org.junit.jupiter.api.Test;

import com.gmeunier.matrix.exception.DimensionMismatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutableMatrixTest {

	@Test
	void testMutableMatrixOfIllegalArgument() {
		double[][] data1 = {};
		double[][] data2 = {{}};
		
		assertThrows(IllegalArgumentException.class, () -> MutableMatrix.of(data1));
		assertThrows(IllegalArgumentException.class, () -> MutableMatrix.of(data1));
	}
	
	@Test
	void testSet() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.set(1, 0, 10);
		
		assertEquals(10, m.get(1, 0));
	}
	
	@Test
	void testSetIllegalArgument() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.set(-1, -1, 10));
		assertThrows(IllegalArgumentException.class, () -> m.set(0, 3, 10));
	}
	
	@Test
	void testAddEquals() {
		MutableMatrix a = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		MutableMatrix b = MutableMatrix.of(new double[][]{
			{5, 6},
			{7, 8}
		});
		
		a.addEquals(b);
		
		assertEquals(6, a.get(0, 0));
		assertEquals(10, a.get(1, 0));
	}
	
	@Test
	void testAddEqualsDimensionMismatch() {
		MutableMatrix a = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		MutableMatrix b = MutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertThrows(DimensionMismatchException.class, () -> a.addEquals(b));
	}

	@Test
	void testSubtractEquals() {
		MutableMatrix a = MutableMatrix.of(new double[][]{
			{5, 6},
			{7, 8}
		});
		
		MutableMatrix b = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		a.subtractEquals(b);
		
		assertEquals(4, a.get(0, 0));
		assertEquals(4, a.get(1, 0));
	}
	
	@Test
	void testSubtractEqualsDimensionMismatch() {
		MutableMatrix a = MutableMatrix.of(new double[][]{
			{5, 6},
			{7, 8}
		});
		
		MutableMatrix b = MutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertThrows(DimensionMismatchException.class, () -> a.addEquals(b));
	}
	
	@Test
	void testMultiplyEqualsScalar() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.multiplyEquals(2);
		
		assertEquals(2, m.get(0, 0));
		assertEquals(6, m.get(1, 0));
	}
	
}
