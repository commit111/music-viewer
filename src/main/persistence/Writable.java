package persistence;

import org.json.JSONObject;


// Represents a writable object for JSON.
public interface Writable {
    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
