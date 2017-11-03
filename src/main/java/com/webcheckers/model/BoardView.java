package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Shows the board view for the current player.
 *
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class BoardView {
    static final int BOARD_LENGTH = 8;
    private final MasterEnum.Color color;
    private Board board;

    public BoardView(MasterEnum.Color color){
        this.color = color;
        board = new Board(color);
    }

    public BoardView(MasterEnum.Color color, Board board){
        this.color = color;
        this.board = board;
    }

    public Board getBoard() {return board;}

    /**
     * Adds rows to board.
     * @return iterator
     */
    public Iterator<Row> iterator(){
        List<Row> rows = new ArrayList<>();
        for(int i = 0; i < BOARD_LENGTH; i++)
            rows.add(new Row(i, board));
        return rows.iterator();
    }
}
