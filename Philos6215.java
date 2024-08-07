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

  static {
    for (int i = 0; i < FREE_POLYOMINOES.length; i++) {
      System.out.println("Free polyomino #" + (i + 1));
      System.out.println();
      printPolyomino(FREE_POLYOMINOES[i]);
      System.out.println();
    }
  }
  
  public static void main(String[] args) {
    printBoard();
  }
  
//  private static void printPolyomino(int[][] polyomino) {
//    assert polyomino.length > 0;
//    assert polyomino[0].length > 0;
//    int rows = polyomino.length;
//    int cols = polyomino[0].length;
//    for (int row = 0; row < rows; row++) {
//      for (int col = 0; col < cols; col++) {
//        if (polyomino[row][col] > 0) {
//          if ((row == 0 || polyomino[row - 1][col] == 0)) {
//            System.out.print("+---");
//          } else if (col == 0 || polyomino[row][col - 1] == 0) {
//            System.out.print("+   ");
//          } else {
//            System.out.print("    ");
//          }
//        } else {
//          if (col > 0 && polyomino[row][col - 1] > 0) {
//            System.out.print("+   ");
//          } else {
//            System.out.print("    ");            
//          }
//        }
//      }
//      System.out.println("+");
//      for (int col = 0; col < cols; col++) {
//        if (polyomino[row][col] > 0) {
//          if ((col == 0 || polyomino[row][col - 1] == 0)) {
//            System.out.print("|   ");
//          } else {
//            System.out.print("    ");
//          }
//        } else {
//          if (col > 0 && polyomino[row][col - 1] == 1) {
//            System.out.print("|   ");
//          } else {
//            System.out.print("    ");
//          }
//        }
//      }
//      System.out.println("|");
//    }
//    for (int col = 0; col < cols; col++) {
//      if (polyomino[rows - 1][col] > 0) {
//        System.out.print("+---");
//      }
//    }
//    System.out.println("+");
//  }

  private static void printPolyomino(int[][] polyomino) {
    assert polyomino.length > 0;
    assert polyomino[0].length > 0;
    int rows = polyomino.length;
    int cols = polyomino[0].length;
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < cols; col++) {
        if (!up(polyomino, row, col) && !left(polyomino, row, col)) {
          if (polyomino[row][col] > 0) {
            System.out.print("+---");
          } else {
            System.out.print(upperLeft(polyomino, row, col) ? "+   " : "    ");
          }
        } else if (up(polyomino, row, col) && !left(polyomino, row, col)) {
          System.out.print(polyomino[row][col] > 0 ? "+   " : "+---");
        } else if (!up(polyomino, row, col) && left(polyomino, row, col)) {
          System.out.print(polyomino[row][col] > 0 ? "+---" : "+   ");
        } else {
          if (polyomino[row][col] > 0) {
            System.out.print(upperLeft(polyomino, row, col) ? "    " : "+   ");
          } else {
            System.out.print("+---");
          }
        }
      }
      System.out.println(polyomino[row][cols - 1] > 0 || up(polyomino, row, cols - 1) ? "+" : " ");
      for (int col = 0; col < cols; col++) {
        System.out.print(left(polyomino, row, col) == polyomino[row][col] > 0 ? "    " : "|   ");
      }
      System.out.println(polyomino[row][cols - 1] > 0 ? "|" : " ");
    }
    for (int col = 0; col < cols; col++) {
      if (polyomino[rows - 1][col] > 0) {
        System.out.print("+---");
      } else {
        System.out.print(left(polyomino, rows - 1, col) ? "+   " : "    ");
      }
    }
    System.out.println(polyomino[rows - 1][cols - 1] > 0 ? "+" : " ");
  }

  private static boolean left(int[][] polyomino, int row, int col) {
    if (col == 0) {
      return false;
    }
    return polyomino[row][col - 1] > 0;
  }

  private static boolean up(int[][] polyomino, int row, int col) {
    if (row == 0) {
      return false;
    }
    return polyomino[row - 1][col] > 0;
  }

  private static boolean upperLeft(int[][] polyomino, int row, int col) {
    if (row == 0 || col == 0) {
      return false;
    }
    return polyomino[row - 1][col - 1] > 0;
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
}
