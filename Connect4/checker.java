package Connect4;
import java.util.*;
public class checker {
  public int evaluate(gamePlay game) {
    List<int[]> validPositions = this.generatePositions(game.board);
    boolean flag2 = true;
    for(int[] m : validPositions) {
      int flag = this.checkWinner(game.board, m);
      if (flag == 1 || flag == -1)
        return flag;
      if (m[0] != 0)
        flag2 = false;
    }
    if (flag2)
      return 0;// the game is a tie
    return 2; // game still on
  }

  int checkWinner(int[][] gameState, int[] m) {
    int flag;
    int i = m[0];
    int j = m[1];
    flag = this.checkLeft(i,j,gameState, 0);
    if (flag == 1||flag == -1)
      return flag;
    flag = this.checkRight(i,j,gameState, 0);
    if (flag == 1||flag == -1)
      return flag;
    flag = this.checkBelow(i,j,gameState, 0);
    if (flag == 1||flag == -1)
      return flag;
    flag = this.checkLeftBelow(i,j,gameState, 0);
    if (flag == 1||flag == -1)
      return flag;
    flag = this.checkRightBelow(i,j,gameState, 0);
    if (flag == 1||flag == -1)
      return flag;
    return 0;
  }

  int checkLeft(int x, int y, int[][] gameState, int flag) {
    try {
      while(gameState[x][y] == gameState[x][y+1]) {
        y++;
      }
    }
    catch(IndexOutOfBoundsException e) {
      x = x + 0;
    }
    int sum = 0;
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x][y-i];
          // System.out.println(gameState[x-i][y]+"!");
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
      // System.out.println("y :"+y+" sum : "+sum);
			if (sum == 4 || sum == -4)
        if (flag == 0)
          return (sum/4);
        else
          return sum;
		return 0;
	}

	int checkRight(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
    try {
      while(gameState[x][y] == gameState[x][y-1]) {
        y--;
      }
    }
    catch(IndexOutOfBoundsException e) {
      x = x + 0;
    }
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x][y+i];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
			if (sum == 4 || sum == -4)
        if (flag == 0)
          return (sum/4);
        else
          return sum;
  		return 0;
	}

	int checkBelow(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x+i][y];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
			if (sum == 4 || sum == -4)
        if (flag == 0)
          return (sum/4);
        else
          return sum;
  		return 0;
	}

	int checkRightBelow(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
    try {
      while(gameState[x][y] == gameState[x-1][y-1]) {
        x--;
        y--;
      }
    }
    catch(IndexOutOfBoundsException e) {
      x = x + 0;
    }
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x+i][y+i];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
			if (sum == 4 || sum == -4)
        if (flag == 0)
          return (sum/4);
        else
          return sum;
		return 0;
	}

	int checkLeftBelow(int x, int y, int[][] gameState, int flag) {
		int sum = 0;
    try {
      while(gameState[x][y] == gameState[x-1][y+1]) {
        y++;
        x--;
      }
    }
    catch(IndexOutOfBoundsException e) {
      x = x + 0;
    }
			for(int i = 0; i < 4; i++) {
				try {
					sum = sum + gameState[x+i][y-i];
				}
				catch(IndexOutOfBoundsException e) {
					sum = sum + 0;
				}
			}
			if (sum == 4 || sum == -4)
        if (flag == 0)
          return (sum/4);
        else
          return sum;
  		return 0;
	}

  List<int[]> generatePositions(int[][] gameState){
    List<int[]> temp = new ArrayList<int[]>();
    for (int col = 0; col < 7; col++) {
      int i;
      for(i = 0; i < 6; i++)
  			if (gameState[i][col] != 0)
  				break;
  		i++;

  		if (i != 7) {
  			temp.add(new int[] {i-1 , col});
      }
  		else
        continue;
    }
    return temp;
  }

}
