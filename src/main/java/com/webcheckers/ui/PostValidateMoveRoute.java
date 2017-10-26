package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.model.Move;
import com.webcheckers.model.Position;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostValidateMoveRoute implements Route {
    private final Position start;
    private final Position end;
    private final Gson gson;

    public PostValidateMoveRoute(Position start, Position end, Gson gson) {
        this.start = start;
        this.end = end;
        this.gson = gson;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        return gson.toJson(new Move(start, end));
    }
}
