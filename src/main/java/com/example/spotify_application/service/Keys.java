package com.example.spotify_application.service;

import org.springframework.beans.factory.annotation.Value;

public enum Keys {
    CLIENT_ID(System.getenv("clientId")), CLIENT_SECRET(System.getenv("clientSecret")), CLIENT_PLAYLIST(System.getenv("playlist"));
    private final String key;
    Keys(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
