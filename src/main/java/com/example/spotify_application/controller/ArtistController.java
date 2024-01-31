package com.example.spotify_application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import se.michaelthelin.spotify.model_objects.specification.Artist;

@RestController
@RequestMapping("/api/v1/artists")
public class ArtistController {


    @GetMapping
    public ResponseEntity<Artist> getArtist(@RequestParam(name = "artist") String artist) {


    }
}
