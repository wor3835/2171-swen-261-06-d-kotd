package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * This is a single tile on a checkers board
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:kcz3966@g.rit.edu'>Kerri Zalba</a>
 * @author <a href='mailto:rwk8144@g.rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:wor3835@g.rit.edu'>William Raffaelle</a>
 */
public class Tile {
    private final Point coord;
    public Tile(Point p){
        coord = new Point(p);
    }

    public Tile(int x, int y){
        coord = new Point(x, y);
    }

    public Point getCoord(){return coord;}
}
