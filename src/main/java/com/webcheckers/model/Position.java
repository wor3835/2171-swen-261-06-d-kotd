package com.webcheckers.model;


/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class Position {
    //The row and col (cell) of this position
    private int row, cell;
    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    public int getRow(){return row;}

    public int getCol(){return cell;}

    /**
     * A generic equals method that compares values of position and not hashcodes
     * @param o the object to be tested
     * @return if o equals this
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Position){
            if(((Position)o).getRow() == this.getRow() &&
                    ((Position)o).getCol() == this.getCol())
                return true;
        }
        return false;
    }
}
