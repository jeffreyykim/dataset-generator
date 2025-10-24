
package persistence;

import model.DataField;
import model.DataSet;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class JsonReaderTest extends JsonTest {
    
    @Test 
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DataSet ds = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test 
    void testReaderEmptyDataSet() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDataSet.json");
        try {
            DataSet ds = reader.read();
            assertEquals("My dataset", ds.getName());
            assertEquals(0, ds.getFields().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test 
    void testReaderGeneralDataSet() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDataSet.json");
        try {
            DataSet ds = reader.read();
            assertEquals("My dataset", ds.getName());
            List<DataField> fields = ds.getFields();
            assertEquals(2, fields.size());
            checkField("Name", "String", fields.get(0));
            checkField("Age", "Integer", fields.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

