package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class SongTest {

    @Test
    public void songConstructorTest() {
        Song song = new Song("Track Name", "Artist Name", "2000",
                "1", "1", "1000",
                "1", "1", "1", "123");
        assertNotNull(song);
        assertNotNull(song.trackName());
        assertNotNull(song.artistName());
        assertNotNull(song.releasedYear());
        assertNotNull(song.releasedMonth());
        assertNotNull(song.releasedDay());
        assertNotNull(song.totalNumberOfStreamsOnSpotify());
        assertNotNull(song.spotifyCharts());
        assertNotNull(song.appleCharts());
        assertNotNull(song.shazamCharts());
        assertNotNull(song.bpm());
    }

    @Test
    public void invalidYearTest() {
        assertThrows(IllegalArgumentException.class, () -> new Song("", "Artist Name", "invalidYear",
                "1", "1", "1000",
                "1", "1", "1", "123"));
        assertThrows(IllegalArgumentException.class, () -> new Song("", "Artist Name", "1929",
                "1", "1", "1000",
                "1", "1", "1", "123"));
    }

    @Test
    public void invalidMonthTest() {
        assertThrows(IllegalArgumentException.class, () -> new Song("Track Name", "Artist Name", "2000",
                "0","1", "1000",
                "1", "1", "1", "123"));
        assertThrows(IllegalArgumentException.class, () -> new Song("Track Name", "Artist Name", "2000",
                "13","1", "1000",
                "1", "1", "1", "123"));
    }

    @Test
    public void invalidDayTest() {
        assertThrows(IllegalArgumentException.class, () -> new Song("Track Name", "Artist Name", "2000",
                "1","0", "1000",
                "1", "1", "1", "123"));
        assertThrows(IllegalArgumentException.class, () -> new Song("Track Name", "Artist Name", "2000",
                "1","32", "1000",
                "1", "1", "1", "123"));
    }

    @Test
    public void songAccessorTest() {
        Song song = new Song("Track Name", "Artist Name", "2000",
                "1", "1", "1000",
                "1", "1", "1", "123");

        assertEquals("Track Name", song.trackName());
        assertEquals("Artist Name", song.artistName());
        assertEquals("2000", song.releasedYear());
        assertEquals("1", song.releasedMonth());
        assertEquals("1", song.releasedDay());
        assertEquals("1000", song.totalNumberOfStreamsOnSpotify());
        assertEquals("1", song.spotifyCharts());
        assertEquals("1", song.appleCharts());
        assertEquals("1", song.shazamCharts());
        assertEquals("123", song.bpm());
    }

    @Test
    public void toStringTest() {
        Song testSong = new Song("Track Name", "Artist Name", "2000",
                "1", "1", "1000",
                "1", "1", "1", "123");

        String expectedOutput = "Song[trackName=Track Name, artistName=Artist Name, releasedYear=2000, releasedMonth=1, releasedDay=1, " +
                "totalNumberOfStreamsOnSpotify=1000, spotifyCharts=1, appleCharts=1, shazamCharts=1, bpm=123]";

        String testSongToString = testSong.toString();

        assertEquals(expectedOutput, testSongToString);
    }
}
