package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

/**
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
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
