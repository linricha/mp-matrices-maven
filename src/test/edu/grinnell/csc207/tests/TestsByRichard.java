package edu.grinnell.csc207;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import edu.grinnell.csc207.util.Matrix;
import edu.grinnell.csc207.util.MatrixV0;

public class TestsByRichard {

  @Test
  public void checkHeightChanges() {
    MatrixV0<String> matrix1 = new MatrixV0<String>(3, 4);

    assertEqual(4, matrix1.height(), "Checks height for initial declaration");


    matrix1.deleteRow(2);
    assertEqual(3, matrix.height(), "Checks that height updates with deleteRow.");

    matrix1.insertRow(2);
    assertEqual(4, matrix1.height(), "Checks that height updates with insertRow.");

    matrix1.deleteCol(2);
    assertEqual(4, matrix1.height(), "Checks that height is not updated with deleteCol.");

    matrix1.insertCol(2);
    matrix1.insertCol(2);
    assertEqual(4, matrix1.height(), "Checks that height is not updated with insertCol.");

    matrix1.fillRegion(0, 0, 3, 3, "Hi");
    assertEqual(4, matrix1.height(), "Checks that height is not updated with fillRegion.");

    matrix1.fillLine(0, 0, 0, 0, 3, 4, "Bye");
    assertEqual(4, matrix1.height(), "Checks that height is not updated with fillRegion.");
  } // checkHeightChanges()

  @Test
  public void checkWidthChanges() {
    MatrixV0<String> matrix1 = new MatrixV0<String>(3, 4);

    assertEqual(3, matrix1.width(), "Checks width for initial declaration");


    matrix1.deleteRow(2);
    assertEqual(3, matrix.width(), "Checks that width does not update with deleteRow.");

    matrix1.insertRow(2);
    assertEqual(3, matrix1.width(), "Checks that width does not update with insertRow.");

    matrix1.deleteCol(2);
    assertEqual(2, matrix1.width(), "Checks that width is updated with deleteCol.");

    matrix1.insertCol(2);
    matrix1.insertCol(2);
    assertEqual(4, matrix1.width(), "Checks that width is updated with insertCol.");

    matrix1.fillRegion(0, 0, 3, 3, "Hi");
    assertEqual(4, matrix1.width(), "Checks that width is not updated with fillRegion.");

    matrix1.fillLine(0, 0, 0, 0, 3, 4, "Bye");
    assertEqual(4, matrix1.width(), "Checks that width is not updated with fillRegion.");
  } // checkWidthChanges()

  @Test
  public void checkEqualityWithMatrixAlterations() {
    MatrixV0<Integer> matrix1 = new MatrixV0<Integer>(4, 4, 1);
    MatrixV0<Integer> matrix2 = new MatrixV0<Integer>(4, 4, 2);
    MatrixV0<Integer> matrix4 = new MatrixV0<Integer>(4, 4, 1);

    assertEqual(true, matrix1.equals(matrix4), "Check that equals actually works");

    assertEqual(false, matrix1.equals(matrix2), "Check that two different matrices are indeed not equal.");

    matrix2.fillRegion(0, 0, 5, 5, 1);
    assertEqual(true, matrix1.equals(matrix2), "Checks that fillRegion works as described with end Row and Col exclusive");

    MatrixV0<Intger> matrix3 = null;
    matrix3 = matrix2.clone();
    assertEqual(true, matrix2.equals(matrix3), "Checks that clone is equal to its original");
    assertEqual(true, matrix1.equals(matrix3), "Checks that clone is actually equal to not just its original");
    assertEqual(true, matrix3.equals(matrix1), "Checks that equal works both ways (can call from either matrix)");
    assertEqual(true, matrix3.equals(matrix2), "Second check that equal works both ways");

  }

} // class TestsByRichard
