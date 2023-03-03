package persistence;

import model.Product;
import model.ProductManagementSystem;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class to ensure jsonWriter writes to file correctly
public class JsonWriterTest extends JsonTest {

    //test writer throws exception when invalid file name provided
    @Test
    void testJsonWriterInvalidFile() {
        try {
            ProductManagementSystem productSys = new ProductManagementSystem();
            JsonWriter jsonWriter = new JsonWriter("./data/badFile\0\nHereHmmmm.json");
            jsonWriter.openFile();
            fail("Nonexistent file should not have opened, IOException should have been thrown.");
        } catch (IOException e) {
            //all good, test passed
        }
    }


    //test writer can correctly write empty productsystem
    @Test
    void testJsonReaderEmptyProductFile() {
        try {
            ProductManagementSystem productSys = new ProductManagementSystem();
            JsonWriter jsonWriter = new JsonWriter("./data/testJsonWriterEmptyProductSystem.json");
            jsonWriter.openFile();
            jsonWriter.write(productSys);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testJsonWriterEmptyProductSystem.json");
            productSys = jsonReader.read();

            assertEquals(0,productSys.getCatalogueSize());

        } catch (IOException e) {
            fail("Unexpected IOException, file invalid or writer error.");
        }
    }

    //test writer can correctly write non-empty productsystem
    @Test
    void testJsonReaderRegularProductFile() {
        try {
            ProductManagementSystem productSys = new ProductManagementSystem();
            Product p1 = new Product("Apple", 0.99, 0001);
            Product p2 = new Product("Orange", 4.79, 1234);
            p1.addStock(3);

            productSys.addProduct(p1);
            productSys.addProduct(p2);

            JsonWriter jsonWriter = new JsonWriter("./data/testJsonWriterNormalProductSystem.json");
            jsonWriter.openFile();
            jsonWriter.write(productSys);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testJsonWriterNormalProductSystem.json");
            productSys = jsonReader.read();

            assertEquals(2,productSys.getCatalogueSize());
            validateProduct("Apple", 0.99, 0001, 3, p1);
            validateProduct("Orange", 4.79, 1234, 0, p2);

        } catch (IOException e) {
            fail("Unexpected IOException, file invalid or writer error.");
        }
    }
}
