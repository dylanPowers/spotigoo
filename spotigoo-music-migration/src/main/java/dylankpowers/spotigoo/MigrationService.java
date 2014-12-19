package dylankpowers.spotigoo;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import kaaes.spotify.webapi.android.*;
import kaaes.spotify.webapi.android.models.User;
import retrofit.Callback;
import retrofit.RetrofitError;

public class MigrationService extends Service {
    final MigrationBinder mBinder = new MigrationBinder();

    public class MigrationBinder extends Binder {
        MigrationService getService() {
            return MigrationService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void getUserInfo(String spotifyAccessToken, final UserInfoCallback callback) {
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(spotifyAccessToken);
        SpotifyService spotifyService = spotifyApi.getService();

        spotifyService.getMe(new Callback<User>() {
            @Override
            public void success(User user, retrofit.client.Response response) {
               Log.d("Spotify request", user.display_name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("Spotify request", "Spotify request failed");
            }
        });
    }

    public interface UserInfoCallback {
        public void call(String userInfo);
    }
}

