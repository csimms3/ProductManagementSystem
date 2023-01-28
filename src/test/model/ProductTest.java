package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    Product p1;
    Product p2;
    Product p3;


    @BeforeEach
    public void setup(){
        p1 = new Product("P1", 5.99, 1000);
        p2 = new Product("P2", 0.56, 9999);
        p3 = new Product("Ball", 10.00, 8981);
    }

    @Test
    public void testConstructor() {
        assertEquals(p1.getName(), "P1");
        assertEquals(p1.getPrice(), 5.99);
        assertEquals(p1.getStock(), 0);
        assertEquals(p1.getId(), 1000);

        assertEquals(p2.getName(), "P2");
        assertEquals(p2.getPrice(), 0.56);
        assertEquals(p2.getStock(), 0);
        assertEquals(p2.getId(), 9999);

        assertEquals(p3.getName(), "Ball");
        assertEquals(p3.getPrice(), 10.00);
        assertEquals(p3.getStock(), 0);
    }
}
