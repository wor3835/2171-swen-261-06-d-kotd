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

import static com.webcheckers.ui.GetGameRoute.GAME_ATTR;
import static com.webcheckers.ui.PostStartRoute.VALIDATED;
import static spark.Spark.halt;

/**
 * @author <a href='mailto:rwk8144@rit.edu'>Robert Kurdziel</a>
 * @author <a href='mailto:ajn3687@rit.edu'>Arthur Nagashima</a>
 */
public class PostSubmitTurnRoute implements Route {

    @Override
    public Object handle(Request request, Response response) {
        //Get the current HttpSession
        Session session = request.session();

        //Move is pulled down from the session
        Move move = session.attribute(PostValidateMoveRoute.MOVE_ATTR);

        Gson gson = new Gson();

        if (((Game)session.attribute(GetGameRoute.GAME_ATTR)).isGameOver()){
            return gson.toJson(new Message("Game is over", MasterEnum.MessageType.info));
        }


        if(move == null)
            return gson.toJson(new Message("move is null", MasterEnum.MessageType.error));

        session.attribute(PostValidateMoveRoute.MOVE_ATTR, null);
        //Game is pulled down from the session
        Game game = session.attribute(GAME_ATTR);

        //Get each board
        Board b1 = game.getB1();
        Board b2 = game.getB2();

        ArrayList<Move> moves;
        if(session.attribute(GetGameRoute.ACTIVE_COLOR) == MasterEnum.Color.RED) {
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.WHITE);
            b1.makeMove(move, session.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
            b2.inverseMove(move, session.attribute(GetGameRoute.OPPONENT_ATTR));
            b2.updateMovesList(((Player)session.attribute(GetGameRoute.OPPONENT_ATTR)).getPosList());

        } else {
            session.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.RED);
            b1.inverseMove(move, session.attribute(GetGameRoute.OPPONENT_ATTR));
            b2.makeMove(move, session.attribute(GetHomeRoute.CUR_PLAYER_ATTR));
            b1.updateMovesList(((Player)session.attribute(GetGameRoute.OPPONENT_ATTR)).getPosList());
        }
        game.addMove(move);
        game.switchActive();

        Message msg = new Message("turn processed", MasterEnum.MessageType.info);
        session.attribute(VALIDATED, Boolean.FALSE);

        return gson.toJson(msg);
    }
}
