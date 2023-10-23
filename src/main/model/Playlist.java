package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Represents a playlist with a name and a list of songs.
public class Playlist implements Writable {
    private String name;
    private ArrayList<Song> songs;

    //Constructor
    //EFFECTS: creates a playlist with a name and empty list of songs
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    //EFFECTS: returns true if song with a given name and artist exists in the playlist, false otherwise
    public boolean doesSongExist(String name, String artist) {
        for (Song s : this.getSongs()) {
            if (s.getName().equals(name) && s.getArtist().equals(artist)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: returns the first song with a given name and artist if it is in the playlist, null otherwise
    public Song getSongByNameAndArtist(String name, String artist) {
        for (Song s : this.getSongs()) {
            if (s.getName().equals(name) && s.getArtist().equals(artist)) {
                return s;
            }
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: adds a song to the playlist
    public void addSong(Song song) {
        this.songs.add(song);
    }

    //MODIFIES: this
    //EFFECTS: removes a song from the playlist
    public void removeSong(Song song) {
        this.songs.remove(song);
    }



    // EFFECTS: returns an unmodifiable list of playlists in this music organizer
    public List<Song> getUnmodifiableSongs() {
        return Collections.unmodifiableList(songs);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("songs", songsToJson());
        return json;
    }

    // EFFECTS: returns playlists in this music organizer as a JSON array
    private JSONArray songsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Song s : songs) {
            jsonArray.put(s.toJson());
        }

        return jsonArray;
    }

}
