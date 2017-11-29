package com.webcheckers.appl;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import com.webcheckers.model.Position;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import java.util.ArrayList;

/**
 * The unit test suite for the {@link Game} component.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */

public class GameTest {

    // set up Game for all test
    private Game CuT;

    private Request request;
    private Session session;
    private boolean inGame;
    private Game game;

    @Before
    /**
     * set up test environment
     */
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        inGame = false;
        game = mock(Game.class);
        CuT = new Game();
    }
    @Test
    /**
     * Tests to make sure an empty game can be initialized
     */
    public void createGame() {
        //final Game CuT = new Game();

        final Player player1 = new Player("player1");
        final Player player2 = new Player("player2");

        CuT.applyGame(player1, player2); // assigns the players to the game

        assertEquals(MasterEnum.Color.RED, CuT.getP1color());
        assertEquals(MasterEnum.Color.WHITE, CuT.getP2color());


        // checks if names are assigned to players in game
        assertTrue(CuT.getP1().getName().equals(player1.getName()));
        assertTrue(CuT.getP2().getName().equals(player2.getName()));

        // makes sure moves list is empty
        assertEquals(0, CuT.movesListSize());

        // makes sure boards are empty
        assertNull(CuT.getB1());
        assertNull(CuT.getB2());

        /**
         * Heres an example of a failed game. Player 3 is already in a game.
         */

        final Player player3 = new Player("player3");
        final Player player4 = new Player("player4");
        final Player player5 = new Player("player5");

        final Game test = new Game();
        test.applyGame(player3, player5);
        assertEquals(true, player5.isInGame());

        assertEquals(false, CuT.applyGame(player4, player5));
    }

    @Test
    /**
     * Makes sure boards can be set for each player
     */
    public void setBoards() {
        //final Game CuT = new Game();

        final Player player1 = new Player("player1");
        final Player player2 = new Player("player2");

        CuT.applyGame(player1, player2); // assigns the players to the game

        Board b1 = new Board(MasterEnum.Color.RED);
        Board b2 = new Board(MasterEnum.Color.WHITE);

        CuT.applyBoard(b1, b2);

        // makes sure boards are not null
        assertNotNull(CuT.getB1());
        assertNotNull(CuT.getB2());

        // make a move
        CuT.addMove(new Move(new Position(0,0), new Position(1, 2)));

        assertNotNull(CuT.getMove(CuT.movesListSize()-1));
    }

    @Test
    /**
     * Setting a single board
     */
    public void setBoard(){
        Board b1 = new Board();
        CuT.applyBoard(b1);

        assertNotNull(CuT.getB());
    }

    @Test
    /**
     * Makes sure you can choose the active color
     */
    public void activeColor() {
        //final Game CuT = new Game();

        final Player player1 = new Player("player1");
        final Player player2 = new Player("player2");

        CuT.applyGame(player1, player2); // assigns the players to the game

        assertEquals(MasterEnum.Color.RED, CuT.getActiveColor()); // active color should start off as red

        CuT.switchActive(); // switch active color

        assertEquals(MasterEnum.Color.WHITE, CuT.getActiveColor());

        CuT.switchActive();

        assertEquals(MasterEnum.Color.RED, CuT.getActiveColor());
    }

    @Test(expected = RuntimeException.class)
    /**
     * tests to make sure games actually end
     */
    public void endingGame(){
        final Player player1 = new Player("player1");
        final Player player2 = new Player("player2");

        CuT.applyGame(player1, player2);

        assertNotNull(player1.getGame());
        assertNotNull(player2.getGame());
        
        CuT.endGame(MasterEnum.GameStatus.OVER, null);

        assertTrue(CuT.getResigner()==null);
        assertTrue(CuT.getStatus()==MasterEnum.GameStatus.OVER);


        //assertNull(player1.getGame());
        //assertNull(player2.getGame());

        assertTrue(CuT.isGameOver());
    }

    @Test (expected = RuntimeException.class)
    /**
     * Test to see if game throws exception when ending and ended game
     */
    public void endEndedGame(){
        CuT.endGame(MasterEnum.GameStatus.OVER, null);
        assertTrue(CuT.isGameOver());
        CuT.endGame(MasterEnum.GameStatus.OVER, null);
    }

    @Test (expected = RuntimeException.class)
    /**
     * Test winner setting/getting
     */
    public void testWinner(){
        String winner = "winner";
        CuT.setWinner(winner);
        assertEquals(CuT.getWinner(), winner);
        CuT.setWinner(winner);
    }

    @Test
    /**
     * Test to see spectator functionality
     */
    public void spectator(){
        final Player spec = new Player("spec");

        CuT.addSpectator(spec);

        assertTrue(CuT.removeSpecator(spec));
    }

    @Test
    /**
     * tests if two Games can be compared to eachother
     */
    public void equalTest(){
        final Player player1 = new Player("player1");
        final Player player2 = new Player("player2");

        CuT.applyGame(player1, player2);

        assertTrue(CuT.equals(CuT));

        assertFalse(CuT.equals(new ArrayList<>()));
    }
}
