package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.sql.rowset.WebRowSet;
import java.util.ArrayList;

//Product management system, keeps a catalogue of products
public class ProductManagementSystem implements Writable {

    private ArrayList<Product> catalogue;


    //EFFECTS: initializes a ProductManagementSystem with an empty catalogue
    public ProductManagementSystem() {
        catalogue = new ArrayList<>();
    }

    //REQUIRES: product with same id not already in catalogue
    //MODIFIES: this
    //EFFECTS: adds product to catalogue
    public void addProduct(Product p) {
        this.catalogue.add(p);
    }

    //REQUIRES: productId is a 4 digit int
    //EFFECTS: returns true if product with id already in catalogue, otherwise false
    public boolean containsProduct(int productId) {
        boolean contains = false;

        for (Product prod:catalogue) {
            if (prod.getId() == productId) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    //EFFECTS: returns true if product with id already in catalogue, otherwise false
    public boolean containsProduct(Product prod) {
        boolean contains = false;

        for (Product p:catalogue) {
            if (prod.getId() == p.getId()) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    //EFFECTS: returns string representation of catalogue
    public StringBuilder displayCatalogue() {
        StringBuilder str = new StringBuilder();

        if (catalogue.size() == 0) {
            str.append("Product System Empty.\n");
        } else {
            for (Product p:catalogue) {
                str.append(p.toString());
            }
        }
        return str;
    }

    //REQUIRES: product in catalogue
    //EFFECTS: returns given product
    public Product getProductById(int id) {
        for (Product p:catalogue) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    //GETTERS

    public int getCatalogueSize() {
        return catalogue.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("products", productsToJson());

        return jsonObj;
    }

    private JSONArray productsToJson() {
        JSONArray jsonArr = new JSONArray();

        for (Product p: this.catalogue) {
            jsonArr.put(p.toJson());
        }
        return jsonArr;
    }

}
