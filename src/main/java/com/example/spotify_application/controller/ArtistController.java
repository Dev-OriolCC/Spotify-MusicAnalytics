package com.example.spotify_application.controller;

import com.example.spotify_application.service.AlbumService;
import com.example.spotify_application.service.ArtistService;
import org.apache.hc.core5.http.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {

    private final ArtistService artistService;
    private final AlbumService albumService;

    public ArtistController(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @GetMapping
    public ResponseEntity<Artist> getArtist(@RequestParam(name = "artist") String artistName) throws IOException, ParseException, SpotifyWebApiException {
        String artistId =  artistService.getArtistIdByName(artistName);
        Artist artist = artistService.getArtistById(artistId);

        return ResponseEntity.ok(artist);

    }

    @GetMapping("/{id}/albums")
    public ResponseEntity<Paging<AlbumSimplified>> getArtistAlbums(@PathVariable String id) throws IOException, ParseException, SpotifyWebApiException {
        Paging<AlbumSimplified> albums =  albumService.getAlbumsByArtistId(id);
        return ResponseEntity.ok(albums);
    }




}
