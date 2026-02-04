package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.Objects;


public class ProductBasket {
    private final Product[] products = new Product[5];
    private int count = 0;

    public void addProduct(Product product) {
        if (product == null) {
            return;
        }
        if (count < products.length) {
            products[count] = product;
            count++;
        } else {
            System.out.println("Невозможно добавить продукт");
        }
    }

    public int getTotalCost() {
        int totalCost = 0;
        for (int i = 0; i < count; i++) {
            Product currentProduct = products[i];
            totalCost += currentProduct.getPrice();
        }
        return totalCost;
    }

    public void printBasket() {
        if (count == 0) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;

        for (int i = 0; i < count; i++) {
            Product currentProduct = products[i];
            if (currentProduct != null) {
                System.out.println(currentProduct.toString());
            }
            if (currentProduct.isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean checkProductByName(String searchName) {
        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getProductName().equals(searchName)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
        count = 0;
    }
}
