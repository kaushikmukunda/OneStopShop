package material.km.com.onestopshop.model;

import org.json.JSONException;
import org.json.JSONObject;

public class Movie extends Card {
    @Override
    public void buildFromJson(JSONObject json) {
        super.buildFromJson(json);
        try {
            this.extra = json.getString("movieExtraImageURL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
