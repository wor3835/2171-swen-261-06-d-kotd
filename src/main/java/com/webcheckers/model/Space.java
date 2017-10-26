package com.webcheckers.model;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by kzalb on 10/15/2017.
 * Represents a space on the board
 */
public class Space{

    private int cellIdx;
    private boolean validSpace;
    private Piece piece;


    /**
     * Physical space on the game board
     * @param cellIdx id of cell
     * @param valid if space is on board or not
     * @param piece game piece on space
     */
    public Space(int cellIdx, boolean valid, Piece piece)
    {
        this.cellIdx = cellIdx;
        this.validSpace = valid;
        this.piece = piece;
    }

    /**
     * @return id of cell
     */
    public int getCellIdx()
    {
        return cellIdx;
    }

    /**
     * @return if space is valid
     */
    public boolean isValid()
    {
        return validSpace;
    }

    /**
     * @return game piece on space
     */
    public Piece getPiece()
    {
        return piece;
    }


}