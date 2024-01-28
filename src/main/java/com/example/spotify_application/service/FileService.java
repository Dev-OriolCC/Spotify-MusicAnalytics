package com.example.spotify_application.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Service
public class FileService {
    private static final String file = "SpotifyPlaylistTracks.csv";

    public CSVPrinter createFile() {

        try {
            BufferedWriter writer = Files.newBufferedWriter(Path.of(file));

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
            return csvPrinter;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void writeDataOnFile(CSVPrinter file, PlaylistTrack[] playlistTracks) throws IOException {
        Arrays.stream(playlistTracks).forEach(playlistTrack -> {
            // Write Track;Artist
            try {

                file.printRecord(playlistTrack.getTrack().getName(),
                        playlistTrack.getTrack().getType()
                );
                System.out.println(playlistTrack.getTrack().getName()+";"+playlistTrack.getTrack());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        //file.printRecord(track, artist);
    }


}
