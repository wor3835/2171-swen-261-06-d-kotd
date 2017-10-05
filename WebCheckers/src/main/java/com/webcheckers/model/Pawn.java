package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * a single pawn
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author
 * @author
 * @author
 */
public class Pawn extends Piece{
    public Pawn(Point p){
        super(p);
    }

    public Pawn(int x, int y){
        super(x, y);
    }

    public boolean isValidMove(Point p){
        return false;
    }
}
