package org.skypro.skyshop.product;

import org.skypro.skyshop.Searchable;

public abstract class Product implements Searchable {
    private final String productName;
    public abstract int getPrice();

    public Product(String productName) {
        if (productName == null || productName.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть null или пустой строкой");
        }
        this.productName = productName;
    }

    public String getProductName() {
        return this.productName;
    }

    public abstract boolean isSpecial();

    public abstract String toString();

    @Override
    public String getSearchTerm() {
        return getProductName();
    }

    @Override
    public String getType() {
        return "PRODUCT";
    }

    @Override
    public String getName() {
        return getProductName();
    }
}
