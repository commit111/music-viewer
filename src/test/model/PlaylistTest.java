package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaylistTest {
    Playlist pl1;

    @BeforeEach
    public void runBefore() {
        pl1 = new Playlist("test1");
    }

    @Test
    public void testConstructor() {
        assertEquals("test1", pl1.getName());
        assertEquals(0, pl1.getSongs().size());
    }

    @Test
    public void testDoesSongExist() {
        Song sample = new Song("notSong", "notArtist");

        assertFalse(pl1.doesSongExist("notSong", "notArtist"));
        assertFalse(pl1.doesSongExist("someSong", "someArtist"));

        pl1.addSong(sample);

        assertTrue(pl1.doesSongExist("notSong", "notArtist"));
        assertFalse(pl1.doesSongExist("someSong", "someArtist"));
    }

    @Test
    public void testGetSongByNameAndArtist() {
        Song sample = new Song("testSong", "testArtist");
        Song other = new Song("otherSong", "otherArtist");
        Song other2 = new Song("other2Song", "other2Artist");

        assertEquals(0, pl1.getSongs().size());
        assertEquals(null, pl1.getSongByNameAndArtist("testSong", "testArtist"));

        pl1.addSong(other);
        pl1.addSong(sample);
        pl1.addSong(other2);
        assertEquals(3, pl1.getSongs().size());

        assertEquals(sample, pl1.getSongByNameAndArtist("testSong", "testArtist"));
        assertFalse(pl1.getSongByNameAndArtist("testSong", "testArtist") == null);
        assertEquals(3, pl1.getSongs().size());
    }

    @Test
    public void testAddSongOnce() {
        Song sample = new Song("testSong", "testArtist");

        assertEquals(0, pl1.getSongs().size());

        pl1.addSong(sample);

        assertEquals(1, pl1.getSongs().size());
        assertEquals(sample, pl1.getSongs().get(0));
    }

    @Test
    public void testAddSongMultipleTimes() {
        Song sample = new Song("testSong", "testArtist");
        Song sample2 = new Song("someSong", "someArtist");

        assertEquals(0, pl1.getSongs().size());

        pl1.addSong(sample);

        assertEquals(1, pl1.getSongs().size());
        assertEquals(sample, pl1.getSongs().get(0));

        pl1.addSong(sample2);

        assertEquals(2, pl1.getSongs().size());
        assertEquals(sample, pl1.getSongs().get(0));
        assertEquals(sample2, pl1.getSongs().get(1));

        pl1.addSong(sample);

        assertEquals(3, pl1.getSongs().size());
        assertEquals(sample, pl1.getSongs().get(0));
        assertEquals(sample2, pl1.getSongs().get(1));
        assertEquals(sample, pl1.getSongs().get(2));
    }

    @Test
    public void testRemoveSongOnce() {
        Song sample = new Song("testSong", "testArtist");

        pl1.addSong(sample);

        assertEquals(1, pl1.getSongs().size());
        assertEquals(sample, pl1.getSongs().get(0));

        pl1.removeSong(sample);

        assertFalse(pl1.getSongs().contains(sample));
        assertEquals(0, pl1.getSongs().size());
    }

    @Test
    public void testRemoveSongMultipleTimes() {
        Song sample = new Song("testSong", "testArtist");
        Song sample2 = new Song("someSong", "someArtist");

        assertEquals(0, pl1.getSongs().size());

        pl1.addSong(sample);
        pl1.addSong(sample2);
        pl1.addSong(sample);

        assertEquals(3, pl1.getSongs().size());
        assertEquals(sample, pl1.getSongs().get(0));
        assertEquals(sample2, pl1.getSongs().get(1));
        assertEquals(sample, pl1.getSongs().get(2));

        pl1.removeSong(sample);

        assertEquals(2, pl1.getSongs().size());
        assertEquals(sample2, pl1.getSongs().get(0));
        assertEquals(sample, pl1.getSongs().get(1));

        pl1.removeSong(sample);
        assertEquals(1, pl1.getSongs().size());
        assertEquals(sample2, pl1.getSongs().get(0));

        pl1.removeSong(sample2);
        assertEquals(0, pl1.getSongs().size());
    }

}
