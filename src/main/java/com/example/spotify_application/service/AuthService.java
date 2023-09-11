package com.example.spotify_application.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.net.URI;

import static com.example.spotify_application.controller.AuthController.spotifyApi;

@Service
public class AuthService {

    public URI login() {
        try {
            final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                    .scope("user-read-private, user-read-email, user-top-read, playlist-modify-private, playlist-read-private, playlist-read-collaborative")
                    .show_dialog(true)
                    .build();
            return authorizationCodeUriRequest.execute();
        } catch (Exception e) {
            return null;
        }
    }

    public String getAccessToken(String userCode) {
        try {
            AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode).build();
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            System.out.println("AccessToken: "+spotifyApi.getAccessToken());
            System.out.println("RefreshToken: "+spotifyApi.getRefreshToken());
            return spotifyApi.getAccessToken();
        } catch (Exception e) {
            return null;
        }

    }

    public User getCurrentUserProfile() {
        try {
            final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest = spotifyApi.getCurrentUsersProfile().build();
            return getCurrentUsersProfileRequest.execute();
        } catch (Exception e) {
            return null;
        }
    }
}
