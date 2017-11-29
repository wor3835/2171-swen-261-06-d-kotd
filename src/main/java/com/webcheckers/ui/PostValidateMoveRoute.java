package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
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

import static com.webcheckers.ui.PostStartRoute.VALIDATED;
import static spark.Spark.halt;

/**
 * @author <a href='mailto:wor3835@rit.edu'>William Raffaelle</a>
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostValidateMoveRoute implements Route {

    private static final Logger LOG = Logger.getLogger(PostValidateMoveRoute.class.getName());


    static final String MOVE_ATTR = "move";

    private Message msg;

    /**
     * Checks the requested move for validity
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   a message of type error if the move is invalid, or of type info if the move is valid
     */
    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        Gson g = new Gson();
        Move move = g.fromJson(body, Move.class);

        Session session = request.session();

        if (session.attribute(VALIDATED) == Boolean.TRUE) {
            msg = new Message("the move is invalid", MasterEnum.MessageType.error);
        } else {

            BoardView boardView = session.attribute(GetGameRoute.BOARD_VIEW_KEY);
            Board board = boardView.getBoard();

            int startRow = move.getStart().getRow();
            int startCol = move.getStart().getCol();

            //Space s = board.getSpaceAt(startRow, startCol);
            //ArrayList<Move> moves = s.validMoves(board, startRow, startCol); // get possible moves at the space

            Player p = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);

            ArrayList<Move> moves = board.getMoves(p.getPosList());

            for (Move m : moves) {
                if (m.equals(move)) {
                    session.attribute(MOVE_ATTR, m);
                    break;
                }
            }

            if (session.attribute(MOVE_ATTR) != null) {

                //String moveString = gson.toJson(make);
                //moveString = request.body();
                msg = new Message("the move is valid", MasterEnum.MessageType.info);
                session.attribute(VALIDATED, Boolean.TRUE);
            } else {
                msg = new Message("the move is invalid", MasterEnum.MessageType.error);
            }
        }

        Gson gson = new Gson();


        return gson.toJson(msg);
    }
}
