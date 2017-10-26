package com.webcheckers.appl;

/**
 * Created by Bobby on 10/25/2017.
 */
public class MasterEnum {
    //Message enum
    public enum MessageType {
        info,
        error
    }

    //Piece enums
    public enum PieceType{
        SINGLE,
        KING
    }

    public enum Color{
        RED,
        WHITE
    }

    //View enums
    public enum ViewMode {
        PLAY, SPECTATOR, REPLAY
    }
}