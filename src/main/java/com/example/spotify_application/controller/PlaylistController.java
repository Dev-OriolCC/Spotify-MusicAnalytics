package com.example.spotify_application.controller;

import com.example.spotify_application.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Playlist;

@RestController
@RequestMapping("/api/v1/playlists")
@CrossOrigin
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<String> getUsersPlaylist() {
        // Paging<SimplifiedPlaylists>
        String playlists = playlistService.getUsersPlaylists();
        if (playlists != null) {
            return ResponseEntity.ok(playlists);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping()
    public ResponseEntity<Playlist> create() {
        Playlist playlist = playlistService.createPlaylist();
        if (playlist != null) {
            return ResponseEntity.ok(playlist);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping("/addTrack/{id}")
    public ResponseEntity<SnapshotResult> addTrack(
            @PathVariable String id
    ) {
        SnapshotResult snapshotResult = playlistService.addItemToPlaylist(id);
        if (snapshotResult != null) {
            return ResponseEntity.ok(snapshotResult);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
