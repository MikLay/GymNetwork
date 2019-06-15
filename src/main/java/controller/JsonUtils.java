package controller;

import com.server.model.entity.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.time.LocalDate;
import java.time.Period;

public class JsonUtils {
    public static JSONObject createJSONClient(Client client) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientId", client.getClientId().toString());
        jsonObject.put("name", writeName(client.getLastname(), client.getFirstname(), client.getMiddlename()));
        jsonObject.put("photo", client.getPhotoUrl());
        jsonObject.put("email", client.getEmail());
        jsonObject.put("phone", client.getPhone());
        jsonObject.put("age", calculateAge(client.getBirthDate().toLocalDate(), LocalDate.now()));
        JSONArray subscriptionsArray = new JSONArray();

        client.getSubscriptions().stream().forEach(subscription -> subscriptionsArray.add((JsonUtils.createJSONSubscription(subscription))));

        jsonObject.put("subscriptions", subscriptionsArray);
        return jsonObject;
    }

    public static JSONObject createJSONCoach(Coach coach) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("coachId", coach.getCoachId());
        jsonObject.put("name", writeName(coach.getLastname(), coach.getFirstname(), coach.getMiddlename()));
        jsonObject.put("email", coach.getEmail());
        jsonObject.put("photo", coach.getPhotoUrl());
        jsonObject.put("phone", coach.getPhone());
        StringBuilder address = new StringBuilder();
        address.append(coach.getCountry());
        if (coach.getArea() != null) {
            address.append(" " + coach.getArea());
        }
        address.append(" " + coach.getTown());
        address.append(" " + coach.getStreet());
        address.append(" " + coach.getBuilding());
        if (coach.getFlat() != null) {
            address.append(" flat " + coach.getFlat());
        }

        jsonObject.put("address", address.toString());
        jsonObject.put("sportRang", coach.getSportRang());
        jsonObject.put("payment", coach.getPayment());
        jsonObject.put("sex", coach.getSex());
        return jsonObject;
    }

    public static String writeName(String lastname, String firstname, String middlename) {
        return lastname + " " + firstname + (middlename == null ? "" : (" " + middlename));
    }

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static StringBuilder writeGymAddress(Gym gym) {
        StringBuilder address = new StringBuilder();
        address.append(gym.getCountry());
        if (gym.getArea() != null) {
            address.append(" " + gym.getArea());
        }
        address.append(" " + gym.getStreet());
        address.append(" " + gym.getBuilding());
        if (gym.getOffice() != null) {
            address.append(" " + gym.getOffice());
        }
        return address;
    }

    public static JSONObject createJSONGym(Gym gym) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gymId", gym.getGymId());
        jsonObject.put("address", writeGymAddress(gym).toString());
        jsonObject.put("fine", gym.getFine());
        jsonObject.put("postIndex", gym.getPostIndex());
        jsonObject.put("email", gym.getEmail());

        JSONArray photos = new JSONArray();
        gym.getGymPhotos().stream().map(GymPhoto::getPhotoUrl).forEach(photos::add);

        jsonObject.put("photos", photos);
        return jsonObject;
    }

    public static JSONObject createJSONSubscription(Subscription subscription) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("subscriptionId", subscription.getSubscriptionId());
        jsonObject.put("startDate", subscription.getStartDate().toString());
        jsonObject.put("startWorkoutTime", subscription.getWorkoutStartTime().toString());
        jsonObject.put("endWorkoutTime", subscription.getWorkoutEndTime().toString());
        jsonObject.put("endDate", subscription.getEndDate().toString());
        jsonObject.put("price", subscription.getPrice());
        return jsonObject;
    }

    public static JSONObject createJSONWorkout(Workout workout) {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("id", workout.getWorkoutId());
        jsonObject.put("date", workout.getStartDate().toLocalDateTime().toLocalDate().toString());
        jsonObject.put("startTime", workout.getStartDate().toLocalDateTime().toLocalTime().toString());
        jsonObject.put("endTime", workout.getEndTime().toString());
        jsonObject.put("price", workout.getSurcharge());
        jsonObject.put("client", JsonUtils.createJSONClient(workout.getClient()));
        jsonObject.put("coach", workout.getCoach() == null ? null : JsonUtils.createJSONCoach(workout.getCoach()));
        jsonObject.put("gym", JsonUtils.createJSONGym(workout.getGym()));

        return jsonObject;
    }
}
