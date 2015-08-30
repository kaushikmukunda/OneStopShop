package material.km.com.onestopshop;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


public class SplashActivity extends Activity {
    private BroadcastReceiver mCompletionRx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        startService(StartupService.getIntent(this));
        setupBroadcastReceiver();
    }

    private void setupBroadcastReceiver() {
        IntentFilter filter = new IntentFilter(InitCompleteRx.ACTION);
        mCompletionRx = new InitCompleteRx();
        registerReceiver(mCompletionRx, filter);
    }

    private void startCardsActivity(String mData, Location location) {
        Intent cardIntent = CardsActivity.getIntent(this);
        cardIntent.putExtra(CardsActivity.DATA, mData);
        cardIntent.putExtra(CardsActivity.LOCATION, location);
        startActivity(cardIntent);
        finish();
    }


    public class InitCompleteRx extends BroadcastReceiver {
        public static final String ACTION = "material.km.com.onestopshop.FINISH_DATA_FETCH";
        public static final String LOCATION = "location";
        public static final String DATA = "data";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("DBG", "got broadcast Listener");
            String data = intent.getStringExtra(DATA);
            Location location = intent.getParcelableExtra(LOCATION);
            startCardsActivity(data, location);
        }
    }
}
