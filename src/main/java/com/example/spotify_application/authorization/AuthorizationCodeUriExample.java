package com.example.spotify_application.authorization;

import com.example.spotify_application.service.Keys;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import java.net.URI;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class AuthorizationCodeUriExample {
    private static final String clientId = Keys.CLIENT_ID.getKey();
    private static final String clientSecret = Keys.CLIENT_SECRET.getKey();
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/v1/get-user-code");

    public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();
    private static final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
              .show_dialog(true)
            .build();

    public static void authorizationCodeUri_Sync() {
        final URI uri = authorizationCodeUriRequest.execute();

        System.out.println("URI: " + uri.toString());
    }

    public static void authorizationCodeUri_Async() {
        try {
            final CompletableFuture<URI> uriFuture = authorizationCodeUriRequest.executeAsync();

            // Thread free to do other tasks...

            // Example Only. Never block in production code.
            final URI uri = uriFuture.join();

            System.out.println("URI: " + uri.toString());
        } catch (CompletionException e) {
            System.out.println("Error: " + e.getCause().getMessage());
        } catch (CancellationException e) {
            System.out.println("Async operation cancelled.");
        }
    }

    public static void main(String[] args) {
        authorizationCodeUri_Sync();
        authorizationCodeUri_Async();
    }
}
