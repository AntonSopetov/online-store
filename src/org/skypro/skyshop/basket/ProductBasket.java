package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
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

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new ArrayList<>();

        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getProductName().equals(name)) {
                removed.add(p);
                iterator.remove();
            }
        }

        return removed;
    }
}