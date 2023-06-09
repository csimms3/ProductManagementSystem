package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


// test suite to test the productManagementSystem, and all related product actions
class ProductManagementSystemTest {

    Product p1;
    Product p2;
    Product testProd;
    Product testProd2;

    ProductManagementSystem emptySys;
    ProductManagementSystem sys1;
    ProductManagementSystem sys2;

    @BeforeEach
    public void setup() {
        p1 = new Product("Apple", 0.99, 0001);
        p2 = new Product("Orange", 0.79, 1234);

        testProd = new Product("test", 0.01, 0000);
        testProd2 = new Product("test2", 0.02, 0002);

        emptySys = new ProductManagementSystem();
        sys1 = new ProductManagementSystem();
        sys2 = new ProductManagementSystem();

        sys1.addProduct(p1);

        sys2.addProduct(p1);
        sys2.addProduct(p2);
    }

    @Test
    public void testAddProductOnce() {
        assertFalse(emptySys.containsProduct(testProd));
        assertEquals(emptySys.getCatalogueSize(),0);
        emptySys.addProduct(testProd);
        assertEquals(emptySys.getCatalogueSize(),1);
        assertTrue(emptySys.containsProduct(testProd));

    }

    @Test
    public void testAddProductTwice() {
        assertFalse(sys2.containsProduct(0000));
        assertEquals(sys2.getCatalogueSize(),2);
        sys2.addProduct(testProd);
        assertEquals(sys2.getCatalogueSize(),3);
        assertTrue(sys2.containsProduct(0000));

        assertFalse(sys2.containsProduct(0002));
        assertEquals(sys2.getCatalogueSize(),3);
        sys2.addProduct(testProd2);
        assertEquals(sys2.getCatalogueSize(),4);
        assertTrue(sys2.containsProduct(0002));
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
    public void testDisplayCatalogue() {
        assertEquals(emptySys.displayCatalogue().toString(), "Product System Empty.\n");
        assertEquals(sys2.displayCatalogue().toString(),
                "| #0001 | Apple | Price: $0.99 | 0 in stock |\n" +
                "| #1234 | Orange | Price: $0.79 | 0 in stock |\n");
    }

    @Test
    public void getProductById() {
        assertNull(emptySys.getProductById(0001));

        assertEquals(sys1.getProductById(0001), p1);
        assertNull(sys1.getProductById(1234));

        assertEquals(sys2.getProductById(0001), p1);
        assertEquals(sys2.getProductById(1234), p2);
        assertNull(sys2.getProductById(4444));

    }

    @Test
    public void testGetStringOfProductAtPosition() {
        assertEquals(sys2.getStringOfProductAtPosition(0),
                "| #0001 | Apple | Price: $0.99 | 0 in stock |\n");
        assertEquals(sys2.getStringOfProductAtPosition(1),
                "| #1234 | Orange | Price: $0.79 | 0 in stock |\n");
    }

    @Test
    public void testDeleteProduct() {
        assertTrue(sys2.containsProduct(0001));
        sys2.deleteProduct(0001);
        assertFalse(sys2.containsProduct(0001));

        assertFalse(sys2.containsProduct(2332));
        sys2.deleteProduct(2332);
        assertFalse(sys2.containsProduct(2332));
    }

}