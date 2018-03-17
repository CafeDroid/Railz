package com.cafedroid.android.railz;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by rahulkathuria on 07/03/18.
 */

public class NetworkUtils {
    private static String API_KEY="juagvgqe6f";
    private static final String BaseURL = "https://api.railwayapi.com/v2";
    public static URL generateLiveStatusURL(String train_no, String date){
        Uri uri = Uri.parse(BaseURL).buildUpon().appendPath("live").appendPath("train").appendPath(train_no).appendPath("date")
                .appendPath(date).appendPath("apikey").appendPath(API_KEY).build();
        URL liveStatusURL=null;
        try {
            liveStatusURL = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return liveStatusURL;

    }
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("hello", "Error Response Code :" + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("hello", "Problem retrieving the books json results", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;

    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }



}
