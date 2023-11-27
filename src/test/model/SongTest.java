package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


//Unit tests for the Song class
public class SongTest {
    Song s1;

    @BeforeEach
    public void runBefore() {
        s1 = new Song("name1", "artist1");
    }

    @Test
    public void testConstructor() {
        assertEquals("name1", s1.getName());
        assertEquals("artist1", s1.getArtist());
        assertEquals("", s1.getDescription());
        assertEquals(0, s1.getTimesPlayed());
    }

    @Test
    public void testGetShortInfo() {
        assertEquals("name1 by artist1", s1.getShortInfo());
    }

    @Test
    public void testSetDescription() {
        String testDesc = "hello";
        assertEquals("", s1.getDescription());
        s1.setDescription("hello");
        assertEquals(testDesc, s1.getDescription());
    }

    @Test
    public void testIncreaseTimesPlayedOnce() {
        assertEquals(0, s1.getTimesPlayed());
        s1.increaseTimesPlayed();
        assertEquals(1,s1.getTimesPlayed());
    }

    @Test
    public void testIncreaseTimesPlayedMultipleTimes() {
        assertEquals(0, s1.getTimesPlayed());
        s1.increaseTimesPlayed();
        assertEquals(1,s1.getTimesPlayed());
        s1.increaseTimesPlayed();
        assertEquals(2,s1.getTimesPlayed());
        s1.increaseTimesPlayed();
        assertEquals(3,s1.getTimesPlayed());
    }

}
