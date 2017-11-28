package com.webcheckers.appl;

/**
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 *
 * This class manages all the enums used in the WebCheckers application
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

    //Game status enums
    public enum GameStatus{
        PLAYING, OVER, RESIGN, SIGNOUT
    }
}
