package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.BoardView;
import com.webcheckers.model.Game;
import com.webcheckers.model.Piece;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;
import spark.Session;

import com.webcheckers.model.Player;

import static spark.Spark.halt;

/**
 * The UI Controller to GET the Home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
public class GetHomeRoute implements Route {

  /**
   * Global variables to keep track of the players, routes, and templates
   */
  private static final Logger LOG = Logger.getLogger(GetHomeRoute.class.getName());

  private final TemplateEngine templateEngine;
  private final PlayerLobby playerLobby;
  private final GameLobby gameLobby;

  static final String VIEW_NAME = "home.ftl";
  static final String PLAYER_LOBBY_KEY = "playerLobby";

  static final String CUR_PLAYER_ATTR = "currentPlayer";


  /**
   * Create the Spark Route (UI controller) for the
   * {@code GET /} HTTP request.
   *
   * @param templateEngine
   *   the HTML template rendering engine
   */
  public GetHomeRoute(final TemplateEngine templateEngine, PlayerLobby playerLobby, GameLobby gameLobby) {
    // validation
    Objects.requireNonNull(templateEngine, "templateEngine must not be null");
    //
    this.templateEngine = templateEngine;
    this.playerLobby = playerLobby;
    this.gameLobby = gameLobby;
    //
    LOG.config("GetHomeRoute is initialized.");
  }

  /**
   * Render the WebCheckers Home page.
   *
   * @param request
   *   the HTTP request
   * @param response
   *   the HTTP response
   *
   * @return
   *   the rendered HTML for the Home page
   */
  @Override
  public Object handle(Request request, Response response) {
    final Session httpSession = request.session();



    Map<String, Object> vm = new HashMap<>();
    vm.put("title", "Welcome!");
    vm.put("error", " ");
    if (httpSession.attribute("err") != null && httpSession.attribute("err").equals("Player selected is already in a game, choose another player."))
      vm.put("error", "Player selected is already in a game, please choose another player or wait until they have finished their game.");

    if(httpSession.attribute(CUR_PLAYER_ATTR)!=null)
      vm.put(CUR_PLAYER_ATTR, httpSession.attribute(CUR_PLAYER_ATTR));

    httpSession.attribute(PLAYER_LOBBY_KEY, playerLobby);
    vm.put(PLAYER_LOBBY_KEY, httpSession.attribute(PLAYER_LOBBY_KEY));

    if(gameLobby.inGame(httpSession.attribute(CUR_PLAYER_ATTR))!=null)
    {
      Game game = gameLobby.inGame(httpSession.attribute(CUR_PLAYER_ATTR));
      vm.put(GetHomeRoute.CUR_PLAYER_ATTR, httpSession.attribute(GetHomeRoute.CUR_PLAYER_ATTR));

      httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY, playerLobby);
      vm.put(GetHomeRoute.PLAYER_LOBBY_KEY, httpSession.attribute(GetHomeRoute.PLAYER_LOBBY_KEY));

      if(httpSession.attribute(GetGameRoute.BOARD_VIEW_KEY)==null) {
        httpSession.attribute(GetGameRoute.BOARD_VIEW_KEY, new BoardView(MasterEnum.Color.WHITE, game.getB2()));
      }
      vm.put(GetGameRoute.BOARD_VIEW_KEY, httpSession.attribute(GetGameRoute.BOARD_VIEW_KEY));

      httpSession.attribute(GetGameRoute.VIEW_MODE, MasterEnum.ViewMode.PLAY);
      vm.put(GetGameRoute.VIEW_MODE, httpSession.attribute(GetGameRoute.VIEW_MODE));

      httpSession.attribute(GetGameRoute.ACTIVE_COLOR, MasterEnum.Color.RED);
      vm.put(GetGameRoute.ACTIVE_COLOR, httpSession.attribute(GetGameRoute.ACTIVE_COLOR));

      httpSession.attribute(GetGameRoute.RED_PLAYER, game.getP1());
      vm.put(GetGameRoute.RED_PLAYER, httpSession.attribute(GetGameRoute.RED_PLAYER));

      httpSession.attribute(GetGameRoute.WHITE_PLAYER, httpSession.attribute(CUR_PLAYER_ATTR));
      vm.put(GetGameRoute.WHITE_PLAYER, httpSession.attribute(GetGameRoute.WHITE_PLAYER));

      httpSession.attribute(GetGameRoute.GAME_ATTR, game);
      response.redirect(WebServer.GAME_URL);
      halt();
        return null;
    }


    LOG.finer("GetHomeRoute is invoked.");
    //

    return templateEngine.render(new ModelAndView(vm , VIEW_NAME));
  }

}