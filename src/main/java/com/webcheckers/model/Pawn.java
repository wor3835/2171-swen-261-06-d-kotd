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
        range = 1;
    }

    public MasterEnum.Color getColor()
    {
        return super.getColor();
    }

    public MasterEnum.PieceType getType()
    {
        return super.getType();
    }

}
