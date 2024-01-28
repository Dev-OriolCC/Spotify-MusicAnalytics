package com.example.spotify_application;

import com.example.spotify_application.service.Keys;
import com.example.spotify_application.service.SpotifyCheckerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpotifyApplication {
	// Read your current spotify song.. if it's a Taylor Swift, continue
	// Validate if it's in the list
	// Change it for the Taylor's Version.

	//2.--
	// Give a Playlist
	// Check for Taylor Swift Songs
	// If it's not TYs Version, change it
	//
	static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
		SpringApplication.run(SpotifyApplication.class, args);
		scheduler.scheduleAtFixedRate(SpotifyCheckerService::checkForSongChange, 0, 30, TimeUnit.SECONDS);

	}

}
