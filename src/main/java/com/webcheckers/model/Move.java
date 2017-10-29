package com.webcheckers.model;

/**
 * Created by arthu on 10/24/2017.
 */
public class Move {
    //Start and end position
    private Position start, end;
    public Move(Position start, Position end){
        this.start = start;
        this.end = end;
    }

    public Position getStart(){
        return start;
    }

    public Position getEnd(){
        return end;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object o){
        if(o instanceof Move){
            if(((Move)o).getStart().equals(this.getStart()) &&
                    ((Move)o).getEnd().equals(this.getEnd()))
                return true;
        }
        return false;
    }

}