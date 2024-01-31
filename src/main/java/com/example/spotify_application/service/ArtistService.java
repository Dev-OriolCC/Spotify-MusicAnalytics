package com.example.spotify_application.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchArtistsRequest;

import java.io.IOException;

import static com.example.spotify_application.controller.AuthController.spotifyApi;

@Service
public class ArtistService {

    public Artist getArtistById(String id) throws IOException, ParseException, SpotifyWebApiException {

        final GetArtistRequest getArtistRequest = spotifyApi.getArtist(id).build();
        final Artist artist1 = getArtistRequest.execute();

        System.out.println(artist1);
        return artist1;
    }

    public String getArtistIdByName(String artistName) throws IOException, ParseException, SpotifyWebApiException {

        final SearchArtistsRequest searchArtistsRequest = spotifyApi.searchArtists(artistName).limit(1).build();
        final Paging<Artist> artistPaging = searchArtistsRequest.execute();

        System.out.println(artistPaging);
        String artistFound = "";

        for (Artist artist: artistPaging.getItems()) {
            artistFound = String.valueOf(artist.getId());
        }
        System.out.println(artistFound);


    }


}
