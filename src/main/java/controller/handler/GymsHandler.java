package controller.handler;

import com.server.model.HibernateUtil;
import com.server.model.entity.Gym;
import com.server.model.service.GymService;
import com.server.model.service.UserService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.HttpServerSport;
import controller.JsonUtils;
import lombok.extern.log4j.Log4j;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;

import java.io.IOException;
import java.util.List;

@Log4j
public class GymsHandler implements HttpHandler {

    private UserService userService;
    private GymService gymService;

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

        log.info("handle start with HttpExchange: " + httpExchange);
        if (HttpServerSport.addResponses(httpExchange)) return;

        String response;
        Transaction transactionGetAllUsers = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();

        try {
            if (!HttpServerSport.isAuth(httpExchange)) {
                response = "Access denied";
                HttpServerSport.writeBadResponse(httpExchange, response);
            } else {
                response = createJSONGymsList(gymService.getAll());
                HttpServerSport.writeSuccessResponse(httpExchange, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transactionGetAllUsers.commit();
        }

        log.info("handle end");
    }

}
