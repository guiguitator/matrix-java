package com.gmeunier.matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
	
}
