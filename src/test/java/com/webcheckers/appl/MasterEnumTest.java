package com.webcheckers.appl;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by wor3835 on 11/7/2017.
 */
public class MasterEnumTest {

    @Test
    public void messageType() {
        MasterEnum.MessageType error = MasterEnum.MessageType.error;
        assertNotNull(error);
    }

    @Test
    public void pieceColor() {
        MasterEnum.Color red = MasterEnum.Color.RED;
        assertNotNull(red);
    }

    @Test
    public void pieceType() {
        MasterEnum.PieceType type = MasterEnum.PieceType.SINGLE;
        assertNotNull(type);
    }

    @Test
    public void viewMode(){
        MasterEnum.ViewMode view = MasterEnum.ViewMode.PLAY;
        assertNotNull(view);
    }
}
