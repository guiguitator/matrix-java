# Changelog

All notable changes to this project will be documented in this file.

## Version 1.1.0

*2026-01-11* [Github compare](https://github.com/guiguitator/matrix-java/compare/v1.0.0...v1.1.0)

- Added the `getRowSum(int row)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the sum of all elements in the specified row.
- Added the `getColumnSum(int column)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the sum of all elements in the specified column.
- Added the `getRowAverage(int row)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the arithmetic mean of all elements in the specified row.
- Added the `getColumnAverage(int column)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the arithmetic mean of all elements in the specified column.
- Added the `getRowMaximum(int row)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the maximum value of all elements in the specified row.
- Added the `getColumnMaximum(int column)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the maximum value of all elements in the specified column.
- Added the `getRowMinimum(int row)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the minimum value of all elements in the specified row.
- Added the `getColumnMinimum(int column)` method to the `Matrix` interface and implemented it in the `AbstractMatrix` class. This method returns the minimum value of all elements in the specified column.
- Added `toString()` method to `AbstractMatrix` class. This method returns a human-readable string representation of the matrix.
- Added `fill(double value)` method to `MutableMatrix` class. This method fills the entire matrix with the specified scalar value.
- Added `shuffle()` method to `MutableMatrix` class. This method randomly shuffles all values in the matrix.
- Added `swapRows(int row1, int row2)` method to `MutableMatrix` class. This method swaps two rows of the matrix.
- Added `swapColumns(int column1, int column2)` method to `MutableMatrix` class. This method swaps two columns of the matrix.
- Added `scaleRow(int row, double scalar)` method to `MutableMatrix` class. This method multiplies all elements of the specified row by a scalar factor.
- Added `scaleColumn(int column, double scalar)` method to `MutableMatrix` class. This method multiplies all elements of the specified column by a scalar factor.
- Added `random(int rowDimension, int columnDimension, double rangeMin, double rangeMax)` static method to `Matrices` class. This method creates a matrix filled with random values within a given range.
- Added `diagonal(double... values)` static method to `Matrices` class. This method creates a diagonal matrix with the specified diagonal values.
- Added `rowVector(double... values)` static method to `Matrices` class. This method creates a row vector from the specified values.
- Added `columnVector(double... values)` static method to `Matrices` class. This method creates a column vector from the specified values.
- Added `requireRowIndex(int row, Matrix matrix)` static method to `MatrixChecks` class. This method checks if the given row index is within the valid range of the matrix.
- Added `requireColumnIndex(int column, Matrix matrix)` static method to `MatrixChecks` class. This method checks if the given column index is within the valid range of the matrix.

## Version 1.0.0

*2026-01-10*

Initial realease