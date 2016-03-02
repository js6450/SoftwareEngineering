package impl;

import java.util.ArrayList;

import api.Game;
import api.Game.Token;
import exc.GameStateException;
import exc.IllegalMoveException;

public class ConnectFourGame extends Game{
	
	//current player token
	Token current;

	ArrayList<Token> players = new ArrayList<Token>();

	public ConnectFourGame() {
		//create game of 6 rows and 9 columns
		super(6,9);
		//initialize surface
		super.surface = new Token[super.getRows()][super.getColumns()];
		//initialize surface with empty tokens
		for(int i = 0; i < super.getRows(); i++){
			for(int j = 0; j < super.getColumns(); j++){
				super.surface[i][j] = Token.EMPTY;
			}
		}
		
		players.add(Token.RED);
		players.add(Token.BLUE);
		
		//set current player
		current = players.listIterator().next();
	}

	@Override
	public void placeDisk(int col) throws GameStateException, IllegalMoveException {
		// catch out of bounds and negative input
		if(col >= super.getColumns() || col < 0){
			throw new IllegalMoveException();
		}
		
		//place disk only when Token is EMPTY
		for(int i = 0; i < super.getRows(); i++){
			if(super.surface[i][col] == Token.EMPTY){
				super.surface[i][col] = current;
				//change current player only if no player has won this round
				if(!isGameOver()){
				current = nextPlayer();
				}
				return;
			}
		}
		
		throw new IllegalMoveException();
	}

	@Override
	public Token currentPlayer() {
		return current;
	}

	@Override
	public Token getWinner() throws GameStateException {
		return current;
	}

	@Override
	public Token nextPlayer() throws GameStateException {
		if(current == Token.RED){
			return Token.BLUE;
		}
		else{
			return Token.RED;
		}
	}

	//check the next four spaces in the row to the right
	private boolean checkRow(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
		//don't check for anything if column number is higher than or equal to than 6
		if(col >=6){
			return false;
		}
		for(int i = 0; i < 4; i++){
			if(surface[row][col+i] == token){
				counter++;
			}
		}
		
		if(counter == 4){
			return true;
		}
		else{
			return false;
		}
	}
	
	//check the top next four space in the column
	private boolean checkCol(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
		//don't check for anything if the row is higher than or equal to 3
		if(row >= 3){
			return false;
		}
		for(int i = 0; i < 4; i++){
			if(surface[row+i][col] == token){
				counter++;
			}
		}
	
		if(counter == 4){
			return true;
		}
		else{
			return false;
		}
	}
	
	//check the next four spaces in the diagonal right
	private boolean checkDiRight(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
		//set boundary to prevent overflow
		if(row >= 3 || col >= 6){
			return false;
		}
		for(int i = 0; i < 4; i++){
			if(surface[row+i][col+i] == token){
				counter++;
			}
		}
		if(counter == 4){
			return true;
		}
		else{
			return false;
		}
	}
	
	//check the next four spaces in the diagonal left
	private boolean checkDiLeft(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
		//set boundary to prevent overflow
		if(row >= 2 || col <= 2){
			return false;
		}
		for(int i = 0; i < 4; i++){
			if(surface[row+i][col-i] == token){
				counter++;
			}
		}
		if(counter == 4){
			return true;
		}
		else{
			return false;
		}
	}
	
	@Override
	public boolean isGameOver() {
		for(int i = 0; i < super.getRows(); i++){
			for(int j = 0; j < super.getColumns(); j++){
				//only check for spaces with disk
				if(super.surface[i][j] != Token.EMPTY){
					if(checkRow(i, j)){
						return true;
					}
					if(checkCol(i, j)){
							return true;
						}
					if(checkDiRight(i, j)){
						return true;
					}
					if(checkDiLeft(i, j)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
