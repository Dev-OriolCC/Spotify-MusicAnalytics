package com.example.spotify_application.controller;

import com.example.spotify_application.service.TrackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

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

    private final TrackService trackService;

    public TracksController(TrackService trackService) {
        this.trackService = trackService;
    }

    /**
     * @param trackName
     * @param trackArtist
     * @return
     * @Example
     */
    @GetMapping
    public ResponseEntity<Paging<Track>> getTrack(
            @RequestParam(name = "track", defaultValue = "honey") String trackName,
            @RequestParam(name = "artist", defaultValue = "Halsey") String trackArtist
    ) {
        Paging<Track> tracks = trackService.searchTrack(trackName, trackArtist);
        if (tracks != null) {
            return ResponseEntity.ok(tracks);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
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
        GetUsersTopTracksRequest getUsersTopTracksRequest = trackService.getUsersTopTracks(timeRange, limit, offset);
        if (getUsersTopTracksRequest != null) {
            return ResponseEntity.ok(getUsersTopTracksRequest);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }




}
