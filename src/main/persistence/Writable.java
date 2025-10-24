package persistence;

import org.json.JSONObject;

/*
 * Represents an object that can be written to a JSON file
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
