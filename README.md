Spotigoo (WIP)
==============
An assistant for migrating between Spotify and Google Music

Why an Android app?
-------------------
While Spotify is nice enough to have official api's available to freely manage
your music, Google's service is not. The current reverse engineering of
Google's music service requires the Google Music Android app to have been
installed, so we might as well follow along and take a few steps out of the
authentication process.

Building
--------
To build, you must specify your own Spotify client id. To get a client id,
sign up as a Spotify developer and then
[create an app](https://developer.spotify.com/my-applications/#!/applications).
This id needs to be put in a resource string named "spotify_client_id".
To do this without breaking things, create a resource file named:
```
spotigoo-music-migration/src/main/res/values/private_keys.xml
```
And put the following in the newly created resource file:
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <string name="spotify_client_id">[Your ID]</string>
</resources>
```