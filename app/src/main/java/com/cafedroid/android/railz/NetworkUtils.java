package com.cafedroid.android.railz;

import android.net.Uri;

import java.net.MalformedURLException;
import java.net.URL;

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

}
