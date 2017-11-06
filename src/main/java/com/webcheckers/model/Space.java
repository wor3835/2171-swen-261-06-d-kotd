package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kzalb on 10/15/2017.
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
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

    public void kingMe(){
        if(piece == null)
            throw new RuntimeException("no piece to king");
        else if(piece.getType() == MasterEnum.PieceType.KING)
            throw new RuntimeException("piece is already a king");
        piece = new King(piece);
    }

    public ArrayList<Move> validMoves(Board b, Position start) {
        int row = start.getRow();
        int col = start.getCol();

        ArrayList<Move> m1 = new ArrayList<>();
        if (row - 1 >= 0 && col + 1 < BoardView.BOARD_LENGTH) {
            if (!(b.hasPiece(row - 1, col + 1)))
                m1.add(new Move(new Position(row, col), new Position(row - 1, col + 1)));
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (!(b.hasPiece(row - 1, col - 1)))
                m1.add(new Move(new Position(row, col), new Position(row - 1, col - 1)));
        }
        if (piece.getType().equals(MasterEnum.PieceType.KING)) {
            if (row + 1 < BoardView.BOARD_LENGTH && col + 1 < BoardView.BOARD_LENGTH) {
                if (!(b.hasPiece(row + 1, col + 1)))
                    m1.add(new Move(new Position(row, col), new Position(row + 1, col + 1)));
            }
            if (row + 1 < BoardView.BOARD_LENGTH && col - 1 >= 0) {
                if (!(b.hasPiece(row + 1, col - 1)))
                    m1.add(new Move(new Position(row, col), new Position(row + 1, col - 1)));
            }
        }
        return m1;
    }

    public ArrayList<Move> validJumps(Board b, Position start, MasterEnum.Color color) {

        int row = start.getRow();
        int col = start.getCol();

        ArrayList<Move> moves = new ArrayList<>();

        if (row - 1 >= 0 && col + 1 < BoardView.BOARD_LENGTH) {
            if (b.hasPiece(row - 1, col + 1) &&
                    (row - 2 >= 0 && col + 2 < BoardView.BOARD_LENGTH && !(b.hasPiece(row - 2, col + 2)))) {
                if(b.getPieceAt(row-1, col+1).getColor() != color) {
                    Position pos = new Position(row-2, col+2);
                    for(Move m: validJumps(b, pos, color)){
                        Move temp = new Move(new Position(row, col), pos, m);
                        moves.add(temp);
                    }
                    moves.add(new Move(start, pos));
                }


            }
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (b.hasPiece(row - 1, col - 1)
                    && (row - 2 >= 0 && col - 2 >= 0 && !(b.hasPiece(row - 2, col - 2)))) {
                if(b.getPieceAt(row-1, col-1).getColor() != color) {
                    Position pos = new Position(row-2, col-2);
                    for(Move m: validJumps(b, pos, color)){
                        Move temp = new Move(new Position(row, col), pos, m);
                        moves.add(temp);
                    }
                    moves.add(new Move(start, pos));
                }
            }
        }
        if (piece.getType().equals(MasterEnum.PieceType.KING)) {
            if (row + 1 < BoardView.BOARD_LENGTH && col + 1 < BoardView.BOARD_LENGTH) {
                if (b.hasPiece(row + 1, col + 1) &&
                        (row + 2 < BoardView.BOARD_LENGTH && col + 2 < BoardView.BOARD_LENGTH && !(b.hasPiece(row + 2, col + 2)))) {
                    if(b.getPieceAt(row+1, col+1).getColor() != color) {
                        Position pos = new Position(row+2, col+2);
                        for(Move m: validJumps(b, pos, color)){
                            Move temp = new Move(new Position(row, col), pos, m);
                            moves.add(temp);
                        }
                        moves.add(new Move(start, pos));
                    }
                }
            }
            if (row + 1 < BoardView.BOARD_LENGTH && col - 1 >= 0) {
                if (b.hasPiece(row + 1, col - 1) &&
                        (row + 2 < BoardView.BOARD_LENGTH && col - 2 >= 0 && !(b.hasPiece(row + 2, col - 2)))) {
                    if(b.getPieceAt(row+1, col-1).getColor() != color) {
                        Position pos = new Position(row+2, col-2);
                        for(Move m: validJumps(b, pos, color)){
                            Move temp = new Move(start, pos, m);
                            moves.add(temp);
                        }
                        moves.add(new Move(start, pos));
                    }
                }
            }
        }

        return moves;
    }

}

