package persistence;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//Unit tests for the JsonReader class
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/json-tests/notActualFile.json");
        try {
            MusicOrganizer mo = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMusicOrganizer() {
        JsonReader reader = new JsonReader("./data/json-tests/testReaderEmptyMusicOrganizer.json");
        try {
            MusicOrganizer mo = reader.read();
            assertEquals(0, mo.getAllPlaylists().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMusicOrganizer() {
        JsonReader reader = new JsonReader("./data/json-tests/testReaderGeneralMusicOrganizer.json");
        try {
            MusicOrganizer mo = reader.read();
            assertEquals(2, mo.getAllPlaylists().size());
            assertTrue(mo.doesPlaylistExist("fall songs"));
            assertTrue(mo.doesPlaylistExist("summer songs"));

            Playlist fallSongs = mo.getPlaylistByName("fall songs");
            Playlist summerSongs = mo.getPlaylistByName("summer songs");

            assertEquals(2, fallSongs.getSongs().size());
            assertEquals(2, summerSongs.getSongs().size());

            assertTrue(fallSongs.doesSongExist("hello", "adele"));
            assertTrue(fallSongs.doesSongExist("stay", "jb"));
            assertTrue(summerSongs.doesSongExist("firework", "katy perry"));
            assertTrue(summerSongs.doesSongExist("stressed out", "21p"));

            Song song1 = fallSongs.getSongByNameAndArtist("hello", "adele");
            Song song2 = fallSongs.getSongByNameAndArtist("stay", "jb");
            Song song3 = summerSongs.getSongByNameAndArtist("firework", "katy perry");
            Song song4 = summerSongs.getSongByNameAndArtist("stressed out", "21p");

            checkSong("hello", "adele", "love", 2, song1);
            checkSong("stay", "jb", "hate", 1, song2);
            checkSong("firework", "katy perry", "love", 3, song3);
            checkSong("stressed out", "21p", "hate", 4, song4);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
