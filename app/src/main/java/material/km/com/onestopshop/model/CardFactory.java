package material.km.com.onestopshop.model;

import org.json.JSONException;
import org.json.JSONObject;

public class CardFactory {
    public static Card buildCard(JSONObject jsonObject) {
        Card card = null;
        try {
            int type = Types.typeLookup.get(jsonObject.getString("type"));
            if (type == Types.PLACE) {
                card = new Place();
                card.buildFromJson(jsonObject);
            } else if (type == Types.MUSIC){
                card = new Music();
                card.buildFromJson(jsonObject);
            } else if (type == Types.MOVIE) {
                card = new Movie();
                card.buildFromJson(jsonObject);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return card;
    }
}
