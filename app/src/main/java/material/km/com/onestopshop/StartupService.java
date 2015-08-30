package material.km.com.onestopshop;

import android.accounts.NetworkErrorException;
import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;

import static material.km.com.onestopshop.SplashActivity.*;

public class StartupService extends IntentService implements GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private static final String TAG = StartupService.class.getSimpleName();
    private GoogleApiClient mLocationApiClient;
    private Location mLocation;
    private String mData;
    private DataFetch mDataFetch;

    public static Intent getIntent(Context context) {
       return new Intent(context, StartupService.class);
    }

    public StartupService() {
        super(StartupService.class.getSimpleName());
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mDataFetch = new DataFetch((ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE));
        fetchData();
        fetchLocation();
    }

    private void fetchLocation() {
        Log.d(TAG, "init location fetch");
        mLocationApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();

        mLocationApiClient.connect();
    }

    private void fetchData() {
        Log.d(TAG, "init data fetch");
        new FetchContent().execute(getString(R.string.data_url));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mLocationApiClient);
        if (location != null) {
            mLocation = location;
            Log.d(TAG, "got location");
            reportResult();
        } else {
            // No cached location, request a single location update
            LocationRequest locationRequest = new LocationRequest();
            locationRequest.setNumUpdates(1);
            locationRequest.setExpirationDuration(5000);
            locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

            LocationServices.FusedLocationApi.requestLocationUpdates(
                mLocationApiClient,
                locationRequest,
                this
                );
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (connectionResult.getErrorCode() == ConnectionResult.SERVICE_DISABLED ||
            connectionResult.getErrorCode() == ConnectionResult.SERVICE_MISSING) {
            Log.e(TAG, "Location Service unavailable!");
            Log.e(TAG, "will use mock data");
            mLocation = new Location("San Diego");
            reportResult();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocation = location;
        Log.d(TAG, "got location");
        reportResult();
    }


    private void reportResult() {
        if (mLocation != null && mData != null) {
            Log.d(TAG, "now got both");
            Log.d(TAG, mData);
            Log.d(TAG, mLocation.toString());

            Intent response = new Intent();
            response.setAction(SplashActivity.InitCompleteRx.ACTION);
            response.putExtra(SplashActivity.InitCompleteRx.DATA, mData);
            response.putExtra(SplashActivity.InitCompleteRx.LOCATION, mLocation);
            sendBroadcast(response);
            stopSelf();
        }
    }

    public class FetchContent extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... url) {
            String data = "{}";
            try {
                data = mDataFetch.getDataFromUrl(url[0]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NetworkErrorException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d(TAG, "Data fetched");
            mData = s;
            reportResult();
        }
    }

}
