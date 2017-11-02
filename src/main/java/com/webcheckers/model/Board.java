package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    public ArrayList<Move> getMoves(List<Position> posList){
        ArrayList<Move> moves = new ArrayList<>();
        for(int i = 0; i < posList.size(); i++){
            Position pos = posList.get(i);
            Space s = board[pos.getRow()][pos.getCol()];
            moves.addAll(s.validJumps(this, pos, playerColor));
        }

        if(moves.size() <= 0) {
            for (int i = 0; i < posList.size(); i++) {
                Position pos = posList.get(i);
                Space s = board[pos.getRow()][pos.getCol()];
                moves.addAll(s.validMoves(this, pos));
            }
        }


        return moves;
    }

    public void makeMove(Move move, Player p) {
        Position start = move.getStart();
        Position end = move.getEnd();
        int endCol = end.getCol();
        int endRow = end.getRow();
        int startCol = start.getCol();
        int startRow = start.getRow();
        if(Math.abs(startCol-endCol) == 2){
            board[startRow+(endRow-startRow)/2][startCol+(endCol-startCol)/2] =
                    new Space(startCol+(endCol-startCol)/2, true, null);
            Position piecepos = new Position(startRow+(endRow-startRow)/2,
                    startCol+(endCol-startCol)/2);
        }
        Space s = new Space(endCol, false, board[startRow][startCol].getPiece());
        if(s.getPiece().getType() == MasterEnum.PieceType.SINGLE &&
                endRow == 0){
            s.kingMe();
        }
        board[endRow][endCol] = s;
        board[startRow][startCol] = new Space(startCol, true, null);
        p.movePiece(move);
        if(move.getMove() != null )
            makeMove(move.getMove(), p);
    }

    public void inverseMove(Move move, Player p){
        Position start = move.getStart();
        Position end = move.getEnd();
        int endCol = BoardView.BOARD_LENGTH-end.getCol()-1;
        int endRow = BoardView.BOARD_LENGTH-end.getRow()-1;
        int startCol = BoardView.BOARD_LENGTH-start.getCol()-1;
        int startRow = BoardView.BOARD_LENGTH-start.getRow()-1;
        if(Math.abs(startCol-endCol) == 2){
            board[startRow+(endRow-startRow)/2][startCol+(endCol-startCol)/2] =
                    new Space(startCol+(endCol-startCol)/2, true, null);
            Position piecepos = new Position(start.getRow()+(end.getRow()-start.getRow())/2,
                    start.getCol()+(end.getCol()-start.getCol())/2);
            p.removePiece(piecepos);
        }
        Space s = new Space(endCol, false, board[startRow][startCol].getPiece());
        if(s.getPiece().getType() == MasterEnum.PieceType.SINGLE &&
                endRow == BoardView.BOARD_LENGTH-1){
            s.kingMe();
        }
        board[endRow][endCol] = s;
        board[startRow][startCol] = new Space(startCol, true, null);
        if(move.getMove() != null)
            inverseMove(move.getMove(), p);
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
        return board[i][j].getPiece() != null;
    }
}
