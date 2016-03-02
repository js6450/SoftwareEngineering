package test.system;

import api.View;

import java.util.InputMismatchException;
import java.util.Scanner;

import api.Game;
import api.Game.Token;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.ConnectFourGame;
import impl.ConnectFourView;

public class GameTest {
    public static void main(String[] args) throws InterruptedException, GameStateException, IllegalMoveException {

	Game game = new ConnectFourGame();
	View view = new ConnectFourView();
	
	//for user input
	Scanner reader = new Scanner(System.in); 
	
	game.addObserver(view);
	//render blank board
	view.render(game);
	
	while (!game.isGameOver()) {
		int column;
		System.out.println("Enter column number: ");
		column = reader.nextInt();
		try{
			game.placeDisk(column);
		}
		//check for illegal input
		catch(IllegalMoveException e){
			System.out.println("Illegal move. Please enter a different column number: ");
			column = reader.nextInt();
			game.placeDisk(column);
		}
		//check for non-numeric input
		catch(InputMismatchException e){
			System.out.println("Please enter a number of a column: ");
			column = reader.nextInt();
			game.placeDisk(column);
		}
	    view.render(game);
	}

		//check for winner when isGameOver is true
		try {
		    Token winner = game.getWinner();
		    String winnermessage = "Game over. The winner is " + winner;
		    System.out.println(winnermessage);
		}
		catch (GameStateException e) {
		    System.out.println("It was a tie!");
		}
    }
}
