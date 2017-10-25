package com.webcheckers.model;

/**
 * Created by arthu on 10/15/2017.
 */
public class Board {

    /**
     * Creates the board
     */
    Piece board[][] = new Piece[BoardView.BOARD_LENGTH][BoardView.BOARD_LENGTH];
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
        initialize();
    }

    /**
     * Initializes the board
     */
    private void initialize(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < BoardView.BOARD_LENGTH;j++){
                if((i+j)%2==1){
                    board[i][j] = new Piece(opponentColor, Piece.Type.SINGLE);
                }
            }
        }

        for(int i = 5; i < BoardView.BOARD_LENGTH; i++){
            for(int j = 0; j < BoardView.BOARD_LENGTH;j++){
                if((i+j)%2==1){
                    board[i][j] = new Piece(playerColor, Piece.Type.SINGLE);
                }
            }
        }


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