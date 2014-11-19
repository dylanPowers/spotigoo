package com.dylankpowers.spotigoo;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.spotify.sdk.android.authentication.SpotifyAuthentication;

public class MigrationService extends Service {
    private final MigrationBinder mBinder = new MigrationBinder();


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

public class SpotigooSpotifyAuthentication extends SpotifyAuthentication {
    private static final String REDIRECT_URI = "spotigoo://callback/";
    private static final String[] PERMISSIONS = new String[] {
            "playlist-read-private", "playlist-modify-public", "playlist-modify-private"
    };

    public static void openAuthWindow(String clientId, Activity activity) {
        SpotifyAuthentication.openAuthWindow(clientId, "token", REDIRECT_URI,
                                             PERMISSIONS, null, activity);
    }
}
