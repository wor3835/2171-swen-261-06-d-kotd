package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 *
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostBackRoute implements Route {


    /**
     * If possible, backs up a move in replay mode
     *
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     *
     * @return
     *   A message of type error if the move cannot be backed up, of type info if the backup was a success
     */
    @Override
    public Object handle(Request request, Response response){
        Session session = request.session();

        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        int currentIdx = session.attribute(PostReplayRoute.CURRENT_IDX_ATTR);

        if(currentIdx<=-1) {
            Message msg = new Message("Cannot go back any further", MasterEnum.MessageType.error);

            Gson gson = new Gson();
            return gson.toJson(msg);
        }

        if(session.attribute(GetGameRoute.ACTIVE_COLOR)== MasterEnum.Color.RED){
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.WHITE);
        }else
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.RED);

        Move move = game.getMove(currentIdx);

        Board b = game.getB();

        if((game.getP1().equals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)) && currentIdx%2==1) ||
                (game.getP2().equals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)) && currentIdx%2==0) )
            b.undoMove(move, true);
        else
            b.undoMove(move, false);

        currentIdx--;
        session.attribute(PostReplayRoute.CURRENT_IDX_ATTR, currentIdx);

        Message msg = new Message("Success", MasterEnum.MessageType.info);

        Gson gson = new Gson();
        return gson.toJson(msg);
    }
}
