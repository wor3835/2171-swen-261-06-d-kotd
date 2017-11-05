package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;

/**
 * Created by Bobby on 10/25/2017.
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class King extends Piece {


    /**
     * Sets color and type of piece to king
     *
     * @param color red or black
     */
    public King(MasterEnum.Color color) {
        super(color, MasterEnum.PieceType.KING);
    }

    /**
     * Copy constuctor, creates a king from an existing piece color
     * @param p The piece to be copied
     */
    public King(Piece p){
        super(p.getColor(), MasterEnum.PieceType.KING);
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
