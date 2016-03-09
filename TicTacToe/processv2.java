// TicTactoe v1.01
import java.util.*;
public class processv2 {
  int[][] board = new int[3][3];
  int[] marks = new int[8]; // 0 - 2 rows 3 - 5 cols 6 - 7 diagonals

  processv2() {
    for(int i = 0; i < 3; i++)
      for(int j = 0; j < 3; j++)
        this.board[i][j] = 0;
  }

  public String makePlay(int player, int x, int y) {
    if (this.board[x][y] != 0)
      return "Wrong";
    this.board[x][y] = player;
    return ("Next Move");
  }

  public void deletePlay(int player, int x, int y) {
  this.board[x][y] = 0;
  }

  public void printState() {
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        System.out.printf(this.board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public void printStateTab(int depth) {
    for(int i = 0; i < 3; i++) {
      for(int k = 0; k < depth; k++) {
        System.out.printf("     ");
      }
      for(int j = 0; j < 3; j++) {
        System.out.printf(this.board[i][j] + " ");
      }
      System.out.println();
    }
  }

  public int checkWin() {
    for(int i = 0; i < 3; i++) {
      int sumrow = 0, sumcol = 0;
      int[] sumdiag = new int[2];
      sumdiag[0] = 0;
      sumdiag[1] = 0;
      for(int j = 0; j < 3; j++) {
        sumrow = sumrow + this.board[i][j];
        sumcol = sumcol + this.board[j][i];
        sumdiag[0] = sumdiag[0] + this.board[j][j];
        sumdiag[1] = sumdiag[1] + this.board[j][2-j];
      }
      if (sumrow == 3 || sumcol == 3 || sumdiag[0]==3||sumdiag[1]==3) return 1;
      if (sumrow == -3|| sumcol == -3||sumdiag[0]==-3||sumdiag[1]==-3)return -1;
    }
    int flag = 0;
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        if (this.board[i][j] == 0)
          flag = 1;
      }
    }
    if (flag == 0) return 0;
    return 2;
  }

  public static void main(String[] args) {
    processv2 p = new processv2();
    processv2 p1 = new processv2();
    processv2 p2 = new processv2();
    processv2 p3 = new processv2();
    processv2 p4 = new processv2();
    AI atom = new AI();
    int a = 1;
    if (args.length > 0)
      a = Integer.parseInt(args[0]);
    if (a == 0) {
      p4.makePlay(-1,0,0);
      p4.makePlay(1,0,1);
      p4.makePlay(-1,0,2);
      p4.makePlay(1,1,0);
      p4.makePlay(-1,1,1);
      p4.makePlay(1,1,2);
      p4.makePlay(-1,2,0);
      // p.makePlay(-1,1,2);
      // p.makePlay(1,1,0);
      // List<int[]> t_ = atom.generateMoves(p,-1);
      // for (int[] m : t_) {
      //   System.out.println(m[0]+" "+m[1]);
      // }
      p4.printState();
      System.out.println("Win Check : "+p4.checkWin());
      System.out.printf("Test%n");

      p1.makePlay(-1,1,1);
      p1.makePlay(1,2,1);
      p1.printState();
      System.out.println();
      p1.deletePlay(1,2,1);
      p1.printState();
      p1.makePlay(-1,0,1);
      p1.printState();
      System.out.println("Win Check : "+p1.checkWin()+" ");

      p2.makePlay(-1,0,2);
      p2.makePlay(-1,0,1);
      p2.makePlay(-1,0,0);
      p2.printState();
      System.out.println("Win Check : "+p2.checkWin()+" ");

      p3.makePlay(-1,1,1);
      p3.makePlay(-1,2,0);
      // p3.makePlay(1,0,2);
      p3.printState();
      System.out.println("Win check : "+p3.checkWin()+" ");
      System.out.println("Possible moves :");
      List<int[]> t = atom.generateMoves(p3,1);
      for (int[] m : t)
        System.out.println(m[0]+" "+m[1]);
    }
    else if (a == 1) {
      System.out.printf("Start the game?%n");
      Scanner user_input = new Scanner(System.in);
      int[] x = new int[3];
      int y;
      String w;
      while(true) {

        x = atom.minimax(p,1,0,-1);
        System.out.println(x[0]+" "+x[1]+" "+x[2]);
        p.makePlay(1,x[1],x[2]);
        p.printState();
        y = p.checkWin();
        if (y != 2) break;

        int t = 0;
        do {
          x[0] = (int)user_input.nextInt();
          x[1] = (int)user_input.nextInt();
          w = p.makePlay(-1,x[0],x[1]);
          System.out.println(w + " "+t);
          t++;
        } while(w == "Wrong");

        p.printState();
        y = p.checkWin();
        if (y != 2) break;

        // x = atom.minimax(p,1,0,-1);
        // System.out.println(x[0]+" "+x[1]+" "+x[2]);
        // p.makePlay(1,x[1],x[2]);
        // p.printState();
        // y = p.checkWin();
        // if (y != 2) break;

      }
      System.out.println(y+" ");
    }
    else if (a == 2) {
      p1.makePlay(1,2,2);
      p1.makePlay(-1,1,1);
      // p1.makePlay(1,1,2);
      // p1.makePlay(-1,0,2);
      // p1.makePlay(1,0,0);
      // p1.makePlay(-1,0,1);
      // p1.makePlay(1,2,0);
      int[] x;
      x = atom.minimax(p1,1,0,-1);
    }
  }
}

class AI {

  public int[] minimax(processv2 p, int a, int depth, int CallerId) {
    int bestValue = 0, myRow = 3, myCol = 3;
    int[] v;
    List<int[]> nextMoves = generateMoves(p,a);
    // System.out.println();
    //
    // for(int i = 0; i < depth; i++) {
    //   System.out.printf("     ");
    // }
    // System.out.println("Holy Molly1111!!! " + (++CallerId)+" :");
    // // //
    // p.printStateTab(depth);
    int q = p.checkWin();
    //
    // for(int i = 0; i < depth; i++) {
    //   System.out.printf("     ");
    // }
    // System.out.println("Value of q = "+q);
    //

    if (q != 2) {
      // for(int i = 0; i < depth; i++) {
      //   System.out.printf("     ");
      // }
      // System.out.println("Holy Molly!!!");
      // for(int i = 0; i < depth; i++) {
      //   System.out.printf("     ");
      // }
      // System.out.println("CallerId = "+(CallerId-1)+" NextChance = "+a+" returnValue = "+(q*10000)+" returnRow = "+myRow+" returnCol = "+myCol+" returnOpt = 1");
      return new int[] {q*10000,3,3,1};
    }

    int flag = 0;
    for (int[] move : nextMoves) {
      p.makePlay(a,move[0],move[1]);
      if (a == 1) {// maximizing player
        bestValue = -10000;
        v = this.minimax(p,-1*a,depth + 1, CallerId);// point where it goes deeper
        if(v[0] == 10000 && v[1] == 3 && v[3] == 1) {
          // for(int i = 0; i < depth; i++) {
          //   System.out.printf("     ");
          // }
          // System.out.println("CallerId = "+(CallerId-1)+" NextChance = "+a+" Chosen one == returnValue = "+10000+" returnRow = "+move[0]+" returnCol = "+move[1]+" returnOpt = 0");
          p.deletePlay(a,move[0],move[1]);
          return new int[] {10000,move[0],move[1],0};
        }
        if ( v[0] == 0) {
        bestValue = v[0];
        myRow = move[0];
        myCol = move[1];
        // if (CallerId == 0)
        //   System.out.println("Damn it");
        flag = 1;
        }
        else if (v[0] == 10000) {
            // for(int i = 0; i < depth; i++) {
            //   System.out.printf("     ");
            // }
            // System.out.println("CallerId = "+(CallerId-1)+" NextChance = "+a+" Chosen one == returnValue = "+10000+" returnRow = "+move[0]+" returnCol = "+move[1]+" returnOpt = 0");
            p.deletePlay(a,move[0],move[1]);
            return new int[] {10000,move[0],move[1],0};
        }
      }
      else {// minimizing player
        bestValue = 10000;
        v = this.minimax(p,-1*a, depth + 1,CallerId);//point where it goes deeper
        if(v[0] == -10000 && v[1] == 3 && v[3] == 1) {
          // for(int i = 0; i < depth; i++) {
          //   System.out.printf("     ");
          // }
          // System.out.println("CallerId = "+(CallerId-1)+" NextChance = "+a+" Chosen one == returnValue = "+(-10000)+" returnRow = "+move[0]+" returnCol = "+move[1]+" returnOpt = 0");
          p.deletePlay(a,move[0],move[1]);
          return new int[] {-10000,move[0],move[1],0};
        }
        if ( v[0] == 0) {
        bestValue = v[0];
        myRow = move[0];
        myCol = move[1];
        // if (CallerId == 0)
        //   System.out.println("Damn it");
        flag = 1;
        }
        else if (v[0] == -10000) {
            // for(int i = 0; i < depth; i++) {
            //   System.out.printf("     ");
            // }
            // System.out.println("CallerId = "+(CallerId-1)+" NextChance = "+a+" Chosen one == returnValue = "+10000+" returnRow = "+move[0]+" returnCol = "+move[1]+" returnOpt = 0");
            p.deletePlay(a,move[0],move[1]);
            return new int[] {-10000,move[0],move[1],0};
        }
      }
      p.deletePlay(a,move[0],move[1]);
    }
    if ( myRow == 3) {
      // for(int i = 0; i < depth; i++) {
      //   System.out.printf("     ");
      // }
      // System.out.println("I was executed");
      int[] temp = nextMoves.get(0);
      myRow = temp[0];
      myCol = temp[1];
    }
    // System.out.println("CallerId = "+(CallerId-1)+" NextChance = "+a+" returnValue = "+bestValue+" returnRow = "+myRow+" returnCol = "+myCol+" returnOpt = 0 THIS HAPPENED");
    return new int[] {bestValue,myRow,myCol,0};
  }

  public List<int[]> generateMoves(processv2 p,int a) {
    List<int[]> temp = new ArrayList<int[]>();
    int m = p.checkWin();
    if (m == 1 || m == -1 || m == 0)
      return temp;
    else {
      for (int i = 0; i < 3; i++)
        for (int j = 0; j < 3; j++)
          if (p.board[i][j] == 0)
            temp.add(new int[] {i,j});
    }
    return temp;
  }
}
