package com.webcheckers.model;



/**
 * Created by kzalb on 10/15/2017.
 */
public class Piece {

    static enum Type{
        SINGLE,
        KING
    }

    static enum Color{
        RED,
        WHITE
    }

    private Type eType;
    private Color eColor;

    public Piece(Color color, Type type)
    {
        this.eType = type;
        this.eColor = color;
    }

    public Type getType()
    {
        return eType;
    }

    public Color getColor()
    {
        return eColor;
    }




}
