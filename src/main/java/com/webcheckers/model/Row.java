package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kzalb on 10/15/2017.
 * Represents a row on the board.
 */
public class Row {

    private int index;

    private Board board;

    /**
     * Sets row in board.
     * @param index index of row
     * @param board board
     */
    public Row(int index, Board board)
    {
        this.index = index;
        this.board = board;
    }

    /**
     * Gets index of row
     * @return row index
     */
    public int getIndex()
    {
        return index;
    }

    /**
     * Adds spaces to each row.
     * @return iterator
     */
    public Iterator<Space> iterator(){
        List<Space> spaces = new ArrayList<>();
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++){
            spaces.add(new Space(i, (i+index)%2 == 1 ? true : false, board.board[index][i].getPiece()));
        }
        return spaces.iterator();
    }
}
