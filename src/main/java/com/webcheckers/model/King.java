package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;

/**
 * Created by Bobby on 10/25/2017.
 */
public class King extends Piece {


    /**
     * Sets color and type of piece
     *
     * @param color red or black
     */
    public King(MasterEnum.Color color) {
        super(color, MasterEnum.PieceType.KING);
        range = 7;
    }

    public King(Piece p){
        super(p.getColor(), MasterEnum.PieceType.KING);
        range = 7;
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
