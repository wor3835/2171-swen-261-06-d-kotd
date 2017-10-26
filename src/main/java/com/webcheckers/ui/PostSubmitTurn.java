package com.webcheckers.ui;

import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostSubmitTurn implements Route {
    private final Move move;
    private final Player player;

    public PostSubmitTurn(Move move, Player player) {
        this.move = move;
        this.player = player;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return null;
    }
}
