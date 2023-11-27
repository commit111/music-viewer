package persistence;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


//Unit tests for the JsonWriter class
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MusicOrganizer mo = new MusicOrganizer();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
    void testWriterEmptyWorkroom() {
        try {
            MusicOrganizer mo = new MusicOrganizer();
            JsonWriter writer = new JsonWriter("./data/json-tests/testWriterEmptyMusicOrganizer.json");
            writer.open();
            writer.write(mo);
            writer.close();

            JsonReader reader = new JsonReader("./data/json-tests/testWriterEmptyMusicOrganizer.json");
            mo = reader.read();
            assertEquals(0, mo.getAllPlaylists().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralWorkroom() {
        try {
            MusicOrganizer mo = new MusicOrganizer();
            mo.addPlaylist("fall songs");
            mo.addPlaylist("summer songs");
            Playlist fallSongs = mo.getPlaylistByName("fall songs");
            Playlist summerSongs = mo.getPlaylistByName("summer songs");

            Song song1 = new Song("hello", "adele", "good", 2);
            Song song2 = new Song("stay", "jb", "bad", 1);
            Song song3 = new Song("firework", "katy perry", "good", 3);
            Song song4 = new Song("stressed out", "21p", "bad", 4);

            fallSongs.addSong(song1);
            fallSongs.addSong(song2);
            summerSongs.addSong(song3);
            summerSongs.addSong(song4);

            JsonWriter writer = new JsonWriter("./data/json-tests/testWriterGeneralMusicOrganizer.json");
            writer.open();
            writer.write(mo);
            writer.close();

            JsonReader reader = new JsonReader("./data/json-tests/testWriterGeneralMusicOrganizer.json");
            mo = reader.read();

            assertEquals(2, mo.getAllPlaylists().size());
            assertTrue(mo.doesPlaylistExist("fall songs"));
            assertTrue(mo.doesPlaylistExist("summer songs"));

            assertEquals(2, fallSongs.getSongs().size());
            assertEquals(2, summerSongs.getSongs().size());

            assertTrue(fallSongs.doesSongExist("hello", "adele"));
            assertTrue(fallSongs.doesSongExist("stay", "jb"));
            assertTrue(summerSongs.doesSongExist("firework", "katy perry"));
            assertTrue(summerSongs.doesSongExist("stressed out", "21p"));

            Song readSong1 = fallSongs.getSongByNameAndArtist("hello", "adele");
            Song readSong2 = fallSongs.getSongByNameAndArtist("stay", "jb");
            Song readSong3 = summerSongs.getSongByNameAndArtist("firework", "katy perry");
            Song readSong4 = summerSongs.getSongByNameAndArtist("stressed out", "21p");

            checkSong("hello", "adele", "good", 2, readSong1);
            checkSong("stay", "jb", "bad", 1, readSong2);
            checkSong("firework", "katy perry", "good", 3, readSong3);
            checkSong("stressed out", "21p", "bad", 4, readSong4);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
