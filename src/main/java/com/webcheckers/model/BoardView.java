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
    //The board length
    static final int BOARD_LENGTH = 8;
    //The color of the player using the boardView
    private final MasterEnum.Color color;
    //A board object used by the current player
    private Board board;

    public BoardView(MasterEnum.Color color){
        this.color = color;
        board = new Board(color);
    }

    /**
     * Takes a board reference at sets this.board = the reference
     * @param board the board reference
     */
    public BoardView(Board board){
        color = null;
        this.board = board;
    }

    //Returns the board object
    public Board getBoard() {return board;}

    /**
     * Adds rows from board.
     * @return iterator
     */
    public Iterator<Row> iterator(){
        List<Row> rows = new ArrayList<>();
        for(int i = 0; i < BOARD_LENGTH; i++)
            rows.add(new Row(i, board));
        return rows.iterator();
    }
}
