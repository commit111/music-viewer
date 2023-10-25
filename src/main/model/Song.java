package model;

import org.json.JSONObject;
import persistence.Writable;

//Represents a song with a name, an artist, a description and number of times played.
//The description is empty at the start when the song is created, and can be edited later on.
public class Song implements Writable {
    private String name;
    private String artist;
    private String description;
    private int timesPlayed;

    //Constructor
    //EFFECTS: creates a song with a name, artist, description, and number of times played
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.description = "";
        this.timesPlayed = 0;
    }

    //Overloaded constructor
    public Song(String name, String artist, String description, int timesPlayed) {
        this.name = name;
        this.artist = artist;
        this.description = description;
        this.timesPlayed = timesPlayed;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getDescription() {
        return description;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    //MODIFIES: this
    public void setDescription(String desc) {
        description = desc;
    }

    //EFFECTS: returns the name and artist together for a song
    public String getShortInfo() {
        return name + " by " + artist;
    }

    //MODIFIES: this
    //EFFECTS: adds one to the number of times song has been played
    public void increaseTimesPlayed() {
        timesPlayed++;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("artist", artist);
        json.put("description", description);
        json.put("timesPlayed", timesPlayed);

        return json;
    }

}
