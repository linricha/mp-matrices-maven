package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Richard Lin
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * Stores elements of Matrix.
   */
  T[] storage;

  /**
   * Height of Matrix.
   */
  int height;

  /**
   * Width of Matrix.
   */
  int width;

  /**
   * Default value for element in matrix.
   */
  T def;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width1
   *   The width of the matrix.
   * @param height1
   *   The height of the matrix.
   * @param def1
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  @SuppressWarnings (("unchecked"))
  public MatrixV0(int width1, int height1, T def1) {
    if (width1 < 1 || height1 < 1) {
      throw new NegativeArraySizeException();
    } // if
    storage = (T[]) (new Object[width1 * height1]);

    this.width = width1;
    this.height = height1;
    this.def = def1;

    for (int i = 0; i < this.storage.length; i++) {
      this.storage[i] = def1;
    } // for

  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width1
   *   The width of the matrix.
   * @param height1
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width1, int height1) {
    this(width1, height1, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public T get(int row, int col) throws IndexOutOfBoundsException {
    if ((row >= this.height) || (col >= this.width) || (row < 0) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if
    return this.storage[(this.width * row) + col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) throws IndexOutOfBoundsException {
    if ((row >= this.height) || (col >= this.width) || (row < 0) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if
    this.storage[(this.width * row) + col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.height;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.width;
  } // width()

  /**
   * Returns new array of type T and size newArrLength.
   *
   * @param newArrLength Length of new array.
   * @return An array of type T of size newArrLength.
   */
  @SuppressWarnings(("unchecked"))
  public T[] createEmptyArray(int newArrLength) {
    return (T[]) (new Object[newArrLength]);
  } // createEmptyArray(int)

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    T[] defArr = createEmptyArray(this.width);
    for (int i = 0; i < this.width; i++) {
      defArr[i] = def;
    } // for

    try {
      insertRow(row, defArr);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException();
      // only this exception goes through since defArr is correct Array Size.
    } // try/catch
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if ((row > height) || (row < 0)) {
      throw new IndexOutOfBoundsException();
    } // if

    if (vals.length != this.width) {
      throw new ArraySizeException();
    } // if

    T[] placeholder = createEmptyArray(this.storage.length + this.width);
    // Loops through a new array with the addition of a new row.
    for (int i = 0, j = 0, k = 0; i < placeholder.length; i++) {
      // Checks that i is in the specific row to that is inserted.
      if ((i >= ((row) * (this.width)) && (i <= ((row + 1) * (this.width) - 1)))) {
        placeholder[i] = vals[k];
        k++;
      } else {
        placeholder[i] = storage[j];
        j++;
      } // if/else
    } // for
    this.storage = placeholder;
    this.height++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    T[] defArr = createEmptyArray(this.height);
    for (int i = 0; i < this.height; i++) {
      defArr[i] = def;
    } // for

    try {
      insertCol(col, defArr);
    } catch (Exception e) {
      throw new IndexOutOfBoundsException();
      // only this exception goes through since defArr is correct Array Size.
    } // try/catch
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if ((col > width) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if

    if (vals.length != this.height) {
      throw new ArraySizeException();
    } // if

    T[] placeholder = createEmptyArray(this.storage.length + this.height);
    // Loops through a new array with the addition of column.
    for (int i = 0, j = 0, k = 0; i < placeholder.length; i++) {
      // Checks that i is in Column that is inserted
      if (i == (col + k * (this.width + 1))) {
        placeholder[i] = vals[k];
        k++;
      } else {
        placeholder[i] = storage[j];
        j++;
      } // if/else
    } // for
    this.storage = placeholder;
    this.width++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if ((row >= height) || (row < 0)) {
      throw new IndexOutOfBoundsException();
    } // if

    T[] placeholder = createEmptyArray(this.storage.length - this.width);
    for (int i = 0, j = 0; j < this.storage.length; j++) {
      // Checks that j is not in the excluded row.
      if (!((j >= (row * this.width)) && (j <= ((row + 1) * this.width - 1)))) {
        placeholder[i] = storage[j];
        i++;
      } // if
    } // for
    this.storage = placeholder;
    this.height--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if ((col >= width) || (col < 0)) {
      throw new IndexOutOfBoundsException();
    } // if

    T[] placeholder = createEmptyArray(this.storage.length - this.height);
    for (int i = 0, j = 0; j < this.storage.length; j++) {
      // Checks that j is not in the excluded column.
      if (!(j % (this.width) == col)) {
        placeholder[i] = storage[j];
        i++;
      } // if
    } // for
    this.storage = placeholder;
    this.width--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {

    if ((startRow >= this.height) || (startRow < 0)
        || (endCol < 0) || (endRow < 0) || (endCol > this.width) || (endRow > this.height)) {
      throw new IndexOutOfBoundsException();
    } // if

    int rowCurrent = startRow;

    // fills lines in the designated area
    for (int i = rowCurrent; i < endRow; i++) {
      fillLine(i, startCol, 0, 1, endRow, endCol, val);
    } // for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {

    if ((startRow >= this.height) || (startRow < 0)
        || (endCol < 0) || (endRow < 0) || (endCol > this.width) || (endRow > this.height)) {
      throw new IndexOutOfBoundsException();
    } // if

    int modifyStartIndex = (startCol) + (startRow * this.width);
    int modifyDelta = (deltaCol) + (deltaRow * this.width);
    // Tracks the next index for modification.
    int modifyCurrentIndex = modifyStartIndex;

    int previousCol = startCol;
    int currentCol;

    for (int i = 0; i < this.storage.length; i++) {
      // Checks that i is in the area to be modified in terms of rows.
      if (i / this.width < endRow) {
        // Checks that i is in the area to be modified in terms of columns.
        if (i % this.width < endCol) {
          // Checks that i is at an area where the matrix should be modified.
          if (i == modifyCurrentIndex) {
            this.storage[i] = val;
            modifyCurrentIndex += modifyDelta;
            currentCol = modifyCurrentIndex % this.width;
            // Checks that the modification does not wrap around.
            if (currentCol < previousCol) {
              return;
            } // if
            previousCol = currentCol;
          } // if
        } // if
      } else {
        return; // Since not in modify area if modify height surpassed.
      } // if/else
    } // for
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  public Matrix<T> clone() {
    // Copies and creates a new matrix with the same width, height, and default value.
    MatrixV0<T> cloneMatrix = new MatrixV0<T>(this.width, this.height, this.def);

    // Loops through matrix copying the contents stored in storage.
    for (int i = 0; i < cloneMatrix.storage.length; i++) {
      cloneMatrix.storage[i] = this.storage[i];
    } // for

    return cloneMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings(("unchecked"))
  public boolean equals(Object other) {
    // Checks that other is a Matrix
    if (other instanceof Matrix) {
      // checks that the width and height match
      if ((((Matrix<T>) other).width() == this.width)
          && (((Matrix<T>) other).height() == this.height)) {
        // Loops through tht columns of the matrix
        for (int i = 0; i < ((Matrix<T>) other).width(); i++) {
          // Loops through the rows of the Matrix
          for (int j = 0; j < ((Matrix<T>) other).height(); j++) {
            // Checks that the contents don't match
            if (!((Matrix<T>) other).get(j, i).equals(this.get(j, i))) {
              return false;
            } // if
          } // for
        } // for
        return true;
      } // if
    } // if
    return false;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
