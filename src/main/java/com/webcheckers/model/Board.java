package com.webcheckers.model;

/**
 * Created by arthu on 10/15/2017.
 */
public class Board {

    /**
     * Creates the board
     */
    Space board[][] = new Space[BoardView.BOARD_LENGTH][BoardView.BOARD_LENGTH];
    private final Piece.Color playerColor, opponentColor;


    /**
     * Constructor for the board
     */
    public Board(Piece.Color color)
    {
        playerColor = color;
        if(color == Piece.Color.RED)
            opponentColor = Piece.Color.WHITE;
        else
            opponentColor = Piece.Color.RED;
        init();
    }

    /**
     * Initializes the board
     */
    private void init(){
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++){
            for(int j = 0; j < BoardView.BOARD_LENGTH;j++){
                if((i+j)%2==1 && i < 3){
                    board[i][j] = new Space(j, false, new Piece(opponentColor, Piece.Type.SINGLE));
                } else if ((i+j)%2==1 && i >= 5) {
                    board[i][j] = new Space(j, false, new Piece(playerColor, Piece.Type.SINGLE));
                } else {
                    board[i][j] = new Space(j, false, null);
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

    /**
     * Checks to see if a piece is on the board
     * @param i
     * @param j
     * @return true or false if the piece is on the board
     */
    public boolean hasPiece(int i, int j){
        return board[i][j] != null ? true : false;
    }
}
