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
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MusicOrganizer mo = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            MusicOrganizer mo = reader.read();
//            assertEquals("My work room", wr.getName());
//            assertEquals(0, wr.numThingies());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            MusicOrganizer mo = reader.read();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("needle", Category.STITCHING, thingies.get(0));
//            checkThingy("saw", Category.WOODWORK, thingies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
