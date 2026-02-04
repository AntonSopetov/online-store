package org.skypro.skyshop;

import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import java.util.Objects;
import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.Searchable;

public class App {
    public static void main(String[] args) {
        Product phone = new SimpleProduct("Телефон", 50_000);
        Product computer = new DiscountedProduct("Компьютер", 150_000, 20); // 120000 руб.
        Product car = new FixPriceProduct("Машина"); // 1000 руб.
        Product extra = new SimpleProduct("Лишний товар", 999_999); // Добавлен для проверки лимита корзины

        ProductBasket basket = new ProductBasket();

        System.out.println("--- Добавляем 3 продукта ---");
        basket.addProduct(phone);
        basket.addProduct(computer);
        basket.addProduct(car);

        System.out.println("--- Пробуем добавить 6-й продукт (корзина заполнена) ---");
        basket.addProduct(extra);

        System.out.println("--- Печать корзины ---");
        basket.printBasket();

        System.out.println("--- Общая стоимость корзины ---");
        System.out.println("Итоговая сумма: " + basket.getTotalCost());

        System.out.println("--- Поиск существующего товара (Телефон) ---");
        System.out.println("Найден? " + basket.checkProductByName("Телефон")); // Ожидаем true

        System.out.println("--- Поиск несуществующего товара (Утюг) ---");
        System.out.println("Найден? " + basket.checkProductByName("Утюг")); // Ожидаем false

        System.out.println("--- Очистка корзины ---");
        basket.clearBasket();
        System.out.println("Корзина очищена.");

        System.out.println("--- Проверка пустой корзины ---");
        basket.printBasket();
        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());

        System.out.println("--- Поиск в пустой корзине ---");
        System.out.println("Найден? " + basket.checkProductByName("Телефон")); // Ожидаем false

        System.out.println("--- ТЕСТ ПОИСКА ---");
        SearchEngine engine = new SearchEngine(10);

        engine.add(phone);
        engine.add(computer);
        engine.add(car);

        Article article1 = new Article("О телефонах", "Телефоны бывают разные...");
        Article article2 = new Article("Компьютеры", "Мощные машины для работы");
        engine.add(article1);
        engine.add(article2);

        System.out.println("Поиск 'телефон':");
        Searchable[] phoneResults = engine.search("телефон");
        System.out.println(java.util.Arrays.toString(phoneResults));

        System.out.println("Поиск 'компьютер':");
        Searchable[] compResults = engine.search("компьютер");
        System.out.println(java.util.Arrays.toString(compResults));
    }
}