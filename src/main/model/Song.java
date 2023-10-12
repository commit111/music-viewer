package model;

//Represents a song with a name and an artist and a description.
//The description is empty at the start when the song is created, and can be edited later on.
public class Song {
    private String name;
    private String artist;
    private String description;
    //private Integer timesPlayed;

    //REQUIRES: name is a non-empty string, artist is a non-empty string
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.description = "";
        //this.timesPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    //add specification and test
    public String getShortInfo() {
        return name + " by " + artist;
    }

    public String getDescription() {
        return description;
    }

    //MODIFIES: this
    public void setDescription(String desc) {
        description = desc;
    }

/*
    public Integer getTimesPlayed() {
        return timesPlayed;
    }

    //add specification and test
    public String getLongInfo() {
        return (name + " by " + artist + "\ndetails:"
                + "\nyou've played this song " + timesPlayed.toString() + " times!");
    }

    //MODIFIES: this
    EFFECTS: adds one to the number of times song has been played
    public void increaseTimesPlayed() {
        timesPlayed++;
    }
    */

}
