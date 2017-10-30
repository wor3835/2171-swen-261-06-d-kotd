package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Session;

import javax.print.attribute.standard.MediaSize;
import javax.swing.text.DefaultEditorKit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by kzalb on 10/29/2017.
 */
public class PieceTest {

    /*
    Piece - getting the type of the piece works, getting the color of the piece works
            take 2 pieces I know are the same and see if equals works and 2 pieces I know are different and see if equals works
 */
    private Piece CuT;

    private Pawn Piece_to_test;
    private King Other_Piece_to_test;
    private Request request;
    private Session session;
    //private Piece piece;

    @Before
    public void setup() {
        //names to test the set name functionality
        Piece_to_test = new Pawn(MasterEnum.Color.RED);
        Other_Piece_to_test = new King(MasterEnum.Color.WHITE);

        //set up mock objects
        request = mock(Request.class);
        session = mock(Session.class);
        when(request.session()).thenReturn(session);


        // create a unique CuT for each test
        CuT = new Pawn(MasterEnum.Color.RED);
    }

    @Test
    public void check_color(){
        CuT.getColor();
        assertNotNull(CuT.getColor());
        assertEquals(Piece_to_test.getColor(), CuT.getColor());
    }

    @Test
    public void check_type(){
        CuT.getType();
        assertNotNull(CuT.getType());
        assertEquals(Piece_to_test.getType(), CuT.getType());
    }

    @Test
    public void check_equals(){
        assertEquals(false, CuT.equals(Other_Piece_to_test));
    }
}
