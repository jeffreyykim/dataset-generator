package model;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataSetTest {

    private DataSet testDataSet;
    private DataField stringDataField;
    private DataField intDataField;
    private DataField unknownDataField;

    @BeforeEach
    public void runBefore() {
        testDataSet = new DataSet("Test Dataset");
        stringDataField = new DataField("First Name", "String");
        intDataField = new DataField("Age", "Integer");
        unknownDataField = new DataField("Unknown", "Boolean");
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testDataSet.getFields().size());
    }

    @Test
    public void testAddField() {
        testDataSet.addField(stringDataField);
        assertEquals(1, testDataSet.getFields().size());
        assertTrue(testDataSet.getFields().contains(stringDataField));
        testDataSet.addField(intDataField);
        assertEquals(2, testDataSet.getFields().size());
        assertTrue(testDataSet.getFields().contains(intDataField));
    }

    @Test 
    public void testRemoveField() {
        testDataSet.addField(stringDataField);
        assertEquals(1, testDataSet.getFields().size());
        testDataSet.removeField(stringDataField);
        assertEquals(0, testDataSet.getFields().size());
    }

    @Test 
    public void testGetFields() {
        testDataSet.addField(stringDataField);
        assertTrue(testDataSet.getFields().contains(stringDataField));
        testDataSet.addField(intDataField);
        assertTrue(testDataSet.getFields().contains(intDataField));
    }

    @Test
    public void testGenerateData() {
        testDataSet.addField(stringDataField);
        testDataSet.addField(intDataField);
        testDataSet.addField(unknownDataField);

        List<String> generated = testDataSet.generateData();
        assertEquals(testDataSet.getFields().size(), 3);

        assertTrue(generated.get(0).startsWith("Sample_"));
        assertEquals(generated.get(2), "Unknown Type");
    }
}