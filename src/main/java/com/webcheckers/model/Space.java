package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kzalb on 10/15/2017.
 * Represents a space on the board
 */
public class Space{

    private int cellIdx;
    private boolean validSpace;
    private Piece piece;


    /**
     * Physical space on the game board
     * @param cellIdx id of cell
     * @param valid if space is on board or not
     * @param piece game piece on space
     */
    public Space(int cellIdx, boolean valid, Piece piece)
    {
        this.cellIdx = cellIdx;
        this.validSpace = valid;
        this.piece = piece;
    }

    /**
     * @return id of cell
     */
    public int getCellIdx()
    {
        return cellIdx;
    }

    /**
     * @return if space is valid
     */
    public boolean isValid()
    {
        return validSpace;
    }

    /**
     * @return game piece on space
     */
    public Piece getPiece()
    {
        return piece;
    }

    public ArrayList<Move> validMoves(Board b, int row, int col) {
        ArrayList<Move> m1 = new ArrayList<>();
        if (b.hasPiece(row, col)) {
            Piece curr = b.getPieceAt(row, col);
            if (curr.getType().equals(MasterEnum.PieceType.SINGLE)) {
                if(row - 1 > 0 && col + 1 < BoardView.BOARD_LENGTH) {
                    if(!(b.hasPiece(row - 1, col + 1))) {
                        m1.add(new Move(new Position(row, col), new Position(row - 1, col + 1)));
                    }
                    else if(row-2 > 0 && col+2 < BoardView.BOARD_LENGTH && !(b.hasPiece(row-2, col+2))){
                        m1.add(new Move(new Position(row,col), new Position(row-2, col+2)));
                    }
                }
                if(row - 1 > 0 && col - 1 >= 0) {
                    if(!(b.hasPiece(row - 1, col - 1))) {
                        m1.add(new Move(new Position(row, col), new Position(row -1, col - 1)));
                    }
                    else if(row-2 > 0 && col-2 >= 0 && !(b.hasPiece(row-2, col-2))){
                        m1.add(new Move(new Position(row,col), new Position(row-2, col-2)));
                    }
                }
            } else if (curr.getType().equals(MasterEnum.PieceType.KING)) {
                if(row - 1 > 0 && col + 1 < BoardView.BOARD_LENGTH) {
                    if(!(b.hasPiece(row - 1, col + 1))) {
                        m1.add(new Move(new Position(row, col), new Position(row - 1, col + 1)));
                    }
                    else if(row-2 > 0 && col+2 < BoardView.BOARD_LENGTH && !(b.hasPiece(row-2, col+2))) {
                        m1.add(new Move(new Position(row, col), new Position(row - 2, col + 2)));
                    }
                }
                if(row - 1 > 0 && col - 1 >= 0) {
                    if(!(b.hasPiece(row - 1, col - 1))) {
                        m1.add(new Move(new Position(row, col), new Position(row -1, col - 1)));
                    }
                    else if(row-2 > 0 && col-2 >= 0 && !(b.hasPiece(row -2, col -2))) {
                        m1.add(new Move(new Position(row, col), new Position(row - 2, col - 2)));
                    }
                }
                if(row + 1 < BoardView.BOARD_LENGTH && col + 1 < BoardView.BOARD_LENGTH) {
                    if(!(b.hasPiece(row + 1, col + 1))) {
                        m1.add(new Move(new Position(row, col), new Position(row + 1, col + 1)));
                    }
                    else if(row+2 < BoardView.BOARD_LENGTH && col+2 < BoardView.BOARD_LENGTH && !(b.hasPiece(row+2, col+2))) {
                        m1.add(new Move(new Position(row, col), new Position(row + 2, col + 2)));
                    }
                }
                if(row + 1 < BoardView.BOARD_LENGTH && col - 1 >= 0) {
                    if(!(b.hasPiece(row + 1, col - 1))) {
                        m1.add(new Move(new Position(row, col), new Position(row + 1, col - 1)));
                    }
                    else if(row+2 < BoardView.BOARD_LENGTH && col-2 >= 0 && !(b.hasPiece(row+2, col-2))) {
                        m1.add(new Move(new Position(row, col), new Position(row + 2, col - 2)));
                    }
                }
            }
        }
        return m1;
    }

}

