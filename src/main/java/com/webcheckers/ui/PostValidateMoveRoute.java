package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.ArrayList;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostValidateMoveRoute implements Route {

    static final String MOVE_ATTR = "move";

    private Message msg;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        Gson g = new Gson();
        Move move = g.fromJson(body, Move.class);

        Session session = request.session();

        Gson gson = new Gson();

        BoardView boardView = session.attribute(GetGameRoute.BOARD_VIEW_KEY);
        Board board = boardView.getBoard();

        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();

        Space s = board.getSpaceAt(startRow, startCol);
        ArrayList<Move> moves = s.validMoves(board, startRow, startCol); // get possible moves at the space
        if(moves.contains(move)) {
            session.attribute(MOVE_ATTR, move);
            //String moveString = gson.toJson(make);
            //moveString = request.body();
            msg = new Message("the move is valid", MasterEnum.MessageType.info);
        } else {
            msg = new Message("the move is invalid", MasterEnum.MessageType.info);
        }

        return gson.toJson(msg);
    }
}
