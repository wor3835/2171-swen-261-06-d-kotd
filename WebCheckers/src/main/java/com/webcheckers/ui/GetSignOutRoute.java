package com.webcheckers.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateEngine;

/**
 * Created by kzalb on 10/11/2017.
 */
public class GetSignOutRoute implements Route{
    private static final Logger LOG = Logger.getLogger(GetSignOutRoute.class.getName());

    private final TemplateEngine templateEngine;

    /**
     * Create the Spark Route (UI controller) for the
     * {@code GET /signing} HTTP request.
     *
     * @param templateEngine
     *   the HTML template rendering engine
     */
    public GetSignOutRoute(final TemplateEngine templateEngine) {
        // validation
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        //
        this.templateEngine = templateEngine;
        //
        LOG.config("GetSignOutRoute is initialized.");
    }

    /**
     * Render the WebCheckers SignIn page.
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
        LOG.finer("GetSignOutRoute is invoked.");
        //
        Map<String, Object> vm = new HashMap<>();
        vm.put("title", "Sign out to exit");
        return templateEngine.render(new ModelAndView(vm , "home.ftl"));
    }
}
