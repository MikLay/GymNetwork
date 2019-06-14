package controller.handler;

import com.server.model.HibernateUtil;
import com.server.model.entity.Coach;
import com.server.model.service.CoachService;
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
public class CoachesHandler implements HttpHandler {

    private UserService userService;
    private CoachService coachService;

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
        log.info("handle start with httpExchange: " + httpExchange);

        if (HttpServerSport.addResponses(httpExchange)) return;

        String response;
        Transaction transactionGetAllUsers = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        try {
            if (!HttpServerSport.isAuth(httpExchange)) {
                response = "User is unauthorized";
                HttpServerSport.writeBadResponse(httpExchange, response);
            } else {
                response = createJSONCoachesList(coachService.getAll());
                HttpServerSport.writeSuccessResponse(httpExchange, response);
            }
        } catch (Exception e) {
            log.info("handle caught Exception: " + e);
            e.printStackTrace();
        } finally {
            transactionGetAllUsers.commit();
        }
        log.info("handle end");
    }


}
