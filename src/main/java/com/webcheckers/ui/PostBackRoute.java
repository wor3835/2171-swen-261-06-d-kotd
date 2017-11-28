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
 * Created by arthu on 11/27/2017.
 */
public class PostBackRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();



        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        int currentIdx = session.attribute(PostReplayRoute.CURRENT_IDX_ATTR);

        if(currentIdx<=-1) {
            Message msg = new Message("Cannot go back any further", MasterEnum.MessageType.error);

            Gson gson = new Gson();
            return gson.toJson(msg);
        }

        Move move = game.getMove(currentIdx);

        Board b = game.getB();

        if((game.getP1().equals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)) && currentIdx%2==1) ||
                (game.getP2().equals(session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)) && currentIdx%2==0) )
            b.undoMove(move, true);
        else
            b.undoMove(move, false);


        Message msg = new Message("Success", MasterEnum.MessageType.info);

        Gson gson = new Gson();
        return gson.toJson(msg);
    }
}
