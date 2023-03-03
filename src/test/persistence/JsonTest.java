package persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import model.Product;

// parent json testing class, provides product validation functionality
public class JsonTest {
    protected void validateProduct(String name, double price, int id, int stock, Product product) {
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(id, product.getId());
        assertEquals(stock, product.getStock());

    }
}
