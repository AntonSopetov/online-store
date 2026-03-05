package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;


public class ProductBasket {
    Map<String, List<Product>> products = new HashMap<>();

    public void addProduct(Product product) {
        String name = product.getName();
        products
                .computeIfAbsent(name, k -> new ArrayList<>())
                .add(product);
    }

    public double getTotalCost() {
        double totalCost = 0.0;
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                totalCost += p.getPrice();
            }
        }
        return totalCost;
    }

    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        int specialCount = 0;

        for (List<Product> list : products.values()) {
            for (Product p : list) {
                System.out.println(p.toString());
                if (p.isSpecial()) {
                    specialCount++;
                }
            }
        }
        System.out.println("Итого: " + getTotalCost());
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean checkProductByName(String searchName) {
        for (List<Product> list : products.values()) {
            for (Product p : list) {
                if (p.getProductName().equals(searchName))
                    return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        products.clear();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new ArrayList<>();

        List<Product> list = products.get(name);
        if (list != null) {
            removed.addAll(list);
            products.remove(name);
        }
        return removed;
    }

    public List<Product> getProductsByName(String name) {
        return products.getOrDefault(name, new ArrayList<>());
    }
}