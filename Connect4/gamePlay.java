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
		System.out.println();
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
		for(i = 0; i < 6; i++) {
			if (this.board[i][move] != 0)
				break;
		}
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
		game.samplePlay();
		game.printState();
		System.out.println(game.checkWin()+" ");
		System.out.println(atom.heuristicFunc(game)+" ");
		System.out.println();
		List<int[]> temp = atom.generateMoves(game,1);
		for (int[] m : temp)
			System.out.println(m[0]+"");
		atom.rearrangeMoves(game, temp, 1);
		int[][] a = new int[7][2];
		game.setValue(a);
		for (int k = 0; k < 7 ; k++)
			System.out.println(a[k][0]+" "+a[k][1]);
		atom.QuickSort(a,0,6);
		for (int k = 0; k < 7 ; k++)
			System.out.println(a[k][0]+" "+a[k][1]);
	}

	void samplePlay() {
		this.makePlay(-1,1);
		this.makePlay(1,0);
		this.makePlay(-1,1);
		this.makePlay(1,0);
		this.makePlay(-1,1);
		this.makePlay(1,1);
	}

	void setValue(int[][] a) {
		a[0][0] = -1000;
		a[0][1] = 0;
		a[1][0] = 500;
		a[1][1] = 1;
		a[2][0] = -50;
		a[2][1] = 2;
		a[3][0] = -10000;
		a[3][1] = 3;
		a[4][0] = 2;
		a[4][1] = 4;
		a[5][0] = 700;
		a[5][1] = 5;
		a[6][0] = -20000;
		a[6][1] = 6;
	}
}
