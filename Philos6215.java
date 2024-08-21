import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Philos6215 {

  public static final int ROW_COL_SIZE = 8;

  public static final int[][][] FREE_POLYOMINOES = {
      {
        {1, 1},
        {1, 1}
      },
      {
        {1, 1, 0},
        {1, 1, 1}
      },
      {
        {0, 1, 0},
        {1, 1, 1},
        {0, 1, 0}
      },
      {
        {1, 0, 1},
        {1, 1, 1}
      },
      {
        {0, 1, 0},
        {0, 1, 0},
        {1, 1, 1}
      },
      {
        {0, 1, 0},
        {1, 1, 1},
        {1, 0, 0}
      },
      {
        {0, 1, 1},
        {1, 1, 0},
        {1, 0, 0}
      },
      {
        {1, 1, 1},
        {1, 0, 0},
        {1, 0, 0}
      },
      {
        {0, 1, 1},
        {0, 1, 0},
        {1, 1, 0}
      },
      {
        {1, 0, 0, 0},
        {1, 1, 1, 1}
      },
      {
        {0, 0, 1, 1},
        {1, 1, 1, 0}
      },
      {
        {0, 1, 0, 0},
        {1, 1, 1, 1}        
      },
      {
        {1, 1, 1, 1, 1}
      }
  };

  //  +---+---+---+---+---+
  //  |           |       |
  //  +   +---+---+---+   +
  //  |   |   |       |   |
  //  +---+   +       +---+
  //  |       |       |   |
  //  +---+---+---+---+   +
  //  |           |       |
  //  +---+       +---+   +
  //  |   |           |   |
  //  +---+---+---+---+---+   
  public static final int[][] SOLUTION_SAMPLE_5_BY_5 = {
      { 1, 1, 1, 2, 2 },
      { 1, 3, 4, 4, 2 },
      { 3, 3, 4, 4, 9 },
      { 5, 5, 5, 9, 9 },
      { 8, 5, 5, 5, 9 } 
  };

  static {
    for (int i = 0; i < FREE_POLYOMINOES.length; i++) {
      System.out.println("Free polyomino #" + (i + 1));
      System.out.println();
      printPolyomino(FREE_POLYOMINOES[i]);
      System.out.println();
      System.out.println("All fixed polyominoes for free polyomino #" + (i + 1));
      for (int[][] fixedPolyomino : generateFixedPolyominoes(FREE_POLYOMINOES[i])) {
        System.out.println();
        printPolyomino(fixedPolyomino);
        System.out.println();
      }
    }
  }

  public static void main(String[] args) {
    printSolution(SOLUTION_SAMPLE_5_BY_5);
  }

  private static void printPolyomino(int[][] polyomino) {
    assert polyomino.length > 0;
    assert polyomino[0].length > 0;
    int rows = polyomino.length;
    int cols = polyomino[0].length;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (!up(polyomino, row, col, false) && !left(polyomino, row, col, false)) {
          if (polyomino[row][col] > 0) {
            System.out.print("+---");
          } else {
            System.out.print(upperLeft(polyomino, row, col, false) ? "+   " : "    ");
          }
        } else if (up(polyomino, row, col, false) && !left(polyomino, row, col, false)) {
          System.out.print(polyomino[row][col] > 0 ? "+   " : "+---");
        } else if (!up(polyomino, row, col, false) && left(polyomino, row, col, false)) {
          System.out.print(polyomino[row][col] > 0 ? "+---" : "+   ");
        } else {
          if (polyomino[row][col] > 0) {
            System.out.print(upperLeft(polyomino, row, col, false) ? "    " : "+   ");
          } else {
            System.out.print("+---");
          }
        }
      }
      System.out.println(polyomino[row][cols - 1] > 0 || up(polyomino, row, cols - 1, false) ? "+" : " ");
      for (int col = 0; col < cols; col++) {
        System.out.print(left(polyomino, row, col, false) == polyomino[row][col] > 0 ? "    " : "|   ");
      }
      System.out.println(polyomino[row][cols - 1] > 0 ? "|" : " ");
    }
    for (int col = 0; col < cols; col++) {
      if (polyomino[rows - 1][col] > 0) {
        System.out.print("+---");
      } else {
        System.out.print(left(polyomino, rows - 1, col, false) ? "+   " : "    ");
      }
    }
    System.out.println(polyomino[rows - 1][cols - 1] > 0 ? "+" : " ");
  }

  /* solution: whether a is a complete solution or just a single polyomino? */
  private static boolean left(int[][] a, int row, int col, boolean solution) {
    assert !solution || a[row][col] > 0;
    if (col == 0) {
      return false;
    }
    return solution ? a[row][col - 1] == a[row][col] : a[row][col - 1] > 0;
  }

  private static boolean up(int[][] a, int row, int col, boolean solution) {
    assert !solution || a[row][col] > 0;
    if (row == 0) {
      return false;
    }
    return solution ? a[row - 1][col] == a[row][col] : a[row - 1][col] > 0;
  }

  private static boolean upperLeft(int[][] a, int row, int col, boolean solution) {
    assert !solution || a[row][col] > 0;
    if (row == 0 || col == 0) {
      return false;
    }
    return solution ? a[row - 1][col - 1] == a[row][col] : a[row - 1][col - 1] > 0;
  }

  private static int[][] rotateClockwise(int[][] polyomino) {
    int rows = polyomino.length;
    int cols = polyomino[0].length;
    int[][] rotated = new int[cols][rows];
    for (int i = 0; i < cols; i++) {
      for (int j = 0; j < rows; j++) {
        rotated[i][j] = polyomino[rows - 1 - j][i];
      }
    }
    return rotated;
  }

  private static int[][] flip(int[][] polyomino) {
    int rows = polyomino.length;
    int cols = polyomino[0].length;
    int[][] flipped = new int[rows][cols];
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        flipped[i][j] = polyomino[i][cols - 1 - j];
      }
    }
    return flipped;
  }

  private static List<int[][]> generateFixedPolyominoes(int[][] polyomino) {
    List<int[][]> fixedPolyominoes = new ArrayList<>();
    fixedPolyominoes.add(polyomino);
    int[][] rotated = rotateClockwise(polyomino);
    addIfNotPresent(fixedPolyominoes, rotated);
    rotated = rotateClockwise(rotated);
    addIfNotPresent(fixedPolyominoes, rotated);
    rotated = rotateClockwise(rotated);
    addIfNotPresent(fixedPolyominoes, rotated);
    int[][] flipped = flip(polyomino);
    addIfNotPresent(fixedPolyominoes, flipped);
    rotated = rotateClockwise(flipped);
    addIfNotPresent(fixedPolyominoes, rotated);
    rotated = rotateClockwise(rotated);
    addIfNotPresent(fixedPolyominoes, rotated);
    rotated = rotateClockwise(rotated);
    addIfNotPresent(fixedPolyominoes, rotated);
    return fixedPolyominoes;
  }

  private static void addIfNotPresent(List<int[][]> fixedPolyominoes, int[][] newFixed) {
    for (int[][] existing : fixedPolyominoes) {
      if (Arrays.deepEquals(existing, newFixed)) {
        return;
      }
    }
    fixedPolyominoes.add(newFixed);
  }

  private static void printBoard() {
    for (int row = 0; row < ROW_COL_SIZE; row++) {
      for (int col = 0; col < ROW_COL_SIZE; col++) {
        System.out.print("+---");
      }
      System.out.println("+");
      for (int col = 0; col < ROW_COL_SIZE; col++) {
        System.out.print("|   ");
      }
      System.out.println("|");
    }
    for (int col = 0; col < ROW_COL_SIZE; col++) {
      System.out.print("+---");
    }
    System.out.println("+");
  }

  private static void printSolution(int[][] solution) {
    assert solution.length > 0;
    assert solution[0].length > 0;
    int rows = solution.length;
    int cols = solution[0].length;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        assert solution[row][col] > 0;
        if (!up(solution, row, col, true)) {
          System.out.print("+---");
        } else if (!left(solution, row, col, true)) {
          System.out.print("+   ");
        } else {
          System.out.print(upperLeft(solution, row, col, true) ? "    " : "+   ");
        }
      }
      System.out.println("+");
      for (int col = 0; col < cols; col++) {
        System.out.print(left(solution, row, col, true) ? "    " : "|   ");
      }
      System.out.println("|");
    }
    for (int col = 0; col < cols; col++) {
      System.out.print("+---");
    }
    System.out.println("+");
  }
}
