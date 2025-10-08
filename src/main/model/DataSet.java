package model;

import java.util.List;

/*
 * Represents a dataset which contains numerous DataFields
 * A dataset has the ability to add, remove, and list its fields,
 * and can generate mock data entries from them.
 * 
 */
public class DataSet {
    private String name;
    private List<DataField> fields;


    // REQUIRES: name is not an empty string
    // EFFECTS: constructs an empty dataset using the given name
    public DataSet(String name) {

    }

    // MODIFIES: this
    // EFFECTS: adds the given field to the dataset
    public void addField(DataField field) {

    }

    // MODIFIES: this
    // EFFECTS: removes the given field from the dataset if it exists
    public void removeField(DataField field) {

    }

    // EFFECTS: returns a list of all datafields in the dataset
    public List<DataField> getFields() {
        return null;
    }

    // EFFECTS: uses generateValue() for each datafield to generate mock data
    public List<String> generateData() {
        return null;
    }
}
