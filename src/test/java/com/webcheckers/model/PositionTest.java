package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/6/2017.
 */
public class PositionTest {
    private Position CuT;

    private Position position;
    private Request request;
    private Session session;

    @Before
    public void setup() {
        position = new Position(0,0);

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        CuT = new Position(0,0);
    }

    @Test
    public void GetRow() {
        int row = CuT.getRow();
        assertEquals(0, row);
    }

    @Test
    public void GetCol() {
        int col = CuT.getCol();
        assertEquals(0, col);
    }

    @Test
    public void testEquals() {
         Position p = new Position(0,0);
         boolean e = CuT.equals(p);
         assertEquals(true, e);
    }
}
