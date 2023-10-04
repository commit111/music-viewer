package model;

public class Song {
    private String name;
    private String artist;
    private String genre;
    private int duration;

    //creates a song with a name and an artist,
    //and a genre or duration (in seconds) if known.
    //note: genre will be stored as "unknown" if unspecified,
    //and duration will be stored as -1 if unspecified.
    Song(String name, String artist) {
        this.name = name;
        this.artist = artist;
        this.genre = "unknown";
        this.duration = -1;
    }

    Song(String name, String artist, String genre) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = -1;
    }

    Song(String name, String artist, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = "unknown";
        this.duration = duration;
    }

    Song(String name, String artist, String genre, int duration) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

    public String getShortInfo() {
        return name + " by " + artist;
    }


}
