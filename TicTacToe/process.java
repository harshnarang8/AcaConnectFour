// TicTactoe v1.01
//initialization works
// makePlay works
// printState works
//checkWin Works
//heurist nd
//minimax nd
//nextmove nd
// package TicTacToe;
import java.util.Scanner;
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

  public String checkWinForMe(int[] x, int[] y, int[] mark) {
    int[][] temp = new int[3][3];
    for (int i = 0; i < 3; i++ ) {
      for (int j = 0; j < 3; j++) {
        temp[i][j] = this.board[i][j];
      }
    }
    for (int k = 0; k < x.length ; k++) {
      temp[x[k]][y[k]] = mark[k];
    }
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
    // node root = new node(9);
    // root.value = 9;
    AI atom = new AI();

    System.out.printf("Start the game?%n");
    Scanner user_input = new Scanner(System.in);
    int[] x = new int[2];
    String y;
    while(true) {
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
      System.out.println(x[0]+" "+x[1]);
      p.makePlay("AI",x[0],x[1]);
      p.printState();
      y = p.checkWin();
      if (y != "None") break;
    }
    System.out.println(y);

    // node trial = new node(2);
    // trial.addChild();

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

  public int[] nextMove(process p) {
    int count = 0;
    for(int i = 0; i < 3; i++) {
      for(int j = 0; j < 3; j++) {
        if (p.board[i][j] == 0)
          count++;
      }
    }
    node decisionRoot = new node(count);
    int[] x = new int[2];
    x[0] = 2;
    x[1] = 2;
    this.buildTree(p,decisionRoot,9,1);
    this.minimax(decisionRoot,9,1);
    System.out.println("Current value : " + decisionRoot.value );
    for(int i = 0; i < decisionRoot.child.length; i++) {
      System.out.printf("Value of child["+i+"] :");
      System.out.println(" "+decisionRoot.child[i].value);
      if (decisionRoot.value == decisionRoot.child[i].value) {
        System.out.println("Passed this mark.");
        x[0] = decisionRoot.child[i].memory.x[1];
        x[1] = decisionRoot.child[i].memory.y[1];
        break;
      }
    }
    System.out.println(" "+decisionRoot.child[0].memory.x[1]+" "+x[0]);
    System.out.println(" "+decisionRoot.child[0].memory.y[1]+" "+x[1]);
    return x;
  }

  public void buildTree(process p, node node1, int depth, int a) {
    // System.out.println(node1.value+" ");
    String b = p.checkWinForMe(node1.memory.x,node1.memory.y,node1.memory.mark);
    if (b == "None") {
      if (depth != 0 && node1.level != 0) {
        int k = 0;
        int flag2 = 0;
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
              if (node1.noOfChild == node1.level)
                flag2 = 1;
              if (((p.board[i][j] == 0) && (flag!=1))&& (flag2!=1) ) {
                if (p.board[i][j] != 0) {
                  System.out.println("Oh My God!!");
                  for (int l = 0; l < 20; l++) System.out.println();
                }
                node1.addChild(i,j,a,node1);
                // if (depth == 3)
                  // System.out.println("Voila "+i+" "+j);
                // System.out.println("Inner Loop :"+i+" "+j);
                buildTree(p,node1.child[k],depth-1,-1 * a);
                k++;
              }
            }
          }
      }
      else
        node1.value = this.heurist(p, node1, a);
        // System.out.println("node1.value : "+node1.value);
    }
    else if (b == "AI") {// If AI already won
      if (a == 1)
        node1.value = 10000;
      else
        node1.value = -10000;
    }
    else { // If Human already won
      if (a == 1)
        node1.value = -10000;
      else
        node1.value = 10000;
    }
  }

  public int heurist(process p, node node1, int a) {
    int[] pcount = new int[4];
    int[] ncount = new int[4];
  String m = p.checkWinForMe(node1.memory.x, node1.memory.y, node1.memory.mark);
  if (m == "AI") {
    if (a == 1) return 10000;
    if (a == -1) return -10000;
  }
  else if (m == "Human") {
    if (a == -1) return 10000;
    else if (a == 1) return -10000;
  }
  else if (m == "Tied") {
    return 0;
  }
    for(int i = 0; i < 3; i++) {
      if (p.col[i]> 0)
        pcount[p.col[i]]++;
      else if (p.col[i] == 0) {
        pcount[0]++;
        ncount[0]++;
      }
      else {
        ncount[-1*p.col[i]]++;
      }
      if (p.row[i]> 0)
        pcount[p.row[i]]++;
      else if (p.row[i] == 0) {
        pcount[0]++;
        ncount[0]++;
      }
      else
        ncount[-1*p.row[i]]++;
      if(i<2) {
        if (p.diag[i]> 0)
          pcount[p.diag[i]]++;
        else if (p.diag[i] == 0) {
          pcount[0]++;
          ncount[0]++;
        }
        else
          ncount[-1*p.diag[i]]++;
      }
    }
    if (ncount[3] > 0) return -10000;
    if (pcount[3] > 0) return 10000;
    if (ncount[2] > 0) return -7500;
    if (pcount[2] > 0) return 7500;
    if (ncount[1] > 0) return -2000;
    if (pcount[1] > 0) return 2000;
    return 0;
  }

  public int minimax(node node1, int depth, int a) {
    int bestValue, v;

    if ((depth == 0) || (this.term(node1)) == 1)
      try {
        return node1.value;
      }
      catch (java.lang.NullPointerException e) {
        System.out.printf("depth : "+depth+" node.level :" + node1.level+"%n");
        System.out.println(" "+node1.value);
      }
    if (a == 1) {
      bestValue = -10000;
      for (node c : node1.child) {
        v = this.minimax(c, depth - 1, -1*a);
        bestValue = (v > bestValue) ? v : bestValue;
      }
      node1.value = bestValue;
      return bestValue;
    }
    else {
      bestValue = 10000;
      for (node c : node1.child) {
        v = this.minimax(c, depth - 1, -1*a);
        bestValue = (v < bestValue)? v : bestValue;
      }
      node1.value = bestValue;
      return bestValue;
    }
  }

  public int term(node nod) {
    // System.out.println(" "+nod.value);
    try {
      // System.out.println("Visited here");
      // System.out.println(" "+nod.level);
      // System.out.println(" "+nod.value);
      if ((nod.value ==10000)||(nod.value== -10000)) {
        return 1;
      }
      else
        return 0;
    }
    catch (java.lang.NullPointerException e) {
      System.out.println(" "+nod.level);
      return 0;
    }
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
    value = 0;
 }
 public void addChild(int x, int y, int a, node ins) {
  //  System.out.println("ins.level : "+ins.level);
  //  System.out.println("noOfChild : "+ins.noOfChild);
  if (x == 0 && y == 0) System.out.println("Caught you !");
   ins.child[ins.noOfChild] = new node(level - 1);
   node temp = ins.child[ins.noOfChild];
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
