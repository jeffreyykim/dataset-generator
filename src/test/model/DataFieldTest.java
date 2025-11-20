package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataFieldTest {

    private DataField testDataField;


    @BeforeEach
    public void runBefore() {
        testDataField = new DataField("First Name", "String");
    }

    @Test
    public void testConstructor() {
        assertEquals("First Name", testDataField.getName());
        assertEquals("String", testDataField.getType());
    }

    @Test
    public void testGetName() {
        assertEquals("First Name", testDataField.getName());
    }

    @Test
    public void testGetType() {
        assertEquals("String", testDataField.getType());
    }

    @Test
    public void testGenerateValue() {
        String value = testDataField.generateValue();
        assertNotNull(value);
        assertTrue(value.startsWith("Sample_"));
    }

    @Test 
    public void testGenerateValueIntegerType() {
        DataField intField = new DataField("Age", "Integer");
        String value = intField.generateValue();

        int intValue = Integer.parseInt(value);
        assertTrue (intValue >= 0 && intValue < 100);
    }

    @Test
    public void testGenerateValueUnknownType() {
        DataField unknown = new DataField("Something", "Boolean");
        String value = unknown.generateValue();

        assertEquals("Unknown Type", value);
    } 
}
