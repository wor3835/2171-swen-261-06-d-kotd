package com.webcheckers.model;

import com.webcheckers.appl.Game;

import spark.*;

import static org.junit.Assert.*;
import static spark.Spark.halt;

import java.util.*;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

/**
 * The unit test suite for the {@link Player} component.
 *
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */


public class PlayerTest {

    private Player CuT;

    private String NAME_FOR_TEST;
    private String OTHER_NAME_FOR_TEST;
    private Request request;
    private Session session;
    private boolean inGame;
    private Game game;

    @Before
    public void setup() {
        //names to test the set name functionality
        NAME_FOR_TEST = "Bobby";
        OTHER_NAME_FOR_TEST = "Will";

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);
        inGame = false;
        game = mock(Game.class);

        // create a unique CuT for each test
        CuT = new Player(NAME_FOR_TEST);
    }

    //tests whether an unnamed player can be assigned a name
    @Test
    public void set_new_name(){
        assertNotNull(CuT.getName());
        assertEquals(NAME_FOR_TEST, CuT.getName());
        assertEquals(Boolean.FALSE, CuT.isInGame());
    }

    //tests if a player can be assigned a game
    @Test
    public void game_test(){
        assertNotNull(CuT.getName());
        assertEquals(Boolean.FALSE, CuT.isInGame());
        CuT.assignGame(game);
        assertEquals(Boolean.TRUE, CuT.isInGame());
        assertNotNull(CuT.getGame());
        CuT.leaveGame();
        assertFalse(CuT.isInGame());
        assertNull(CuT.getGame());
    }

    //tests the equals method
    @Test
    public void equals_test(){
        assertNotNull(CuT);
        ArrayList<String> TEST_LIST = new ArrayList();
        assertFalse(CuT.equals(TEST_LIST));
        assertTrue(CuT.equals(CuT));
    }
}
