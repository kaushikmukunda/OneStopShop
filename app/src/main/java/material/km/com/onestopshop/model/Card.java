package material.km.com.onestopshop.model;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class Card {
    String type;
    String title;
    String thumbNail;
    String extra;

    public void buildFromJson(JSONObject json) {
        try {
            this.type = json.getString("type");
            this.title = json.getString("title");
            this.thumbNail = json.getString("imageURL");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getThumbNail() {
        return thumbNail;
    }

    public String getExtra() {
        return extra;
    }
}
