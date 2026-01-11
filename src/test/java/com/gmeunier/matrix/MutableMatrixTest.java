package com.gmeunier.matrix;

import org.junit.jupiter.api.Test;

import com.gmeunier.matrix.exception.DimensionMismatchException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MutableMatrixTest {

	@Test
	void testMutableMatrixOfIllegalArgument() {
		double[][] data1 = {};
		double[][] data2 = {{}};
		
		assertThrows(IllegalArgumentException.class, () -> MutableMatrix.of(data1));
		assertThrows(IllegalArgumentException.class, () -> MutableMatrix.of(data2));
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
	
	@Test
	void testFill() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.fill(5);
		
		assertEquals(5, m.get(0, 0));
		assertEquals(5, m.get(0, 1));
		assertEquals(5, m.get(1, 0));
		assertEquals(5, m.get(1, 1));
	}
	
	@Test
	void testShuffle() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2, 3}
		});
		
		m.shuffle();
		
		assertTrue(m.get(0, 0) == 1 || m.get(0, 0) == 2 || m.get(0, 0) == 3);
		assertTrue(m.get(0, 1) == 1 || m.get(0, 1) == 2 || m.get(0, 1) == 3);
		assertTrue(m.get(0, 2) == 1 || m.get(0, 2) == 2 || m.get(0, 2) == 3);
	}
	
	@Test
	void testSwapRows() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.swapRows(0, 1);
		
		assertEquals(3, m.get(0, 0));
		assertEquals(4, m.get(0, 1));
		assertEquals(1, m.get(1, 0));
		assertEquals(2, m.get(1, 1));
	}
	
	@Test
	void testSwapRowsIllegalArgument() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.swapRows(0, 5));
		assertThrows(IllegalArgumentException.class, () -> m.swapRows(-1, 1));
	}
	
	@Test
	void testSwapColumns() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.swapColumns(0, 1);
		
		assertEquals(2, m.get(0, 0));
		assertEquals(1, m.get(0, 1));
		assertEquals(4, m.get(1, 0));
		assertEquals(3, m.get(1, 1));
	}
	
	@Test
	void testSwapColumnsIllegalArgument() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.swapColumns(0, 5));
		assertThrows(IllegalArgumentException.class, () -> m.swapColumns(-1, 1));
	}
	
	@Test
	void testScaleRow() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.scaleRow(0, 2);
		
		assertEquals(2, m.get(0, 0));
		assertEquals(4, m.get(0, 1));
	}
	
	@Test
	void testScaleRowIllegalArgument() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.scaleRow(-1, 2));
		assertThrows(IllegalArgumentException.class, () -> m.scaleRow(5, 2));
	}
	
	@Test
	void testScaleColumn() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		m.scaleColumn(0, 2);
		
		assertEquals(2, m.get(0, 0));
		assertEquals(6, m.get(1, 0));
	}
	
	@Test
	void testScaleColumnIllegalArgument() {
		MutableMatrix m = MutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.scaleColumn(-1, 2));
		assertThrows(IllegalArgumentException.class, () -> m.scaleColumn(5, 2));
	}
}
