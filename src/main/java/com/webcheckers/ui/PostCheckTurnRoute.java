package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Game;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

/**
 * Created by wor3835 on 10/23/2017.
 */
public class PostCheckTurnRoute implements Route {

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Session session = request.session();
        Player currentPlayer = session.attribute(GetHomeRoute.CUR_PLAYER_ATTR);
        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        String text;

        if((currentPlayer.equals(session.attribute(GetGameRoute.RED_PLAYER))
                && game.getActiveColor() == MasterEnum.Color.RED)
                || (currentPlayer.equals(session.attribute(GetGameRoute.WHITE_PLAYER))
                && game.getActiveColor() == MasterEnum.Color.WHITE)){
            session.attribute(GetGameRoute.ACTIVE_COLOR, game.activeColor);
            text = "true";
        }else {
            text = "false";
        }

        Message msg = new Message(text, MasterEnum.MessageType.info);

        Gson gson = new Gson();
        return gson.toJson(msg);
    }
}
