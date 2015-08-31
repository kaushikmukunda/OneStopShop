package material.km.com.onestopshop.card;

import android.view.LayoutInflater;
import android.view.View;

import material.km.com.onestopshop.R;
import material.km.com.onestopshop.model.Types;

public class CardHolderFactory {
    public static CardHolder getCardHolder(int type, LayoutInflater inflater) {

        if (type == Types.PLACE) {
            View v = inflater.inflate(R.layout.places_card, null);
            return new PlacesCardHolder(v);
        } else if (type == Types.MUSIC) {
            View v = inflater.inflate(R.layout.music_card, null);
            return new MusicCardHolder(v);
        } else {
            View v = inflater.inflate(R.layout.movie_card, null);
            return new MovieCardHolder(v);
        }
    }
}
