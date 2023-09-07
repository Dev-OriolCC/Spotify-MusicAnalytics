package com.example.spotify_application.service;

import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfUsersPlaylistsRequest;

import static com.example.spotify_application.controller.AuthController.spotifyApi;


@Service
public class PlaylistService {

    private final AuthService authService;
    private static final String playlistName = Keys.CLIENT_PLAYLIST.getKey();
    public PlaylistService(AuthService authService) {
        this.authService = authService;
    }
    // Get full info from track

    //* Get Spotify ID
    //* Get User Playlists
    //* Create Playlist
    //* Add Item to Playlist

    public Paging<PlaylistSimplified> getUsersPlaylists() {
        try {
            String userSpotifyId = authService.getCurrentUserProfile().getId();
            final GetListOfUsersPlaylistsRequest getListOfUsersPlaylistsRequest = spotifyApi.getListOfUsersPlaylists(userSpotifyId).build();
            return getListOfUsersPlaylistsRequest.execute();
        } catch (Exception e) {
            return null;
        }
    }

    public Playlist createPlaylist() {
        try {
            String userSpotifyId = authService.getCurrentUserProfile().getId();
            final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist(userSpotifyId, playlistName).build();
            return createPlaylistRequest.execute();
        } catch (Exception e) {
            return null;
        }
    }

    public SnapshotResult addItemToPlaylist() {
        try {
            String[] uri = new String[]{"test"};
            String playlistId = "";
            final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyApi.addItemsToPlaylist(playlistId, uri).build();
            return addItemsToPlaylistRequest.execute();
        } catch (Exception e) {
            return null;
        }
    }

}
