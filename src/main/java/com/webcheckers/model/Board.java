package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;

/**
 * Created by arthu on 10/15/2017.
 */
public class Board {

    /**
     * Creates the board
     */
    Space board[][] = new Space[BoardView.BOARD_LENGTH][BoardView.BOARD_LENGTH];
    private final MasterEnum.Color playerColor, opponentColor;


    /**
     * Constructor for the board
     */
    public Board(MasterEnum.Color color)
    {
        playerColor = color;
        if(color == MasterEnum.Color.RED)
            opponentColor = MasterEnum.Color.WHITE;
        else
            opponentColor = MasterEnum.Color.RED;
        init();
    }

    public void makeMove(Move move) {
        Position start = move.getStart();
        Position end = move.getEnd();
        Space s = new Space(end.getCol(), true, board[start.getRow()][start.getCol()].getPiece());
        board[end.getRow()][end.getCol()] = s;
        board[start.getRow()][start.getCol()] = new Space(start.getCol(), true, null);
    }

    public void inverseMove(Move move){
        Position start = move.getStart();
        Position end = move.getEnd();
        int endCol = BoardView.BOARD_LENGTH-end.getCol();
        int endRow = BoardView.BOARD_LENGTH-end.getRow();
        int startCol = BoardView.BOARD_LENGTH-start.getCol();
        int startRow = BoardView.BOARD_LENGTH-start.getRow();
        Space s = new Space(endCol, true, board[startRow][startCol].getPiece());
        board[endRow][endCol] = s;
        board[startRow][startCol] = new Space(startCol, true, null);
    }

    /**
     * Initializes the board
     */
    private void init(){
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++){
            for(int j = 0; j < BoardView.BOARD_LENGTH;j++){
                if((i+j)%2==1 && i < 3){
                    board[i][j] = new Space(j, false, new Pawn(opponentColor));
                } else if ((i+j)%2==1 && i >= 5) {
                    board[i][j] = new Space(j, false, new Pawn(playerColor));
                } else {
                    board[i][j] = new Space(j, (i+j)%2==1, null);
                }
            }
        }
    }

    /**
     * Gets the piece at a specified space
     * @param i
     * @param j
     * @return
     */
    public Piece getPieceAt(int i, int j)
    {
        return board[i][j].getPiece();
    }

    public Space getSpaceAt(int i, int j) { return board[i][j];}

    /**
     * Checks to see if a piece is on the board
     * @param i
     * @param j
     * @return true or false if the piece is on the board
     */
    public boolean hasPiece(int i, int j){
        return board[i][j] != null;
    }
}
