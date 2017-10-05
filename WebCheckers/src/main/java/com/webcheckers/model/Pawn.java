package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * a single pawn
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:kcz3966@g.rit.edu'>Kerri Zalba</a>
 * @author <a href='mailto:rwk8144@g.rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:wor3835@g.rit.edu'>William Raffaelle</a>
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
