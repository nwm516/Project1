package org.example;

/**
 * Defines SongManager class
 *
 * @author  Nicholas Miller
 * @version 10/20/2023
 */

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class SongManager implements SongManagerInterface{
    /**
     * Assigns yearCount to a private int
     */
    private int yearCount;
    /**
     * Initializes one-dimesional String array called yearArray
     */
    private String[] yearArray;
    /**
     * Initializes two-dimensional Song array called songArray
     */
    private Song[][] songArray;
    /**
     * Initializes an int variable called totalSongCount
     * & sets it to zero
     */
    int totalSongCount = 0;

    //public SongManager(){}

    /**
     * SongManager constructor, which reads in two files, the yearFile and spotifyFile.
     * This initializes the size of the arrays, and splices song information from the files
     * into fields for the creation of Song objects, which will go into the songArray.
     *
     * @param yearFile  contains a count of the years present within the most popular songs of 2023
     * @param spotifyFile   contains the songs that comprise the yearFile's data
     */
    public SongManager(File yearFile, File spotifyFile) {
        try {
            Scanner yearScanner = new Scanner(yearFile);

            // Initializing the arrays
            yearCount = Integer.parseInt(yearScanner.nextLine());
            yearArray = new String[yearCount];
            yearScanner.nextLine();     // Skip header info

            songArray = new Song[yearCount][];


            for (int yearIndex = 0; yearIndex < yearCount; yearIndex++) {
                String yearInfo = yearScanner.nextLine();
                String[] infoBits = yearInfo.split(",");
                String year = infoBits[0];
                yearArray[yearIndex] = year;
                int songCount = Integer.parseInt(infoBits[1]);
                songArray[yearIndex] = new Song[songCount];
            }

            yearScanner.close();

            CSVReader spotifyReader = new CSVReader(new FileReader(spotifyFile));
            // Header removal line
            spotifyReader.readNext();

            String[] info;
            while ((info = spotifyReader.readNext()) != null) {
                String releaseYear = info[3];
                int yearIndex = findYearIndex(releaseYear);
                if (yearIndex >= 0) {
                    String trackName = info[0];
                    String artistName = info[1];
                    String releaseMonth = info[4];
                    String releaseDay = info[5];
                    String streams = info[8];
                    String spotifyCharts = info[7];
                    String appleCharts = info[10];
                    String shazamCharts = info[13];
                    String bpm = info[14];

                    Song newSong = new Song(trackName, artistName, releaseYear, releaseMonth, releaseDay,
                            streams, spotifyCharts, appleCharts, shazamCharts, bpm);

                    int songIndex = findEmptySlot(yearIndex);
                    songArray[yearIndex][songIndex] = newSong;
                    totalSongCount++;
                    }
                }
            spotifyReader.close();
            } catch (CsvValidationException | IOException e) {
        }
    }

    /**
     * Finds the specific index of the year based upon the song's release year to be used within the songArray.
     *
     * @param releaseYear   year for which we are determining the index of within the song array
     * @return  int index for the year within the song array
     */
    public int findYearIndex(String releaseYear) {
        for (int yearIndex = 0; yearIndex < yearArray.length; yearIndex++) {
            if (yearArray[yearIndex].equals(releaseYear)) {
                return yearIndex;
            }
        }
        return -1;
    }

    /**
     * Finds an empty slot(s) within the specific song's index to place song within.
     *
     * @param yearIndex    year for which to determine if empty slot is present
     * @return  int index of empty slot to place song information within
     */
    private int findEmptySlot(int yearIndex) {
        for (int check = 0; check < songArray[yearIndex].length; check++) {
            if (songArray[yearIndex][check] == null) {
                return check;
            }
        }
        return -1;
    }

    /**
     * Retrieves an array of songs
     * @return      two-dimensional Song array
     */
    public Song[][] getSongArray() {
        return songArray;
    }

    /**
     * Retrieves the count of release years
     * @return      count of release years
     */
    @Override
    public int getYearCount() {
        return 0;
    }

    /**
     * Retrieves the number of songs in the specified release year (by index)
     * @param yearIndex       the index of the release year
     * @return                song count in that release year
     */
    @Override
    public int getSongCount(int yearIndex) {
        return 0;
    }

    /**
     * Retrieves the number of songs in all release years
     * @return                song count in all release years
     */
    @Override
    public int getSongCount() {
        return 0;
    }

    /**
     * Retrieves the release year at the specified index
     * @param yearIndex       index of the desired release year
     * @return                release year
     */
    @Override
    public String getYearName(int yearIndex) {
        return null;
    }

    /**
     * Retrieves the number of songs in the specified release year (by name)
     * @param year       the release year
     * @return           song count in that release year
     */
    @Override
    public int getSongCount(String year) {
        return 0;
    }

    /**
     * Retrieves the song at the specific release year and song index
     * @param yearIndex       release year index
     * @param songIndex       song index
     * @return                song at that array position
     */
    @Override
    public Song getSong(int yearIndex, int songIndex) {
        return null;
    }

    /**
     * Retrieves a copy of the song array for the release year at the specified index
     * @param yearIndex       release year index
     * @return                copy of song array (not a reference to the internal one)
     */
    @Override
    public Song[] getSongs(int yearIndex) {
        return new Song[0];
    }

    /**
     * Retrieves the first release year index associated with the specified song's track name
     * @param trackName       the track name to search for
     * @return                the first release year index containing the specified song, or -1 if not found
     */
    @Override
    public int findSongYear(String trackName) {
        return 0;
    }
}
