package controller;

import com.server.model.entity.Client;
import com.server.model.entity.Coach;
import com.server.model.entity.Gym;
import com.server.model.entity.GymPhoto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonUtils {
    public static JSONObject createJSONClient(Client client) {
        JSONObject jsonClient = new JSONObject();
        jsonClient.put("clientId", client.getClientId().toString());
        jsonClient.put("name", writeName(client.getLastname(), client.getFirstname(), client.getMiddlename()));
        jsonClient.put("photo", client.getPhotoUrl());
        jsonClient.put("email", client.getEmail());
        jsonClient.put("phone", client.getPhone());
        jsonClient.put("birthDate", client.getBirthDate().toString());
        return jsonClient;
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

}
