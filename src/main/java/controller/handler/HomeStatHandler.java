package controller.handler;

import com.server.model.HibernateUtil;
import com.server.model.entity.*;
import com.server.model.service.ClientService;
import com.server.model.service.CoachService;
import com.server.model.service.GymService;
import com.server.model.service.WorkoutService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import controller.HttpServerSport;
import controller.JsonUtils;
import lombok.extern.log4j.Log4j;
import org.hibernate.Transaction;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

@Log4j
public class HomeStatHandler implements HttpHandler {
    GymService gymService;
    private CoachService coachService;
    private WorkoutService workoutService;
    private ClientService clientService;

    public HomeStatHandler(CoachService coachService, WorkoutService workoutService,
                           GymService gymService, ClientService clientService) {
        this.coachService = coachService;
        this.workoutService = workoutService;
        this.gymService = gymService;
        this.clientService = clientService;
    }

    private String createJSONClientStats(List<Workout> workouts,
                                         List<Gym> gyms, List<Coach> coaches) {
        JSONObject jsonClientStats = new JSONObject();
        jsonClientStats.put("workouts", workouts.size());

        JSONArray gymsStats = new JSONArray();

        gyms.stream().forEach(gym -> gymsStats.add(JsonUtils.createJSONGym(gym)));

        JSONArray coachesStats = new JSONArray();

        coaches.stream().forEach(coach -> coachesStats.add(JsonUtils.createJSONCoach(coach)));

        jsonClientStats.put("gyms", gymsStats);
        jsonClientStats.put("coaches", coachesStats);
        return jsonClientStats.toString();
    }

    private String createJSONCoachStats(List<Workout> workouts, List<Gym> gyms, List<Client> clients) {
        JSONObject jsonCoachStats = new JSONObject();
        jsonCoachStats.put("workouts", workouts.size());

        JSONArray gymsStats = new JSONArray();

        gyms.stream().forEach(gym -> {
            gymsStats.add(JsonUtils.createJSONGym(gym));
        });

        JSONArray clientStats = new JSONArray();

        clients.stream().forEach(client -> {
            clientStats.add(JsonUtils.createJSONClient(client));
        });

        jsonCoachStats.put("gyms", gymsStats);
        jsonCoachStats.put("clients", clientStats);
        return jsonCoachStats.toString();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        log.info("handle start with httpExchange: " + httpExchange);
        if (HttpServerSport.addResponses(httpExchange)) return;

        String response = "";
        Transaction transactionGetAllUsers = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        User user = HttpServerSport.getUser(httpExchange);
        if (user == null) {
            response = "Access denied";
            HttpServerSport.writeBadResponse(httpExchange, response);
        } else {
            Integer natural_id = user.getUser_real_id();
            switch (user.getUserType()) {
                case "client": {
                    try {
                        response = createJSONClientStats(workoutService.findByClient(natural_id),
                                gymService.getByClient(natural_id), coachService.getByClient(natural_id));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
                //coach
                default: {
                    response = createJSONCoachStats(workoutService.findByCoach(natural_id),
                            gymService.getByCoach(natural_id), clientService.getByCoach(natural_id));
                    break;
                }
                //TODO: add admin

            }

            HttpServerSport.writeSuccessResponse(httpExchange, response);
        }
        transactionGetAllUsers.commit();
        log.info("handle end");
    }
}
