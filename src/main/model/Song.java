package model;

public class Song {
    private String name;
    private String artist;
    private String description;
    //creates a song with a name and an artist and an empty description.
    //the description for the song can be changed later on.

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

    public void setDescription(String desc) {
        description = desc;
    }

    public String getLongInfo() {
        return name + " by " + artist + "\n\nDetails:" + description;
    }

}
