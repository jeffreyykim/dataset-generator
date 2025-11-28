package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DataSet;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;

import java.awt.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.Event;
import model.EventLog;

/*
 * Represent the main window for the mock data generator application.
 * Displays the current DataSet and illustrates control for user stories. 
 */
@ExcludeFromJacocoGeneratedReport
public class DataGeneratorGUI extends JFrame {
    private static final String JSON_STORE = "./data/dataset.json";
    private static final String STATUS_OK = "Ready";

    private JLabel statusLabel;
    private JLabel imageLabel;

    private DataSet dataSet;
    private DataSetPanel dataSetPanel;

    private JTextField fieldNameField;
    private JComboBox<String> fieldTypeCombo;
    private JButton addFieldButton;
    private JButton removeFieldButton;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTextArea outputArea;
    private JButton generateButton;

    private JButton saveButton;
    private JButton loadButton;

    // EFFECTS: constructs the main GUI window
    public DataGeneratorGUI() {
        super("Mock Data Generator");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // EFFECTS: when the main window is closing, print all logged events to console
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event ev : EventLog.getInstance()) {
                    System.out.println(ev.toString());
                }
            }
        });

        dataSet = new DataSet("My dataset");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initTopPanel();

        dataSetPanel = new DataSetPanel(dataSet);
        add(dataSetPanel, BorderLayout.CENTER);

        addControlPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates bottom panel with controls for adding/removing fields
    @SuppressWarnings("methodlength")
    private void addControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("EditFields"));

        fieldNameField = new JTextField(10);
        fieldTypeCombo = new JComboBox<>(new String[] { "String", "Integer" });

        addFieldButton = new JButton("Add Field");
        removeFieldButton = new JButton("Remove Selected Field");

        controlPanel.add(new JLabel("Name:"));
        controlPanel.add(fieldNameField);
        controlPanel.add(new JLabel("Type:"));
        controlPanel.add(fieldTypeCombo);
        controlPanel.add(addFieldButton);
        controlPanel.add(removeFieldButton);

        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane outputScroll = new JScrollPane(outputArea);

        generateButton = new JButton("Generate Data Row");
        saveButton = new JButton("Save");
        loadButton = new JButton("Load");

        JPanel ioPanel = new JPanel();
        ioPanel.add(generateButton);
        ioPanel.add(saveButton);
        ioPanel.add(loadButton);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(controlPanel, BorderLayout.NORTH);
        bottomPanel.add(outputScroll, BorderLayout.CENTER);
        bottomPanel.add(ioPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
        addListeners();
    }


    // MODIFIES: this
    // EFFECTS: adds all button listeners for the GUI controls
    private void addListeners() {
        addAddFieldListener();
        addRemoveFieldListener();
        addGenerateButtonListener();
        addSaveButtonListener();
        addLoadButtonListener();
    }

    // MODIFIES: this, dataSet
    // EFFECTS: adds listener to addFieldButton to create a 
    private void addAddFieldListener() {
        addFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = fieldNameField.getText().trim();
                String type = fieldTypeCombo.getSelectedItem().toString();

                if (name.isEmpty()) {
                    statusLabel.setText("Please enter a field name");
                    return;
                }

                dataSet.addField(new model.DataField(name, type));
                dataSetPanel.refresh();
                statusLabel.setText(STATUS_OK);
                fieldNameField.setText("");
            }
        });
    }

    // MODIFIES: this, dataSet
    // EFFECTS: adds listener to removeFieldButton to remove the selected DataField from the DataSet
    private void addRemoveFieldListener() {
        removeFieldButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = dataSetPanelIndexOfSelection();
                if (index == -1) {
                    statusLabel.setText("No field selected to remove.");
                    return;
                }
                model.DataField toRemove = dataSet.getFields().get(index);
                dataSet.removeField(toRemove);

                dataSetPanel.refresh();
                statusLabel.setText(STATUS_OK);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds listener to generateButton to display a row of mock data in the text area
    private void addGenerateButtonListener() {
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dataSet.getFields().isEmpty()) {
                    statusLabel.setText("Add at least one field before generating data.");
                    return;
                }

                List<String> values = dataSet.generateData();
                List<model.DataField> fields = dataSet.getFields();

                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < fields.size(); i++) {
                    model.DataField f = fields.get(i);
                    String v = values.get(i);
                    sb.append(f.getName())
                        .append(". ")
                        .append(v)
                        .append("\n");
                }

                outputArea.setText(sb.toString());
                statusLabel.setText(STATUS_OK);
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: adds listener to saveButton to write the current DataSet to JSON_STORE
    private void addSaveButtonListener() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    jsonWriter.open();
                    jsonWriter.write(dataSet);
                    jsonWriter.close();
                    statusLabel.setText("Saved " + dataSet.getName() + " to " + JSON_STORE);
                } catch (FileNotFoundException ex) {
                    statusLabel.setText("Unable to write to file: " + JSON_STORE);
                    JOptionPane.showMessageDialog(
                        DataGeneratorGUI.this,
                        "Unable to write to file:\n" + JSON_STORE,
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    // MODIFIES: this, dataSet
    // EFFECTS: adds listener to loadButton to read a DataSet from JSON_STORE and update the UI
    private void addLoadButtonListener() {
        loadButton.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {
                try {
                    DataSet loaded = jsonReader.read();
                    dataSet = loaded;
                    dataSetPanel.setDataSet(dataSet);
                    outputArea.setText("");
                    statusLabel.setText("Loaded " + dataSet.getName() + " from " + JSON_STORE);
                } catch (IOException ex) {
                    statusLabel.setText("Unable to read from file: " + JSON_STORE);
                    JOptionPane.showMessageDialog(
                        DataGeneratorGUI.this,
                        "Unable to read from file:\n" + JSON_STORE,
                        "Load Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
    }

    // EFFECTS: returns index of selected field in dataSetPanel;
    // otherwise, returns -1 if nothing is selected
    private int dataSetPanelIndexOfSelection() {
        return dataSetPanel.getSelectedIndex();
    }

    // MODIFIES: this
    // EFFECTS: creates the top panel with an image and status;
    // if there is no image, does not include image and only status
    private void initTopPanel() {
        statusLabel = new JLabel(STATUS_OK);
        String imgPath = "images/data.png";
        ImageIcon rawIcon = new ImageIcon(imgPath);

        if (rawIcon.getIconWidth() > 0) {
            Image scaled = rawIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(scaled);
            imageLabel = new JLabel(icon);
        } else {
            imageLabel = new JLabel();
        }

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(imageLabel, BorderLayout.WEST);
        topPanel.add(statusLabel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
    }

}
