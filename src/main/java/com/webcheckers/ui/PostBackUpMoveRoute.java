package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * Created by wor3835 on 10/26/2017.
 */
public class PostBackUpMoveRoute implements Route {
    private final Game game;

    public PostBackUpMoveRoute(Game game) {
        this.game = game;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        Message t;
        Session session = request.session();
        if (session.attribute(PostValidateMoveRoute.MOVE_ATTR) == null)
            t = new Message("back up move failure", MasterEnum.MessageType.error);
        else {
            session.attribute(PostValidateMoveRoute.MOVE_ATTR, null);
            t = new Message("back up move successful", MasterEnum.MessageType.info);
        }

        return gson.toJson(t);
    }
}