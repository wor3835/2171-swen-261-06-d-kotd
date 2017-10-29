package com.webcheckers.model;


/**
 * Created by arthu on 10/24/2017.
 */
public class Position {
    private int row, cell;
    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    public int getRow(){return row;}

    public int getCol(){return cell;}

    public boolean equals(Object o){
        if(o instanceof Position){
            if(((Position)o).getRow() == this.getRow() &&
                    ((Position)o).getCol() == this.getCol())
                return true;
        }
        return false;
    }
}
