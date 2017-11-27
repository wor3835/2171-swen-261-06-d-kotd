package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * Created by arthu on 11/27/2017.
 */
public class PostCheckChangeRoute implements Route {
    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();

        Game game = session.attribute(GetGameRoute.GAME_ATTR);
        Message msg;

        if(game.getActiveColor() == session.attribute(GetGameRoute.ACTIVE_COLOR)){
            msg = new Message("false", MasterEnum.MessageType.info);
        } else {
            session.attribute(GetGameRoute.ACTIVE_COLOR, game.getActiveColor());
            msg = new Message("true", MasterEnum.MessageType.info);
        }

        Gson gson = new Gson();
        return gson.toJson(msg);
    }
}
