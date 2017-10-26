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
}