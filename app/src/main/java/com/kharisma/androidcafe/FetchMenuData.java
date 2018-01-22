package com.kharisma.androidcafe;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by 207-1 on 15/01/2018.
 */

public class FetchMenuData extends AsyncTask<Void, Void, String> {
    public AsyncResponce delegate = null;

    @Override
    protected String doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        try {
            URL url = null;
            try {
                url = new
                        URL("http://560057.youcanlearnit.net/services/json/itemsfeed.php");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
// biar gampang debugging.
                buffer.append(line + "\n");

            }
            if (buffer.length() == 0) {
// Stream was empty. No point in parsing.

                return null;

            }
            String forecastJsonStr = buffer.toString();
            return forecastJsonStr;
        } catch (IOException e) {
            Log.e("PlaceholderFragment", "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

