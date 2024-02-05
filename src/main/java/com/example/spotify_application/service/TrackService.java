package com.example.spotify_application.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.enums.ModelObjectType;
import se.michaelthelin.spotify.model_objects.miscellaneous.CurrentlyPlaying;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.personalization.simplified.GetUsersTopTracksRequest;
import se.michaelthelin.spotify.requests.data.player.GetUsersCurrentlyPlayingTrackRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.util.Arrays;

import static com.example.spotify_application.controller.AuthController.spotifyApi;

@Service
public class TrackService {
    

    public CurrentlyPlaying getCurrentlyPlayingTrack() {
        try {
            final GetUsersCurrentlyPlayingTrackRequest currentlyPlayingTrackRequest = spotifyApi.getUsersCurrentlyPlayingTrack().build();
            final CurrentlyPlaying currentlyPlayingTrack = currentlyPlayingTrackRequest.execute();

            System.out.println(currentlyPlayingTrack.getItem().getName());
            System.out.println(getArtistFromTrackObject(currentlyPlayingTrack));

            return currentlyPlayingTrack;
        } catch (Exception e) {
            return null;
        }
    }

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

    public PlaylistTrack[] getTracksFromPlaylistById(String playlistId) {
        try {
            final GetPlaylistsItemsRequest getPlaylistsItemsRequest = spotifyApi.getPlaylistsItems(playlistId).build();
            final Paging<PlaylistTrack> playlistTracks = getPlaylistsItemsRequest.execute();
            System.out.println("Total: "+playlistTracks.getTotal());
            return playlistTracks.getItems();
        } catch (Exception e) {
            System.out.println("getTracksFromPlaylist()__Error: "+e);
        }

        return new PlaylistTrack[0];
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

    public String getArtistFromTrackObject(CurrentlyPlaying currentlyPlaying) {
        String currentTrack = currentlyPlaying.toString();
        int startIndex = currentTrack.indexOf("ArtistSimplified(name=");

        if (startIndex != -1) {
            // Extract the substring starting from the index of "ArtistSimplified(name=" to the end
            String remainingString = currentTrack.substring(startIndex + "ArtistSimplified(name=".length());

            // Find the index of the closing parenthesis
            int endIndex = remainingString.indexOf(",");

            if (endIndex != -1) {
                // Extract the substring up to the closing parenthesis
                return remainingString.substring(0, endIndex);
            }
        }
        return currentTrack;
    }



}
