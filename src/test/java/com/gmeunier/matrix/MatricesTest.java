package com.gmeunier.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatricesTest {

	@Test
	void testZeros() {
		ImmutableMatrix m = Matrices.zeros(3, 3);
		
		assertEquals(0, m.get(0, 0));
		assertEquals(0, m.get(1, 1));
		assertEquals(0, m.get(2, 0));
	}
	
	@Test
	void testZerosIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.zeros(0, 0));
		assertThrows(IllegalArgumentException.class, () -> Matrices.zeros(-2, -1));
	}
	
	@Test
	void testOnes() {
		ImmutableMatrix m = Matrices.ones(3, 3);
		
		assertEquals(1, m.get(0, 0));
		assertEquals(1, m.get(1, 1));
		assertEquals(1, m.get(2, 0));
	}
	
	@Test
	void testOnesIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.ones(0, 0));
		assertThrows(IllegalArgumentException.class, () -> Matrices.ones(-2, -1));
	}
	
	@Test
	void testIdentity() {
		ImmutableMatrix m = Matrices.identity(3);
		
		assertEquals(1, m.get(0, 0));
		assertEquals(1, m.get(1, 1));
		assertEquals(1, m.get(2, 2));
		assertEquals(0, m.get(2, 0));
	}
	
	@Test
	void testIdentityIllegalArgument() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.identity(0));
		assertThrows(IllegalArgumentException.class, () -> Matrices.identity(-1));
	}
	
	@Test
	void testRandom() {
		double rangeMin = 0;
		double rangeMax = 100;
		
		ImmutableMatrix m = Matrices.random(2, 2, rangeMin, rangeMax);
		
		assertTrue(rangeMin <= m.get(0, 0) && m.get(0, 0) <= rangeMax);
		assertTrue(rangeMin <= m.get(1, 1) && m.get(1, 1) <= rangeMax);
	}
	
	@Test
	void testRandomIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.random(0, 0, 0, 10));
		assertThrows(IllegalArgumentException.class, () -> Matrices.random(2, 2, 10, 0));
	}
	
	@Test
	void testDiagonal() {
		ImmutableMatrix m = Matrices.diagonal(1, 2, 3);
		
		assertEquals(1, m.get(0, 0));
		assertEquals(2, m.get(1, 1));
		assertEquals(3, m.get(2, 2));
		assertEquals(0, m.get(0, 1));
	}
	
	@Test
	void testDiagonalIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.diagonal());
	}
	
	@Test
	void testRowVector() {
		ImmutableMatrix m = Matrices.rowVector(1, 2, 3);
		
		assertEquals(1, m.get(0, 0));
		assertEquals(2, m.get(0, 1));
		assertEquals(3, m.get(0, 2));
	}
	
	@Test
	void testRowVectorIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.rowVector());
	}
	
	@Test
	void testColumnVector() {
		ImmutableMatrix m = Matrices.columnVector(1, 2, 3);
		
		assertEquals(1, m.get(0, 0));
		assertEquals(2, m.get(1, 0));
		assertEquals(3, m.get(2, 0));
	}
	
	@Test
	void testColumnVectorIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> Matrices.columnVector());
	}
}
