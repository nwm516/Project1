package org.example;

import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File yearFile = new File("count-by-release-year.csv");
        File spotifyFile = new File("spotify-2023.csv");

        SongManager songManager = new SongManager(yearFile, spotifyFile);

        SongViewer songFrame = new SongViewer(yearFile, songManager);
    }
}