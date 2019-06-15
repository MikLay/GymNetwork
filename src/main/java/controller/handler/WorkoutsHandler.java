package controller.handler;

import com.server.model.HibernateUtil;
import com.server.model.entity.User;
import com.server.model.entity.Workout;
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
public class WorkoutsHandler implements HttpHandler {

    WorkoutService workoutService;

    public WorkoutsHandler(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }


    private String createJSONWorkouts(List<Workout> workouts) {
        JSONObject jsonObject = new JSONObject();

        JSONArray jsonWorkouts = new JSONArray();

        workouts.stream().forEach(workout -> jsonWorkouts.add(JsonUtils.createJSONWorkout(workout)));

        jsonObject.put("workouts", jsonWorkouts);

        return jsonObject.toString();
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        log.info("handle start with httpExchange: " + httpExchange);

        if (HttpServerSport.addResponses(httpExchange)) return;

        String response = "Default exception";

        Transaction transactionGetAllUsers = HibernateUtil.getSessionFactory().getCurrentSession().beginTransaction();
        User user = HttpServerSport.getUser(httpExchange);
        if (user == null) {
            response = "Access denied";
            HttpServerSport.writeBadResponse(httpExchange, response);
        } else {
            Integer natural_id = user.getUser_real_id();
            switch (user.getUserType()) {
                //coach
                case "coach": {
                    response = createJSONWorkouts(workoutService.findByCoach(natural_id));
                    break;
                }

                case "manager": {
                    response = createJSONWorkouts(workoutService.findByGym(natural_id));
                    break;
                }
                default:
                case "client": {
                    try {
                        response = createJSONWorkouts(workoutService.findByClient(natural_id));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }

            }

            HttpServerSport.writeSuccessResponse(httpExchange, response);
        }
        transactionGetAllUsers.commit();
        log.info("handle end");
    }

}
