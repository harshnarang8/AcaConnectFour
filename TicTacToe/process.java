// TicTactoe v1.01
public class process {
  int[][] board = new int[3][3];
  int[] row = new int[3];
  int[] col = new int[3];
  int[] diag = new int[2];

  process() {
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        this.board[i][j] = 0;
      }
    }
  }

  public void makePlay(String player, int x, int y) {
    int a = 0;
        if (player == "AI")
            a = 1;
        else if (player == "Human")
            a = -1;
        this.board[x][y] = a;
        this.col[y] += a;
        this.row[x] += a;
        if (x == y)
            this.diag[0] += a;
        if (x + y == 2)
            this.diag[1] += a;
  }

  public void printState() {
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        System.out.printf(this.board[i][j] + " ");
      }
      System.out.println();
    }
  }
  public String checkWin() {
    for (int m : this.row) {
            if (m == 3)
                return "AI";
            else if (m == -3)
                return "Human";
    }
    for (int m : this.col) {
            if (m == 3)
                return "AI";
            else if (m == -3)
                return "Human";
    }
    for (int m : this.diag) {
            if (m == 3)
                return "AI";
            else if (m == -3)
                return "Human";
    }
    return "None";
  }

  public static void main(String[] args) {
    process p = new process();
    // // Test print for the board
    // p.printState();
    // //trial play moves
    // System.out.println();
    // p.makePlay("AI",1,1);
    // p.printState();
    // System.out.println(p.checkWin());
    // System.out.println();
    // p.makePlay("Human",1,2);
    // p.printState();
    // System.out.println(p.checkWin());
    // System.out.println();
    // p.makePlay("AI",2,1);
    // p.printState();
    // System.out.println(p.checkWin());
    // System.out.println();
    // p.makePlay("Human",0,2);
    // p.printState();
    // System.out.println(p.checkWin());
    // System.out.println();
    // p.makePlay("AI",0,1);
    // p.printState();
    // System.out.println(p.checkWin());
    // System.out.println();
  }
}
