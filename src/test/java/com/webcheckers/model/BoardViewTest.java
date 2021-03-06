package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by wor3835 on 11/7/2017.
 */
public class BoardViewTest {

    /**
     * Variables
     */
    private BoardView CuT;

    private Board board;
    private BoardView boardView;
    private Request request;
    private Session session;

    /**
     * Sets up the mock objects and the CuT
     */
    @Before
    public void setup() {

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        CuT = new BoardView(MasterEnum.Color.RED);
    }

    /**
     * Test for the makeView() function
     */
    @Test
    public void makeView() {
        BoardView bv = new BoardView(MasterEnum.Color.RED);
        assertNotNull(bv);
        BoardView bv2 = new BoardView(bv.getBoard());
        assertNotNull(bv2);
    }

    /**
     * Test for the getBoard() function
     */
    @Test
    public void getBoard() {
        Board b = CuT.getBoard();
        assertNotNull(b);
    }

    /**
     * Test for the Iterator
     */
    @Test
    public void iterator_test(){
        assertNotNull(CuT.iterator());
    }
}
