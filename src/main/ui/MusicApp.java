package ui;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;

import java.util.ArrayList;
import java.util.Scanner;

public class MusicApp {
    private MusicOrganizer myMusic;
    private Scanner input;
    private String menuState;
    private String viewedPlaylist;
    private Song viewedSong;

    //  EFFECTS: runs music application
    public MusicApp() {
        runMusicApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMusicApp() {
        boolean appOn = true;
        String command = null;
        init();
        showHomeMenu();

        while (appOn) {
            //System.out.println("app is asking for ur input");
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("e")) {
                break;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nApp closed. Thanks for using MusicOrganizer!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a music organizer, menu state, and name of viewed playlist
    private void init() {
        myMusic = new MusicOrganizer();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        menuState = "home";
        viewedPlaylist = "";
        viewedSong = new Song("", "");
    }

    // MODIFIES: this
    // EFFECTS: processes user command based on menu state
    private void processCommand(String command) {
        switch (menuState) {
            case "home":
                //System.out.println("switched to home");
                processHomeCommand(command);
                break;
            case "playlists":
                //System.out.println("switched to playlist");
                processPlaylistsCommand(command);
                break;
            case "singlePlaylist":
                //System.out.println("switched to singlePlaylist");
                processSinglePlaylistCommand(command);
                break;
            case "singleSong":
                //System.out.println("switched to singleSong");
                processSingleSongCommand(command);
                break;
            default:
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in home menu state
    private void processHomeCommand(String command) {
        if (command.equals("v")) {
            //System.out.println("playlist state activated");
            menuState = "playlists";
            showPlaylistsMenu();
        } else {
            System.out.println("Home action is invalid. Please select again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: processes user command in playlists menu state
    private void processPlaylistsCommand(String command) {
        if (command.equals("p")) {
            doAddPlaylist();
            showPlaylistsMenu();
        } else if (command.equals("x")) {
            if (!doChoosePlaylistToView()) {
                menuState = "playlists";
                showPlaylistsMenu();
            }
        } else if (command.equals("h")) {
            //System.out.println("home state activated");
            menuState = "home";
            showHomeMenu();
        } else {
            System.out.println("Playlists action is invalid. Please select again.");
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command in single playlist menu state
    private void processSinglePlaylistCommand(String command) {
        if (command.equals("s")) {
            doAddSong();
        } else if (command.equals("r")) {
            doRemoveSong();
        } else if (command.equals("z")) {
            if (!doChooseSongToView()) {
                menuState = "playlists";
                showPlaylistsMenu();
            }
        } else if (command.equals("h")) {
            //System.out.println("home state activated");
            menuState = "home";
            showHomeMenu();
        } else {
            System.out.println("Single playlist action is invalid. Please select again.");
        }
    }

    //MODIFIES: this
    //EFFECTS: processes user command in single song state
    private void processSingleSongCommand(String command) {
        if (command.equals("d")) {
            //System.out.println("getting ready to edit details...");
            doEditSongDetails();
        } else if (command.equals("h")) {
            //System.out.println("home state activated");
            menuState = "home";
            showHomeMenu();
        } else {
            System.out.println("Single song action is invalid. Please select again.");
        }
    }

    //EFFECTS: shows user the home menu options
    private void showHomeMenu() {
        System.out.println("\n~ Home Menu ~");
        System.out.println("Choose an option: ");
        System.out.println("\tview your playlists - v");
        System.out.println("\texit app - e");
    }

    //EFFECTS: shows options for user's created playlists
    private void showPlaylistsMenu() {
        System.out.println("*** Your Playlists ***");
        showAllPlaylists(myMusic);
        System.out.println("\nChoose an option: ");
        System.out.println("\tadd a new playlist - p");
        System.out.println("\tview an existing playlist - x");
        System.out.println("\treturn to home menu - h");
        System.out.println("\texit app - e");

    }

    //EFFECTS: shows the options for a selected playlist with a given name
    private void showSinglePlaylistMenu(String name) {
        System.out.println("***** Songs in Playlist: " + name + " *****");
        showSongsInPlaylist(name);
        System.out.println("\nChoose an option: ");
        System.out.println("\tadd a new song - s");
        System.out.println("\tremove a song - r");
        System.out.println("\tview an existing song - z");
        System.out.println("\treturn to home menu - h");
        System.out.println("\texit app - e");
    }

    //EFFECTS: shows the options for a selected song
    private void showSingleSongMenu(Song s) {
        String name = s.getName();
        String artist = s.getArtist();
        System.out.println("***** Song: " + name + " by " + artist + " *****");
        System.out.println("Details: " + s.getDescription());
        System.out.println("\nChoose an option: ");
        System.out.println("\tedit song details - d");
        System.out.println("\treturn to home menu - h");
        System.out.println("\texit app - e");
    }

    //EFFECTS: shows all of user's created playlists
    private void showAllPlaylists(MusicOrganizer mo) {
        ArrayList<Playlist> allPlaylists = mo.getAllPlaylists();
        for (Playlist p : allPlaylists) {
            System.out.println("\t" + p.getName());
        }
    }

    //EFFECTS: shows all the songs in a playlist with a given name
    private void showSongsInPlaylist(String name) {
        Playlist thisPlaylist = myMusic.getPlaylistByName(name);
        for (Song s : thisPlaylist.getSongs()) {
            System.out.println(s.getShortInfo());
        }
    }

    // MODIFIES: this, MusicOrganizer
    // EFFECTS: carries out the action of adding a playlist to the music organizer
    private void doAddPlaylist() {
        System.out.println("What would you like to call your new playlist? Enter a name: ");
        String nameInput = input.next();
        myMusic.addPlaylist(nameInput);
        System.out.println("Successfully added new playlist!");
    }

    //MODIFIES: this
    //EFFECTS: chooses a playlist to view based on user input, returns false if music organizer has no playlists to view
    private boolean doChoosePlaylistToView() {
        if (!(myMusic.getAllPlaylists().size() == 0)) {
            System.out.println("Which playlist would you like to view? Enter a name: ");
            String nameInput = input.next();
            while (!myMusic.doesPlaylistExist(nameInput)) {
                System.out.println("Playlist name not found. Please enter another playlist name.");
                nameInput = input.next();
            }
            menuState = "singlePlaylist";
            viewedPlaylist = nameInput;
            showSinglePlaylistMenu(nameInput);
            return true;
        } else {
            System.out.println("There are no playlists to view. Maybe add one? (y/n)");
            String answer = input.next();
            answer = answer.toLowerCase();
            if (answer.equals("y")) {
                doAddPlaylist();
            } else {
                System.out.println("Okay, fine. But you'll have no music!");
            }
            return false;
        }
    }

    //MODIFIES: this, Playlist
    //EFFECTS: carries out the action of adding a user-inputted song to a viewed playlist
    private void doAddSong() {
        Playlist thisPlaylist = myMusic.getPlaylistByName(viewedPlaylist);

        System.out.println("So, you want to add a song? Enter the song name: ");
        String nameInput = input.next();
        System.out.println("Haven't heard of that one before. Who's it by? Enter the song artist: ");
        String artistInput = input.next();
        Song songInput = new Song(nameInput, artistInput);

        thisPlaylist.addSong(songInput);
        System.out.println("Successfully added!");
        menuState = "singlePlaylist";
        showSinglePlaylistMenu(viewedPlaylist);
    }

    //MODIFIES: this, Playlist
    //EFFECTS: carries out the action of removing a song from a viewed playlist, if it exists
    private void doRemoveSong() {
        Playlist thisPlaylist = myMusic.getPlaylistByName(viewedPlaylist);

        if (!(thisPlaylist.getSongs().size() == 0)) {
            System.out.println("Didn't work for ya, eh? Enter the song name to be removed: ");
            String nameInput = input.next();
            System.out.println("Okay. And to confirm, who's it by? Enter the song artist: ");
            String artistInput = input.next();

            if (thisPlaylist.doesSongExist(nameInput, artistInput)) {
                Song toBeRemoved = thisPlaylist.getSongByNameAndArtist(nameInput, artistInput);
                thisPlaylist.removeSong(toBeRemoved);
                System.out.println("Removed from " + viewedPlaylist + " successfully!");
            } else {
                System.out.println("That song doesn't appear in the playlist, so there's nothing to be removed.");
            }
        } else {
            System.out.println("Hey, don't get too ahead of yourself. The playlist is already empty.");
        }
        menuState = "singlePlaylist";
        showSinglePlaylistMenu(viewedPlaylist);
    }

    //MODIFIES: this
    //EFFECTS: chooses a song to view based on user input, returns false if viewed playlist has no songs to view
    private boolean doChooseSongToView() {
        Playlist thisPlaylist = myMusic.getPlaylistByName(viewedPlaylist);
        if (!(thisPlaylist.getSongs().size() == 0)) {
            System.out.println("Which song would you like to view? Enter the song name: ");
            String nameInput = input.next();
            System.out.println("Alright, but remind me, who's it by? Enter the artist name: ");
            String artistInput = input.next();
            while (!thisPlaylist.doesSongExist(nameInput, artistInput)) {
                System.out.println("Song or artist not found. Please enter another song name.");
                nameInput = input.next();
                System.out.println("Alright, but remind me, who's it by? Enter the artist name: ");
                artistInput = input.next();
            }
            menuState = "singleSong";
            viewedSong = thisPlaylist.getSongByNameAndArtist(nameInput, artistInput);
            showSingleSongMenu(viewedSong);
            return true;
        } else {
            System.out.println("There are no songs to view in this playlist. Maybe try another playlist?");
            return false;
        }
    }

    //MODIFIES: this, Song
    //EFFECTS: carries out the action of editing a selected song's details and sets menu state
    private void doEditSongDetails() {
        System.out.println("Enter a new description:");
        String editedDesc = input.next();
        viewedSong.setDescription(editedDesc);
        System.out.println("Successfully updated song details!");

        menuState = "singleSong";
        showSingleSongMenu(viewedSong);
    }

}
