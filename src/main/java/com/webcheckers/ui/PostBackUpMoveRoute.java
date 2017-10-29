package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import spark.Request;
import spark.Response;
import spark.Route;

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
        if(!game.movesList.isEmpty()) {
            game.movesList.remove(0); // remove last validated move
            Message t = new Message("back up move successful", MasterEnum.MessageType.info);
            String tru = gson.toJson(t);
            tru = response.body();
        } else {
            Message f = new Message("error: move was not removed", MasterEnum.MessageType.info);
            String fal = gson.toJson(f);
            fal = response.body();
        }

        return null;
    }
}
