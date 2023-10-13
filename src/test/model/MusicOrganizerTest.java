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
        Playlist testPl = new Playlist("testPlaylist");

        assertEquals(0, moTest.getAllPlaylists().size());
        assertFalse(moTest.doesPlaylistExist("testPlaylist"));

        moTest.addPlaylist("testPlaylist");

        assertEquals(1, moTest.getAllPlaylists().size());
        assertEquals("testPlaylist", moTest.getAllPlaylists().get(0).getName());
        assertEquals(0, moTest.getAllPlaylists().get(0).getSongs().size());
        assertTrue(moTest.doesPlaylistExist("testPlaylist"));
    }

    @Test
    public void testGetPlaylistByName() {
        Playlist test1 = new Playlist("test1");
        Playlist test2 = new Playlist("test2");
        Playlist test3 = new Playlist("test3");

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
        Playlist test1 = new Playlist("test1");

        assertEquals(0, moTest.getAllPlaylists().size());

        moTest.addPlaylist("test1");

        assertEquals(1, moTest.getAllPlaylists().size());
        assertTrue(moTest.getAllPlaylists().get(0).getName().equals("test1"));
    }

    @Test
    public void testAddPlaylistMultipleTimes() {
        Playlist test1 = new Playlist("test1");
        Playlist test2 = new Playlist("test2");
        Playlist test3 = new Playlist("test3");

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
