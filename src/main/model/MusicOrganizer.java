package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


// Represents a music organizer with a list of playlists.
public class MusicOrganizer implements Writable {
    private ArrayList<Playlist> allPlaylists;

    // Constructor
    // EFFECTS: creates a music organizer with a name and empty list of playlists
    public MusicOrganizer() {
        this.allPlaylists = new ArrayList<>();
    }

    public ArrayList<Playlist> getAllPlaylists() {
        return allPlaylists;
    }

    // EFFECTS: returns true if playlist with a given name exists in the system, false otherwise
    public boolean doesPlaylistExist(String name) {
        for (Playlist p : this.getAllPlaylists()) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns the first playlist with a given name
    public Playlist getPlaylistByName(String name) {
        for (Playlist p : this.getAllPlaylists()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECTS: creates a new playlist in the music organizer
    public void addPlaylist(String name) {
        Playlist playlist = new Playlist(name);
        this.allPlaylists.add(playlist);
        EventLog.getInstance().logEvent(new Event("A new playlist was created: " + name));
    }

    // EFFECTS: returns a JSON object of the playlists in the music organizer
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("allPlaylists", allPlaylistsToJson());
        return json;
    }

    // EFFECTS: returns playlists in this music organizer as a JSON array
    private JSONArray allPlaylistsToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Playlist p : allPlaylists) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }

}

