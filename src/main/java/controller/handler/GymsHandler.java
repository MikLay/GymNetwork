package controller.handler;

import com.server.model.entity.Gym;
import com.server.model.entity.User;
import com.server.model.service.GymService;
import com.server.model.service.UserService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.HttpServerSport;
import controller.JsonUtils;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

public class GymsHandler implements HttpHandler {
  
  UserService userService;
  GymService gymService;
  
  public GymsHandler(UserService userService, GymService gymService) {
    this.userService = userService;
    this.gymService = gymService;
  }
  
  private String createJSONGymsList(List<Gym> gyms) {
    JSONArray jsonArray = new JSONArray();
    for (Gym gym : gyms) {
      jsonArray.add(JsonUtils.createJSONGym(gym));
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
      response = createJSONGymsList(gymService.getAll());
    }
    HttpServerSport.writeSuccessResponse(httpExchange, response);
  }
}
