package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;


public class ProductBasket {
    List<Product> products = new LinkedList<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public double getTotalCost() {
        int totalCost = 0;
        for (Product p : products) {
            totalCost += p.getPrice();
        }
        return totalCost;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;

        for (Product p : products) {
            System.out.println(p.toString());
            if (p.isSpecial()) {
                specialCount++;
            }
        }
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean checkProductByName(String searchName) {
        for (Product p : products) {
            if (p.getProductName().equals(searchName))
                return true;
        }
        return false;
    }

    public void clearBasket() {
        products.clear();
    }

    public boolean removeProductByName(String name) {
        for (Product p : products) {
            if (p.getProductName().equals(name)) {
                products.remove(p);
                return true;
            }
        }
        return false;
    }
}
