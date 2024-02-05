package com.example.spotify_application.service;

import org.apache.hc.core5.http.ParseException;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.AlbumSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.requests.data.artists.GetArtistsAlbumsRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.example.spotify_application.controller.AuthController.spotifyApi;

@Service
public class AlbumService {
    public Paging<AlbumSimplified> getAlbumsByArtistId(String artistId) throws IOException, ParseException, SpotifyWebApiException {
        final GetArtistsAlbumsRequest getArtistsAlbumsRequest = spotifyApi.getArtistsAlbums(artistId).build();
        final Paging<AlbumSimplified> albums = getArtistsAlbumsRequest.execute();
        System.out.println(albums);
        //TODO
        // Filter Results
        LinkedHashMap<String, String> linkedList = new LinkedHashMap<>();
        //ArrayList arrayList = new ArrayList<>();

        for (AlbumSimplified album: albums.getItems()) {
            linkedList.put(album.getId(), album.getName());

//            album.getId();
//            album.getName();
        }
        // Remove duplicate albums by keywords like LIVE, EDITION, DELUXE
        LinkedHashMap<String, String> duplicatedVariants = new LinkedHashMap<>();
        LinkedHashMap<String, String> baseAlbums = new LinkedHashMap<>();


        for (Map.Entry<String, String> entry : linkedList.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());

        }


        return albums;
    }

    private static String getBaseAlbum(String value) {
        // Extract the base album name without variants
        int index = value.indexOf('(');
        return (index != -1) ? value.substring(0, index).trim() : value.trim();
    }

}
