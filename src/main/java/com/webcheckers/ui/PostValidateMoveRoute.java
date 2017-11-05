package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;
import sun.reflect.generics.reflectiveObjects.LazyReflectiveObjectGenerator;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by wor3835 on 10/25/2017.
 *
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostValidateMoveRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());

    static final String MOVE_ATTR = "move";

    private Message msg;

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        Gson g = new Gson();
        Move move = g.fromJson(body, Move.class);

        Session session = request.session();

        BoardView boardView = session.attribute(GetGameRoute.BOARD_VIEW_KEY);
        Board board = boardView.getBoard();

        int startRow = move.getStart().getRow();
        int startCol = move.getStart().getCol();

        Space s = board.getSpaceAt(startRow, startCol);
        //ArrayList<Move> moves = s.validMoves(board, startRow, startCol); // get possible moves at the space

        Player p = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);

        ArrayList<Move> moves = board.getMoves(p.getPosList());

        if(moves.size() == 0){
            System.err.println("No moves left for Player:" +p.getName());
        }

        boolean hasMove = false;
        for(Move m: moves){
            if(m.equals(move)) {
                hasMove = true;
                LOG.finer("CONTAINS MOVE");
                break;
            }
        }

        if(hasMove) {
            session.attribute(MOVE_ATTR, move);
            //String moveString = gson.toJson(make);
            //moveString = request.body();
            msg = new Message("the move is valid", MasterEnum.MessageType.info);
        } else {
            msg = new Message("the move is invalid", MasterEnum.MessageType.error);
        }

        Gson gson = new Gson();

        return gson.toJson(msg);
    }
}
