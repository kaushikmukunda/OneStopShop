package material.km.com.onestopshop.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Place extends Card {
    @Override
    public void buildFromJson(JSONObject json) {
        super.buildFromJson(json);
        try {
            this.extra = json.getString("placeCategory");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
