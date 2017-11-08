package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

/**
 * Created by Bobby on 10/25/2017.
 */
public class Pawn extends Piece{
    /**
     * Sets color and type of piece
     *
     * @param color red or black
     */
    public Pawn(MasterEnum.Color color) {
        super(color, MasterEnum.PieceType.SINGLE);
    }

    /**
     *
     * @return the color of the piece
     */
    public MasterEnum.Color getColor()
    {
        return super.getColor();
    }

    /**
     *
     * @return the type of the piece
     */
    public MasterEnum.PieceType getType()
    {
        return super.getType();
    }

}
