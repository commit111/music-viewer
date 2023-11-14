package ui.gui;

import model.MusicOrganizer;
import model.Playlist;
import model.Song;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class MusicViewer {
    private static final String IMG_FILE_PATH = "./src/main/ui/gui/abstract.png";
    private static final String JSON_STORE = "./data/music-viewer.json";

    private final JFrame frame;
    private final JPanel bgPanel;
    private final JPanel menuPanel;
    private final JPanel playlistsPanel;
    private JTextField tf;

    private MusicOrganizer musicOrganizer;
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    public static void main(String[] args) throws IOException {
        MusicViewer mv = new MusicViewer();
    }

    //Constructor
    public MusicViewer() throws IOException {
        frame = new JFrame(); //creating instance of JFrame
        bgPanel = new ImagePanel(ImageIO.read(new File(IMG_FILE_PATH)), new GridLayout(1, 0));
        playlistsPanel = new TransparentPanel(new FlowLayout());
        menuPanel = new TransparentPanel(new FlowLayout());

        musicOrganizer = new MusicOrganizer();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        //menuPanel.setBackground(Color.YELLOW);
        //playlistsPanel.setBackground(Color.GREEN);

        frame.add(bgPanel);
        bgPanel.add(menuPanel);
        bgPanel.add(playlistsPanel);

        setUpMainMenu(menuPanel);
        setUpPlaylistsPanel(playlistsPanel);
        setUpJFrameSettings(frame);
    }

    //MODIFIES: panel
    //EFFECTS: adds a text field and main menu buttons to the panel
    private void setUpMainMenu(JPanel panel) {
        tf = new JTextField();
        //tf.setBounds(300,250, 150,25); //x-axis, y-axis, width, height
        tf.setText("Welcome to MusicViewer");
        tf.setFont(tf.getFont().deriveFont(Font.ITALIC, 12));
        tf.setEditable(false);

        JButton b = new JButton("Make a new playlist"); //creating instance of JButton
        JButton b2 = new JButton("Load playlists");
        JButton b3 = new JButton("Save playlists");
        JButton b0 = new JButton("Exit app");

        setMakePlaylistsBtn(b);
        setLoadPlaylistsBtn(b2);
        setSavePlaylistsBtn(b3);
        setExitAppBtn(b0);

        panel.add(tf);
        panel.add(b); //adding button in JFrame
        panel.add(b2);
        panel.add(b3);
        panel.add(b0);
    }

    //MODIFIES: panel
    //EFFECTS: sets up the playlist panel by adding text field to the panel
    private void setUpPlaylistsPanel(JPanel panel) {
        final JTextField ptf = new JTextField("Your Playlists");
        ptf.setFont(ptf.getFont().deriveFont(Font.ITALIC, 12));
        ptf.setEditable(false);
        panel.add(ptf);
    }

    //MODIFIES: f
    //EFFECTS: sets up the settings for the JFrame
    private void setUpJFrameSettings(JFrame f) {
        f.setSize(800, 800); //width and height
        f.setLayout(new GridLayout(1, 0)); //using layout manager
        f.setVisible(true); //making the frame visible
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //MODIFIES: playlistsPanel, menuPanel
    //EFFECTS: sets a shadow on the panel backgrounds if given true, else resets to transparent
    private void showShadowPanels(Boolean hasShadow) {
        if (hasShadow) {
            playlistsPanel.setBackground(new Color(0, 0, 0, 80));
            menuPanel.setBackground(new Color(0, 0, 0, 80));
        } else {
            playlistsPanel.setBackground(new Color(0, 0, 0, 0));
            menuPanel.setBackground(new Color(0, 0, 0, 0));
        }
    }

    //MODIFIES: tf
    //EFFECTS: sets text field to show message and panel appearance to show shadow
    private void activeProgramAppearance(String message) {
        tf.setText(message);
        showShadowPanels(true);
    }

    //MODIFIES: tf
    //EFFECTS: resets text field and panel appearance to default
    private void defaultProgramAppearance() {
        tf.setText("Welcome to MusicViewer");
        showShadowPanels(false);
    }

    //MODIFIES: b
    //EFFECTS:  makes the button produce a 'make a playlist' action
    private void setMakePlaylistsBtn(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProgramAppearance("Making a playlist...");
                String playlistName = JOptionPane.showInputDialog(frame, "Enter playlist name:");
                if (playlistName != null) {
                    musicOrganizer.addPlaylist(playlistName);
                    Playlist p = musicOrganizer.getPlaylistByName(playlistName);
                    JButton pb = new JButton(playlistName);
                    playlistsPanel.add(pb);
                    setPlaylistActionsBtn(pb, p);
                }
                defaultProgramAppearance();
                b.revalidate(); //makes sure button is on screen, not only on mouse hover
            }
        });
    }

    //MODIFIES: b
    //EFFECTS:  makes the button produce a 'load playlists' action
    private void setLoadPlaylistsBtn(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProgramAppearance("Loading playlists...");
                int choice = JOptionPane.showConfirmDialog(frame, "Do you want to load playlists from file?",
                        "Select an option", JOptionPane.OK_CANCEL_OPTION);
                if (choice == 0) {
                    //load playlists
                    loadMyMusic();
                    updatePlaylistsPanel();
                    JOptionPane.showMessageDialog(frame, "Playlists successfully loaded from file!");
                }
                defaultProgramAppearance();
                b.revalidate();
            }
        });
    }

    //MODIFIES: b
    //EFFECTS:  makes the button produce a 'save playlists' action
    private void setSavePlaylistsBtn(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProgramAppearance("Saving playlists...");
                int choice = JOptionPane.showConfirmDialog(frame, "Do you want to save all playlists?",
                        "Select an option", JOptionPane.OK_CANCEL_OPTION);
                if (choice == 0) {
                    //save playlists
                    saveMyMusic();
                    JOptionPane.showMessageDialog(frame, "Playlists successfully saved to file!");
                }
                defaultProgramAppearance();
            }
        });
    }

    //MODIFIES: b
    //EFFECTS:  makes the button produce an 'exit app' action
    private void setExitAppBtn(JButton b) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProgramAppearance("Exiting the app...");
                int choice = JOptionPane.showConfirmDialog(frame, "Do you wish to exit the app?");
                if (choice == 0) {
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    System.exit(0);
                } else {
                    defaultProgramAppearance();
                }
            }
        });
    }

    //MODIFIES: b
    //EFFECTS: adds a playlist actions menu to the button
    private void setPlaylistActionsBtn(JButton b, Playlist p) {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activeProgramAppearance("Viewing playlist...");
                setUpPlaylistActionsMenu(p);
                defaultProgramAppearance();
            }
        });
    }

    //EFFECTS: sets up the playlist actions menu
    private void setUpPlaylistActionsMenu(Playlist p) {
        JPanel playlistActionsPanel = new JPanel();
        JRadioButton r1 = new JRadioButton("View songs", true);
        JRadioButton r2 = new JRadioButton("Add a song");

        playlistActionsPanel.add(r1);
        playlistActionsPanel.add(r2);

        //use a button group tp keep radio buttons mutually exclusive
        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);

        int choice = JOptionPane.showConfirmDialog(frame, playlistActionsPanel,
                "Select an option", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            if (r1.isSelected()) {
                setUpSongsViewMenu(p);
            } else if (r2.isSelected()) {
                JOptionPane.showMessageDialog(frame, "We'll be able to add a song later :)");
            }
        }
    }

    //EFFECTS: sets up the songs view menu
    private void setUpSongsViewMenu(Playlist p) {
        if (p.getSongs().size() != 0) {
            showSongsToChooseFromMenu(p);
        } else {
            JOptionPane.showMessageDialog(frame, "This playlist has no songs to view!");
        }
    }

    //EFFECTS: shows the song selection panel for a non-empty playlist
    private void showSongsToChooseFromMenu(Playlist p) {
        JPanel svPanel = new JPanel(new GridLayout(0, 1)); //zero means it can grow infinitely
        ButtonGroup group = new ButtonGroup();
        String delimiter = "%%%"; //separates song name and artist name
        for (Song s : p.getSongs()) {
            JRadioButton r = new JRadioButton(s.getShortInfo());
            svPanel.add(r);
            if (group.getSelection() == null) {
                r.setSelected(true); //selects the first button by default
            }
            group.add(r);
            r.setActionCommand(s.getName() + delimiter + s.getArtist()); //sets an action command for radio button
        }
        int choice = JOptionPane.showConfirmDialog(frame, svPanel,
                "Select a song to view", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0 && group.getSelection() != null) {
            Song songSelected = getSongSelected(p, group, delimiter);
            setUpSongActionsMenu(songSelected);
        }
    }

    //EFFECTS: returns the song selected from a playlist based on its button group action
    private Song getSongSelected(Playlist p, ButtonGroup group, String delimiter) {
        String actionCommand = group.getSelection().getActionCommand();
        String songName = "";
        String artistName = "";
        int delimiterIndex = actionCommand.indexOf(delimiter);
        if (delimiterIndex != -1) {
            songName = actionCommand.substring(0, delimiterIndex);
            artistName = actionCommand.substring(delimiterIndex + delimiter.length());
        }
        return p.getSongByNameAndArtist(songName, artistName);
    }

    //EFFECTS: shows the action menu for a given song
    private void setUpSongActionsMenu(Song s) {
        JPanel songActionsPanel = new JPanel(new GridLayout(0,1));
        JRadioButton r1 = new JRadioButton("Play song", true);
        JRadioButton r2 = new JRadioButton("View song info");
        JRadioButton r3 = new JRadioButton("Edit song description");

        songActionsPanel.add(r1);
        songActionsPanel.add(r2);
        songActionsPanel.add(r3);

        ButtonGroup group = new ButtonGroup();
        group.add(r1);
        group.add(r2);
        group.add(r3);

        int choice = JOptionPane.showConfirmDialog(frame, songActionsPanel,
                s.getShortInfo(), JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            if (r1.isSelected()) {
                playSongDialog(s);
            } else if (r2.isSelected()) {
                viewSongDialog(s);
            } else if (r3.isSelected()) {
                editSongDescDialog(s);
            }
        }
    }

    //MODIFIES: s
    //EFFECTS: shows the dialog for user to edit the song description
    private void editSongDescDialog(Song s) {
        String input = JOptionPane.showInputDialog(frame, "Enter a new song description: ");
        if (input != null) {
            s.setDescription(input);
            JOptionPane.showMessageDialog(frame, "Successfully updated song description for \n"
                    + s.getShortInfo() + "!");
        }
    }

    //MODIFIES: s
    //EFFECTS: shows the dialog for user to play the song once
    private void playSongDialog(Song s) {
        s.increaseTimesPlayed();
        JOptionPane.showConfirmDialog(frame, "... You've now played it "
                + s.getTimesPlayed() + " times!",
                "Playing " + s.getShortInfo() + "...", JOptionPane.DEFAULT_OPTION);
    }

    //EFFECTS: shows the dialog for user to view the song information
    private void viewSongDialog(Song s) {
        JOptionPane.showConfirmDialog(frame, "Times played: " + s.getTimesPlayed() + "\n"
                        + "Description: " + s.getDescription(),
                s.getShortInfo(), JOptionPane.DEFAULT_OPTION);
    }

    //EFFECTS: saves music organizer object from file
    private void saveMyMusic() {
        try {
            jsonWriter.open();
            jsonWriter.write(musicOrganizer);
            jsonWriter.close();
            System.out.println("Saved the music to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: loads music organizer object from file
    private void loadMyMusic() {
        try {
            musicOrganizer = jsonReader.read();
            System.out.println("Loaded the music from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    //MODIFIES: playlistsPanel
    //EFFECTS: updates playlist panel to show buttons for all playlists in music organizer
    private void updatePlaylistsPanel() {
        playlistsPanel.removeAll();
        setUpPlaylistsPanel(playlistsPanel);
        ArrayList<Playlist> playlists = musicOrganizer.getAllPlaylists();
        for (Playlist p : playlists) {
            JButton b = new JButton(p.getName());
            playlistsPanel.add(b);
            setPlaylistActionsBtn(b, p);
            b.revalidate();
        }
    }
}


