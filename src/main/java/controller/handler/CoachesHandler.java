package controller.handler;

import com.server.model.entity.Coach;
import com.server.model.entity.User;
import com.server.model.service.CoachService;
import com.server.model.service.UserService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.HttpServerSport;
import controller.JsonUtils;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class CoachesHandler implements HttpHandler {

    UserService userService;
    CoachService coachService;

    public CoachesHandler(UserService userService, CoachService coachService) {
        this.userService = userService;
        this.coachService = coachService;
    }

    private String createJSONCoachesList(List<Coach> coaches) {
        JSONArray jsonArray = new JSONArray();
        for (Coach coach : coaches) {
            jsonArray.add(JsonUtils.createJSONCoach(coach));
        }
        return jsonArray.toString();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,authorization");
            httpExchange.sendResponseHeaders(204, -1);
            return;
        }

        String auth;
        Headers headers = httpExchange.getRequestHeaders();

        try {
            auth = headers.getFirst("Authorization");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String[] emailPass = HttpServerSport.parseToken(auth);

        User user;
        try {
            user = userService.validateUser(emailPass[0], emailPass[1]);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        }


        String response = "";

        if (user == null) {
            response = "user is null";
            HttpServerSport.writeBadResponse(httpExchange, response);
        } else {
            response = createJSONCoachesList(coachService.getAll());
        }
        HttpServerSport.writeSuccessResponse(httpExchange, response);
    }


}
