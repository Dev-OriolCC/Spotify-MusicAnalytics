package com.example.spotify_application.controller;

import com.example.spotify_application.service.FileService;
import com.example.spotify_application.service.TrackService;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;

import java.io.IOException;


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
    private final FileService fileService;

    public TracksController(TrackService trackService, FileService fileService) {
        this.trackService = trackService;
        this.fileService = fileService;
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

    @GetMapping("/currentTrack")
    public ResponseEntity<CurrentlyPlaying> getCurrentPlayingTrack() {
        CurrentlyPlaying currentlyPlayingTrack = trackService.getCurrentlyPlayingTrack();
        return ResponseEntity.ok(currentlyPlayingTrack);
    }

    @GetMapping("/playlistTracks")
    public ResponseEntity<String> getPlaylistTracks(
            @RequestParam(name = "playlistId") String playlistId
    ) throws IOException {
       PlaylistTrack[] playlistTracks = trackService.getTracksFromPlaylistById(playlistId);
        CSVPrinter file = fileService.createFile();
        fileService.writeDataOnFile(file, playlistTracks);
        file.flush();

       return ResponseEntity.ok(file.toString());
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
