package com.webcheckers.ui;


import com.google.gson.Gson;
import com.webcheckers.appl.GameLobby;
import com.webcheckers.appl.MasterEnum;
import com.webcheckers.appl.Message;
import com.webcheckers.appl.PlayerLobby;
import com.webcheckers.model.Player;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import spark.Session;
import spark.TemplateEngine;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by arthu on 11/7/2017.
 */
public class WebServerTest {

    private WebServer CuT;

    private TemplateEngine engine;
    private Gson gson;
    private PlayerLobby playerLobby;
    private GameLobby gameLobby;

    @Before
    public void set_up(){
        engine = mock(TemplateEngine.class);
        gson = new Gson();
        playerLobby = new PlayerLobby();
        gameLobby = new GameLobby();

        CuT = new WebServer(engine, gson, playerLobby, gameLobby);
    }

    @Test
    public void test_init(){
        CuT.initialize();
    }
}
