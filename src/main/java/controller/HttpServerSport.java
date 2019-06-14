package controller;


import com.server.model.QueriesManager;
import com.server.model.dao.*;
import com.server.model.dao.impl.*;
import com.server.model.entity.User;
import com.server.model.service.*;
import com.server.model.service.impl.*;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import controller.handler.AuthHandler;
import controller.handler.CoachesHandler;
import controller.handler.GymsHandler;
import controller.handler.HomeStatHandler;

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
        GymDao gymDao = new GymDaoImpl(QueriesManager.getProperties("gym"));
        WorkoutDao workoutDao = new WorkoutDaoImpl(QueriesManager.getProperties("workout"));


        userService = new UserServiceImpl(userDao);
        ClientService clientService = new ClientServiceImpl(clientDao);
        CoachService coachService = new CoachServiceImpl(coachDao);
        GymService gymService = new GymServiceimpl(gymDao);
        WorkoutService workoutService = new WorkoutServiceImpl(workoutDao);

        server.createContext("/auth", new AuthHandler(userService, clientService, coachService));
        server.createContext("/coaches", new CoachesHandler(userService, coachService));
        server.createContext("/gyms", new GymsHandler(userService, gymService));
        server.createContext("/homestat", new HomeStatHandler(coachService, workoutService, gymService, clientService));

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


    public static User getUser(HttpExchange httpExchange) {
        String auth;
        Headers headers = httpExchange.getRequestHeaders();

        try {
            auth = headers.getFirst("Authorization");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String[] emailPass = HttpServerSport.parseToken(auth);

        User user;

        try {
            user = userService.authUser(emailPass[0], emailPass[1]);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public static boolean addResponses(HttpExchange httpExchange) throws IOException {
        httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

        if (httpExchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,authorization");
            httpExchange.sendResponseHeaders(204, -1);
            return true;
        }
        return false;
    }


    public static String[] parseToken(String token) {
        return token.split("\\+");
    }
}
