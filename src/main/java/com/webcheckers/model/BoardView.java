package com.webcheckers.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by arthu on 10/15/2017.
 * Shows the board view for the current player.
 */
public class BoardView {
    static final int BOARD_LENGTH = 8;
    private final Piece.Color color;
    private Board BOARD;

    public BoardView(Piece.Color color){
        this.color = color;
        BOARD = new Board(color);
    }

    /**
     * Adds rows to board.
     * @return iterator
     */
    public Iterator<Row> iterator(){
        List<Row> rows = new ArrayList<>();
        for(int i = 0; i < BOARD_LENGTH; i++)
            rows.add(new Row(i, BOARD));
        return rows.iterator();
    }
}
