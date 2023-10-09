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
                appOn = false;
                break;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nApp closed. Thanks for using MusicOrganizer!");
    }

    // MODIFIES: this
    // EFFECTS: initializes a music organizer
    private void init() {
        myMusic = new MusicOrganizer();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        menuState = "home";
    }

    // MODIFIES: this
    // EFFECTS: processes user command based on menu state
    private void processCommand(String command) {
        switch (menuState) {
            case "home" :
                //System.out.println("switched to home");
                processHomeCommand(command);
                break;
            case "playlists" :
                //System.out.println("switched to playlist");
                processPlaylistsCommand(command);
                break;
            default:
                break;
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user command in home menu
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
    // EFFECTS: processes user command in playlists menu
    private void processPlaylistsCommand(String command) {
        if (command.equals("p")) {
            doAddPlaylist();
            menuState = "home";
            showHomeMenu();
        } else if (command.equals("x")) {
            doChoosePlaylistToView();
            menuState = "home";
            showHomeMenu();
        } else if (command.equals("h")) {
            //System.out.println("home state activated");
            menuState = "home";
            showHomeMenu();
        } else {
            System.out.println("Playlist action is invalid. Please select again.");
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

    //EFFECTS: shows all of user's created playlists
    private void showAllPlaylists(MusicOrganizer mo) {
        ArrayList<Playlist> allPlaylists = mo.getAllPlaylists();
        for (Playlist p : allPlaylists) {
            System.out.println("\t" + p.getName());
        }
    }

    // MODIFIES: this, MusicOrganizer
    // EFFECTS: carries out the action of adding a playlist to the music organizer
    private void doAddPlaylist() {
        System.out.println("What would you like to call your new playlist? Enter a name: ");
        String nameInput = input.next();
        myMusic.addPlaylist(nameInput);
        System.out.println("Successfully added! \n*** Your Playlists ***");
        showAllPlaylists(myMusic);
    }

    //EFFECTS: chooses a playlist to view in detail based on user input
    private void doChoosePlaylistToView() {
        if (!(myMusic.getAllPlaylists().size() == 0)) {
            System.out.println("Which playlist would you like to view? Enter a name: ");
            String nameInput = input.next();
            while (!myMusic.doesPlaylistExist(nameInput)) {
                System.out.println("Playlist name not found. Please enter another playlist name.");
                nameInput = input.next();
            }
            //menuState = "singlePlaylist";
            showSinglePlaylistMenu(nameInput);
        } else {
            System.out.println("There are no playlists to view. Maybe add one? (y/n)");
            String answer = input.next();
            answer.toLowerCase();
            if (answer.equals("y")) {
                doAddPlaylist();
            } else {
                System.out.println("Okay, fine. But you'll have no music!");
            }
        }
    }

    //EFFECTS: shows the options for a selected playlist with a given name
    private void showSinglePlaylistMenu(String name) {
        System.out.println("~ Songs in Playlist: " + name + " ~");
        showSongsInPlaylist(name);
        System.out.println("\nChoose an option: ");
        System.out.println("\tadd a new song - s");
        System.out.println("\tview an existing song - z");
        System.out.println("\treturn to home menu - h");
        System.out.println("\texit app - e");
    }

    //EFFECTS: shows all the songs in a playlist with a given name
    private void showSongsInPlaylist(String name) {
        Playlist thisPlaylist = myMusic.getPlaylistByName(name);
        for (Song s : thisPlaylist.getSongs()) {
            System.out.println(s.getShortInfo());
        }
    }

}
