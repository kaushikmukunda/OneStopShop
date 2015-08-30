package material.km.com.onestopshop;

import android.accounts.NetworkErrorException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetch {
    private ConnectivityManager mConnectivityManager;

    public DataFetch(ConnectivityManager connectivityManager) {
        mConnectivityManager = connectivityManager;
    }

    public String getDataFromUrl(String dataUrl) throws IOException, NetworkErrorException {
        StringBuilder json = new StringBuilder();
        InputStream is = null;

        if (!hasValidConnection()) {
            Log.w("DBG", "No connection available");
            throw new NetworkErrorException();
        }

        try {
            URL url = new URL(dataUrl);
            // Setup Connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("charset", "utf-8");
            conn.addRequestProperty("content-type", "application/json");
            conn.setReadTimeout(1000 /* milliseconds */);
            conn.setConnectTimeout(1500 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            // Parse if valid response is obtained
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                is = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    json.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return json.toString();
    }

    private boolean hasValidConnection() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

}
