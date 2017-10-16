package com.webcheckers.model;

/**
 * Created by arthu on 10/15/2017.
 */
public class Board {

    Piece board[][] = new Piece[BoardView.BOARD_LENGTH][BoardView.BOARD_LENGTH];

    public Board(){
        initialize();
    }

    private void initialize(){

    }

    public boolean hasPiece(int i, int j){
        return board[i][j] != null ? true : false;
    }
}
