package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

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
        testDataField.generateValue();
    }
}
