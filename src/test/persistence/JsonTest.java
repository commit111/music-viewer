package persistence;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkPlaylists(ArrayList<Playlist> playlists, MusicOrganizer mo) {
        assertEquals(playlists, mo.getAllPlaylists());
    }
    protected void checkPlaylist(String name, ArrayList<Song> songs, Playlist p) {
        assertEquals(name, p.getName());
        assertEquals(songs, p.getSongs());
    }
    protected void checkSong(String name, String artist, String desc, Integer timesPlayed, Song s) {
        assertEquals(name, s.getName());
        assertEquals(artist, s.getArtist());
        assertEquals(desc, s.getDescription());
        assertEquals(timesPlayed, s.getTimesPlayed());
    }
}
