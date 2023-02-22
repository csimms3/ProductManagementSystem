package persistence;

import model.ProductManagementSystem;
import org.json.JSONObject;

import java.io.IOException;

// Allows ProductManagementSystems to be read from Json files
// Code adapted from JsonSerializationDemo project
public class JsonReader {
    private String file;

    //EFFECTS: constructs a JsonReader to read from given file
    public JsonReader(String file) {
        this.file = file;
    }


    //EFFECTS: reads ProductManagementSystem from file and returns it.
    // throws IOException if any errors arise during reading
    public ProductManagementSystem readFile(String file) throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonObj = new JSONObject(jsonData);
        return parseProductManagementSystem
    }
}
