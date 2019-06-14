package controller;

import com.server.model.entity.Client;
import com.server.model.entity.Coach;
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
}
