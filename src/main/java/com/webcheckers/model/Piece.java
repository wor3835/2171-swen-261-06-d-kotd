package com.webcheckers.model;



/**
 * Created by kzalb on 10/15/2017.
 * Represents a piece that goes on game space.
 */
public class Piece {

    /**
     * Describes if piece is single or king
     */
    public static enum Type{
        SINGLE,
        KING
    }

    /**
     * Describes piece color
     */
    public static enum Color{
        RED,
        WHITE
    }

    private Type eType;
    private Color eColor;

    /**
     * Sets color and type of piece
     * @param color red or black
     * @param type normal or king
     */
    public Piece(Color color, Type type)
    {
        this.eType = type;
        this.eColor = color;
    }

    /**
     * @return type of piece
     */
    public Type getType()
    {
        return eType;
    }

    /**
     * @return color of piece
     */
    public Color getColor()
    {
        return eColor;
    }




}
