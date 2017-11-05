package com.webcheckers.ui;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

/**
 * Created by arthu on 11/5/2017.
 */
public class GetEndGameRoute implements Route{

    private final TemplateEngine templateEngine;
    private final GameLobby gameLobby;

    private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

    static final String WINNER_ATTR = "winner";
    static final String VIEW_NAME = "endgame.ftl";

    static final String WIN_MSG = "CONGRATULATIONS %s \n\n YOU WIN";
    static final String LOSE_MSG = "Sorry, but %s won";

    static final String GAME_OVER_ATTR = "message";


    public GetEndGameRoute(final TemplateEngine templateEngine, GameLobby gameLobby) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        this.gameLobby = gameLobby;
        //
        LOG.config("GetHomeRoute is initialized.");
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "GameOver");

        Session session = request.session();
        String name = session.attribute(WINNER_ATTR);

        session.attribute(GetGameRoute.OPPONENT_ATTR, null);
        session.attribute(GetGameRoute.BOARD_VIEW_KEY, null);
        session.attribute(GetGameRoute.WHITE_PLAYER, null);
        session.attribute(GetGameRoute.RED_PLAYER, null);
        session.attribute(GetGameRoute.GAME_ATTR, null);
        session.attribute(GetGameRoute.ACTIVE_COLOR, null);
        session.attribute(GetGameRoute.VIEW_MODE, null);

        if(name.equals(((Player)session.attribute(GetHomeRoute.CUR_PLAYER_ATTR)).getName()))
            vm.put(GAME_OVER_ATTR, String.format(WIN_MSG, name));
        else
            vm.put(GAME_OVER_ATTR, String.format(LOSE_MSG, name));


        return templateEngine.render(new ModelAndView(vm, VIEW_NAME));
    }
}
