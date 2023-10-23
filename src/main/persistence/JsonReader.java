package persistence;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file.
// It is modelled after the JsonSerializationDemo project.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads MusicOrganizer from file and returns it;
    // throws IOException if an error occurs reading data from file
    public MusicOrganizer read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMusicOrganizer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses music organizer from JSON object and returns it
    private MusicOrganizer parseMusicOrganizer(JSONObject jsonObject) {
        MusicOrganizer mo = new MusicOrganizer();
        JSONArray jsonArray = jsonObject.getJSONArray("playlists");
        for (Object json : jsonArray) {
            JSONObject nextPlaylist = (JSONObject) json;
            addPlaylist(mo, nextPlaylist);
        }
        return mo;
    }

    // the top level object has no key
    // "playlists" is a json array with many "playlist" json objects
    // "playlist" is a json object that stores songs
    // "songs" is a json array
    // "song" is a json object
    // but individual data is stored inside the json object

    // to write an int as a string, use:
    // Integer.toString(timesPlayed)
    // to read a string as an int, use:
    // Integer.parseInt(jsonObject.getString("timesPlayed")

    // MODIFIES: mo
    // EFFECTS: parses a playlist from JSON object and adds it to music organizer
    private void addPlaylist(MusicOrganizer mo, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        JSONArray jsonSongs = jsonObject.getJSONArray("songs");

        mo.addPlaylist(name);
        Playlist p = mo.getPlaylistByName(name);

        for (Object json : jsonSongs) {
            JSONObject nextSong = (JSONObject) json;
            addSong(p, nextSong);
        }
    }

    // MODIFIES: p
    // EFFECTS: parses a song from JSON object and adds it to the given playlist
    private void addSong(Playlist p, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String artist = jsonObject.getString("artist");
        String description = jsonObject.getString("description");
        int timesPlayed = jsonObject.getInt("timesPlayed");

        Song song = new Song(name, artist, description, timesPlayed);
        p.addSong(song);
    }
}

