package com.webcheckers.model;
import com.webcheckers.appl.MasterEnum;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

/**
 * The unit test suite for the {@link Game} component.
 *
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 */

public class GameTest {

    private Game CuT;

    private Request request;
    private Session session;
    private boolean inGame;
    private Game game;

    @Before
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

        // checks if names are assigned to players in game
        assertTrue(CuT.getP1().getName().equals(player1.getName()));
        assertTrue(CuT.getP2().getName().equals(player2.getName()));

        // makes sure moves list is empty
        assertEquals(0, CuT.movesList.size());

        // makes sure boards are empty
        assertNull(CuT.getB1());
        assertNull(CuT.getB2());
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
    }
}
