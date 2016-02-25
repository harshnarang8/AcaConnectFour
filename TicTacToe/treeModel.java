
public class TreeModel {

  public void buildTree(node node1,int depth,int a) {
    if (depth != 0) {
      int k = 0;
        for (int i = 0; i < 3; i++) {
          for (int j = 0; j < 3; j++) {
            int flag = 0;
            // check saved possible future pos
            for (int m = 0;m < node1.memory.x.length;m++) {
              if ( (i == node1.memory.x[m]) && j == node1.memory.y[m])
                flag = 1;
            }
            // check saved possible future pos completed
            // child added if the place is still empty
            if ((node1.state[i][j] == 0) && (flag!=1)) {
              node1.addChild(i,j,a);
              buildTree(node1.child[k],depth-1,-1 * a);
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
  public static void main(String[] args) {
    System.out.printf("I did it.%n");
    node root = new node(9);
    int a = 1;
    int b = 2;
    root.addChild(a,b,1);
    System.out.println("root.memory.x.length : "+root.memory.x.length);
    System.out.println("root.child.length : "+root.child.length);
    node temp = root.child[0];
    System.out.println("temp.level : "+temp.level);
    // System.out.println(a.memory.x[0]);
    // a.s();
    // System.out.println(a.memory.x[0]);
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
 void addChild(int x, int y, int a) {
   this.child[noOfChild] = new node(level - 1);
   node temp = this.child[noOfChild];
   if (this.memory.x.length != 0) {
     int i;
     for(i = 0; i < this.memory.x.length; i++) {
       temp.memory.x[i] = this.memory.x[i];
       temp.memory.y[i] = this.memory.y[i];
       temp.memory.mark[i] = this.memory.mark[i];
     }
     temp.memory.x[i] = x;
     temp.memory.y[i] = y;
     temp.memory.mark[i] = a;
   }
   noOfChild++;
 }


  // void s() {
  //   this.memory.x[0] = 1;
  //  }

}
