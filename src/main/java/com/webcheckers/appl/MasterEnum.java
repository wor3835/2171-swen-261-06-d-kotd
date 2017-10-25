package com.webcheckers.appl;

/**
 * Created by Bobby on 10/25/2017.
 */
public class MasterEnum {
    //Message enum
    public enum messageType {info, error}

    //Piece enums
    public static enum pieceType{
        SINGLE,
        KING
    }

    public static enum pieceColor{
        RED,
        WHITE
    }

    //GetGameRoute enums
    public enum getGameViewMode {
        PLAY, SPECTATOR, REPLAY
    }

    public enum getGameActiveColor {
        RED, WHITE
    }

    //PostStartRoute enums
    public enum startViewMode {
        PLAY, SPECTATOR, REPLAY
    }

    public enum startActiveColor {
        RED, WHITE
    }


}
