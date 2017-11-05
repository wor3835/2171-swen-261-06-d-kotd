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
public class SpaceTest {

    private Space CuT;

    private Space Space_to_test;
    private Space Other_Space_to_test;
    private Request request;
    private Session session;

    @Before
    public void setup() {

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        Pawn piece = new Pawn(null);
        CuT = new Space(0, false,  piece);
    }

    @Test
    public void TestGetCellIdx()
    {
        CuT.getCellIdx();
        assertNotNull(CuT.getCellIdx());
        assertEquals(0, CuT.getCellIdx());
    }

    @Test
    public void TestIsValid()
    {
        assertNotNull(CuT.isValid());
        assertEquals(false, CuT.isValid());
        assertEquals(true, !CuT.isValid());
    }

    @Test
    public void TestGetPiece()
    {
        Pawn piece = new Pawn(null);
        assertNotNull(CuT.getPiece());
        assertEquals(piece, CuT.getPiece());
    }

    @Test
    public void TestKingMe()
    {
        final Pawn piece = new Pawn(null);
        final King king = new King(piece);
        CuT.kingMe();
        assertEquals(king.getType(), CuT.getPiece().getType());
    }

    @Test
    public void TestValidMoves()
    {
        final Board b = new Board(MasterEnum.Color.RED);
        final Position start = new Position(0, 0);
        final Pawn piece = new Pawn(MasterEnum.Color.RED);
        final Space space = new Space(0, false, piece);
        assertEquals(space.validMoves(b, start), CuT.validMoves(b, start));
    }

    @Test
    public void TestValidJumps()
    {

    }



}
