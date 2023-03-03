package persistence;

import model.ProductManagementSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// test class to ensure the jsonReader functions as expected
public class JsonReaderTest extends JsonTest {

    // test reader fails when opening nonexistent file
    @Test
    void testJsonReaderInvalidFile() {
        JsonReader jsonReader = new JsonReader("./data/noFileHereHmmmm.json");

        try {
            ProductManagementSystem productSys = jsonReader.read();
            fail("File should not have loaded, IOException should have been thrown.");
        } catch (IOException e) {
            //all good, test passed
        }
    }

    //test reader can correctly load empty productsystem
    @Test
    void testJsonReaderEmptyProductFile() {
        JsonReader jsonReader = new JsonReader("./data/testJsonReaderEmptyProductSystem.json");

        try {
            ProductManagementSystem productSys = jsonReader.read();
            assertEquals(0,productSys.getCatalogueSize());

        } catch (IOException e) {
            fail("Unexpected IOException, file invalid or reader error.");
        }
    }

    //test reader can accurately load non-empty productsystem
    @Test
    void testJsonReaderRegularProductFile() {
        JsonReader jsonReader = new JsonReader("./data/testJsonReaderNormalProductSystem.json");

        try {
            ProductManagementSystem productSys = jsonReader.read();
            assertEquals(3,productSys.getCatalogueSize());

            validateProduct("Apple", 0.99, 1, 3, productSys.getProductById(0001));
            validateProduct("Ball", 4.00, 13, 0, productSys.getProductById(13));
            validateProduct("210-textbook", 119.99, 1205, 2, productSys.getProductById(1205));

        } catch (IOException e) {
            fail("Unexpected IOException, file invalid or reader error.");
        }
    }

}
