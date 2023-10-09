package model;

//import model.Playlist;
//import model.Song;

import java.util.ArrayList;

public class MusicOrganizer {
    private ArrayList<Playlist> allPlaylists;
    private ArrayList<Song> allSongs;

    //creates a new music organizer with an empty list of all playlists
    //and an empty list of all songs in the system.
    public MusicOrganizer() {
        this.allPlaylists = new ArrayList<Playlist>();
        this.allSongs = new ArrayList<Song>();
    }

    public ArrayList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }

    public ArrayList<Song> getAllSongs() {
        return allSongs;
    }

    //EFFECTS: returns true if playlist with a given name exists in the system, false otherwise
    public boolean doesPlaylistExist(String name) {
        for (Playlist p: this.getAllPlaylists()) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns true if song with a given name and artist exists in the system, false otherwise
    public boolean doesSongExist(String name, String artist) {
        for (Song s : this.getAllSongs()) {
            if (s.getName().equals(name) && s.getArtist().equals(artist)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the first playlist with a given name
    public Playlist getPlaylistByName(String name) {
        for (Playlist p: this.getAllPlaylists()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    //EFFECTS: returns the song with a given name and artist
    public Song getSongByNameAndArtist(String name, String artist) {
        for (Song s : this.getAllSongs()) {
            if (s.getName().equals(name) && s.getArtist().equals(artist)) {
                return s;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: creates a new playlist in the music organizer
    public void addPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        this.allPlaylists.add(playlist);
    }


}
