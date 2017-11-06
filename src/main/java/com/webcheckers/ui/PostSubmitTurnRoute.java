package com.webcheckers.ui;

import com.google.gson.Gson;
import com.webcheckers.appl.Game;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.model.*;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Session;

import java.util.ArrayList;

import static spark.Spark.halt;

/**
 * Created by wor3835 on 10/25/2017.
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostSubmitTurnRoute implements Route {

    private final GameLobby gameLobby;

    public PostSubmitTurnRoute(GameLobby gameLobby){this.gameLobby = gameLobby;}

    @Override
    public Object handle(Request request, Response response) throws Exception {
        //Get the current HttpSession
        Session session = request.session();

        //Move is pulled down from the session
        Move move = session.attribute(PostValidateMoveRoute.MOVE_ATTR);

        Gson gson = new Gson();
        if(move == null)
            return gson.toJson(new Message("move is null", MasterEnum.MessageType.error));

        session.attribute(PostValidateMoveRoute.MOVE_ATTR, null);
        //Game is pulled down from the session
        Game game = session.attribute(GetGameRoute.GAME_ATTR);

        //Get each board
        Board b1 = game.getB1();
        Board b2 = game.getB2();

        ArrayList<Move> moves;
        if(session.attribute(GetGameRoute.ACTIVE_COLOR) == MasterEnum.Color.RED) {
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.WHITE);
            b1.makeMove(move, session.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
            b2.inverseMove(move, session.attribute(GetGameRoute.OPPONENT_ATTR));
            moves = b2.updateMovesList(((Player)session.attribute(GetGameRoute.OPPONENT_ATTR)).getPosList());

        } else {
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.RED);
            b1.inverseMove(move, session.attribute(GetGameRoute.OPPONENT_ATTR));
            b2.makeMove(move, session.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
            moves = b1.updateMovesList(((Player)session.attribute(GetGameRoute.OPPONENT_ATTR)).getPosList());
        }
        game.getMovesList().add(move);
        game.switchActive();


        if(moves.size() == 0){

            String name = ((Player)session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).getName();
            session.attribute(GetEndGameRoute.WINNER_ATTR, name);

            game.endGame();

            gameLobby.removeGame(game);

            response.redirect(WebServer.ENDGAME_URL);
            halt();
            return null;
        }

        Message msg = new Message("turn processed", MasterEnum.MessageType.info);

        return gson.toJson(msg);
    }
}
