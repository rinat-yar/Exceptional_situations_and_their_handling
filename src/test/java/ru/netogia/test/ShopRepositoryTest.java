package ru.netogia.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netogia.NotFoundException;
import ru.netogia.Product;
import ru.netogia.ShopRepository;

public class ShopRepositoryTest {

    Product item1 = new Product (1, "хлеб", 50);
    Product item2 = new Product (5, "Мясо",300 );
    Product item3 = new Product (38,"Сыр", 220);

    @Test
    public void testRemoveExisting() {
        ShopRepository shop = new ShopRepository();

        shop.add(item1);
        shop.add(item2);
        shop.add(item3);
        shop.remove(5);

        Product[] expected = {item1, item3};
        Product[] actual = shop.findAll();

        Assertions.assertArrayEquals(expected,actual);
    }

    @Test
    public void testAdd() {
        ShopRepository shop = new ShopRepository();
        shop.add(item1);
        shop.add(item2);
        shop.add(item3);
        Product item4 = new Product(4, "Колбаса", 250);
        shop.add(item4);

        Product[] products = shop.findAll();
        Assertions.assertEquals(4, products.length);
        Product[] expectedProducts = {item1, item2, item3, item4};
        Assertions.assertArrayEquals(expectedProducts, products);
    }

    @Test
    public void testRemoveNot() {
        ShopRepository shop = new ShopRepository();

        shop.add(item1);
        shop.add(item2);
        shop.add(item3);

        Assertions.assertThrows(NotFoundException.class,
        () -> shop.remove(44)
        );
    }
}
