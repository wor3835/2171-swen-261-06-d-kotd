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
     * @param type  normal or king
     */
    public King(MasterEnum.Color color, MasterEnum.PieceType type) {
        super(color, type);
        range = 7;
    }
}
