package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private final int basePrice;
    private final int discountInWholePercentages;

    public DiscountedProduct(String name, int basePrice, int discountInWholePercentages) {
        super(name);
        this.basePrice = basePrice;
        this.discountInWholePercentages = discountInWholePercentages;
    }

    @Override
    public int getPrice() {
        return basePrice - basePrice * discountInWholePercentages / 100;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        String name = getProductName();
        int basePrice = getPrice();

        return name + " со скидкой: " + basePrice + "(" + discountInWholePercentages + "%)";
    }
}

