package model;

/*
 * Represents a single field definition within a data set
 * name represents the label of the data
 * type represents the type of values generated.
 * 
 */


public class DataField {
    private String name; 
    private String type; 

    // REQUIRES: name and type are not empty 
    // EFFECTS: constructs a datafield with the given name and type
    public DataField(String name, String type){

    }

    // EFFECTS: returns the name of this datafield
    public String getName(){
        return null;
    }

    // EFFECTS: returns the type of this datafield
    public String getType() {
        return null;
    }

    // EFFECTS: generates a random value based on the type of field
    public String generateValue() {
        return null;
    }
}
