package ui;

import model.DataField;
import model.DataSet;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

// Mock data generator application
@ExcludeFromJacocoGeneratedReport
public class DataGeneratorApp {
    private DataSet dataset;
    private Scanner input;
    private static final String JSON_STORE = "./data/dataset.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the data generator application
    public DataGeneratorApp() {
        input = new Scanner(System.in);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        while (keepGoing) {
            displayMenu();
            command = input.nextLine().toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Goodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes a user command
    private void processCommand(String command) {
        if (command.equals("c")) {
            createDataSet();
        } else if (command.equals("a")) {
            addField();
        } else if (command.equals("r")) {
            removeField();
        } else if (command.equals("l")) {
            listFields();
        } else if (command.equals("g")) {
            generateData();
        } else if (command.equals("s")) {
            saveDataSet();
        } else if (command.equals("o")) {
            loadDataSet();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create dataset");
        System.out.println("\ta -> add field");
        System.out.println("\tr -> remove field");
        System.out.println("\tl -> list fields");
        System.out.println("\tg -> generate data");
        System.out.println("\ts -> save dataset as JSON");
        System.out.println("\to -> load dataset from JSON");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: creates a new dataset
    private void createDataSet() {
        System.out.println("Enter dataset name: ");
        String name = input.nextLine();
        dataset = new DataSet(name);
        System.out.println("Dataset \"" + name + "\" created.");
    }

    // MODIFIES: this
    // EFFECTS: adds a new field to the current dataset
    private void addField() {
        if (dataset == null) {
            System.out.println("Please create a dataset first.");
        } else {
            System.out.println("Enter field name: ");
            String name = input.nextLine();
            System.out.println("Enter field type (String/Integer): ");
            String type = input.nextLine();
            dataset.addField(new DataField(name, type));
            System.out.println("Field \"" + name + "\" added.");
        }
    }

    // MODIFIES: this
    // EFFECTS: removes a field from the current dataset by name
    private void removeField() {
        if (dataset == null) {
            System.out.println("Please create a dataset first.");
        } else {
            System.out.println("Enter field name to remove: ");
            String name = input.nextLine();
            DataField toRemove = null;
            for (DataField f : dataset.getFields()) {
                if (f.getName().equalsIgnoreCase(name)) {
                    toRemove = f;
                }
            }

            if (toRemove != null) {
                dataset.removeField(toRemove);
                System.out.println("Field \"" + name + "\" removed.");
            } else {
                System.out.println("Field not found.");
            }
        }
    }

    // EFFECTS: displays all fields in the current dataset
    private void listFields() {
        if (dataset == null) {
            System.out.println("Please create a dataset first.");
        } else {
            System.out.println("Fields in dataset \"" + dataset.getName() + "\":");
            for (DataField f : dataset.getFields()) {
                System.out.println("- " + f.getName() + " (" + f.getType() + ")");
            }
        }
    }

    // EFFECTS: generates mock data for all fields in the dataset
    private void generateData() {
        if (dataset == null) {
            System.out.println("Please create a dataset first.");
        } else {
            List<String> data = dataset.generateData();
            List<DataField> fields = dataset.getFields();
            System.out.println("Generated data:");
            for (int i = 0; i < fields.size(); i++) {
                System.out.println(fields.get(i).getName() + ": " + data.get(i));
            }
        }
    }

    // EFFECTS: saves the current dataset to file
    private void saveDataSet() {
        if (dataset == null) {
            System.out.println("Please create a dataset first.");
            return;
        }
        try {
            jsonWriter.open();
            jsonWriter.write(dataset);
            jsonWriter.close();
            System.out.println("Saved " + dataset.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads dataset from file
    private void loadDataSet() {
        try {
            dataset = jsonReader.read();
            System.out.println("Loaded " + dataset.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
