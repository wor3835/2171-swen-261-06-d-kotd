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
 * Created by Bobby on 11/7/2017.
 */
public class RowTest {

    /**
     * Variables
     */
    private int index;

    private Row CuT;

    private Board board;

    /***
     * Sets up the mock objects
     */
    @Before
    public void setup() {
        index = 0;

        //set up mock objects
        board = mock(Board.class);

        // create a unique CuT for each test
        CuT = new Row(0, board);
    }

    /**
     * Test for the getIndex function
     */
    @Test
    public void indexTest(){
        assertEquals(0, CuT.getIndex());
    }

    /**
     * Test for the iterator
     */
    @Test
    public void iteratorTest(){
        assertNotNull(CuT.iterator());
    }
}
