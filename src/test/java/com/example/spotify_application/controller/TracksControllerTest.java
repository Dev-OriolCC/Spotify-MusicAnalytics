package com.example.spotify_application.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TracksControllerTest {

    @Test
    public void getTrackSingleWord() {
        //https://api.spotify.com/v1/search?q=remaster%2520track%3AAMOUR%2520artist%3AThe%2520Warning&type=track&limit=1
        String track = "vampire";
        String expectedUri = "https://api.spotify.com/v1/search?q=remaster%2520track%3Avampire%2520artist%3AOlivia%2520Rodrigo&type=track&limit=1";
        track = track.replaceAll(" ", "%20");

        String uri = "https://api.spotify.com/v1/search?q=remaster%2520track%3A"+track+"%2520artist%3AOlivia%2520Rodrigo&type=track&limit=1";
        System.out.println(uri);
        assertEquals(uri, expectedUri);
    }

    @Test
    public void getTrackTwoWords() {
        String track = "Misery Business";
        String expectedUri = "https://api.spotify.com/v1/search?q=remaster%2520track%3AMisery%20Business%2520artist%3AOlivia%2520Rodrigo&type=track&limit=1";
        track = track.replaceAll(" ", "%20");

        String uri = "https://api.spotify.com/v1/search?q=remaster%2520track%3A"+track+"%2520artist%3AOlivia%2520Rodrigo&type=track&limit=1";
        System.out.println(uri);
        assertEquals(uri, expectedUri);
    }

    @Test
    public void getTrackMultipleWordsAndArtistMultipleWords() {
        String track = "Heart Attack - Rock Version";
        String artist = "Demi Lovato";
        String expectedUri = "https://api.spotify.com/v1/search?q=remaster%2520track%3AHeart%2520Attack%2520-%2520Rock%2520Version%2520artist%3ADemi%2520Lovato&type=track&limit=1";
        track = track.replaceAll(" ", "%2520");
        artist = artist.replaceAll(" ", "%2520");
        String uri = "https://api.spotify.com/v1/search?q=remaster%2520track%3A"+track+"%2520artist%3A"+artist+"&type=track&limit=1";
        System.out.println(uri);
        assertEquals(uri, expectedUri);
    }

    @Test
    public void getTrackSingleWordAndArtistTwoWords() {
        String track = "AMOUR";
        String artist = "The Warning";
        String expectedUri = "https://api.spotify.com/v1/search?q=remaster%2520track%3AAMOUR%2520artist%3AThe%2520Warning&type=track&limit=1";
        track = track.replaceAll(" ", "%2520");
        artist = artist.replaceAll(" ", "%2520");
        String uri = "https://api.spotify.com/v1/search?q=remaster%2520track%3A"+track+"%2520artist%3A"+artist+"&type=track&limit=1";
        System.out.println(uri);
        assertEquals(uri, expectedUri);
    }

}