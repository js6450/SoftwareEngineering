package test.unit.jsw7;

import api.Game;
import api.Game.Token;
import exc.GameStateException;
import exc.IllegalMoveException;
import impl.ConnectFourGame;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ConnectFourGameTest {
    @Test
    public void testInitSetsPlayer() {
        Game game = new ConnectFourGame();
        assertNotEquals(game.currentPlayer(), Token.EMPTY);
    }

    @Test
    public void testInitBlanksBoard() {
        Game game = new ConnectFourGame();
        Token[][] surface = game.getBoard();

        for (int row = 0; row < game.getRows(); row++) {
            for (int col = 0; col < game.getColumns(); col++) {
                assertEquals(surface[row][col], Token.EMPTY);
            }
        }
    }

    @Test
    public void testInitSetsRows() {
        Game game = new ConnectFourGame();
        assertEquals(game.getRows(), 6);
    }

    @Test
    public void testInitSetsColumns() {
        Game game = new ConnectFourGame();
        assertEquals(game.getColumns(), 9);
    }

    @Test
    public void testNextPlayerSetAfterPlacedDisk()
        throws GameStateException,
               IllegalMoveException {
        Game game = new ConnectFourGame();
        
        Token firstPlayer = game.currentPlayer();
        game.placeDisk(0);
        Token secondPlayer = game.currentPlayer();
        
        assertNotEquals(firstPlayer, secondPlayer);
    }

    @Test(expected = IllegalMoveException.class)
    public void testDisksCannotBePlacedOutOfBounds()
        throws GameStateException,
               IllegalMoveException {
        Game game = new ConnectFourGame();
        
        Token firstPlayer = game.currentPlayer();
        game.placeDisk(game.getColumns() + 1);
    }

    @Test(expected = IllegalMoveException.class)
    public void testDisksCannotBePlacedNegatively()
        throws GameStateException,
               IllegalMoveException {
        Game game = new ConnectFourGame();
        
        Token firstPlayer = game.currentPlayer();
        game.placeDisk(-1);
    }
    
    @Test(expected = IllegalMoveException.class)
    public void testColumnPlacementBounded() throws GameStateException,
                                                    IllegalMoveException {
        Game game = new ConnectFourGame();

        for (int i = 0; i < game.getRows() + 1; i++) {
            game.placeDisk(0);
        }
    }
    
    @Test
    public void testVerticalWinnerFound() throws GameStateException,
                                                 IllegalMoveException {
        Game game = new ConnectFourGame();

        for (int i = 0; i < 7; i++) {
            game.placeDisk(i % 2);
        }
        
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHorizontalWinnerFound() throws GameStateException,
                                                   IllegalMoveException {
        Game game = new ConnectFourGame();

        for (int i = 0; i < 4; i++) {
            int stop = (i < 3) ? 2 : 1;
            for (int j = 0; j < stop; j++) {
                game.placeDisk(i);
            }
        }
        
        assertTrue(game.isGameOver());
    }
}
