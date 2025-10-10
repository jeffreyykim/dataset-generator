package model;

import java.util.Random;

/*
 * Represents a single field definition within a data set
 * name represents the label of the data
 * type represents the type of values generated.
 */
public class DataField {
    private String name;
    private String type;
    private static final Random rand = new Random();

    // REQUIRES: name and type are not empty
    // EFFECTS: constructs a datafield with the given name and type
    public DataField(String name, String type) {
        this.name = name;
        this.type = type;
    }

    // EFFECTS: returns the name of this DataField
    public String getName() {
        return name;
    }

    // EFFECTS: returns the type of this DataField
    public String getType() {
        return type;
    }

    // EFFECTS: generates a random value based on the type of field
    public String generateValue() {
        if (type.equalsIgnoreCase("integer")) {
            return "" + rand.nextInt(100);
        } else if (type.equalsIgnoreCase("string")) {
            return "Sample_" + rand.nextInt(100);
        } else {
            return "Unknown Type";
        }
    }
}
