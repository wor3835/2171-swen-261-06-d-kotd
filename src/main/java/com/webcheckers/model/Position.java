package com.webcheckers.model;


/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class Position {
    //The row and col (cell) of this position
    private int row, cell;
    public Position(int row, int cell){
        this.row = row;
        this.cell = cell;
    }

    /**
     *
     * @return the row of the board
     */
    public int getRow(){return row;}

    /**
     *
     * @return the column of the board
     */
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
