package persistence;

import model.Song;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkSong(String name, String artist, String desc, int timesPlayed, Song s) {
        assertEquals(name, s.getName());
        assertEquals(artist, s.getArtist());
        assertEquals(desc, s.getDescription());
        assertEquals(timesPlayed, s.getTimesPlayed());
    }
}
