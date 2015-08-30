package material.km.com.onestopshop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class CardsActivity extends Activity {

    public static final String LOCATION = "location";
    public static final String DATA = "data";

    public static Intent getIntent(Context context) {
        return new Intent(context, CardsActivity.class);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cards);
    }
}
