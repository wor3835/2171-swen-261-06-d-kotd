package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * A single tile on a checkers board
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author
 * @author
 * @author
 * @author
 */
public class Tile {
    Point coord;
    public Tile(Point p){
        coord = new Point(p);
    }

    public Tile(int x, int y){
        coord = new Point(x, y);
    }
}
