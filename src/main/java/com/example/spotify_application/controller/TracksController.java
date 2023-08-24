package com.example.spotify_application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import static com.example.spotify_application.controller.AuthController.spotifyApi;


/**
 * Controller with endpoints for the spotify tracks requests.
 *
 * @version 1
 * @since 08/22/2023
 */
@RestController
@RequestMapping("/api/v1/tracks")
@CrossOrigin
public class TracksController {


    /**
     *
     * @param trackName
     * @param trackArtist
     * @return
     */
    @GetMapping
    public ResponseEntity<SearchTracksRequest> getTrack(
            @RequestParam(name = "track", defaultValue = "") String trackName,
            @RequestParam(name = "artist", defaultValue = "") String trackArtist
    ) {
        System.out.println("/tracks -> AccessToken: "+spotifyApi.getAccessToken());
        try {
            SearchTracksRequest track = spotifyApi.searchTracks("2").build();
                    //getTrack("dasdasd").build();
            return ResponseEntity.ok(track);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     *
     * @param timeRange
     * @param limit
     * @param offset
     * @return
     */
    @GetMapping("/top")
    public ResponseEntity<GetUsersTopTracksRequest> getTopTracks(
            @RequestParam(name = "time_range", defaultValue = "medium_term") String timeRange,
            @RequestParam(defaultValue = "5") int limit,
            @RequestParam(defaultValue = "0") int offset
    ) {
        System.out.println("/tracks -> AccessToken: "+spotifyApi.getAccessToken());
        try {
            GetUsersTopTracksRequest getUsersTopTracksRequest = spotifyApi.getUsersTopTracks()
                    .time_range(timeRange)
                    .limit(limit)
                    .offset(offset)
                    .build();
            return ResponseEntity.ok(getUsersTopTracksRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }




}
