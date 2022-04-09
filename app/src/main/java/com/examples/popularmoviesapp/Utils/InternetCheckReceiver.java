package com.examples.popularmoviesapp.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetCheckReceiver extends BroadcastReceiver {
    CheckedIsOnline checkedIsOnline;

    public InternetCheckReceiver(CheckedIsOnline checkedIsOnline) {
        this.checkedIsOnline = checkedIsOnline;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (isOnline(context)) {
            checkedIsOnline.IsOnLine(true);
        } else {
            checkedIsOnline.IsOnLine(false);
        }

    }

    public boolean isOnline(Context context) {
        try {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo Info = cm.getActiveNetworkInfo();
            return (Info != null && Info.isConnected());
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }

    }
}
