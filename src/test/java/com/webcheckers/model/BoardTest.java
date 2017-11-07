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
    public void TestInit()
    {

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
        final Pawn piece = new Pawn(null);
        assertNotNull(CuT);
        assertEquals(piece, CuT.getPieceAt(0, 0));
    }

    @Test
    public void TestGetSpaceAt()
    {

    }

    @Test
    public void TestHasSpace()
    {

    }

}
