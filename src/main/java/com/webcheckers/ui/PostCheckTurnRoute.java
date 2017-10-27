package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by wor3835 on 10/23/2017.
 */
public class PostCheckTurnRoute implements Route {
    Game game;

    public PostCheckTurnRoute(Game game) {
        this.game = game;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Gson gson = new Gson();
        Message t = new Message("true", MasterEnum.MessageType.info);
        Message f = new Message("false", MasterEnum.MessageType.info);
        return null;
    }
}
