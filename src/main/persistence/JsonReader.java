package persistence;

import model.DataField;
import model.DataSet;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

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
    // throws IOException if an error occurs reading data from the file
    public DataSet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDataSet(jsonObject);
    }

    // EFFECTS: reads the file contents at 'source' and returns it as a string;
    // throws IOException if unable to read from the file
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses a DataSet from a JSON object and returns it;
    // throws IOException if the data format is invalid
    private DataSet parseDataSet(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        DataSet ds = new DataSet(name);
        addFields(ds, jsonObject);
        return ds;
    }

    // MODIFIES: ds
    // EFFECTS: parses each DataField object from the JSON data and adds it to ds
    private void addFields(DataSet ds, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("fields");
        for (Object json : jsonArray) {
            JSONObject nextField = (JSONObject) json;
            addField(ds, nextField);
        }
    }

    // MODIFIES: ds
    // EFFECTS: parses one DataField from the JSON object and adds it to ds
    private void addField(DataSet ds, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String type = jsonObject.getString("type");
        DataField field = new DataField(name, type);
        ds.addField(field);

    }
}


