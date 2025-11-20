package ui;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;
import model.DataField;
import model.DataSet;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/*
 * Panel that displays all DataFields currently in a DataSet.
 * Each field is shown on its own line.
 */
@ExcludeFromJacocoGeneratedReport
public class DataSetPanel extends JPanel {
    private DataSet dataSet;
    private DefaultListModel<String> listModel;
    private JList<String> fieldList;

    // EFFECTS: constructs panel that displays fields from given DataSet
    public DataSetPanel(DataSet dataSet) {
        this.dataSet = dataSet;

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Fields in Dataset"));

        listModel = new DefaultListModel<>();
        fieldList = new JList<>(listModel);

        add(new JScrollPane(fieldList), BorderLayout.CENTER);
        refresh();
    }

    // MODIFIES: this
    // EFFECTS: reloads the list of fields from the DataSet
    public void refresh() {
        listModel.clear();
        List<DataField> fields = dataSet.getFields();
        for (DataField f : fields) {
            String line = f.getName() + " (" + f.getType() + ")";
            listModel.addElement(line);
        }
    }

    public int getSelectedIndex() {
        return fieldList.getSelectedIndex();
    }
    
    // MODIFIES: this 
    // EFFECTS: updates this panel to display fields from the new DataSet
    public void setDataSet(DataSet dataSet) {
        this.dataSet = dataSet;
        refresh();
    }
}
