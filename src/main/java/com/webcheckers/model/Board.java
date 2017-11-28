package com.webcheckers.model;

import com.webcheckers.appl.MasterEnum;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 * @author <a href='mailto:gep2494@rit.edu'>George-Edward Pinal</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 */
public class Board {

    //Creates board with definition outlined in boardView
    private Space board[][] = new Space[BoardView.BOARD_LENGTH][BoardView.BOARD_LENGTH];
    //Colors of the first player using the board and opponent of the player
    private final MasterEnum.Color playerColor, opponentColor;

    private ArrayList<Move> movesList;


    /**
     * Constructor for board
     * @param color The color of the player viewing/using this board
     */
    public Board(MasterEnum.Color color)
    {
        //Set player to color
        playerColor = color;
        //Set opponent to the other color specified in MasterEnum.Color
        if(color == MasterEnum.Color.RED)
            opponentColor = MasterEnum.Color.WHITE;
        else
            opponentColor = MasterEnum.Color.RED;
        init();
    }

    public Board(){
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++)
            for(int j = 0; j < BoardView.BOARD_LENGTH; j++)
                board[i][j] = new Space(j, true, null);
        playerColor = null;
        opponentColor = null;
    }

    /**
     * add a piece to the specified location
     * @param p - the piece to be added
     * @param r - the row of the location
     * @param c - the column of the location
     */
    public void addPiece(Piece p, int r, int c){
        board[r][c] = new Space(c, false, p);
    }

    /**
     * Initializes board by adding spaces to the board at all locations
     */
    private void init(){
        for(int i = 0; i < BoardView.BOARD_LENGTH; i++){
            for(int j = 0; j < BoardView.BOARD_LENGTH;j++){
                // (i+j)%2 = 1 when on a black space and 0 on white
                //Whites are invalid
                if((i+j)%2==1 && i < 3){
                    //Add in Opponent spaces on top
                    board[i][j] = new Space(j, false, new Pawn(opponentColor));
                } else if ((i+j)%2==1 && i >= 5) {
                    //Add in CurPlayer spaces on bottom
                    board[i][j] = new Space(j, false, new Pawn(playerColor));
                } else {
                    //Add Empty spaces everywhere else
                    board[i][j] = new Space(j, (i+j)%2==1, null);
                }
            }
        }
    }

    /**
     * Creates a list of moves for that player
     * @param posList The list of piece positions from the player
     * @return a full list of moves for a player
     */
    public ArrayList<Move> updateMovesList(List<Position> posList){
        //Moves list to be returned
        ArrayList<Move> moves = new ArrayList<>();

        for(int i = 0; i < posList.size(); i++){
            //create a position, and fetch the space from that position on board
            Position pos = posList.get(i);
            //Add all the moves created from that spaces validJumps method
            moves.addAll(validJumps(this, pos, playerColor));
        }

        //Only go through this loop if there are no valid jumps
        if(moves.size() <= 0) {
            for (int i = 0; i < posList.size(); i++) {
                //create a position, and fetch the space from that position on board
                Position pos = posList.get(i);
                //Add all the moves created from that spaces validMoves method
                moves.addAll(validMoves(pos));
            }
        }

        movesList = moves;
        return movesList;
    }

    /**
     * returns the list of moves on the board and (if necessary) updates that list
     * @param posList - list of positions where there are pieces
     * @return list of moves
     */
    public ArrayList<Move> getMoves(ArrayList<Position> posList){
        if(movesList == null)
            updateMovesList(posList);
        return movesList;
    }

    /**
     * find valid moves for the given position
     * @param start - the position
     * @return list of valid moves for the provided position
     */
    public ArrayList<Move> validMoves(Position start) {
        int row = start.getRow();
        int col = start.getCol();

        ArrayList<Move> m1 = new ArrayList<>();
        if (row - 1 >= 0 && col + 1 < BoardView.BOARD_LENGTH) {
            if (!(hasPiece(row - 1, col + 1)))
                m1.add(new Move(new Position(row, col), new Position(row - 1, col + 1)));
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (!(hasPiece(row - 1, col - 1)))
                m1.add(new Move(new Position(row, col), new Position(row - 1, col - 1)));
        }
        if (getPieceAt(row,col).getType().equals(MasterEnum.PieceType.KING)) {
            if (row + 1 < BoardView.BOARD_LENGTH && col + 1 < BoardView.BOARD_LENGTH) {
                if (!(hasPiece(row + 1, col + 1)))
                    m1.add(new Move(new Position(row, col), new Position(row + 1, col + 1)));
            }
            if (row + 1 < BoardView.BOARD_LENGTH && col - 1 >= 0) {
                if (!(hasPiece(row + 1, col - 1)))
                    m1.add(new Move(new Position(row, col), new Position(row + 1, col - 1)));
            }
        }
        return m1;
    }

    /**
     * finds valid jump moves for the provided position
     * @param b - the board
     * @param start - the position jump moves are being found for
     * @param color - the color of the piece at the provided position
     * @return a list of valid jump moves for the provided position
     */
    public ArrayList<Move> validJumps(Board b, Position start, MasterEnum.Color color)
    {return validJumps(start,color,getPieceAt(start.getRow(),start.getCol()),new ArrayList<>());}

    /**
     * finds valid jump moves for the provided position
     * @param start - the position jump moves are being found for
     * @param color -
     * @param piece - the color of the piece at the provided position
     * @param p - all previously found jump moves for the provided position
     * @return a list of valid jump moves for the provided position
     */
    private ArrayList<Move> validJumps(Position start, MasterEnum.Color color, Piece piece,
                                      ArrayList<Position> p) {

        int row = start.getRow();
        int col = start.getCol();

        ArrayList<Move> moves = new ArrayList<>();
        ArrayList<Position> prev = new ArrayList<>();
        prev.addAll(p);

        if (row - 1 >= 0 && col + 1 < BoardView.BOARD_LENGTH) {
            if (hasPiece(row - 1, col + 1) &&
                    (row - 2 >= 0 && col + 2 < BoardView.BOARD_LENGTH && !(hasPiece(row - 2, col + 2)))) {
                if(getPieceAt(row-1, col+1).getColor() != color) {
                    Position pos = new Position(row-2, col+2);
                    if(!prev.contains(pos)) {
                        prev.add(pos);
                        for (Move m : validJumps(pos, color, piece, prev)) {
                            Move temp = new Move(new Position(row, col), pos, m);
                            moves.add(temp);
                        }
                        moves.add(new Move(start, pos));
                    }

                }


            }
        }
        if (row - 1 >= 0 && col - 1 >= 0) {
            if (hasPiece(row - 1, col - 1)
                    && (row - 2 >= 0 && col - 2 >= 0 && !(hasPiece(row - 2, col - 2)))) {
                if(getPieceAt(row-1, col-1).getColor() != color) {
                    Position pos = new Position(row-2, col-2);
                    if(!prev.contains(pos)) {
                        prev.add(pos);
                        for (Move m : validJumps(pos, color, piece, prev)) {
                            Move temp = new Move(new Position(row, col), pos, m);
                            moves.add(temp);
                        }
                        moves.add(new Move(start, pos));
                    }

                }
            }
        }
        if (piece.getType().equals(MasterEnum.PieceType.KING)) {
            if (row + 1 < BoardView.BOARD_LENGTH && col + 1 < BoardView.BOARD_LENGTH) {
                if (hasPiece(row + 1, col + 1) &&
                        (row + 2 < BoardView.BOARD_LENGTH && col + 2 < BoardView.BOARD_LENGTH && !(hasPiece(row + 2, col + 2)))) {
                    if(getPieceAt(row+1, col+1).getColor() != color) {
                        Position pos = new Position(row+2, col+2);
                        if(!prev.contains(pos)) {
                            prev.add(pos);
                            for (Move m : validJumps(pos, color, piece, prev)) {
                                Move temp = new Move(new Position(row, col), pos, m);
                                moves.add(temp);
                            }
                            moves.add(new Move(start, pos));
                        }

                    }
                }
            }
            if (row + 1 < BoardView.BOARD_LENGTH && col - 1 >= 0) {
                if (hasPiece(row + 1, col - 1) &&
                        (row + 2 < BoardView.BOARD_LENGTH && col - 2 >= 0 && !(hasPiece(row + 2, col - 2)))) {
                    if(getPieceAt(row+1, col-1).getColor() != color) {
                        Position pos = new Position(row+2, col-2);
                        if(!prev.contains(pos)) {
                            prev.add(pos);
                            for (Move m : validJumps(pos, color, piece, prev)) {
                                Move temp = new Move(new Position(row, col), pos, m);
                                moves.add(temp);
                            }
                        }
                        moves.add(new Move(start, pos));
                    }
                }
            }
        }

        return moves;
    }

    /**
     * Moves a players piece on board, updates the player's posList
     * @param move The move to be applied to the board
     * @param p The player who is making the move
     */
    public void makeMove(Move move, Player p) {
        //Set everything to easy to use variables
        Position start = move.getStart();
        Position end = move.getEnd();
        int endCol = end.getCol();
        int endRow = end.getRow();
        int startCol = start.getCol();
        int startRow = start.getRow();

        //If the start is two spaces apart it must be a jump
        if(Math.abs(startCol-endCol) == 2){
            //row and col for piece to be removed
            int r = startRow+(endRow-startRow)/2;
            int c = startCol+(endCol-startCol)/2;
            move.pieceTaken(board[r][c].getPiece());
            //set the space it used to be in as null
            board[r][c] = new Space(c, true, null);
        }

        //Create a space that has the properties of the previously occupied space
        Space s = new Space(endCol, false, board[startRow][startCol].getPiece());

        //Check to see if the space is at the end of the board and a SINGLE. If so make it a king
        if(s.getPiece().getType() == MasterEnum.PieceType.SINGLE &&
                endRow == 0){
            s.kingMe();
            move.kinged();
        }
        //Change the end location in board to the temp space created
        board[endRow][endCol] = s;

        //Change the start space to empty
        board[startRow][startCol] = new Space(startCol, true, null);

        //moves position from start to end in p.posList
        if(p!=null)
            p.movePiece(move);

        //if there are move moves, loop again
        if(move.getMove() != null )
            makeMove(move.getMove(), p);
    }

    /**
     * Makes a move on an inverted board, the inversion is a reflection about the origin
     * This is done because each player has their own board so moves need to be done seperately
     * @param move The move to be made (must be inversed before being used)
     * @param p The player that the move is being done on
     */
    public void inverseMove(Move move, Player p){
        //Set everything to easy to use variables
        Position start = move.getStart();
        Position end = move.getEnd();
        //Inverts the board move to allow a move on the other players board
        int endCol = BoardView.BOARD_LENGTH-end.getCol()-1;
        int endRow = BoardView.BOARD_LENGTH-end.getRow()-1;
        int startCol = BoardView.BOARD_LENGTH-start.getCol()-1;
        int startRow = BoardView.BOARD_LENGTH-start.getRow()-1;

        //If the start is two spaces apart it must be a jump
        if(Math.abs(startCol-endCol) == 2){
            //row and col for piece to be removed
            int r = startRow+(endRow-startRow)/2;
            int c = startCol+(endCol-startCol)/2;

            //set the space of the piece being jumped to empty
            board[r][c] = new Space(c, true, null);

            //remove that piece from the posList in player
            if(p!=null)
                p.removePiece(new Position(r,c));
        }

        //Create a space with the properties of the previously occupied space
        Space s = new Space(endCol, false, board[startRow][startCol].getPiece());

        //Check to see if the piece is at the end of the board and a SINGLE. If so make it a king
        if(s.getPiece().getType() == MasterEnum.PieceType.SINGLE &&
                endRow == BoardView.BOARD_LENGTH-1){
            s.kingMe();
            move.kinged();
        }

        //Change end to temp space created
        board[endRow][endCol] = s;

        //Change start to empty space
        board[startRow][startCol] = new Space(startCol, true, null);

        //If move has a move then loop but with move's move
        if(move.getMove() != null)
            inverseMove(move.getMove(), p);
    }

    /**
     * undoes the last move (used in replay mode ONLY)
     * @param move - the move to be undone
     * @param inversed - whether this is the player's or their opponent's move
     */
    public void undoMove(Move move, boolean inversed){
        //if there are move moves, loop again
        if(move.getMove() != null )
            undoMove(move.getMove(), inversed);

        //Set everything to easy to use variables
        Position start = move.getStart();
        Position end = move.getEnd();
        //Inverts the board move to allow a move on the other players board

        int startCol;
        int startRow;
        int endCol;
        int endRow;

        //if the move was made inversed, reinverse it
        if(inversed) {
            startCol = BoardView.BOARD_LENGTH-end.getCol()-1;
            startRow = BoardView.BOARD_LENGTH-end.getRow()-1;
            endCol = BoardView.BOARD_LENGTH-start.getCol()-1;
            endRow = BoardView.BOARD_LENGTH-start.getRow()-1;
        }else{
            startCol = end.getCol();
            startRow = end.getRow();
            endCol = start.getCol();
            endRow = start.getRow();
        }

        //if the move was a jump, undo the jump
        if(Math.abs(startCol-endCol) == 2){
            //row and col for piece to be removed
            int r = startRow+(endRow-startRow)/2;
            int c = startCol+(endCol-startCol)/2;

            //set the space of the piece being jumped to what was once there

            board[r][c] = new Space(c, true, move.pieceTaken());
        }

        Space s = new Space(endCol, false, board[startRow][startCol].getPiece());

        if(move.wasKinged()){
            s = new Space(endCol, false, new Pawn(board[startRow][startCol].getPiece().getColor()));
        }
        //Change the end location in board to the temp space created
        board[endRow][endCol] = s;

        //Change the start space to empty
        board[startRow][startCol] = new Space(startCol, true, null);
    }

    /**
     * Gets the piece at a specified space
     * @param row The row the piece is located at
     * @param col The col the piece is located at
     * @return the piece at board[row][col]
     */
    public Piece getPieceAt(int row, int col) {return board[row][col].getPiece();}

    /**
     * Gets the space at a specified row,col
     * @param row The row the space is located at
     * @param col The col the space is located at
     * @return The space at board[row][col]
     */
    public Space getSpaceAt(int row, int col) { return board[row][col];}

    /**
     * Checks to see if a piece is on the board
     * @param row The row the piece is located at
     * @param col The col the piece is located at
     * @return true or false if the piece is on the board at board[row][col]
     */
    public boolean hasPiece(int row, int col){return board[row][col].getPiece() != null;}
}
