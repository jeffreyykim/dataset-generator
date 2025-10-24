package persistence;

import model.DataField;
import model.DataSet;
import org.junit.jupiter.api.Test;

import ca.ubc.cs.ExcludeFromJacocoGeneratedReport;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExcludeFromJacocoGeneratedReport
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDataSet() {
        try {
            DataSet ds = new DataSet("My dataset");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDataSet.json");
            writer.open();
            writer.write(ds);
            writer.close();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDataSet() {
        try {
            DataSet ds = new DataSet("My dataset");
            ds.addField(new DataField("Name", "String"));
            ds.addField(new DataField("Age", "Integer"));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDataSet.json");
            writer.open();
            writer.write(ds);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDataSet.json");
            ds = reader.read();
            assertEquals("My dataset", ds.getName());
            List<DataField> fields = ds.getFields();
            assertEquals(2, fields.size());
            checkField("Name", "String", fields.get(0));
            checkField("Age", "Integer", fields.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
