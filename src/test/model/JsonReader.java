package model;

import model.DataSet;
import java.io.IOException;

/*
 * Represents a reader that loads a DataSet object from JSON data stored in a file.
 * The reader reads the contents of a JSON file, then reconstructs all fields of the DataSet,
 * then finally returns it as a fully restored object.
 */



public class JsonReader {
    private String source;

    // REQUIRES: source is a valid non-null file path string
    // EFFECTS: constructs a JsonReader to read a DataSet from the given file path
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS :reads a DataSet from file and returns it;
    //          throws IOException if an error occurs reading data from the file
    public DataSet read() throws IOException {
        // stub
        return null;
    } 

    // EFFECTS: reads the file contents at 'source' and returns it as a string;
    //          throws IOException if unable to read from the file
    public String readFile(String source) throws IOException {
        // stub
        return null;
    }

    // EFFECTS: parses a DataSet from a JSON object and returns it;
    //          throws IOException if the data format is invalid
    private DataSet parseDataSet() {
        // stub
        return null;
    }

    // MODIFIES: ds
    // EFFECTS: parses each DataField object from the JSON data and adds it to ds
    private void addFields() {

    }

    // MODIFIES: ds
    // EFFECTS: parses one DataField from the JSON object and adds it to ds
    private void addField() {
        
    }

}
