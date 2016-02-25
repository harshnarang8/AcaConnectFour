// TicTactoe v1.01
//initialization works
// makePlay works
// printState works
//checkWin Works
// package TicTacToe;
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
    node root = new node(9);
    root.value = 9;
    AI atom = new AI();
    // atom.buildTree(p,root,3,1);
    // node temp;
    // for (int k = 0; k < 9 ; k++ ) {
    //   temp = root.child[k];
    //   System.out.println(temp.memory.x[0]+" "+temp.memory.y[0]);
    // }
    // System.out.println(root.noOfChild);
  }
}

class AI {

  public int nextMove() {
    return 0;
  }

  public void buildTree(process p, node node1, int depth, int a) {
    // System.out.println(node1.value+" ");
    if (depth != 0) {
      int k = 0;
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            int flag = 0;
            // check saved possible future pos
            for (int m = 0;m < node1.memory.x.length;m++) {
              if ( (i == node1.memory.x[m]) && j == node1.memory.y[m]) {
                flag = 1;
                // System.out.println("Flagged at depth "+depth);
              }
            }
            // check saved possible future pos completed
            // child added if the place is still empty
            if ((p.board[i][j] == 0) && (flag!=1)) {
              node1.addChild(i,j,a,node1);
              if (depth == 3)
                // System.out.println("Voila "+i+" "+j);
              // System.out.println("Inner Loop :"+i+" "+j);
              buildTree(p,node1.child[k],depth-1,-1 * a);
              k++;
            }
          }
        }
    }
    else
      node1.value = this.heurist(node1, a);
  }

  public int heurist(node node1, int a) {
    return 0;
  }
}

class node {
  // I need child parent value record
  node[] child;
  int noOfChild;
  int level;
  node parent;
  record memory;
  int value;
  class record {
    int[] x;
    int[] y;
    int[] mark;
  }
  node(int n) {
    memory = new record();
    child = new node[n];
    level = n;
    memory.x = new int[9-n];
    memory.y = new int[9-n];
    memory.mark = new int[9-n];
    noOfChild = 0;
 }
 public void addChild(int x, int y, int a, node ins) {
   ins.child[noOfChild] = new node(level - 1);
   node temp = ins.child[noOfChild];
   int i = 0;
   if (ins.memory.x.length != 0) {
     for(i = 0; i < ins.memory.x.length; i++) {
       temp.memory.x[i] = ins.memory.x[i];
       temp.memory.y[i] = ins.memory.y[i];
       temp.memory.mark[i] = ins.memory.mark[i];
     }
    }
    temp.memory.x[i] = x;
    temp.memory.y[i] = y;
    temp.memory.mark[i] = a;
    temp.parent = ins;
    // System.out.printf("ins.value : "+ins.value+" ");
    // System.out.printf("The added index : "+i+" The values :");
    // System.out.printf(" "+ins.child[noOfChild].memory.x[i]);
    // System.out.println(" "+ins.child[noOfChild].memory.y[i]);
    ins.noOfChild++;
 }

}
