package dylankpowers.spotigoo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import kaaes.spotify.webapi.android.*;

import java.util.HashMap;
import java.util.Map;

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
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://api.spotify.com/v1/me";
        SpotifyRequest request = new SpotifyRequest(
                url,
                new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                callback.call(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Spotify request", "Spotify request failed");
            }
        });

        try {
            request.getHeaders().put("Authorization", "Bearer " + spotifyAccessToken);
        } catch (AuthFailureError e) {
            Log.e("Spotify request", "failed setting the authorization header");
        }

        queue.add(request);
    }

    public interface UserInfoCallback {
        public void call(String userInfo);
    }

    public class SpotifyRequest extends StringRequest {
        private HashMap<String, String> mHeaders = new HashMap<String, String>();

        SpotifyRequest(String url, Response.Listener<String> listener,
                       Response.ErrorListener errorListener) {
            super(url, listener, errorListener);
        }

        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            return mHeaders;
        }
    }
}

