package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * A single checkers piece
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author
 * @author
 * @author
 * @author
 */
public abstract class Piece {
    private Point position;
    public Piece(int x, int y){
        position = new Point(x, y);
    }

    public Piece(Point p){
        position = new Point(p);
    }

    public Point getPosition(){
        return position;
    }

    public abstract boolean validMove(Point p);

}
