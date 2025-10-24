package persistence;

import model.DataField;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

@ExcludeFromJacocoGeneratedReport
public class JsonTest {

    protected void checkField(String name, String type, DataField field) {
        assertEquals(name, field.getName());
        assertEquals(type, field.getType());
    }

}
