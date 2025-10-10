package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class DataSetTest {

    private DataSet testDataSet;
    private DataField dataField1;
    private DataField dataField2;

    @BeforeEach
    public void runBefore() {
        testDataSet = new DataSet("Test Dataset");
        dataField1 = new DataField("First Name", "String");
        dataField2 = new DataField("Age", "Integer");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testDataSet.getFields().size());
    }

    @Test
    public void testAddField() {
        testDataSet.addField(dataField1);
        assertEquals(1, testDataSet.getFields().size());
        testDataSet.addField(dataField2);
        assertEquals(2, testDataSet.getFields().size());
    }

    @Test 
    public void testRemoveField() {
        testDataSet.addField(dataField1);
        assertEquals(1, testDataSet.getFields().size());
        testDataSet.removeField(dataField1);
        assertEquals(0, testDataSet.getFields().size());
    }

    @Test 
    public void testGetFields() {
        testDataSet.addField(dataField1);
        assertTrue(testDataSet.getFields().contains(dataField1));
        testDataSet.addField(dataField2);
        assertTrue(testDataSet.getFields().contains(dataField2));
    }

    @Test
    public void testGenerateData() {
        testDataSet.addField(dataField1);
        testDataSet.addField(dataField2);

        assertEquals(testDataSet.getFields().size(), testDataSet.generateData().size());
    }
}