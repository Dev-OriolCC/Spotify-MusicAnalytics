package com.example.spotify_application.service;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.util.Arrays;

import static com.example.spotify_application.controller.AuthController.spotifyApi;

@Service
public class TrackService {
    public Paging<Track> searchTrack(String trackName, String trackArtist) {
        trackName = trackName.replaceAll("%20", " ");
        trackArtist = trackArtist.replaceAll("%20", " ");
        String type = ModelObjectType.TRACK.getType();
        try {
            String searchQuery = "track:"+trackName+" artist:\""+trackArtist+"\"";
            System.out.println("Search Query: "+searchQuery);
            final SearchItemRequest searchItemRequestTrack = spotifyApi.searchItem(searchQuery, type).limit(1).offset(0).build(); // offset(1)
            final SearchResult searchResult = searchItemRequestTrack.execute();
            System.out.println("Tracks->"+ Arrays.toString(searchResult.getTracks().getItems()));
            return searchResult.getTracks();
        } catch (Exception e) {
            return null;
        }
    }

    public GetUsersTopTracksRequest getUsersTopTracks(String timeRange, int limit, int offset) {

        try {
            return spotifyApi.getUsersTopTracks()
                    .time_range(timeRange)
                    .limit(limit)
                    .offset(offset)
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

}
