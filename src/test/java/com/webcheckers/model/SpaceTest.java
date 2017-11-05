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
        CuT = new Space(0, false, null);
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
    }

    @Test
    public void TestGetPiece()
    {
        //assertNotNull(CuT.getPiece());
        assertEquals(null, CuT.getPiece());
    }
}
