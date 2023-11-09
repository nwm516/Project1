package org.example;

import java.util.Objects;

public record Song(String trackName, String artistName, String releasedYear, String releasedMonth,
                   String releasedDay, String totalNumberOfStreamsOnSpotify, String spotifyCharts,
                   String appleCharts, String shazamCharts, String bpm) {
    public Song {
        // Null checks
        Objects.requireNonNull(trackName, "Track name cannot be null.");
        Objects.requireNonNull(artistName, "Artist(s) name cannot be null.");
        Objects.requireNonNull(releasedYear, "Released year cannot be null.");
        Objects.requireNonNull(releasedMonth, "Released month cannot be null.");
        Objects.requireNonNull(releasedDay, "Released day cannot be null.");
        Objects.requireNonNull(spotifyCharts, "Spotify Charts Ranking cannot be null.");
        Objects.requireNonNull(appleCharts, "Apple Charts Ranking cannot be null.");
        Objects.requireNonNull(shazamCharts, "Shazam Charts Ranking cannot be null.");
        Objects.requireNonNull(bpm, "BPM cannot be null.");

        // Empty value checks

        if (trackName.isEmpty()) {
            throw new IllegalArgumentException("Track name cannot be empty.");
        }
        if (artistName.isEmpty()) {
            throw new IllegalArgumentException("Artist(s) name cannot be empty.");
        }
        if (releasedYear.isEmpty()) {
            throw new IllegalArgumentException("Released year cannot be empty.");
        }
        if (releasedMonth.isEmpty()) {
            throw new IllegalArgumentException("Released month cannot be empty.");
        }
        if (releasedDay.isEmpty()) {
            throw new IllegalArgumentException("Released day cannot be empty.");
        }
        if (totalNumberOfStreamsOnSpotify.isEmpty()) {
            throw new IllegalArgumentException("Total number of streams on Spotify cannot be empty.");
        }

        // Valid values checks

        if (!isValidYear(releasedYear)) {
            throw new IllegalArgumentException("Invalid year: " + releasedYear);
        }
        if (!isValidMonth(releasedMonth)) {
            throw new IllegalArgumentException("Invalid month: " + releasedMonth);
        }
        if (!isValidDay(releasedDay)) {
            throw new IllegalArgumentException("Invalid day: " + releasedDay);
        }
        if (!isValidStreams(totalNumberOfStreamsOnSpotify)) {
            throw new IllegalArgumentException(("Invalid total amount of streams: " + totalNumberOfStreamsOnSpotify));
        }
    }

    /**
     * Determines whether releasedYear fits within range of 1930 - 2023,
     * for the sake of this program
     * @param releasedYear      year value to be evaluated
     * @return  boolean value determining whether year in question fits within range
     */
    private static boolean isValidYear(String releasedYear){
        try {
            int year = Integer.parseInt(releasedYear);
            return year >= 1930 && year <= 2023;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determines whether releasedMonth fits within range of 1 -12
     * @param releasedMonth     month value to be evaluated
     * @return  boolean value determining whether month in question fits within range
     */
    private static boolean isValidMonth(String releasedMonth) {
        try {
            int month = Integer.parseInt(releasedMonth);
            return month >= 1 && month <= 12;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determines whether releasedDay fits within range of 1 -12
     * @param releasedDay     day value to be evaluated
     * @return  boolean value determining whether day in question fits within range
     */
    private static boolean isValidDay(String releasedDay) {
        try {
            int day = Integer.parseInt(releasedDay);
            return day >= 1 && day <= 31;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Determines whether totalNumberOfStreamsOnSpotify is a value equal or greater than 0
     * @param totalNumberOfStreamsOnSpotify     streams value to be evaluated
     * @return  boolean value determining whether total streams in question is greater than or equal to 0
     */
    private static boolean isValidStreams(String totalNumberOfStreamsOnSpotify) {
        try {
            long streams = Long.parseLong((totalNumberOfStreamsOnSpotify));
            return streams >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * An override of an equals method to compare against Song-type objects
     * @param object   the reference object with which to compare.
     * @return  boolean value determining whether object in questions compares equally to Song-type object
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Song other = (Song)object;
        return Objects.equals(trackName, other.trackName) &&
                Objects.equals(artistName, other.artistName) &&
                Objects.equals(releasedYear, other.releasedYear) &&
                Objects.equals(releasedMonth, other.releasedMonth) &&
                Objects.equals(releasedDay, other.releasedDay) &&
                Objects.equals(totalNumberOfStreamsOnSpotify, other.totalNumberOfStreamsOnSpotify) &&
                Objects.equals(spotifyCharts, other.spotifyCharts) &&
                Objects.equals(appleCharts, other.appleCharts) &&
                Objects.equals(shazamCharts, other.shazamCharts) &&
                Objects.equals(bpm, other.bpm);
    }
}
