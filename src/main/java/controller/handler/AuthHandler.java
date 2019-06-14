package controller.handler;

import com.server.model.HibernateUtil;
import com.server.model.entity.Client;
import com.server.model.entity.Coach;
import com.server.model.entity.User;
import com.server.model.service.ClientService;
import com.server.model.service.CoachService;
import com.server.model.service.UserService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.HttpServerSport;
import controller.JsonUtils;
import org.hibernate.Transaction;
import org.json.simple.JSONObject;

import java.io.IOException;

public class AuthHandler implements HttpHandler {
    private UserService userService;
    private ClientService clientService;
    private CoachService coachService;

    public AuthHandler(UserService userService, ClientService clientService, CoachService coachService) {
        this.userService = userService;
        this.clientService = clientService;
        this.coachService = coachService;
    }

    public static String createJSONClient(Client client) {
        JSONObject jsonClient = new JSONObject();
        jsonClient.put("userType", "client");
        jsonClient.put("userData", JsonUtils.createJSONClient(client));
        return jsonClient.toString();
    }

    public static String createJSONCoach(Coach coach) {
        JSONObject jsonClient = new JSONObject();
        jsonClient.put("userType", "coach");
        jsonClient.put("userData", JsonUtils.createJSONCoach(coach));
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
        Transaction transactionUserAuth = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

        User user;

        try {
            user = userService.authUser(emailPass[0], emailPass[1]);

        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            transactionUserAuth.commit();
        }

        Transaction transactionGetUser = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

        String response;
        if (user == null) {
            response = "Access denied";
            HttpServerSport.writeBadResponse(httpExchange, response);
        } else {
            switch (user.getUserType()) {
                case "client": {
                    response = createJSONClient(clientService.getById(user.getUser_real_id()));
                    break;
                }
                // coach
                default: {
                    response = createJSONCoach(coachService.getById(user.getUser_real_id()));
                    break;
                }
                //TODO: Add admin
            }

            HttpServerSport.writeSuccessResponse(httpExchange, response);
        }

        transactionGetUser.commit();
    }
}
