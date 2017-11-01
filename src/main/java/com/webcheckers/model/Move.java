package com.webcheckers.model;

/**
 * Created by arthu on 10/24/2017.
 */
public class Move {
    //Start and end position
    private Position start, end;
    private Move move;
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }

    private Move(Position start, Position end, Move move){
        this.start = start;
        this.end = end;
        this.move = move;
    }

    public Move getMove(){return move;}

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }

    public Position getFinal(){
        if(move == null)
            return getEnd();
        return move.getFinal();
    }


    @Override
    public String toString() {
        return super.toString();
    }

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