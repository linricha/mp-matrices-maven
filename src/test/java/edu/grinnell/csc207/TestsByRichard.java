package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

public class TestsByRichard {

  @Test
  public void checkHeightChanges() {
    Matrix<String> matrix1 = new MatrixV0<String>(3, 4);

    assertEquals(4, matrix1.height(), "Checks height for initial declaration");


    matrix1.deleteRow(2);
    assertEquals(3, matrix1.height(), "Checks that height updates with deleteRow.");

    matrix1.insertRow(2);
    assertEquals(4, matrix1.height(), "Checks that height updates with insertRow.");

    matrix1.deleteCol(2);
    assertEquals(4, matrix1.height(), "Checks that height is not updated with deleteCol.");

    matrix1.insertCol(2);
    matrix1.insertCol(2);
    assertEquals(4, matrix1.height(), "Checks that height is not updated with insertCol.");

    matrix1.fillRegion(0, 0, 3, 3, "Hi");
    assertEquals(4, matrix1.height(), "Checks that height is not updated with fillRegion.");

    matrix1.fillLine(0, 0, 0, 0, 3, 4, "Bye");
    assertEquals(4, matrix1.height(), "Checks that height is not updated with fillRegion.");
  } // checkHeightChanges()

  @Test
  public void checkWidthChanges() {
    Matrix<String> matrix1 = new MatrixV0<String>(3, 4);

    assertEquals(3, matrix1.width(), "Checks width for initial declaration");


    matrix1.deleteRow(2);
    assertEquals(3, matrix1.width(), "Checks that width does not update with deleteRow.");

    matrix1.insertRow(2);
    assertEquals(3, matrix1.width(), "Checks that width does not update with insertRow.");

    matrix1.deleteCol(2);
    assertEquals(2, matrix1.width(), "Checks that width is updated with deleteCol.");

    matrix1.insertCol(2);
    matrix1.insertCol(2);
    assertEquals(4, matrix1.width(), "Checks that width is updated with insertCol.");

    matrix1.fillRegion(0, 0, 3, 3, "Hi");
    assertEquals(4, matrix1.width(), "Checks that width is not updated with fillRegion.");

    matrix1.fillLine(0, 0, 0, 0, 3, 4, "Bye");
    assertEquals(4, matrix1.width(), "Checks that width is not updated with fillRegion.");
  } // checkWidthChanges()

  @Test
  public void checkEqualityWithMatrixAlterations() {
    Matrix<Integer> matrix1 = new MatrixV0<Integer>(4, 4, 1);
    Matrix<Integer> matrix2 = new MatrixV0<Integer>(4, 4, 2);
    Matrix<Integer> matrix4 = new MatrixV0<Integer>(4, 4, 1);
    Matrix<Integer> matrixNotEqual = new MatrixV0<>(4, 5);
    Matrix<Integer> matrixNotEqual2 = new MatrixV0<>(5, 4);
    Matrix<Integer> matrixNotEqual3 = new MatrixV0<>(5, 5);
    int[] notEqual4 = new int[4];

    assertEquals(true, matrix1.equals(matrix4), "Check that equals actually works");

    assertEquals(false, matrix1.equals(matrix2), 
        "Check that two matrices of similar size and different def are not equal, i.e. different initial contents.");

    assertEquals(false, matrix1.equals(matrixNotEqual), "Check that two matrices of different heights are not equal.");

    assertEquals(false, matrix1.equals(matrixNotEqual2), "Check that two matrices of different widths are not equal.");

    assertEquals(false, matrix1.equals(matrixNotEqual3), "Check that two matrices of different sizes are not equal.");

    assertEquals(false, matrix1.equals(notEqual4), "Check that two different objects are not equal.");


    matrix2.fillRegion(0, 0, 4, 4, 1);
    assertEquals(true, matrix1.equals(matrix2), "Checks that fillRegion works as described with end Row and Col exclusive");

    Matrix<Integer> matrix3 = null;
    matrix3 = matrix2.clone();
    assertEquals(true, matrix2.equals(matrix3), "Checks that clone is equal to its original");
    assertEquals(true, matrix1.equals(matrix3), "Checks that clone is actually equal to not just its original");
    assertEquals(true, matrix3.equals(matrix1), "Checks that equal works both ways (can call from either matrix)");
    assertEquals(true, matrix3.equals(matrix2), "Second check that equal works both ways");

  }

} // class TestsByRichard
