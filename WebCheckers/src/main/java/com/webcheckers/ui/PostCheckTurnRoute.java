package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by wor3835 on 10/23/2017.
 */
public class PostCheckTurnRoute implements Route {
    private final Gson gson;
    GameLobby gameLobby;

    public PostCheckTurnRoute(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
