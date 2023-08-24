package com.example.spotify_application.controller;

import com.example.spotify_application.service.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;
import java.net.URI;
import java.util.List;

/**
 * Controller with endpoints for the spotify authentication code flow.
 *
 * @version 1
 * @since 08/22/2023
 */
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthController {

    private static final String clientId = Keys.CLIENT_ID.getKey();
    private static final String clientSecret = Keys.CLIENT_SECRET.getKey();
    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/v1/get-user-code");
    public static final SpotifyApi spotifyApi =  new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    /**
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<String> spotifyLogin() {
        try {
            final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri().scope("user-read-private, user-read-email, user-top-read")
                    .show_dialog(true)
                    .build();
            final URI uri = authorizationCodeUriRequest.execute();
            return ResponseEntity.ok(uri.toString());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    /**
     *
     * @param userCode
     * @return
     */
    @GetMapping("/get-user-code")
    public ResponseEntity<String> getSpotifyUserCode(@RequestParam("code") String userCode)  {
        try {
            System.out.println("Code: "+userCode);
            AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode).build();
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            // Set access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            System.out.println("AccessToken: "+spotifyApi.getAccessToken());
            System.out.println("RefreshToken: "+spotifyApi.getRefreshToken());
            return ResponseEntity.ok(spotifyApi.getAccessToken());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     *
     * @param timeRange
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/user-top-artists")
        public ResponseEntity<List<Artist>> getUserTopArtists(
            @RequestParam(name = "time_range", defaultValue = "medium_term") String timeRange,
            @RequestParam(defaultValue = "20") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        System.out.println("/user-top-artists -> AccessToken: "+spotifyApi.getAccessToken());
        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .time_range(timeRange)
                .limit(limit)
                .offset(offset)
                .build();
        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();
            //System.out.println("Spring Boot-Artist"+ Arrays.toString(artistPaging.getItems()));
            return ResponseEntity.ok(List.of(artistPaging.getItems()));
        } catch (Exception e) {
            // Handle errors appropriately, possibly returning an error response
            System.out.println("Error: "+ e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

}
