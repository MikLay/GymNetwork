package controller.handler;

import com.server.model.entity.Client;
import com.server.model.entity.User;
import com.server.model.service.ClientService;
import com.server.model.service.UserService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.HttpServerSport;
import controller.JsonUtils;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AuthHandler implements HttpHandler {
    UserService userService;
    ClientService clientService;

    public AuthHandler(UserService userService, ClientService clientService) {
        this.userService = userService;
        this.clientService = clientService;
    }

    public static String createJSONClient(Client client) {
        JSONObject jsonClient = new JSONObject();
        jsonClient.put("userType", "client");
        jsonClient.put("userData", JsonUtils.createJSONClient(client));
        return jsonClient.toString();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        System.out.println("handle works");

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
            switch (user.getUserType()) {
                case "client": {
                    response = createJSONClient(clientService.getById(user.getUser_real_id()));
                }
            }

            HttpServerSport.writeSuccessResponse(httpExchange, response);
        }
    }
}
