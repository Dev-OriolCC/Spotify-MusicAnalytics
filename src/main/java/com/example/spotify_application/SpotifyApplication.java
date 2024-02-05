package com.example.spotify_application;

import com.example.spotify_application.service.SpotifyCheckerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class SpotifyApplication {
	// TODO

	// Read your current spotify song.. X
	// Return artist and track
	//
	// if it's a Taylor Swift, continue
	// Validate if it's in the list
	// Change it for the Taylor's Version.

	//2.-- TODO
	// Give a Artist X
	// Get all albums from artist X
	// Create playlist and add them
	// Play them
	// If it's not TYs Version, change it
	//
	static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	public static void main(String[] args) {
		SpringApplication.run(SpotifyApplication.class, args);
		//scheduler.scheduleAtFixedRate(SpotifyCheckerService::checkForSongChange, 0, 60, TimeUnit.SECONDS);

	}

}
