package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class Move {
    //Start and end position
    private Position start, end;
    //The next move, only used for a sequence of captures
    private Move move;
    //a boolean for whether or not a piece was kinged on this turn
    private boolean wasKinged=false;
    //a value to show whether or not a piece was taken on this turn
    private Piece piece;

    /**
     * Constuctor that creates a move with start and end position
     * @param start The start position of the move
     * @param end The end position of the move
     */
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }

    public Move(Position start, Position end, Move move){
        this.start = start;
        this.end = end;
        this.move = move;
    }

    public Move getMove(){return move;}

    public void kinged(){wasKinged=true;}

    public boolean wasKinged(){return wasKinged;}

    public void pieceTaken(Piece p){this.piece = p;}

    public Piece pieceTaken(){return piece;}

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }

    /**
     * Gets the final position, at the end of the linked series of moves
     * @return The final position
     */
    public Position getFinal(){
        //If move is null then final is this move's end
        if(move == null)
            return getEnd();
        //Otherwise call move's final method
        return move.getFinal();
    }

    /**
     *
     * @param o : the object being compared
     * @return If the object is of type move then check if its start is the same as this start
                Also check if o's end is the same as this.getFinal()
     */
    @Override
    public boolean equals(Object o){
        if(o instanceof Move){
            if(((Move)o).getStart().equals(this.getStart()) &&
                    ((Move)o).getFinal().equals(this.getFinal()))
                return true;
        }
        return false;
    }


}