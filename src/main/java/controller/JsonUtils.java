package controller;

import com.server.model.entity.Client;
import com.server.model.entity.Coach;
import com.server.model.entity.Gym;
import com.server.model.entity.GymPhotos;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonUtils {
    public static JSONObject createJSONClient(Client client) {
        JSONObject jsonClient = new JSONObject();
        jsonClient.put("clientId", client.getClientId().toString());
        jsonClient.put("name", client.getLastname() + " " + client.getFirstname()
                + (client.getMiddlename() == null ? "" : " " + client.getMiddlename()));
        jsonClient.put("photo", client.getPhotoUrl());
        jsonClient.put("email", client.getEmail());
        jsonClient.put("phone", client.getPhone());
        jsonClient.put("birthDate", client.getBirthDate().toString());
        return jsonClient;
    }

    public static JSONObject createJSONCoach(Coach coach) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("coachId", coach.getCoachId());
        jsonObject.put("name", coach.getLastname() + " " + coach.getFirstname() + (coach.getMiddlename() == null ? "" : (" " + coach.getMiddlename())));
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
    
    public static JSONObject createJSONGym(Gym gym) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("gymId", gym.getGymId());
        StringBuilder address = new StringBuilder();
        address.append(gym.getCountry());
        if(gym.getArea() != null) {
            address.append(" " + address.getArea());
        }
        address.append(" " + gym.getStreet());
        address.append(" " + gym.getBuilding());
        if(gym.getOffice() != null) {
            address.append(" " + gym.getOffice());
        }
        jsonObject.put("address", address.toString());
        jsonObject.put("fine", gym.getFine().toString());
        jsonObject.put("postIndex", gym.getPostIndex());
        jsonObject.put("email", gym.getEmail());
        
        JSONArray photos = new JSONArray();
        gym.getGymPhotos().stream().map(GymPhotos::getPhotoUrl).toArray(String[]::new);
        jsonObject.put("photos", photos);
        
        return jsonObject;
    }
}
