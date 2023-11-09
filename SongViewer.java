package org.example;

/**
 * SongViewer supplies GUI for loading and browsing song data by year
 * @author Nicholas Miller
 * @version 11/2/2023
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SongViewer{

    private int yearIndex = 0;
    private int currentSongIndex = 0;
    private Song[][] songArray;
    private JFrame songFrame;
    private JComboBox<String> yearDropDown;
    private JLabel totalSongCountLabel;
    private JTextField trackNameField;
    private JTextField artistsNameField;
    private JTextField releaseDateField;
    private JTextField totalStreamsField;
    private JLabel spotifyChartText;
    private JLabel spotifyChartsRank;
    private JLabel appleChartText;
    private JLabel appleChartsRank;
    private JLabel shazamChartText;
    private JLabel shazamChartsRank;
    private JLabel presenceRankText;
    private JPanel presenceRankPanel;
    private Font fieldFont;

    public SongViewer(File yearFile, SongManager songManager) throws FileNotFoundException {
        Scanner yearScanner = new Scanner(yearFile);
        songArray = songManager.getSongArray();

        //Initialize JFrame object
        songFrame = new JFrame("Popular Songs of 2023");

        // Load Data button
        JButton loadData = new JButton("Load Data");
        loadData.setBounds(25, 25, 100, 30);
        songFrame.add(loadData);

        // Previous Button
        JButton prevButton = new JButton("Prev");
        prevButton.setBounds(130, 25, 100, 30);
        prevButton.setEnabled(false);
        songFrame.add(prevButton);

        // Next Button
        JButton nextButton = new JButton("Next");
        nextButton.setBounds(235, 25, 100, 30);
        nextButton.setEnabled(false);
        songFrame.add(nextButton);

        // Pulling in year information for the drop-down menu
        int yearCount = Integer.parseInt(yearScanner.nextLine());
        String[] yearArray = new String[yearCount];
        yearScanner.nextLine();

        for (int yearIndex = 0; yearIndex < yearCount; yearIndex++) {
            String yearInfo = yearScanner.nextLine();
            String[] infoBits = yearInfo.split(",");
            String year = infoBits[0];
            yearArray[yearIndex] = year;
        }
        yearScanner.close();

        // Drop-down Menu
        yearDropDown = new JComboBox<>(yearArray);
        yearDropDown.setBounds(25, 70, 100, 30);
        yearDropDown.setEnabled(false);
        songFrame.add(yearDropDown);

        songFrame.setSize(375, 400);
        songFrame.setLayout(null);
        songFrame.setVisible(true);

        // Current & Total Song Count Field
        totalSongCountLabel = new JLabel("");
        totalSongCountLabel.setVisible(false);
        totalSongCountLabel.setBounds(130, 70, 200, 30);
        songFrame.add(totalSongCountLabel);

        // Track Name Label
        JLabel trackName = new JLabel("Track Name:");
        trackName.setBounds(25, 125, 100, 30);
        songFrame.add(trackName);

        // Track Name Field
        trackNameField = new JTextField("");
        trackNameField.setEditable(false);
        trackNameField.setBounds(130, 117, 205, 30);
        songFrame.add(trackNameField);

        // Artist(s) Label
        JLabel artists = new JLabel("Artist(s):");
        artists.setBounds(25, 165, 100, 30);
        songFrame.add(artists);

        // Artist(s) Field
        artistsNameField = new JTextField("");
        artistsNameField.setEditable(false);
        artistsNameField.setBounds(130, 157, 205, 30);
        songFrame.add(artistsNameField);

        // Release Date Label
        JLabel releaseDate = new JLabel("Release Date:");
        releaseDate.setBounds(25, 205, 100, 30);
        songFrame.add(releaseDate);

        // Release Date Field
        releaseDateField = new JTextField("");
        releaseDateField.setEditable(false);
        releaseDateField.setBounds(130, 197, 205, 30);
        songFrame.add(releaseDateField);

        // Total Streams Label
        JLabel totalStreams = new JLabel("Total Streams:");
        totalStreams.setBounds(25, 245, 100, 30);
        songFrame.add(totalStreams);

        // Total Streams Field
        totalStreamsField = new JTextField("");
        totalStreamsField.setEditable(false);
        totalStreamsField.setBounds(130, 237, 205, 30);
        songFrame.add(totalStreamsField);

        // Spotify Chart Text
        spotifyChartText = new JLabel("Spotify:");
        spotifyChartText.setBounds(50, 300, 100, 30);
        spotifyChartText.setVisible(false);
        songFrame.add(spotifyChartText);

        // Spotify Charts Rank
        spotifyChartsRank = new JLabel("");
        spotifyChartsRank.setBounds(60, 320, 100, 30);
        spotifyChartsRank.setVisible(false);
        songFrame.add(spotifyChartsRank);

        // Apple Charts Text
        appleChartText = new JLabel("Apple:");
        appleChartText.setBounds(160, 300, 100, 30);
        appleChartText.setVisible(false);
        songFrame.add(appleChartText);

        // Apple Charts Rank
        appleChartsRank = new JLabel("");
        appleChartsRank.setBounds(165, 320, 100, 30);
        appleChartsRank.setVisible(false);
        songFrame.add(appleChartsRank);

        // Shazam Charts Text
        shazamChartText = new JLabel("Shazam:");
        shazamChartText.setBounds(260, 300, 100, 30);
        shazamChartText.setVisible(false);
        songFrame.add(shazamChartText);

        // Shazam Charts Rank
        shazamChartsRank = new JLabel("");
        shazamChartsRank.setBounds(275, 320, 100, 30);
        shazamChartsRank.setVisible(false);
        songFrame.add(shazamChartsRank);

        // Presence & Rank in Charts Text
        presenceRankText = new JLabel("-- Presence & Rank in Charts --");
        presenceRankText.setBounds(90, 270, 250, 30);
        presenceRankText.setVisible(false);
        presenceRankText.setBackground(Color.WHITE);
        songFrame.add(presenceRankText);

        // Presence & Rank in Charts Panel
        presenceRankPanel = new JPanel();
        presenceRankPanel.setBounds(85, 278, 185, 17);
        presenceRankPanel.setBackground(new java.awt.Color(204, 153, 255));
        presenceRankPanel.setVisible(false);
        songFrame.add(presenceRankPanel);

        int fontSize = 12;

        fieldFont = new Font("SanSerif", Font.PLAIN, fontSize);

        // Load Data Button Action Listener
        loadData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                songArray = songManager.getSongArray();

                totalSongCountLabel.setVisible(true);
                loadData.setEnabled(false);
                prevButton.setEnabled(true);
                nextButton.setEnabled(true);
                yearDropDown.setEnabled(true);

                spotifyChartText.setVisible(true);
                appleChartText.setVisible(true);
                shazamChartText.setVisible(true);

                spotifyChartsRank.setVisible(true);
                appleChartsRank.setVisible(true);
                shazamChartsRank.setVisible(true);

                presenceRankPanel.setVisible(true);
                presenceRankText.setVisible(true);

                showSong(currentSongIndex, songManager);
            }
        });

        // Prev Button Action Listener
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSongIndex > 0) {
                    currentSongIndex--;

                    showSong(currentSongIndex, songManager);
                }
            }
        });

        // Next Button Action Listener
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentSongIndex < songArray[yearIndex].length - 1) {
                    currentSongIndex++;

                    showSong(currentSongIndex, songManager);
                }
            }
        });

        // Year Drop-down Menu Action Listener
        yearDropDown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedYear = yearDropDown.getItemAt(yearDropDown.getSelectedIndex());
                yearIndex = songManager.findYearIndex(selectedYear);

                if (yearIndex >= 0 && yearIndex < songArray.length) {
                    currentSongIndex = 0;

                    showSong(currentSongIndex, songManager);
                }
            }
        });
    }

    /**
     * showSong methods provides information about specific song from specific year selected in the drop-down menu
     * @param songIndex     index of song within the specified year of the Song array
     * @param songManager   instance of SongManager class used to pull in info regarding arrays
     */
    private void showSong (int songIndex, SongManager songManager) {
            Song song = songArray[yearIndex][songIndex];

            if (song != null) {
                songFrame.setTitle("Songs | " + (songIndex + 1) + " of " + (songArray[yearIndex].length) + " songs");

                int totalSongCount = songManager.totalSongCount;

                totalSongCountLabel.setText(totalSongCount + " total songs");
                trackNameField.setFont(fieldFont);
                artistsNameField.setFont(fieldFont);
                releaseDateField.setFont(fieldFont);
                totalStreamsField.setFont(fieldFont);

                trackNameField.setText(song.trackName());
                artistsNameField.setText(song.artistName());
                releaseDateField.setText(song.releasedMonth() + "/" + song.releasedDay() + "/" + song.releasedYear());
                try {
                    long streams = Long.parseLong(song.totalNumberOfStreamsOnSpotify());
                    String formattedStreamOutput = String.format("%,d", streams);
                    totalStreamsField.setText(formattedStreamOutput);
                } catch (NumberFormatException ex) {
                    totalStreamsField.setText("n/a");
                }

                spotifyChartsRank.setText(song.spotifyCharts());
                appleChartsRank.setText(song.appleCharts());
                shazamChartsRank.setText(song.shazamCharts());
            }
        }
    }
