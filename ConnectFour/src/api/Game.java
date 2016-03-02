package api;

import exc.GameStateException;
import exc.IllegalMoveException;

import java.util.Observable;

public abstract class Game extends Observable {
    public enum Token {
        RED,
        BLUE,
        EMPTY,
    }
    private int rows;
    private int columns;
    protected Token[][] surface;

    public Game(int rows, int columns) {
	this.rows = rows;
	this.columns = columns;
    }
    
    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Token[][] getBoard() {
        return surface;
    }

    public abstract void placeDisk(int col) throws GameStateException,
						   IllegalMoveException;
    
    public abstract Token currentPlayer();
    public abstract Token getWinner() throws GameStateException;
    public abstract Token nextPlayer() throws GameStateException;
    public abstract boolean isGameOver();
}
