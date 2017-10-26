package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.ArrayList;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostValidateMoveRoute implements Route {
    private final Position start;
    private final Position end;
    private final Board board;
    private final Gson gson;

    public PostValidateMoveRoute(Position start, Position end, Board board, Gson gson) {
        this.start = start;
        this.end = end;
        this.board = board;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        int startCol = start.getCol();
        int startRow = start.getRow();
        int endCol = end.getCol();
        int endRow = end.getRow();
        Space s = board.getSpaceAt(startRow, startCol);
        ArrayList<Move> moves = s.validMoves(board, startRow, startCol);
        if(moves.contains(new Move(start, end))) {
            Move make = new Move(start, end);
            String moveString = make.toString();
            moveString = request.body();
            Message tru = new Message("the move is valid", MasterEnum.MessageType.info);
        } else {
            Message fal = new Message("the move is invalid", MasterEnum.MessageType.info);
        }
        return gson.toJson(new Move(start, end));
    }
}
