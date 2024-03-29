package com.example.spotify_application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SpotifyCheckerService {
    static final Logger logger = LoggerFactory.getLogger(SpotifyCheckerService.class);
    static String previousTrackInfo = "";

    private static TrackService trackService = new TrackService();

    public static void checkForSongChange() {
        //
        String currentTrackInfo = String.valueOf(trackService.getCurrentlyPlayingTrack());
        logger.info("Current Track: " + currentTrackInfo);

        if (currentTrackInfo != null && !currentTrackInfo.equals(previousTrackInfo) && previousTrackInfo != "") { // test remove previous
            // Track has changed, perform notification or other actions
            logger.info("Track changed: " + currentTrackInfo);
            // Update the previous track information
            previousTrackInfo = currentTrackInfo;
        }
    }
}
