package main.java.com.webcheckers.model;

import java.awt.Point;

/**
 * A single king piece
 *
 * @author <a href='mailto:ajn3687@g.rit.edu'>AJ Nagashima</a>
 * @author <a href='mailto:gep2494@g.rit.edu'>George-Edward Pinal</a>
 * @author
 * @author
 * @author
 */
public class King extends Piece{
    public King(Piece p)
    {
        super(p.getPosition());
    }

    public boolean isValidMove(Point p){
        return false;
    }
}
