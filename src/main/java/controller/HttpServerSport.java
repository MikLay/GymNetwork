package controller;


import com.server.model.QueriesManager;
import com.server.model.dao.ClientDao;
import com.server.model.dao.CoachDao;
import com.server.model.dao.UserDao;
import com.server.model.dao.impl.ClientDaoImpl;
import com.server.model.dao.impl.CoachDaoImpl;
import com.server.model.dao.impl.UserDaoImpl;
import com.server.model.service.ClientService;
import com.server.model.service.CoachService;
import com.server.model.service.UserService;
import com.server.model.service.impl.ClientServiceImpl;
import com.server.model.service.impl.CoachServiceImpl;
import com.server.model.service.impl.UserServiceImpl;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import controller.handler.AuthHandler;
import controller.handler.CoachesHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class HttpServerSport {
    private static UserService userService;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(3001), 0);
        ClientDao clientDao = new ClientDaoImpl(QueriesManager.getProperties("client"));
        CoachDao coachDao = new CoachDaoImpl(QueriesManager.getProperties("coach"));
        UserDao userDao = new UserDaoImpl(QueriesManager.getProperties("user"));

        userService = new UserServiceImpl(userDao);
        ClientService clientService = new ClientServiceImpl(clientDao);
        CoachService coachService = new CoachServiceImpl(coachDao);

        server.createContext("/auth", new AuthHandler(userService, clientService));
        server.createContext("/coaches", new CoachesHandler(userService, coachService));

        server.setExecutor(null); // creates a default executor

        server.start();
        System.out.println("The server is running");
    }

    public static void writeSuccessResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.getResponseHeaders().add("Content-Type", "application/json");
        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static void writeBadResponse(HttpExchange httpExchange, String response) throws IOException {
        httpExchange.sendResponseHeaders(400, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    public static boolean isAuth(HttpExchange httpExchange) {
        String auth;
        Headers headers = httpExchange.getRequestHeaders();

        try {
            auth = headers.getFirst("Authorization");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        String[] emailPass = HttpServerSport.parseToken(auth);
        return userService.validateAccess(emailPass[0], emailPass[1]);
    }

    public static String[] parseToken(String token) {
        return token.split("\\+");
    }
}
