package model;

import model.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


// Represents a log of music organizer events.
// It is the only event log in the system, and uses a Singleton design pattern.
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> events;

    // Constructor
    // EFFECTS: creates an event log with an empty list of events
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: returns an instance of the event log and creates one if it doesn't exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECTS: clears the event log and adds it as an event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECTS: returns an iterator of events
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
