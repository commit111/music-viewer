package persistence;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/notActualFile.json");
        try {
            MusicOrganizer mo = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMusicOrganizer() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMusicOrganizer.json");
        try {
            MusicOrganizer mo = reader.read();
            assertEquals(0, mo.getAllPlaylists().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMusicOrganizer() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMusicOrganizer.json");
        try {
            MusicOrganizer mo = reader.read();
            assertEquals(2, mo.getAllPlaylists().size());
            assertTrue(mo.doesPlaylistExist("fall songs"));
            assertTrue(mo.doesPlaylistExist("summer songs"));

            assertTrue(mo.getPlaylistByName("fall songs").doesSongExist("hello", "adele"));
            assertTrue(mo.getPlaylistByName("fall songs").doesSongExist("stay", "jb"));
            assertTrue(mo.getPlaylistByName("summer songs").doesSongExist("firework", "katy perry"));
            assertTrue(mo.getPlaylistByName("summer songs").doesSongExist("stressed out", "21p"));

//            checkThingy("needle", Category.STITCHING, thingies.get(0));
//            checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
