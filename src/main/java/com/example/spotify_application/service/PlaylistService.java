package com.example.spotify_application.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

import java.util.Arrays;
import java.util.Optional;

import static com.example.spotify_application.controller.AuthController.spotifyApi;


@Service
public class PlaylistService {

    private final AuthService authService;
    private static final String playlistName = Keys.CLIENT_PLAYLIST.getKey();
    public PlaylistService(AuthService authService) {
        this.authService = authService;
    }

    public String getUsersPlaylists() {
        try {
            String userSpotifyId = authService.getCurrentUserProfile().getId();
            final GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = spotifyApi.getListOfUsersPlaylists(userSpotifyId).limit(50).build();
            PlaylistSimplified[] usersPlaylists = getListOfUsersPlaylistsRequest.execute().getItems();

            Optional<PlaylistSimplified> playlistFound = Arrays.stream(usersPlaylists).filter(element ->
                    element.getName().equals(playlistName)) // Change here
                    .findFirst();

            if (playlistFound.isPresent()) {
                return playlistFound.get().getId();
            } else {
                Playlist playlist = this.createPlaylist();
                System.out.println("Created MusicAppTracks Playlist ID-> "+playlist.getId());
                return playlist.getId();
            }
        } catch (Exception e) {
            return null;
        }
    }

    public Playlist createPlaylist() {
        try {
            String userSpotifyId = authService.getCurrentUserProfile().getId(); // Change
            final CreatePlaylistRequest createPlaylistRequest = spotifyApi
                    .createPlaylist(userSpotifyId, playlistName)
                    .public_(false)
                    .description("Spotify playlist created with your Shazam songs")
                    .build();
            return createPlaylistRequest.execute();
        } catch (Exception e) {
            return null;
        }
    }


    public SnapshotResult addItemToPlaylist(String trackId) {
        try {
            String[] uri = new String[]{"spotify:track:"+trackId};
            String playlistId = this.getUsersPlaylists();
            if (playlistId != null) {
                final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(playlistId, uri).build();
                return addItemsToPlaylistRequest.execute();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

}
