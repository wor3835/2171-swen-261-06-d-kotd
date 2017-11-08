package com.webcheckers.model;

import com.webcheckers.appl.Game;

import com.webcheckers.appl.MasterEnum;
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

    /**
     * Variables
     */
    private Player CuT;

    private String NAME_FOR_TEST;
    private String OTHER_NAME_FOR_TEST;
    private Request request;
    private Session session;
    private boolean inGame;
    private Game game;

    /**
     * Sets up the mock objects and the CuT
     */
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
    @Test(expected = RuntimeException.class)
    public void game_test(){
        assertNotNull(CuT.getName());
        assertEquals(Boolean.FALSE, CuT.isInGame());
        CuT.assignGame(game);
        assertEquals(Boolean.TRUE, CuT.isInGame());
        assertNotNull(CuT.getGame());
        CuT.leaveGame();
        assertFalse(CuT.isInGame());
        assertNull(CuT.getGame());

        //tests whether the program allows a player to be assigned to multiple games at once
        CuT.assignGame(game);
        CuT.assignGame(game);
    }

    //tests the equals method
    @Test
    public void equals_test(){
        assertNotNull(CuT);
        ArrayList<String> TEST_LIST = new ArrayList();
        assertFalse(CuT.equals(TEST_LIST));
        assertTrue(CuT.equals(CuT));
    }

    //test if a piece is removed from a player's list
    @Test
    public void remove_piece_test(){
        //create board and assign player to it
        Board boardTest = new Board(MasterEnum.Color.RED);
        CuT.assignPos(boardTest, MasterEnum.Color.RED);

        //make sure the player's board contains a piece at 5,4
        assertTrue(CuT.getPosList().contains(new Position(5, 4)));

        //remove the piece
        CuT.removePiece(new Position(5, 4));

        int x = CuT.getPosList().size();
        //make sure the player's board no longer contains the piece
        assertFalse(CuT.getPosList().contains(new Position(5,4)));

        //makes sure nothing happens if remove piece is called on an empty position
        CuT.removePiece(new Position(5,4));
        assertTrue(x == CuT.getPosList().size());
    }
}
