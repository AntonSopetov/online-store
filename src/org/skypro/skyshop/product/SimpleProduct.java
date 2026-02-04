package org.skypro.skyshop.product;

public class SimpleProduct extends Product {
    private final int priceProduct;

    public SimpleProduct(String name, int price) {
        super(name);
        this.priceProduct = price;
    }

    @Override
    public int getPrice() {
        return this.priceProduct;
    }

    @Override
    public boolean isSpecial() {
        return false;
    }

    @Override
    public String toString() {
        String name = getProductName();
        int price = getPrice();

        return name + ": " + price;
    }
}
