package com.example.spotify_application.service;

public enum Keys {
    CLIENT_ID(System.getenv("clientId")), CLIENT_SECRET(System.getenv("clientSecret")), CLIENT_PLAYLIST(System.getenv("playlist")),
    GENIUS_KEY(System.getenv("geniusKey")),GENIUS_HOST(System.getenv("geniusHost"));
    private final String key;
    Keys(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
