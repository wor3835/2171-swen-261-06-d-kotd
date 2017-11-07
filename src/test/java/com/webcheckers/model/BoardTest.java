package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kzalb on 11/5/2017.
 */
public class BoardTest {

    private Board CuT;

    private Board Board_to_test;
    private Space Board_Space_to_test;
    private Request request;
    private Session session;

    @Before
    public void setup() {

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        CuT = new Board(MasterEnum.Color.RED);
    }

    @Test
    public void TestGetMoves()
    {
        //create a new player and assign the it to the test board
        Player playTest = new Player("player1");
        playTest.assignPos(CuT, MasterEnum.Color.RED);

        //since recreating a full list of moves without using the method would be awful,
        //this test only makes sure the returned moves list is not null
        assertNotNull(CuT.getMoves(playTest.getPosList()));
    }

    @Test
    public void TestMakeMove()
    {
        //create the player, move to be applied for testing, and assign player to the test board
        Player playTest = new Player("player1");
        playTest.assignPos(CuT, MasterEnum.Color.RED);
        Move moveTest = new Move(new Position(5,4), new Position(4, 3));

        //make sure that the piece is at the start position and the end position is free
        assertNotNull(CuT.getPieceAt(5,4));
        assertNull(CuT.getPieceAt(4,3));

        CuT.makeMove(moveTest, playTest);

        //make sure that the piece is now in the end position and the start position is free
        assertNull(CuT.getPieceAt(5,4));
        assertNotNull(CuT.getPieceAt(4,3));

    }

    @Test
    public void TestInverseMove()
    {
        //create the player, move to be applied for testing, and assign player to the test board
        Player playTest = new Player("player1");
        playTest.assignPos(CuT, MasterEnum.Color.RED);
        Move moveTest = new Move(new Position(5,4), new Position(4, 3));

        //make sure the start position is occupied and the end position is free
        assertNotNull(CuT.getPieceAt(2, 3));
        assertNull(CuT.getPieceAt(3, 4));

        CuT.inverseMove(moveTest, playTest);

        //make sure the piece is now in the end position and the start position is free
        assertNull(CuT.getPieceAt(2,3));
        assertNotNull(CuT.getPieceAt(3,4));
    }

    @Test
    public void TestGetPieceAt()
    {
        //red piece only created to make sure get piece returns what we expect it to
        final Pawn piece = new Pawn(MasterEnum.Color.RED);

        //make sure board is not null
        assertNotNull(CuT);

        //5,4 is a position in board where we expect a red piece to be
        assertEquals(piece, CuT.getPieceAt(5, 4));
    }

    @Test
    public void TestGetSpaceAt()
    {
        //this space is located at 5,4, it at the start of a game it shouldn't be valid, it's piece should be a red pawn
        Space test = new Space(4, false, new Pawn(MasterEnum.Color.RED));

        //make sure board is not null
        assertNotNull(CuT);

        //make sure the space we are looking for exists
        assertNotNull(CuT.getSpaceAt(5, 4));

        //we expect our test space to be the same as the space returned by this getSpaceAt call
        assertEquals(test, CuT.getSpaceAt(5,4));
    }

}
