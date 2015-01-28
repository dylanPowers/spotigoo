package dylankpowers.spotigoo;

import android.app.Activity;
import com.spotify.sdk.android.authentication.SpotifyAuthentication;

public class SpotigooSpotifyAuthentication extends SpotifyAuthentication {
    private static final String REDIRECT_URI = "spotigoo://callback/";
    private static final String[] PERMISSIONS = new String[] {
            "playlist-read-private", "playlist-modify-public", 
            "playlist-modify-private"
    };

    public static void openAuthWindow(String clientId, Activity activity) {
        SpotifyAuthentication.openAuthWindow(clientId, "code", REDIRECT_URI,
                                             PERMISSIONS, null, activity);
    }
}
