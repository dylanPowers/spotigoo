package com.dylankpowers.spotigoo;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MigrationService extends Service {
    final MigrationBinder mBinder = new MigrationBinder();


    public class MigrationBinder extends Binder {
        MigrationService getService() {
            return MigrationService.this;
        }
    }

    public void authenticateWithSpotify(String spotifyClientId, Activity reference) {
        SpotigooSpotifyAuthentication.openAuthWindow(spotifyClientId, reference);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}

