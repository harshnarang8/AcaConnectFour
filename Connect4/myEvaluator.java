package Connect4;
import java.util.*;

public class myEvaluator {
  public int tellMe(gamePlay game) {
    checker temporary = new checker();
    List<int[]> toBeChecked = temporary.generatePositions(game.board);
    int flag = 0;
    for(int [] m : toBeChecked) {
      int i = m[0], j = m[1], temp;
      temp = this.checkLeft(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkRight(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkBelow(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkLeftBelow(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkRightBelow(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
    }
    return flag;
  }
  public int tellMeInOrder(gamePlay game) {
    checker temporary = new checker();
    List<int[]> toBeChecked = temporary.generatePositions(game.board);
    int flag = 0;
    for(int [] m : toBeChecked) {
      int i = m[0], j = m[1], temp;
      temp = this.checkLeft(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkRight(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkBelow(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkLeftBelow(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
      temp = this.checkRightBelow(i,j,game.board, 1);
      temp = this.scale(temp);
      flag = flag + temp;
    }
    return flag;
  }

  int scale(int val) {
    switch (val) {
      case 4 :
        return 100000;
      case 3 :
        return 750;
      case 2 :
        return 400;
      case 1 :
        return 100;
      case 0 :
        return 0;
      case -1 :
        return -100;
      case -2 :
        return -400;
      case -3 :
        return -750;
      case -4 :
        return -100000;
    }
    return 0;
  }

  int checkLeft(int x, int y, int[][] gameState, int flag) {
    try {
      if(gameState[x][y+1] != 0 && gameState[x][y] != gameState[x][y+1])
        return 0;
    }
    catch (IndexOutOfBoundsException e) {
      return 0;
    }
    int sum = 0;
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x-i][y];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
    return sum;
	}

	int checkRight(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
    try {
      if(gameState[x][y-1] != 0 && gameState[x][y] != gameState[x][y-1])
        return 0;
    }
    catch (IndexOutOfBoundsException e) {
      return 0;
    }
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x+i][y];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
    return sum;
	}

	int checkBelow(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x][y+i];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
    return sum;
	}

	int checkRightBelow(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x+i][y+i];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
    return sum;
	}

	int checkLeftBelow(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x-i][y+i];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
    return sum;
	}

}
