# Music Playlist Project
### By: Linda L.

## Introduction
Hi! Thanks for stopping by.
Let's start with some quick facts.
#### What will the application *do*?

- The application will allow you to store information about your favorite songs
in a dedicated place, where you will have the freedom to organize them into 
playlists, and view details about a song whenever you want.

#### Who will *use* it?
- It is intended for anyone who listens to music often, likes to record information 
about their favorite songs, and enjoys viewing their songs displayed neatly as 
playlists in a Java application.

#### Why is this project of *interest* to you?
- As someone who listens to hours of music on the daily, I thought to myself,
  "Hey, wouldn't it be cool to have my own music playlist organizer that is *not* riddled
  with ads?" And so came the birth of this project.

## User Stories

- As a user, I want to be able to add a song to a playlist 
- As a user, I want to be able to view a list of songs on a playlist
- As a user, I want to be able to remove a song from a playlist
- As a user, I want to be able to edit the details of a song
- As a user, I want to be able to create multiple playlists 
- As a user, I want to be able to optionally save my music data to file
- As a user, I want to be able to optionally load my music data from file

---

# Instructions For Grader

### To start, please run the MusicViewer class in the "gui" folder under "ui" to open the app.
### You can generate the first required action (adding multiple songs to a playlist) by ...
  - run the MusicViewer class under the "gui" folder in "ui", if not already done
  - make a new playlist (or use an existing playlist if you've made one from before)
  - to make a new playlist, see below
    - click **"Make a new playlist**" button, then type a name for the playlist
    - click **"OK"** to create the new playlist
    - a new button should be created with the name you inputted
      - this contains the menu options for the playlist 
  - once you have a playlist, continue below...
  - click on the button with the **name you inputted**, and a dialog will pop up
  - select **"Add a song"** and click **"OK**"
  - enter a name for the song, click **"OK"**
  - then enter a name for the artist, click **"OK"**
  - click **"OK"** when success message pops up
  - to re-enter the action menu, click on the button with the **name you inputted**
  - select the option **"View songs"** to view songs in the playlist
  - if you see the song that you added earlier, you are done.
    - optionally, you can click **"OK"** to view the menu options for a single song
  - if you wish to add more songs, repeat the steps above. 
### You can generate the second required action (removing songs from a playlist) by ...
  - click on a button created with the **name you inputted**, where it has at least one song 
    - if you do not have one, follow the steps in "adding a song to a playlist" above until you do
  - select **"Remove a song"** and click **"OK"**
  - select the song you wish to remove from the playlist, and click **"OK"**
  - click **"OK"** when success message pops up
  - to re-enter the action menu, click on the button with the **name you inputted**
  - select the option **"View songs"** to view songs in the playlist
  - if you do not see the song anymore in the playlist, you are done.
### You can locate my visual component by 
  - it is the **background image** of the application.
### You can save the state of my application by 
  - click **"Save playlists"** button, then click **"OK"** when the dialog pops up to confirm.
### You can reload the state of my application by 
  - click **"Load playlists"** button, then click **"OK"** when the dialog pops up to confirm.

---

# Phase 4: Task 2

A representative sample of events logged by the system can be shown below:

    *** Event Log ***
    Sun Nov 26 12:22:31 PST 2023
    Retrieved the music data from file
    Sun Nov 26 12:22:35 PST 2023
    A new playlist was created: winter songs
    Sun Nov 26 12:40:41 PST 2023
    Added a song to playlist: agora hills by doja cat
    Sun Nov 26 12:22:46 PST 2023
    Played the song: agora hills by doja cat
    Sun Nov 26 12:22:54 PST 2023
    Removed a song from playlist: agora hills by doja cat
    Sun Nov 26 12:22:59 PST 2023
    Wrote the music data to file


---

# Phase 4: Task 3

### Reflection
If I had more time, I would have considered abstracting the common behaviors from the MusicOrganizer and Playlist.
Specifically, I would have made it into a broader class, or even an interface, to capture similar elements
common to both classes (e.g. "MusicList"). Additionally, I would have preferred to reduce the amount of repetition in
the code for the MusicApp menu commands, as well as the Music Viewer buttons. This could have been done with a method
specifically designed for processing menu commands, but the downside would be that the argument would need to be very
long for that method. I am also inclined to think that some repetition of code would still be inevitable given that
menu commands would need to be shown alongside other existing options, so it might not be a huge change
in terms of results. Overall, I am satisfied with how my project turned out, and the process of creating it was a
valuable learning experience for me. 

