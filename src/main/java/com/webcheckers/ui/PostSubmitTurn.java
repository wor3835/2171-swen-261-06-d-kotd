package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * Created by wor3835 on 10/25/2017.
 */
public class PostSubmitTurn implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        String body = request.body();
        Gson g = new Gson();
        Move move = g.fromJson(body, Move.class);

        Session session = request.session();
        Game game = session.attribute(GetGameRoute.GAME_ATTR);
        if(game.movesList.contains(move)) {
            Board b1 = game.getB1();
            Board b2 = game.getB2();
            if(session.attribute(GetGameRoute.ACTIVE_COLOR) == MasterEnum.Color.RED) {
                b1.makeMove(move);
                b2.inverseMove(move);
            } else {
                b1.inverseMove(move);
                b2.makeMove(move);
            }
        }
        return null;
    }
}
