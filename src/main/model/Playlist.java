package model;

import java.util.ArrayList;
//import model.Song;

public class Playlist {
    private String name;
    private ArrayList<Song> songs;
    private int numSongs;

    //creates a playlist with a name, a list of songs,
    //and a variable that stores the number of songs.
    Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<Song>();
        this.numSongs = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public int getNumSongs() {
        return numSongs;
    }

}
