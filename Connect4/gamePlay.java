package Connect4;
import java.util.*;
public class gamePlay {
	int[][] board = new int[6][7];

	gamePlay() {
		for(int i = 0; i < 6; i++ )
			for(int j = 0; j < 7; j++)
				this.board[i][j] = 0;
	}

	void printState() {
		for(int i = 0; i < 6; i++) {
			System.out.printf("| ");
			for(int j = 0; j < 7; j++ )
				System.out.printf(this.convert(this.board[i][j])+" | ");
			System.out.println();
		}
	}

	char convert(int a) {
		if (a == 1) return 'x';
		if (a == -1) return 'o';
		return '-';
	}

	int makePlay(int a, int move) {
		int i;
		for(i = 0; i < 6; i++)
			if (this.board[i][move] != 0)
				break;
		i--;
		if (i == -1)
			return -1;

		else
			this.board[i][move] = a;
		return 0;
	}

	int deletePlay(int move) {
		int i;
		for(i = 0; i < 6; i++)
			if (this.board[i][move] != 0)
				break;
		i--;
		if (i == -1)
			return -1;

		else
			this.board[i][move] = 0;
		return 0;
	}

	int checkWin() {
		checker myInit = new checker();
		return myInit.evaluate(this);
	}

	public static void main(String[] args) {
		gamePlay game = new gamePlay();
		AI atom = new AI();
		System.out.printf("x corresponds to 1%no corresponds to -1%n");
		game.printState();
		game.samplePlay();
		// game.makePlay(-1,6);
		System.out.println();
		game.printState();
		List<int[]> temp;
		temp = atom.generateMoves(game);
		for(int[] m : temp)
			System.out.println(m[0]+"	");
		System.out.println(game.checkWin()+" ");
		myEvaluator temp2 = new myEvaluator();
		System.out.println(" " + temp2.tellMe(game) + " ");
	}

	void samplePlay() {
		this.makePlay(1,0);
		this.makePlay(-1,1);
		this.makePlay(1,6);
		this.makePlay(-1,3);
		this.makePlay(1,5);
		this.makePlay(-1,2);
		this.makePlay(1,4);
		this.makePlay(-1,4);
		this.makePlay(1,6);
		this.makePlay(-1,6);
		this.makePlay(1,5);
		this.makePlay(-1,5);
		this.makePlay(1,3);
	}
}
