package model;

//Represents a song with a name, an artist, a description and number of times played.
//The description is empty at the start when the song is created, and can be edited later on.
public class Song {
    private String name;
    private String artist;
    private String description;
    private Integer timesPlayed;

    //Constructor
    //EFFECTS: creates a song with a name, artist, description, and number of times played
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.description = "";
        this.timesPlayed = 0;
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

    public Integer getTimesPlayed() {
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

}
