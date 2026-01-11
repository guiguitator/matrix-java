package com.gmeunier.matrix;

import org.junit.jupiter.api.Test;

import com.gmeunier.matrix.exception.DimensionMismatchException;
import com.gmeunier.matrix.exception.NonSquareMatrixException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ImmutableMatrixTest {
	
	@Test
	void testImmutableMatrixOfIllegalArgument() {
		double[][] data1 = {};
		double[][] data2 = {{}};
		
		assertThrows(IllegalArgumentException.class, () -> ImmutableMatrix.of(data1));
		assertThrows(IllegalArgumentException.class, () -> ImmutableMatrix.of(data2));
	}
	
	@Test
	void testImmutableMatrixIsDefensiveCopied() {
		double[][] data = {
			{1, 2},
			{3, 4}
		};

		ImmutableMatrix m = ImmutableMatrix.of(data);

		data[0][0] = 99;

		assertEquals(1, m.get(0, 0));
	}
	
	@Test
	void testToString() {
		double[][] data = {
			{1, 2},
			{3, 4}
		};

		ImmutableMatrix m = ImmutableMatrix.of(data);
		
		assertEquals(
				"Matrix(2x2)\n"
				+ "[  1,000e+00  2,000e+00 ]\n"
				+ "[  3,000e+00  4,000e+00 ]",
			m.toString());
	}

	@Test
	void testGetRowDimension() {
		Matrix m = ImmutableMatrix.of(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
		});
		
		assertEquals(2, m.getRowDimension());
	}
	
	@Test
	void testGetColumnDimension() {
		Matrix m = ImmutableMatrix.of(new double[][]{
            {1, 2, 3},
            {4, 5, 6}
		});
		
		assertEquals(3, m.getColumnDimension());
	}
	
	@Test
	void testGet() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(1, m.get(0, 0));
		assertEquals(4, m.get(1, 1));
	}
	
	@Test
	void testGetIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{{1}});

		assertThrows(IllegalArgumentException.class, () -> m.get(-1, -1));
		assertThrows(IllegalArgumentException.class, () -> m.get(1, 0));
		assertThrows(IllegalArgumentException.class, () -> m.get(0, 1));
	}
	
	@Test
	void testAdd() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{5, 6},
			{7, 8}
		});
		
		Matrix result = a.add(b);
		
		assertEquals(6, result.get(0, 0));
		assertEquals(12, result.get(1, 1));
	}
	
	@Test
	void testAddDimensionMismatch() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertThrows(DimensionMismatchException.class, () -> a.add(b));
	}
	
	@Test
	void testSubtract() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{5, 6},
			{7, 8}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		Matrix result = a.subtract(b);
		
		assertEquals(4, result.get(0, 0));
		assertEquals(4, result.get(1, 1));
	}
	
	@Test
	void testSubtractDimensionMismatch() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertThrows(DimensionMismatchException.class, () -> a.subtract(b));
	}
	
	@Test
	void testMatrixMultiply() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{5, 6},
			{7, 8}
		});
		
		Matrix result = a.multiply(b);
		
		assertEquals(19, result.get(0, 0));
		assertEquals(22, result.get(0, 1));
		assertEquals(43, result.get(1, 0));
		assertEquals(50, result.get(1, 1));
	}
	
	@Test
	void testMatrixMultiplyDimensionMismatch() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertThrows(DimensionMismatchException.class, () -> a.multiply(b));
	}
	
	@Test
	void testScalarMultiply() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, -2},
			{3, -4}
		});
		
		Matrix result = m.multiply(2);
		
		assertEquals(2, result.get(0, 0));
		assertEquals(-4, result.get(0, 1));
		assertEquals(6, result.get(1, 0));
		assertEquals(-8, result.get(1, 1));
	}
	
	@Test
	void testEquals() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 3}
		});
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
	}

	@Test
	void testHasSameDimensions() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2, 3}
		});
		
		assertTrue(a.equals(b));
		assertFalse(a.equals(c));
	}
	
	@Test
	void testGetTranspose() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{4, 5, 6}
		});
		
		Matrix result = m.getTranspose();
		
		assertEquals(3, result.getRowDimension());
		assertEquals(2, result.getColumnDimension());
		assertEquals(4, result.get(0, 1));
		assertEquals(2, result.get(1, 0));
	}
	
	@Test
	void testGetTrace() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{0, 4}
		});
		
		assertEquals(5, m.getTrace());
	}
	
	@Test
	void testGetTraceNonSquareMatrix() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertThrows(NonSquareMatrixException.class, () -> m.getTrace());
	}
	
	@Test
	void testGetSum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(10, m.getSum());
	}
	
	@Test
	void testGetAverage() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(2.5, m.getAverage());
	}
	
	@Test
	void testGetMaximum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, -2},
			{3, -4}
		});
		
		assertEquals(3, m.getMaximum());
	}
	
	@Test
	void testGetMinimum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{-1, 2},
			{-3, 4}
		});
		
		assertEquals(-3, m.getMinimum());
	}
	
	@Test
	void testGetRowSum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(3, m.getRowSum(0));
		assertEquals(7, m.getRowSum(1));
	}
	
	@Test
	void testGetRowSumIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getRowSum(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getRowSum(2));
	}
	
	@Test
	void testGetRowAverage() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(1.5, m.getRowAverage(0));
		assertEquals(3.5, m.getRowAverage(1));
	}
	
	@Test
	void testGetRowAverageIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getRowAverage(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getRowAverage(2));
	}
	
	@Test
	void testGetRowMaximum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(2, m.getRowMaximum(0));
		assertEquals(4, m.getRowMaximum(1));
	}
	
	@Test
	void testGetRowMaximumIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getRowMaximum(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getRowMaximum(2));
	}
	
	@Test
	void testGetRowMinimum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(1, m.getRowMinimum(0));
		assertEquals(3, m.getRowMinimum(1));
	}
	
	@Test
	void testGetRowMinimumIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getRowMinimum(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getRowMinimum(2));
	}
	
	@Test
	void testGetColumnSum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(4, m.getColumnSum(0));
		assertEquals(6, m.getColumnSum(1));
	}
	
	@Test
	void testGetColumnSumIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getColumnSum(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getColumnSum(2));
	}
	
	@Test
	void testGetColumnAverage() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(2, m.getColumnAverage(0));
		assertEquals(3, m.getColumnAverage(1));
	}
	
	@Test
	void testGetColumnAverageIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getColumnAverage(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getColumnAverage(2));
	}
	
	@Test
	void testGetColumnMaximum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(3, m.getColumnMaximum(0));
		assertEquals(4, m.getColumnMaximum(1));
	}
	
	@Test
	void testGetColumnMaximumIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getColumnMaximum(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getColumnMaximum(2));
	}
	
	@Test
	void testGetColumnMinimum() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(1, m.getColumnMinimum(0));
		assertEquals(2, m.getColumnMinimum(1));
	}
	
	@Test
	void testGetColumnMinimumIllegalArgument() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertThrows(IllegalArgumentException.class, () -> m.getColumnMinimum(-1));
		assertThrows(IllegalArgumentException.class, () -> m.getColumnMinimum(2));
	}
	
	@Test
	void testGetL1Norm() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(6, m.getL1Norm());
	}
	
	@Test
	void testGetInfinityNorm() {
		Matrix m = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertEquals(7, m.getInfinityNorm());
	}
	
	@Test
	void testIsSquare() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		assertTrue(a.isSquare());
		assertFalse(b.isSquare());
	}
	
	@Test
	void testIsZero() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{0, 0},
			{0, 0}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{0, 1}
		});
		
		assertTrue(a.isZero());
		assertFalse(b.isZero());
	}
	
	@Test
	void testIsIdentity() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 0},
			{0, 1}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 0},
			{0, 4}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 0},
			{3, 1}
		});
		
		assertTrue(a.isIdentity());
		assertFalse(b.isIdentity());
		assertFalse(c.isIdentity());
	}
	
	@Test
	void testIsDiagonal() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 0},
			{0, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 0},
			{3, 4}
		});
		
		assertTrue(a.isDiagonal());
		assertFalse(b.isDiagonal());
	}
	
	@Test
	void testIsScalar() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{4, 0},
			{0, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 0},
			{0, 4}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{4, 0},
			{3, 4}
		});
		
		assertTrue(a.isScalar());
		assertFalse(b.isScalar());
		assertFalse(c.isScalar());
	}
	
	@Test
	void testIsSymmetric() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{2, 4}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertTrue(a.isSymmetric());
		assertFalse(b.isSymmetric());
	}
	
	@Test
	void testIsVector() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1},
			{2}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertTrue(a.isVector());
		assertTrue(b.isVector());
		assertFalse(c.isVector());
	}
	
	@Test
	void testIsRowVector() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1},
			{2}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertTrue(a.isRowVector());
		assertFalse(b.isRowVector());
		assertFalse(c.isRowVector());
	}
	
	@Test
	void testIsColumnVector() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1},
			{2}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2},
			{3, 4}
		});
		
		assertTrue(a.isColumnVector());
		assertFalse(b.isColumnVector());
		assertFalse(c.isColumnVector());
	}
	
	@Test
	void testIsTriangular() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 0, 0},
			{4, 5, 0},
			{7, 8, 9}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{0, 5, 6},
			{0, 0, 9}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{0, 5, 6},
			{7, 0, 9}
		});
		
		assertTrue(a.isTriangular());
		assertTrue(b.isTriangular());
		assertFalse(c.isTriangular());
	}
	
	@Test
	void testIsUpperTriangular() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{0, 5, 6},
			{0, 0, 9}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 0, 0},
			{4, 5, 0},
			{7, 8, 9}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{0, 5, 6},
			{7, 0, 9}
		});
		
		assertTrue(a.isUpperTriangular());
		assertFalse(b.isUpperTriangular());
		assertFalse(c.isUpperTriangular());
	}
	
	@Test
	void testIsLowerTriangular() {
		Matrix a = ImmutableMatrix.of(new double[][]{
			{1, 0, 0},
			{4, 5, 0},
			{7, 8, 9}
		});
		
		Matrix b = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{0, 5, 6},
			{0, 0, 9}
		});
		
		Matrix c = ImmutableMatrix.of(new double[][]{
			{1, 2, 3},
			{0, 5, 6},
			{7, 0, 9}
		});
		
		assertTrue(a.isLowerTriangular());
		assertFalse(b.isLowerTriangular());
		assertFalse(c.isLowerTriangular());
	}
}
