package com.kharisma.androidcafe;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by 207-1 on 22/01/2018.
 */

public class NetworkHelper {
    public static boolean hasNetworkAccess(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try {
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
