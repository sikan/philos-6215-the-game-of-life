
public class Philos6215 {

  public static final int ROW_COL_SIZE = 10;

  public static void main(String[] args) {
    printBoard();
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
