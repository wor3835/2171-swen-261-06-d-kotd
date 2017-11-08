package com.webcheckers.appl;

import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/1/2017.
 * Tests GameLobby
 */
public class GameLobbyTest {

    // set up GameLobby for all tests
    private GameLobby CuT;

    private Request request;
    private Session session;
    private GameLobby game;

    @Before
    /**
     * sets up testing environment
     */
    public void setup() {
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        game = mock(GameLobby.class);
        CuT = new GameLobby();
    }

    @Test
    /**
     * checks to see if lobby gets added to gamesList
     */
    public void createLobby() {
        final Game g = new Game();

        CuT.addGame(g);
        assertEquals(1, CuT.getGamesList().size());
    }

    @Test
    /**
     * removes player from game and checks removed name in gamesList
     */
    public void removeGame() {
        final Game g = new Game();

        Player player1 = new Player("player1");
        Player player2 = new Player("player2");

        g.applyGame(player1, player2);

        assertFalse(CuT.getGamesList().contains(g));

        CuT.addGame(g);

        assertTrue(CuT.getGamesList().contains(g));

        CuT.removeGame(g);

        assertFalse(CuT.getGamesList().contains(g));
    }

    @Test
    /**
     * checks if player is in a game
     */
    public void inGame() {
        final Game g = new Game();
        final Player p1 = new Player("player1");
        final Player p2 = new Player("player2");

        g.applyGame(p1, p2);

        CuT.addGame(g);

        assertNotNull(CuT.inGame(p1));
        assertNotNull(CuT.inGame(p2));

        assertNull(CuT.inGame(new Player("playerX")));

        final Game g1 = new Game();
        final Player p3 = new Player("player3");
        final Player p4 = new Player("player4");

        g.applyGame(p3, p4);

        CuT.addGame(g1);

        assertNotNull(CuT.inGame(p3));
        assertNotNull(CuT.inGame(p4));
    }
}
