package com.example.spotify_application.controller;

import com.example.spotify_application.service.Keys;
import lombok.*;
//import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Arrays;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthController {

    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8080/api/v1/get-user-code");
    //private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:4200/dashboard-view");

    private String code = "";

    private static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(Keys.CLIENT_ID.getKey())
            .setClientSecret(Keys.CLIENT_SECRET.getKey())
            .setRedirectUri(redirectUri)
            .build();

    @GetMapping("/login")
    @ResponseBody
    public String spotifyLogin() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("user-read-private, user-read-email, user-top-read")
                .show_dialog(true)
                .build();
        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();
    }

//    @GetMapping("/get-user-code")
//    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
//        code = userCode;
//        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code).build();
//        try {
//            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
//            //
//            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
//            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
//
//            System.out.println("Expires: "+authorizationCodeCredentials.getExpiresIn());
//        } catch (IOException | SpotifyWebApiException | ParseException e) {
//            System.out.println("Error" + e.getMessage());
//        }
//        //response.sendRedirect("http://localhost:8080/api/v1/user-top-artists"); // Dashboard
//        response.sendRedirect("http://localhost:4200/dashboard-view?token=" +spotifyApi.getAccessToken() );
//
//        System.out.println(spotifyApi.getAccessToken()); // Este es el bueno
//        return spotifyApi.getAccessToken();
//    }

    @GetMapping("/get-user-code")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        String res = createAccessToken(userCode);
        System.out.println(res);
        if (res!= null) {
            System.out.println("Correct");
//            customResponse.setAccessToken(spotifyApi.getAccessToken());
//            customResponse.setUrl("http://localhost:4200/dashboard-view");
//            return new ResponseEntity<>(customResponse, HttpStatus.OK);
            response.sendRedirect("http://localhost:4200/dashboard-view?token="+spotifyApi.getAccessToken());
        } else {
            System.out.println("Error");
//            customResponse.setAccessToken(spotifyApi.getAccessToken());
//            customResponse.setUrl("http://localhost:4200/");
//            return new ResponseEntity<>(customResponse, HttpStatus.BAD_REQUEST);
            response.sendRedirect("http://localhost:4200/");
        }
        return spotifyApi.getAccessToken();

        //response.sendRedirect("http://localhost:8080/api/v1/user-top-artists"); // Dashboard
//        response.sendRedirect("http://localhost:4200/dashboard-view?token=" +spotifyApi.getAccessToken() );
//
//        System.out.println(spotifyApi.getAccessToken()); // Este es el bueno
//        return spotifyApi.getAccessToken();
    }

    private String createAccessToken(String userCode) {
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userCode).build();
        System.out.println("Creating access token... ");
        try {
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();
            //
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            System.out.println("Expires: "+authorizationCodeCredentials.getExpiresIn());
            return spotifyApi.getAccessToken();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

//    private boolean checkToken(String token) {
//        spotifyApi.setAccessToken(token);
//        ;
//        if (spotifyApi.get()) {
//            return true;
//        }
//        return false;
//    }

//    @GetMapping("/user-top-artists") // Check if this works...
//    public Artist[] getUserTopArtists() {
//        System.out.println("Spotify: "+spotifyApi.getAccessToken());
//        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
//                .time_range("medium_term")
//                .limit(20)
//                .offset(5)
//                .build();
//        try {
//            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();
//            return artistPaging.getItems();
//        } catch (Exception e) {
//            System.out.println("Error :C = "+ e);
//        }
//        return new Artist[0];
//    }

    @GetMapping("/user-top-artists")
    public Artist[] getUserTopArtists(@RequestHeader("Authorization") String token) {
        System.out.println("Spring Boot-Token: "+token);
        spotifyApi.setAccessToken(token);
        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .time_range("medium_term")
                .limit(20)
                .offset(5)
                .build();
        try {
            final Paging<Artist> artistPaging = getUsersTopArtistsRequest.execute();
            System.out.println("Spring Boot-Artist"+ Arrays.toString(artistPaging.getItems()));
            return artistPaging.getItems();
        } catch (Exception e) {
            System.out.println("Error :C = "+ e);
        }
        return new Artist[0];

    }

//    @Getter
//    @Setter
//    @AllArgsConstructor
//    @NoArgsConstructor
//    @Builder
//    public class CustomResponse {
//        String accessToken;
//        String url;
//        String status;
//    }

}
