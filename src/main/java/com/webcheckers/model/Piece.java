package com.webcheckers.model;


import com.webcheckers.appl.MasterEnum;

/**
 * Created by kzalb on 10/15/2017.
 * Represents a piece that goes on game space.
 */
public abstract class Piece {

    private MasterEnum.PieceType type;
    private MasterEnum.Color color;

    /**
     * Sets color and type of piece
     * @param color red or black
     * @param type normal or king
     */
    public Piece(MasterEnum.Color color, MasterEnum.PieceType type)
    {
        this.type = type;
        this.color = color;
    }

    /**
     * @return type of piece
     */
    public MasterEnum.PieceType getType()
    {
        return type;
    }

    /**
     * @return color of piece
     */
    public MasterEnum.Color getColor()
    {
        return color;
    }
}
