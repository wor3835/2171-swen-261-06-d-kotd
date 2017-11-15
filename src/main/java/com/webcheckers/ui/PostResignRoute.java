package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.Board;
import com.webcheckers.model.Move;
import com.webcheckers.model.Player;
import spark.Request;
import spark.Response;
import spark.Session;

import java.util.ArrayList;

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;

/**
 * Created by wor3835 on 11/14/2017.
 */
public class PostResignRoute {

    private final GameLobby gameLobby;

    public PostResignRoute(GameLobby gameLobby){this.gameLobby = gameLobby;}

    public Object handle(Request request, Response response) {
        //Get the current HttpSession
        Session session = request.session();

        //Game is pulled down from the session
        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        Gson gson = new Gson();
        if(game == null)
            return gson.toJson(new Message("game is null", MasterEnum.MessageType.error));

        game.endGame();

        Message msg = new Message("game ended", MasterEnum.MessageType.info);
        response.redirect(WebServer.ENDGAME_URL);

        return gson.toJson(msg);
    }
}
