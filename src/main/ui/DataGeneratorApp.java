package ui;

import model.DataField;
import model.DataSet;
import java.util.List;
import java.util.Scanner;

// Mock data generator application
public class DataGeneratorApp {
    private DataSet dataset;
    private Scanner input;

    // EFFECTS: runs the data generator application
    public DataGeneratorApp() {
        input = new Scanner(System.in);
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
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> create dataset");
        System.out.println("\ta -> add field");
        System.out.println("\tr -> remove field");
        System.out.println("\tl -> list fields");
        System.out.println("\tg -> generate data");
        System.out.println("\tq -> quit");
    }

    private void createDataSet() {
        System.out.println("Enter dataset name: ");
        String name = input.nextLine();
        dataset = new DataSet(name);
        System.out.println("Dataset \"" + name + "\" created.");
    }

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
}
