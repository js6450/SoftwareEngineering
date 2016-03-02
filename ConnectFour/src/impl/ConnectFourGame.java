package impl;

import java.util.ArrayList;

import api.Game;
import api.Game.Token;
import exc.GameStateException;
import exc.IllegalMoveException;

public class ConnectFourGame extends Game{
	
	Token current;
	ArrayList<Token> players = new ArrayList<Token>();

	public ConnectFourGame() {
		//create game of 6 rows and 9 columns
		super(6,9);
		super.surface = new Token[super.getRows()][super.getColumns()];
		//initialize surface with empty tokens
		for(int i = 0; i < super.getRows(); i++){
			for(int j = 0; j < super.getColumns(); j++){
				super.surface[i][j] = Token.EMPTY;
			}
		}
		
		players.add(Token.RED);
		players.add(Token.BLUE);
		
		current = players.listIterator().next();
	}

	@Override
	public void placeDisk(int col) throws GameStateException, IllegalMoveException {
		// TODO Auto-generated method stub
		if(col >= super.getColumns() || col < 0){
			throw new IllegalMoveException();
		}
		
		for(int i = 0; i < super.getRows(); i++){
			if(super.surface[i][col] == Token.EMPTY){
				super.surface[i][col] = current;
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
		// TODO Auto-generated method stub
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

	private boolean checkRow(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
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
	
	private boolean checkCol(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
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
	
	private boolean checkDiRight(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
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
	
	private boolean checkDiLeft(int row, int col){
		Token token = surface[row][col];
		int counter = 0;
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
