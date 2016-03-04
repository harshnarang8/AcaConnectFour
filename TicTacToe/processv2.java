// TicTactoe v1.01
//initialization works
// makePlay works
// printState works
//checkWin Works
//heurist nd
//minimax nd
//nextmove nd
// package TicTacToe;
import java.util.*;
public class processv2 {
  int[][] board = new int[3][3];
  int[] row = new int[3];
  int[] col = new int[3];
  int[] diag = new int[2];

  processv2() {
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        this.board[i][j] = 0;
      }
      this.row[i] = 0;
      this.col[i] = 0;
    }
    this.diag[0] = 0;
    this.diag[1] = 0;
  }

  public String makePlay(String player, int x, int y) {
    if (this.board[x][y] != 0)
      return "Wrong";
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
    return ("Next Move");
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
    int flag = 0;
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        if (this.board[i][j] == 0)
          flag = 1;
      }
    }
    if (flag == 0) return "Tied";
    return "None";
  }

  public static void main(String[] args) {
    processv2 p = new processv2();
    AI atom = new AI();
    int a1 = 1;
    int a2 = 1;
    if (a2 == 0) {
      p.makePlay("AI",1,1);
      p.makePlay("Human",2,2);
      List<int[]> t_ = atom.generateMoves(p,1);
      for (int[] m : t_) {
        System.out.println(m[0]+" "+m[1]);
      }
      p.printState();
    }
    if (a1 == 1) {
      System.out.printf("Start the game?%n");
      Scanner user_input = new Scanner(System.in);
      int[] x = new int[3];
      String y;
      while(true) {
        // x = atom.nextMove(p);
        // System.out.println(x[0]+" "+x[1]+" "+x[2]);
        // p.makePlay("AI",x[1],x[2]);
        // p.printState();
        // y = p.checkWin();
        // if (y != "None") break;

        x[0] = (int)user_input.nextInt();
        x[1] = (int)user_input.nextInt();

        int t = 0;
        do {
          y = p.makePlay("Human",x[0],x[1]);
          System.out.println(y + " "+t);
          t++;
        } while(y == "Wrong");

        p.printState();
        y = p.checkWin();
        if (y != "None") break;

        x = atom.nextMove(p);
        System.out.println(x[0]+" "+x[1]+" "+x[2]);
        p.makePlay("AI",x[1],x[2]);
        p.printState();
        y = p.checkWin();
        if (y != "None") break;

      }
      System.out.println(y);
    }
  }
}

class AI {

  public int[] nextMove(processv2 p) {
    int[] x = new int[3];
    x = this.minimax(p,9,1);
    return x;
  }

  public int heurist(processv2 p) {
    int[] score = new int[8];
    // if colsum = 3 for any column then score for that column = 10000
    // if colsum = 2 for any column then score for that column = 1000
    // if colsum = 1 for any cloumn then score for that column = 100 if no -
    // - entry has -1
    // else 0
    // same for rows and diagonals
    for(int i = 0; i < 3; i++) {
      switch (p.col[i]) {
        case 3:
          score[i] = 10000;
          break;
        case 2:
          score[i] = 1000;
          break;
        case 1:
          score[i] = 100;
          for (int k = 0; k < 2; k++) {
            if (p.board[k][i] == -1)
              score[i] = 0;
          }
          break;
        case 0:
          score[i] = 0;
          break;
        case -1:
          score[i] = -100;
          for (int k = 0; k < 2; k++) {
            if (p.board[k][i] == 1)
              score[i] = 0;
          }
          break;
        case -2:
          score[i] = -1000;
          break;
        case -3:
          score[i] = -10000;
          break;
      }
      switch (p.row[i]) {
        case 3:
          score[3 + i] = 10000;
          break;
        case 2:
          score[3 + i] = 1000;
          break;
        case 1:
          score[3 + i] = 100;
          for (int k = 0; k < 2; k++) {
            if (p.board[k][i] == -1)
              score[3 + i] = 0;
          }
          break;
        case 0:
          score[3 + i] = 0;
          break;
        case -1:
          score[3 + i] = -100;
          for (int k = 0; k < 2; k++) {
            if (p.board[k][i] == 1)
              score[3 + i] = 0;
          }
          break;
        case -2:
          score[3 + i] = -1000;
          break;
        case -3:
          score[3 + i] = -10000;
          break;
      }
      if ( i < 2 ) {
        switch (p.diag[i]) {
          case 3:
            score[6 + i] = 10000;
            break;
          case 2:
            score[6 + i] = 1000;
            break;
          case 1:
            score[6 + i] = 100;
            for (int k = 0; k < 2; k++) {
              if (p.board[k][k] == -1)
                score[6 + i] = 0;
            }
            break;
          case 0:
            score[6 + i] = 0;
            break;
          case -1:
            score[6 + i] = -100;
            for (int k = 0; k < 2; k++) {
              if (p.board[k][2-k] == 1)
                score[6 + i] = 0;
            }
            break;
          case -2:
            score[6 + i] = -1000;
            break;
          case -3:
            score[6 + i] = -10000;
            break;
        }

      }//if
    }//for
    int r = 0;
    for (int m : score) {
      r = m + r;
    }
    return r;
  }

//
//internet ver
private int evaluate(processv2 p,int a) {
      int score = 0;
      // Evaluate score for each of the 8 lines (3 rows, 3 columns, 2 diagonals)
      score += evaluateLine(p, 0, 0, 0, 1, 0, 2);  // row 0
      score += evaluateLine(p, 1, 0, 1, 1, 1, 2);  // row 1
      score += evaluateLine(p, 2, 0, 2, 1, 2, 2);  // row 2
      score += evaluateLine(p, 0, 0, 1, 0, 2, 0);  // col 0
      score += evaluateLine(p, 0, 1, 1, 1, 2, 1);  // col 1
      score += evaluateLine(p, 0, 2, 1, 2, 2, 2);  // col 2
      score += evaluateLine(p, 0, 0, 1, 1, 2, 2);  // diagonal
      score += evaluateLine(p, 0, 2, 1, 1, 2, 0);  // alternate diagonal
      return score;
   }

   /** The heuristic evaluation function for the given line of 3 p.board
       @Return +100, +10, +1 for 3-, 2-, 1-in-a-line for computer.
               -100, -10, -1 for 3-, 2-, 1-in-a-line for opponent.
               0 otherwise */
   private int evaluateLine(processv2 p,int row1, int col1, int row2, int col2, int row3, int col3) {
      int score = 0;

      // First cell
      if (p.board[row1][col1] == 1) {
         score = 1;
      } else if (p.board[row1][col1] == -1) {
         score = -1;
      }

      // Second cell
      if (p.board[row2][col2] == 1) {
         if (score == 1) {   // cell1 is 1
            score = 10;
         } else if (score == -1) {  // cell1 is -1
            return 0;
         } else {  // cell1 is empty
            score = 1;
         }
      } else if (p.board[row2][col2] == -1) {
         if (score == -1) { // cell1 is -1
            score = -10;
         } else if (score == 1) { // cell1 is 1
            return 0;
         } else {  // cell1 is empty
            score = -1;
         }
      }

      // Third cell
      if (p.board[row3][col3] == 1) {
         if (score > 0) {  // cell1 and/or cell2 is 1
            score *= 10;
         } else if (score < 0) {  // cell1 and/or cell2 is -1
            return 0;
         } else {  // cell1 and cell2 are empty
            score = 1;
         }
      } else if (p.board[row3][col3] == -1) {
         if (score < 0) {  // cell1 and/or cell2 is -1
            score *= 10;
         } else if (score > 0) {  // cell1 and/or cell2 is 1
            return 0;
         } else {  // cell1 and cell2 are empty
            score = -1;
         }
      }
      return score;
   }
   //
   //
   //


  public int[] minimax(processv2 p, int depth, int a) {
    int bestValue, v, myRow, myCol;
    bestValue = 0;
    myRow = 3;
    myCol = 3;
    List<int[]> nextMoves = generateMoves(p,a);
    // I add new moves to the board for each depth, then undo it so tree
    // not required
    if ((depth == 0) || nextMoves.isEmpty()) {
      bestValue = evaluate(p,a);
    }
    for (int[] move : nextMoves) {
      p.board[move[0]][move[1]] = a;
      // p.printState();
      // System.out.println();
      if (a == 1) {
        bestValue = -10000;
        v = this.minimax(p, depth - 1, -1*a)[0];
        if ( v > bestValue) {
        bestValue = v;
        myRow = move[0];
        myCol = move[1];
        }
      }
      else {
        bestValue = 10000;
        v = this.minimax(p, depth - 1, -1*a)[0];
        if ( v < bestValue) {
        bestValue = v;
        myRow = move[0];
        myCol = move[1];
        }
      }
      p.board[move[0]][move[1]] = 0;
    }
    return new int[] {bestValue,myRow,myCol};
  }

  public List<int[]> generateMoves(processv2 p,int a) {
    List<int[]> temp = new ArrayList<int[]>();
    String m = p.checkWin();
    if (m == "AI" || m == "Human" || m == "Tied") {
      return temp;
    }
    else {
      for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
          if (p.board[i][j] == 0) {
            temp.add(new int[] {i,j});
            p.board[i][j] = a;
            m = p.checkWin();
            p.board[i][j] = 0;
            if (m == "AI" || m == "Human" || m == "Tied") {
              return temp;
            }
          }
        }
      }
    }
    return temp;
  }

}
