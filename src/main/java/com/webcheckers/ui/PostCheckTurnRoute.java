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
    private final Gson gson;
    Game game;

    public PostCheckTurnRoute(Gson gson, Game game) {
        this.gson = gson;
        this.game = game;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Message t = new Message("true", MasterEnum.MessageType.info);
        Message f = new Message("false", MasterEnum.MessageType.info);
        return null;
    }
}
