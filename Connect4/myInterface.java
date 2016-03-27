package Connect4;
import java.util.*;
public class myInterface {
  public static void main(String[] args) {
    gamePlay game = new gamePlay();
		AI atom = new AI();
		System.out.printf("x corresponds to 1%no corresponds to -1%n");
    System.out.printf("For Human vs Human enter 0,");
    System.out.println(" For Human vs Atom, enter 1:");
    Scanner user_input = new Scanner(System.in);
    int x;
    try {
      x = (int)user_input.nextInt();
    }
    catch (java.util.InputMismatchException e) {
      x = 2;
    }
    if (x == 0 || x == 1) {
      System.out.printf("To play, enter the column you want to insert :%n");
      game.printState();
    }
    else
      System.out.println("Invalid input.");
    if (x == 0)
      while(true) {
        // Human vs Human
        int player1 = 1, player2 = -1;
        int playerMove, situation;

        playerMove = (int)user_input.nextInt();
        game.makePlay(player1,playerMove);
        game.printState();
        situation = game.checkWin();
        if (situation == 1) {
          System.out.println("Player1 wins.");
          break;
        }
        else if (situation == 0) {
          System.out.println("It is a tie.");
          break;
        }

        playerMove = (int)user_input.nextInt();
        game.makePlay(player2,playerMove);
        game.printState();
        situation = game.checkWin();
        if (situation == -1) {
          System.out.println("Player2 wins.");
          break;
        }
        else if (situation == 0) {
          System.out.println("It is a tie.");
          break;
        }

      }
    else if (x == 1)
      while(true) {
        // Human vs Human
        int player1 = 1, player2 = -1;
        int playerMove, situation;

        playerMove = (int)user_input.nextInt();
        game.makePlay(player1,playerMove);
        game.printState();
        situation = game.checkWin();
        if (situation == 1) {
          System.out.println("Player1 wins.");
          break;
        }
        else if (situation == 0) {
          System.out.println("It is a tie.");
          break;
        }

        playerMove = (atom.minimax(game,1,7))[2];
        game.makePlay(player2,playerMove);
        game.printState();
        situation = game.checkWin();
        if (situation == -1) {
          System.out.println("Atom wins.");
          break;
        }
        else if (situation == 0) {
          System.out.println("It is a tie.");
          break;
        }
      }
  }
}
