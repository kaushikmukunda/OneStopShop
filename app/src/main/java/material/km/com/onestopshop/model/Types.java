package material.km.com.onestopshop.model;

import java.util.HashMap;

public class Types {
    public static final int PLACE = 1;
    public static final int MOVIE = 2;
    public static final int MUSIC = 3;

    public static final HashMap<String, Integer> typeLookup = new HashMap<>(3);

    static {
        typeLookup.put("place", PLACE);
        typeLookup.put("movie", MOVIE);
        typeLookup.put("music", MUSIC);
    }
}
