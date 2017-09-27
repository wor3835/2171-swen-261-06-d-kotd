package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * Created by arthu on 10/5/2017.
 */
public class King extends Piece {

    public King(Piece piece){
        super(piece.getPosition());
    }

    //TODO
    public boolean validMove(Point p){
        return false;
    }
}
