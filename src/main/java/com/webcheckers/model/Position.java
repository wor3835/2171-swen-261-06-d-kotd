package com.webcheckers.model;

/**
 * Created by arthu on 10/24/2017.
 */
public class Position {
    private int row, col;
    public Position(int row, int col){
        this.row = row;
        this.col = col;
    }

    public int getRow(){return row;}

    public int getCol(){return col;}
}
