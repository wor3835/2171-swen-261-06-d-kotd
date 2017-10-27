package com.webcheckers.ui;

import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostSubmitTurn implements Route {
    private final Move move;
    private final Game game;
    private final Piece piece;

    public PostSubmitTurn(Move move, Game game, Piece piece) {
        this.move = move;
        this.game = game;
        this.piece = piece;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if(game.movesList.contains(move)) {
            int startRow = move.getStart().getRow();
            int startCol = move.getEnd().getCol();
            int endRow = move.getEnd().getRow();
            int endCol = move.getEnd().getCol();
            Board b1 = game.getB1();
            Board b2 = game.getB2();
            b1.makeMove(endRow, endCol, piece);
            b2.makeMove(endRow, endCol, piece);
            Piece p1 = b1.getPieceAt(startRow, startCol);
            Piece p2 = b2.getPieceAt(startRow, startCol);
            p1 = null;
            p2 = null;
            return null;
        }
        return null;
    }
}
