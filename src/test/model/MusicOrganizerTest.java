package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        assertNull(moTest.getPlaylistByName("test2Playlist"));

        moTest.addPlaylist("test1");
        moTest.addPlaylist("test2");
        moTest.addPlaylist("test3");
        assertEquals(3, moTest.getAllPlaylists().size());

        assertEquals("test2", moTest.getPlaylistByName("test2").getName());
        assertNotNull(moTest.getPlaylistByName("test2"));
        assertEquals(3, moTest.getAllPlaylists().size());
    }

    @Test
    public void testAddPlaylistOnce() {
        assertEquals(0, moTest.getAllPlaylists().size());

        moTest.addPlaylist("test1");

        assertEquals(1, moTest.getAllPlaylists().size());
        assertEquals("test1", moTest.getAllPlaylists().get(0).getName());
    }

    @Test
    public void testAddPlaylistMultipleTimes() {
        assertEquals(0, moTest.getAllPlaylists().size());

        moTest.addPlaylist("test1");

        assertEquals(1, moTest.getAllPlaylists().size());
        assertEquals("test1", moTest.getAllPlaylists().get(0).getName());

        moTest.addPlaylist("test2");

        assertEquals(2, moTest.getAllPlaylists().size());
        assertEquals("test1", moTest.getAllPlaylists().get(0).getName());
        assertEquals("test2", moTest.getAllPlaylists().get(1).getName());

        moTest.addPlaylist("test3");

        assertEquals(3, moTest.getAllPlaylists().size());
        assertEquals("test1", moTest.getAllPlaylists().get(0).getName());
        assertEquals("test2", moTest.getAllPlaylists().get(1).getName());
        assertEquals("test3", moTest.getAllPlaylists().get(2).getName());

    }


}
