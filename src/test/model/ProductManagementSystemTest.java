package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagementSystemTest {

    Product p1;
    Product p2;
    Product testProd;

    ProductManagementSystem emptySys;
    ProductManagementSystem sys1;
    ProductManagementSystem sys2;

    @BeforeEach
    public void setup() {
        p1 = new Product("Apple", 0.99, 0001);
        p2 = new Product("Orange", 0.79, 1234);

        testProd = new Product("test", 0.01, 0000);

        emptySys = new ProductManagementSystem();
        sys1 = new ProductManagementSystem();
        sys2 = new ProductManagementSystem();

        sys1.addProduct(p1);

        sys2.addProduct(p1);
        sys2.addProduct(p2);
    }

    @Test
    public void testContainsProduct() {
        assertFalse(emptySys.containsProduct(p1));
        assertFalse(emptySys.containsProduct(p2));

        assertTrue(sys1.containsProduct(p1));
        assertFalse(sys1.containsProduct(p2));

        assertTrue(sys2.containsProduct(p1));
        assertTrue(sys2.containsProduct(p2));
    }

    @Test
    public void testAddProduct() {
        assertFalse(emptySys.containsProduct(testProd));
        assertEquals(emptySys.getCatalogueSize(),0);
        emptySys.addProduct(testProd);
        assertEquals(emptySys.getCatalogueSize(),1);
        assertTrue(emptySys.containsProduct(testProd));

        assertFalse(sys2.containsProduct(testProd));
        assertEquals(sys2.getCatalogueSize(),2);
        sys2.addProduct(testProd);
        assertEquals(sys2.getCatalogueSize(),3);
        assertTrue(sys2.containsProduct(testProd));



    }

}