package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MusicOrganizerTest {
    MusicOrganizer moTest;

    @BeforeEach
    public void runBefore() {
        moTest = new MusicOrganizer();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, moTest.getAllPlaylists().size());
    }

    @Test
    public void testDoesPlaylistExist() {
        assertEquals(0, moTest.getAllPlaylists().size());
        assertFalse(moTest.doesPlaylistExist("test2"));

        moTest.addPlaylist("test1");
        moTest.addPlaylist("test2");
        moTest.addPlaylist("test3");

        assertEquals(3, moTest.getAllPlaylists().size());
        assertEquals("test2", moTest.getAllPlaylists().get(1).getName());
        assertTrue(moTest.doesPlaylistExist("test2"));
    }

    @Test
    public void testGetPlaylistByName() {
        assertEquals(0, moTest.getAllPlaylists().size());
        assertEquals(null, moTest.getPlaylistByName("test2Playlist"));

        moTest.addPlaylist("test1");
        moTest.addPlaylist("test2");
        moTest.addPlaylist("test3");
        assertEquals(3, moTest.getAllPlaylists().size());

        assertTrue(moTest.getPlaylistByName("test2").getName().equals("test2"));
        assertFalse(moTest.getPlaylistByName("test2") == null);
        assertEquals(3, moTest.getAllPlaylists().size());
    }

    @Test
    public void testAddPlaylistOnce() {
        assertEquals(0, moTest.getAllPlaylists().size());

        moTest.addPlaylist("test1");

        assertEquals(1, moTest.getAllPlaylists().size());
        assertTrue(moTest.getAllPlaylists().get(0).getName().equals("test1"));
    }

    @Test
    public void testAddPlaylistMultipleTimes() {
        assertEquals(0, moTest.getAllPlaylists().size());

        moTest.addPlaylist("test1");

        assertEquals(1, moTest.getAllPlaylists().size());
        assertTrue(moTest.getAllPlaylists().get(0).getName().equals("test1"));

        moTest.addPlaylist("test2");

        assertEquals(2, moTest.getAllPlaylists().size());
        assertTrue(moTest.getAllPlaylists().get(0).getName().equals("test1"));
        assertTrue(moTest.getAllPlaylists().get(1).getName().equals("test2"));

        moTest.addPlaylist("test3");

        assertEquals(3, moTest.getAllPlaylists().size());
        assertTrue(moTest.getAllPlaylists().get(0).getName().equals("test1"));
        assertTrue(moTest.getAllPlaylists().get(1).getName().equals("test2"));
        assertTrue(moTest.getAllPlaylists().get(2).getName().equals("test3"));

    }


}
