package Connect4;
import java.util.*;
import java.io.*;

public class AI {
  int PrakharVariable;

  public int[] minimax(gamePlay game, int player, int depth) {
    // player refers to the entity who is to play the current move
    // player best move possible is to be evaluated
    // playerNextMove is the move which will correspond to the best score
    // the returning array is a score, the current player, and his best move
    int bestValue = 0, playerNextMove = 7;
    int[] v;
    // nextMoves contains  the list of moves possible
    List<int[]> nextMoves = this.generateMoves(game, player);
    int q = game.checkWin();

    if (q != 2)// this should be returned if no further moves possible
      return new int[] {q*100000,player,-1};
    // q*100000 refers to the current win loss or tie situation
    // -1 refers to no playing move possible in the current situation

    //if depth is 0, we check the possible gameState
    if (depth == 0) {
      return new int[] {this.heuristicFunc(game),player,-1};
    }
    int flag = 0;

    // it is to initialize the bestValue value
    if (player == 1)
      bestValue = -100000;
      // bestValue is the lowest possible value initially
      // if we get a better value later we increase bestValue to that
    else
      bestValue = 100000;
      // bestValue is the highest possible value initially
      // if we get a lower value later we change bestValue to that

    // this is the for loop which loops over the current possible moves
    // the possible moves function is correct
    for (int move[] : nextMoves) {
      game.makePlay(player,move[0]);

      if (player == 1) {// maximizing player
        v = this.minimax(game,-1*player,depth - 1);// point where it goes deeper
        if(v[0] == 100000) {
          // this means the move we made was a definite winning move
          // so first delete the possible move
          // then return the possible move
          game.deletePlay(move[0]);
          return new int[] {100000,player,move[0]};
        }
        else if ( bestValue <= v[0]) {
          // that is the current move returns equal or greater score than
          // currently is possible
          bestValue = v[0];
          playerNextMove = move[0];
          flag = 1;
        }
      }
      else {// minimizing player
        v = this.minimax(game,-1*player, depth - 1);//point where it goes deeper
        if(v[0] == -100000) {
          // this means the move played by the current player is a winning move
          game.deletePlay(move[0]);
          return new int[] {-100000,player,move[0]};
        }
        if ( bestValue >= v[0]) {
          // that is the current move returns score less than currently possible
          bestValue = v[0];
          playerNextMove = move[0];
          flag = 1;
        }
      }
      if (depth == 7) {
        System.out.println(move[0]+" "+v[0]);
      }
      game.deletePlay(move[0]);
    }
    return new int[] {bestValue,player,playerNextMove};
  }

  public int[] alphaBeta(gamePlay game,int alpha, int beta, int player, int depth) {
    // player refers to the entity who is to play the current move
    // player best move possible is to be evaluated
    // the returning array is a score, the current player, and his best move
    // a new variable PrakharVariable has been added
    // to serve as an alternative against alpha beta
    int bestValue = 0, playerNextMove = 7;
    int[] v = new int[3];
    int[] temp = new int[3];
    // nextMoves contains  the list of moves possible
    List<int[]> nextMoves = this.generateMoves(game, player);
    int q = game.checkWin();

    if (q != 2)// this should be returned if no further moves possible
      return new int[] {q*100000,player,-1};
    // q*100000 refers to the current win loss or tie situation
    // -1 refers to no playing move possible in the current situation

    //if depth is 0, we check the possible gameState
    if (depth == 0) {
      return new int[] {this.heuristicFunc(game),player,-1};
    }
    int flag = 0;

    // it is to initialize the bestValue value
    if (player == 1)
       bestValue = -100000;
      // v[0] is the lowest possible value initially
    else
      bestValue = 100000;
      // v[0] is the highest possible value initially

    //till this point, the algorithm is essentially same as basic MiniMax

    // this is the for loop which loops over the current possible moves
    // the possible moves function is correct
    for (int move[] : nextMoves) {
      game.makePlay(player,move[0]);

      if (player == 1) {// maximizing player
        // we call the next positions for next moves
        // we need the cut off at this point
        // if at any moment the value returned by the current move is
        // we break the for loop if we find that
        v = this.alphaBeta(game,alpha,beta, -1*player,depth - 1);
        if(v[0] == 100000) {
          // this means the move played by the current player is a winning move
          game.deletePlay(move[0]);
          // alpha = v[0];
          return new int[] {100000,player,move[0]};
          break;
        }
        if (bestValue <= v[0]) {
          bestValue = v[0];
          playerNextMove = move[0];
        }
        
        if (v[0] >= beta) {
          game.deletePlay(move[0]);
          return new int[] {v[0],player,move[0]};
          // break;
        }
      }

      else {// minimizing player


        v = this.alphaBeta(game,alpha,beta, -1*player,depth - 1);
        if(v[0] == -100000) {
          // this means the move played by the current player is a winning move
          // game.deletePlay(move[0]);
          beta = v[0];
          // return new int[] {-100000,player,move[0]};
          game.deletePlay(move[0]);
          break;
        }


        if (v[0] <= alpha) {
          game.deletePlay(move[0]);
          return new int[] {v[0],player,move[0]};
        }

        //temporary to make similar to prakhar code
        if (beta >= v[0]) {
          beta = v[0];
          playerNextMove = move[0];
        }

      }
      // for(int x = 0; x < depth; x++)
      //   System.out.printf("   ");
      if (depth == 7) {
        System.out.println("Depth : "+depth+" move : "+move[0]+" "+v[0]);
      }
      game.deletePlay(move[0]);
    }
    if (player == 1)
    return new int[] {alpha, player, playerNextMove};
    else
      return new int[] {beta, player, playerNextMove};
  }

  public List<int[]> generateMoves(gamePlay game, int player) {
    List<int[]> temp = new ArrayList<int[]>();
    for (int col = 0; col < 7; col++) {
      int i;
      for(i = 0; i < 6; i++)
  			if (game.board[i][col] != 0)
  				break;
  		i--;

  		if (i != -1) {
  			temp.add(new int[] {col});
      }
  		else
        continue;
    }
    temp = this.rearrangeMoves(game,temp,player);
    for(int[] m : temp)
      if (m[0] == -1)
        System.out.println("I caught ya!!!");
    return temp;
  }

  public List<int[]> rearrangeMoves(gamePlay game, List<int[]> rcvd, int player) {
    int n, i;
    i = 0;
    n = rcvd.size();
    int[][] a = new int[n][2];
    List<int[]> temp = new ArrayList<int[]>();
    for(int m[] : rcvd) {
      game.makePlay(player,m[0]);
      a[i][0] = heuristicFunc(game);
      a[i][1] = m[0];
      game.deletePlay(m[0]);
      i++;
    }
    this.QuickSort(a,0,(rcvd.size() - 1));
    for (int j = 0; j < n; j++ ) {
      temp.add(new int[] {a[j][1]});
    }
    return temp;
  }

  // Heuristics work
  public int heuristicFunc(gamePlay game) {
    myEvaluator judgement = new myEvaluator();
    return judgement.tellMe(game);
  }

  //QuickSort Works
  public void QuickSort(int[][] a, int low, int high) {
    if (low < high) {
      int p = this.partition(a,low,high);
      this.QuickSort(a, low, p-1);
      this.QuickSort(a, p+1, high);
    }
  }

  //Partition Works
  public int partition(int[][] a, int lo, int hi) {
    int pivot = a[hi][0];
    int i = lo;
    for(int j = lo; j < hi ; j++) {
      if(a[j][0] > pivot) {
        int temp1 = a[j][0];
        int temp2 = a[j][1];
        a[j][0] = a[i][0];
        a[j][1] = a[i][1];
        a[i][0] = temp1;
        a[i][1] = temp2;
        i++;
      }
    }
    int temp1 = a[hi][0];
    int temp2 = a[hi][1];
    a[hi][0] = a[i][0];
    a[hi][1] = a[i][1];
    a[i][0] = temp1;
    a[i][1] = temp2;
    return i;
  }

  public void fileWrite(String content) {
    // System.out.println("Oh Damn!!!");
    try {
      File file = new File("./test.txt");
      if (!file.exists()) {
          file.createNewFile();
      }
      FileWriter fw = new FileWriter(file.getAbsoluteFile());
      BufferedWriter bw = new BufferedWriter(fw);
      bw.write(content);
      bw.close();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println("How is this possible???");
  }
}
