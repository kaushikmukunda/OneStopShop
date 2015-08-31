package material.km.com.onestopshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import material.km.com.onestopshop.adapter.CardsAdapter;
import material.km.com.onestopshop.model.Card;
import material.km.com.onestopshop.model.CardFactory;


public class CardsActivity extends Activity {
    public static final String LOCATION = "location";
    public static final String DATA = "data";

    private RecyclerView mCardsList;
    private TextView mLocation;
    private ArrayList<Card> mCards;
    private Location mLoc;

    public static Intent getIntent(Context context) {
        return new Intent(context, CardsActivity.class);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_cards);


        mLoc = getIntent().getParcelableExtra(LOCATION);
        mCards = buildCards(getIntent().getStringExtra(DATA));
        setupViews();
    }

    private ArrayList<Card> buildCards(String stringExtra) {
        ArrayList<Card> cards = new ArrayList<>(4);

        try {
            JSONObject jsonObject = new JSONObject((stringExtra));
            JSONArray jsonCards = jsonObject.getJSONArray("cards");
            for(int i=0; i<jsonCards.length(); i++) {
                cards.add(CardFactory.buildCard(jsonCards.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cards;
    }

    private void setupViews() {
        mCardsList = (RecyclerView) findViewById(R.id.cardsList);
        CardsAdapter adapter = new CardsAdapter(mCards);
        mCardsList.setLayoutManager(new LinearLayoutManager(this));
        mCardsList.setAdapter(adapter);

        mLocation = (TextView) findViewById(R.id.location);
        mLocation.setText(mLoc.getLatitude() + ", " + mLoc.getLongitude());
    }
}
