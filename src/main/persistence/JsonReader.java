package persistence;

import model.Product;
import model.ProductManagementSystem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Allows ProductManagementSystems to be read from Json files
// Code adapted from JsonSerializationDemo project
public class JsonReader {
    private String sourceFile;

    //EFFECTS: constructs a JsonReader to read from given file
    public JsonReader(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    //EFFECTS: reads ProductManagementSystem from file and returns it.
    // throws IOException if any errors arise during reading
    public ProductManagementSystem read(String file) throws IOException {
        String jsonData = readFile(file);
        JSONObject jsonObj = new JSONObject(jsonData);
        return parseProductManagementSystem(jsonObj);
    }

    //EFFECTS: converts given file to string and returns string
    private String readFile(String sourceFile) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(sourceFile), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses ProductManagementSystem from given JSON Object and returns it
    private ProductManagementSystem parseProductManagementSystem(JSONObject jsonObj) {
        ProductManagementSystem productSys = new ProductManagementSystem();
        initializeProducts(productSys, jsonObj);
        return productSys;
    }

    //MODIFIES: ps
    //EFFECTS: parses products from JSON Object and adds them to the ProductManagementSystem
    private void initializeProducts(ProductManagementSystem ps, JSONObject jsonObj) {
        JSONArray jsonArr = jsonObj.getJSONArray("products");

        for (Object json:jsonArr) {
            JSONObject nextProduct = (JSONObject) json;
            addProduct(ps, nextProduct);
        }
    }

    //MODIFIES: ps
    //EFFECTS: parses a single product from JSON Object and adds it to the ProductManagementSystem
    private void addProduct(ProductManagementSystem ps, JSONObject jsonObj) {
        String name = jsonObj.getString("name");
        double price = jsonObj.getDouble("price");
        int id = jsonObj.getInt("id");
        int stock = jsonObj.getInt("stock");

        Product p = new Product(name, price, id, stock);
        ps.addProduct(p);

    }

}
