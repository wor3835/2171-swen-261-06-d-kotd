package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * a generic piece object
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author
 * @author
 * @author
 */
public abstract class Piece {
    private Point position;
    public Piece(Point p){
        position = new Point(p);
    }

    public Piece(int x, int y){
        position = new Point(x, y);
    }

    public abstract boolean isValidMove(Point p);

    public Point getPosition(){
        return position;
    }
}
