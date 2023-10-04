package model;

//import model.Playlist;
//import model.Song;

import java.util.ArrayList;

public class MusicOrganizer {
    private ArrayList<Playlist> allPlaylists;
    private ArrayList<Song> allSongs;

    //creates a new music organizer with a list of all playlists
    //and a list of all songs added to the system.
    MusicOrganizer() {
        this.allPlaylists = new ArrayList<Playlist>();
        this.allSongs = new ArrayList<Song>();
    }

    public ArrayList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }

    public ArrayList<Song> getAllSongs() {
        return allSongs;
    }
}
