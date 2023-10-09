package model;

//Represents a song with a name and an artist and a description.
//The description is empty at the start when the song is created, and can be edited later on.
public class Song {
    private String name;
    private String artist;
    private String description;

    //REQUIRES: name is a non-empty string, artist is a non-empty string
    public Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.description = "";
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getShortInfo() {
        return name + " by " + artist;
    }

    public String getDescription() {
        return description;
    }

    public String getLongInfo() {
        return name + " by " + artist + "\ndetails:" + description;
    }

    //MODIFIES: this
    public void setDescription(String desc) {
        description = desc;
    }

}
