package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

    }

    @Test
    public void TestMakeMove()
    {

    }

    @Test
    public void TestInverseMove()
    {

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
