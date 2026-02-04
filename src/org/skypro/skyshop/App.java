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
        try {
            Product nullName = new SimpleProduct("", 100);
            System.out.println("Пустое имя");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест 2: цена 0
        try {
            Product nullPrice = new SimpleProduct("Какой-то товар", 0);
            System.out.println("Цена 0");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Тест 3: скидка 150%
        try {
            Product wrongDiscount = new DiscountedProduct("Товар", 100, 150);
            System.out.println("Скидка 150%");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
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

        System.out.println("\n--- ТЕСТ BEST RESULT ---");
        try {
            Searchable bestPhone = engine.findBestResult("телефон");
            System.out.println("Лучший результат: " + bestPhone.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        try {
            Searchable bestUnknown = engine.findBestResult("неизвестный_товар");
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
    }
}