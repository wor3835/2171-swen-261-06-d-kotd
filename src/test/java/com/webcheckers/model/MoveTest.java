package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by arthu on 11/7/2017.
 */
public class MoveTest {
    /**
     * Variables
     */
    private Move CuT;

    private Move m2;
    private Move m3;
    private Position p1;
    private Position p2;
    private Position p3;

    /**
     * Sets up objects
     */
    @Before
    public void set_up(){
        p1 = new Position(0,0);
        p2 = new Position(1,1);
        p3 = new Position(2,2);

        m2 = new Move(p2,p3);
        m3 = new Move(p1,p3);
    }

    /**
     * Test for the makeMove() function
     */
    @Test
    public void make_move(){
        CuT = new Move(p1,p2);
        assertNotNull(CuT);
        assertEquals(p1, CuT.getStart());
        assertEquals(p2, CuT.getEnd());
    }

    /**
     * Test for the equals function
     */
    @Test
    public void test_equals(){
        CuT = new Move(p1,p2, m2);
        assertEquals(CuT, m3);

        CuT = new Move(p2, p3);
        assertEquals(CuT, m2);

        assertFalse(CuT.equals(new ArrayList<>()));
    }

    /**
     * Test for the getFinalMove() function
     */
    @Test
    public void get_final_test(){
        CuT = new Move(p1,p2);
        assertEquals(CuT.getFinal(), p2);
        CuT = new Move(p1,p2, m2);
        assertEquals(CuT.getFinal(), p3);
    }

    /**
     * Test for the getMove() function
     */
    @Test
    public void get_move_test() {
        CuT = new Move(p1,p2,m2);
        assertEquals(m2, CuT.getMove());
    }

}
