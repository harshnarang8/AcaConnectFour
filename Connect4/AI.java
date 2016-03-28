package Connect4;
import java.util.*;

public class AI {
  public int[] minimax(gamePlay game, int player, int depth) {
    // player refers to the entity who is to play the current move
    // player best move possible is to be evaluated
    // playerNextMove is the move which will correspond to the best score
    // the returning array is a score, the current player, and his best move
    int bestValue = 0, playerNextMove = 7;
    int[] v;
    // nextMoves contains  the list of moves possible
    List<int[]> nextMoves = this.generateMoves(game);
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
      game.deletePlay(move[0]);
    }
    return new int[] {bestValue,player,playerNextMove};
  }

  public List<int[]> generateMoves(gamePlay game) {
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
    return temp;
  }

  public int heuristicFunc(gamePlay game) {
    myEvaluator judgement = new myEvaluator();
    return judgement.tellMe(game);
  }
}
