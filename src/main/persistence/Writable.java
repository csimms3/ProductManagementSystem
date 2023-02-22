package persistence;


import org.json.JSONObject;

// base object for things that need to be written to JSON
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
