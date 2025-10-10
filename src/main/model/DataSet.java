package model;

import java.util.ArrayList;
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
        this.name = name;
        this.fields = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds the given field to the dataset
    public void addField(DataField field) {
        fields.add(field);
    }

    // MODIFIES: this
    // EFFECTS: removes the given field from the dataset if it exists
    public void removeField(DataField field) {
        fields.remove(field);
    }

    // EFFECTS: returns the name of this dataset
    public String getName() {
        return name;
    }

    // EFFECTS: returns a list of all data fields in the dataset
    public List<DataField> getFields() {
        return fields;
    }

    // EFFECTS: uses generateValue() for each datafield to generate mock data
    public List<String> generateData() {
        List<String> generated = new ArrayList<>();
        for (DataField f : fields) {
            generated.add(f.generateValue());
        }
        return generated;
    }
}
