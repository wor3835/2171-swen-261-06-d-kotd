package com.webcheckers.model;

/**
 * Created by kzalb on 10/15/2017.
 */
public class Space{

    private int cellIdx;
    private boolean validSpace;
    private Piece piece;

    public Space(int cellIdx, boolean valid, Piece piece)
    {
        this.cellIdx = cellIdx;
        this.validSpace = valid;
        this.piece = piece;
    }

    public int getCellIdx()
    {
        return cellIdx;
    }

    public boolean isValid()
    {
        return validSpace;
    }

    public Piece getPiece()
    {
        return piece;
    }
}
