package material.km.com.onestopshop;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;


public class SplashActivity extends Activity {
    private BroadcastReceiver mCompletionRx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        mCompletionRx = new InitCompleteRx();
        startService(StartupService.getIntent(this));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mCompletionRx);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(InitCompleteRx.ACTION);
        registerReceiver(mCompletionRx, filter);
    }

    private void startCardsActivity(String mData, Location location) {
        Intent cardIntent = CardsActivity.getIntent(this);
        cardIntent.putExtra(CardsActivity.DATA, mData);
        cardIntent.putExtra(CardsActivity.LOCATION, location);
        startActivity(cardIntent);
        finish();
    }


    private void showErrorMessage(String error) {
        Snackbar.make(findViewById(R.id.splashLayout), error, Snackbar.LENGTH_LONG)
            .show();
    }

    public class InitCompleteRx extends BroadcastReceiver {
        public static final String ACTION = "material.km.com.onestopshop.FINISH_DATA_FETCH";
        public static final String LOCATION = "location";
        public static final String DATA = "data";
        public static final String ERROR = "error";

        @Override
        public void onReceive(Context context, Intent intent) {
            String error = intent.getStringExtra(ERROR);
            String data = intent.getStringExtra(DATA);
            Location location = intent.getParcelableExtra(LOCATION);

            if (error == null) {
                startCardsActivity(data, location);
            } else {
                showErrorMessage(error);
            }
        }
    }
}
