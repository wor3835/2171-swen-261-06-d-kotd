package com.webcheckers.model;

/**
 * Created by kzalb on 10/15/2017.
 */
public class Space{

    private int cellIdx;
    private boolean validSpace;

    public Space(int cellIdx, boolean valid)
    {
        this.cellIdx = cellIdx;
        this.validSpace = valid;
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
        return null;
    }
}
