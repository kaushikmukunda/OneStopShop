package material.km.com.onestopshop;

import android.accounts.NetworkErrorException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.Downloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetch {
    private ConnectivityManager mConnectivityManager;
    OkHttpClient mClient;

    public DataFetch(ConnectivityManager connectivityManager) {
        mConnectivityManager = connectivityManager;
        mClient = new OkHttpClient();
    }

    public String getDataFromUrl(String dataUrl) throws IOException, NetworkErrorException {
        if (!hasValidConnection()) {
            Log.w("DBG", "No connection available");
            throw new NetworkErrorException();
        }

        Request request = new Request.Builder()
            .url(dataUrl)
            .build();

        Response response = mClient.newCall(request).execute();
        return response.body().string();
    }

    private boolean hasValidConnection() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
