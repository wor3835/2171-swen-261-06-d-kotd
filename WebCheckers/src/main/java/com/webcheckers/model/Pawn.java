package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * A single pawn piece
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author
 * @author
 * @author
 * @author
 */
public class Pawn extends Piece {

    public Pawn(Point p){
        super(p);
    }

    public Pawn(int x, int y){
        super(x, y);
    }

    //TODO
    public boolean validMove(Point p){
        return false;
    }
}
