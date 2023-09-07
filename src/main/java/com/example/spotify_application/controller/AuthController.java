package com.example.spotify_application.controller;

import com.example.spotify_application.service.AuthService;
import com.example.spotify_application.service.Keys;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.model_objects.specification.User;
import java.net.URI;

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

    private final AuthService authService;
    public static final SpotifyApi spotifyApi =  new SpotifyApi.Builder()
            .setClientId(clientId)
            .setClientSecret(clientSecret)
            .setRedirectUri(redirectUri)
            .build();

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * @return
     */
    @GetMapping("/login")
    public ResponseEntity<URI> spotifyLogin() {
        URI uri = authService.login();
        if (uri != null) {
            return ResponseEntity.ok(uri);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     *
     * @param userCode
     * @return
     */
    @GetMapping("/get-user-code")
    public ResponseEntity<String> getSpotifyUserCode(@RequestParam("code") String userCode)  {
        System.out.println("Code: "+userCode);
        String accesToken = authService.getAccessToken(userCode);

        if (!accesToken.isEmpty()) { // Pending to test this
            return ResponseEntity.ok(accesToken);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUserProfile() {
        User user = authService.getCurrentUserProfile();
        if (user.getId() != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
