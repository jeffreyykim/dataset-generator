package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DataSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * Represent the main window for the mock data generator application.
 * Displays the current DataSet and illustrates control for user stories. 
 */
@ExcludeFromJacocoGeneratedReport
public class DataGeneratorGUI extends JFrame {
    private static final String STATUS_OK = "Ready";
    private JLabel statusLabel;

    private DataSet dataSet;
    private DataSetPanel dataSetPanel;

    private JTextField fieldNameField;
    private JComboBox<String> fieldTypeCombo;
    private JButton addFieldButton;
    private JButton removeFieldButton;

    // EFFECTS: constructs the main GUI window
    public DataGeneratorGUI() {
        super("Mock Data Generator");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        dataSet = new DataSet("My dataset");

        statusLabel = new JLabel(STATUS_OK);
        add(statusLabel, BorderLayout.NORTH);

        dataSetPanel = new DataSetPanel(dataSet);
        add(dataSetPanel, BorderLayout.CENTER);

        addControlPanel();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: creates bottom panel with controls for adding/removing fields
    private void addControlPanel() {
        JPanel controlPanel = new JPanel();
        controlPanel.setBorder(BorderFactory.createTitledBorder("EditFields"));

        fieldNameField = new JTextField(10);
        fieldTypeCombo = new JComboBox<>(new String[] {"String", "Integer"});

        addFieldButton = new JButton("Add Field");
        removeFieldButton = new JButton("Remove Selected Field");

        controlPanel.add(new JLabel("Name:"));
        controlPanel.add(fieldNameField);
        controlPanel.add(new JLabel("Type:"));
        controlPanel.add(fieldTypeCombo);
        controlPanel.add(addFieldButton);
        controlPanel.add(removeFieldButton);

        add(controlPanel, BorderLayout.SOUTH);

        addListeners();
    }

    // MODIFIES: this, dataSet
    // EFFECTS: sets up button listeners for adding/removing fields
    private void addListeners() {
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

    // EFFECTS: returns index of selected field in dataSetPanel;
    //          otherwise, returns -1 if nothing is selected
    private int dataSetPanelIndexOfSelection() {
        return dataSetPanel.getSelectedIndex();
    }


}
