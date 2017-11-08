package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import javax.print.attribute.standard.MediaSize;
import javax.swing.text.DefaultEditorKit;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kzalb on 10/29/2017.
 */
public class PieceTest {

    /**
     * Variables
     */
    private Piece CuT;

    private Pawn Piece_to_test;
    private King Other_Piece_to_test;
    private Request request;
    private Session session;
    //private Piece piece;

    /**
     * Sets up the mock objects and the CuT
     */
    @Before
    public void setup() {
        Piece_to_test = new Pawn(MasterEnum.Color.RED);
        Other_Piece_to_test = new King(MasterEnum.Color.WHITE);

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        CuT = new Pawn(MasterEnum.Color.RED);
    }

    /**
     * Test for the getColor() function
     */
    @Test
    public void check_color(){
        CuT.getColor();
        assertNotNull(CuT.getColor());
        assertEquals(Piece_to_test.getColor(), CuT.getColor());
    }

    /**
     * Test for the getType() function
     */
    @Test
    public void check_type(){
        CuT.getType();
        assertNotNull(CuT.getType());
        assertEquals(Piece_to_test.getType(), CuT.getType());
    }

    /**
     * Test for the equals() function
     */
    @Test
    public void check_equals(){
        assertEquals(false, CuT.equals(Other_Piece_to_test));
        assertFalse(CuT.equals(new ArrayList<>()));
    }
}
