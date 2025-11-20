package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import javax.swing.*;
import java.awt.*;

/*
 * Represent the main window for the mock data generator application.
 * Displays the current DataSet and illustrates control for user stories. 
 */
@ExcludeFromJacocoGeneratedReport
public class DataGeneratorGUI extends JFrame {
    private static final String STATUS_OK = "Ready";
    private JLabel statusLabel;
    private JPanel centerPanel;

    // EFFECTS: constructs the main GUI window
    public DataGeneratorGUI() {
        super("Mock Data Generator");

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        statusLabel = new JLabel(STATUS_OK);
        add(statusLabel, BorderLayout.NORTH);

        centerPanel = new JPanel();
        centerPanel.setPreferredSize(new Dimension(600, 400));
        centerPanel.setBackground(new Color(240, 240, 240));
        add(centerPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
