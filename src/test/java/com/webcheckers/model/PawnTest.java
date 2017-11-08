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
 * Created by wor3835 on 11/7/2017.
 */
public class PawnTest {
    /**
     * Variables
     */
    private Pawn CuT;

    private Pawn pawn;
    private Request request;
    private Session session;

    /**
     * Sets up the mock objects and the CuT
     */
    @Before
    public void setup() {
        pawn = new Pawn(MasterEnum.Color.RED);

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        CuT = new Pawn(MasterEnum.Color.RED);
    }

    /**
     * Test for the makePawn() function
     */
    @Test
    public void makePawn() {
        Pawn p = new Pawn(MasterEnum.Color.WHITE);
        assertNotNull(p);
    }

    /**
     * Test for the getColor() function
     */
    @Test
    public void getColor() {
        assertEquals(MasterEnum.Color.RED, CuT.getColor());
    }

    /**
     * Test for the getType() function
     */
    @Test
    public void getType() {
        assertEquals(MasterEnum.PieceType.SINGLE, CuT.getType());
    }
}
