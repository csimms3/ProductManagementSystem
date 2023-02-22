package persistence;

import model.ProductManagementSystem;
import org.json.JSONObject;

import java.io.*;

// JSON writer, used to write ProductManagementSystem objects to file
// this code has been adapted from JsonSerializationDemo
public class JsonWriter {
    private static final int TAB  = 4;
    private PrintWriter writer;
    private String file;

    //EFFECTS: constructs a JsonWriter to write to given file
    public JsonWriter(String file) {
        this.file = file;
    }

    //MODIFIES: this
    //EFFECTS: opens file writer, throws FileNotFoundException if file cannot
    // be opened
    public void openFile() throws FileNotFoundException {
        writer = new PrintWriter(new File(file));
    }

    //MODIFIES: this
    //EFFECTS: writes JSON version of ProductManagementSystem to file
    public void write(ProductManagementSystem ps) {
        JSONObject jsonObj = ps.toJson();
        saveToFile(jsonObj.toString(TAB));
    }


    //MODIFIES: this
    //EFFECTS: closes writer object
    public void close() {
        writer.close();
    }

    //MODIFIES: this
    //EFFECTS: writes given string to file
    public void saveToFile(String json) {
        writer.print(json);
    }

}

