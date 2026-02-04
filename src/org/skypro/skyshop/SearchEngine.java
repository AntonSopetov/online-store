package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.article.Article;

public class SearchEngine {
    private final Searchable[] searchables;

    public SearchEngine(int capacity) {
        this.searchables = new Searchable[capacity];
    }

    public void add(Searchable item) {
        for (int i = 0; i < searchables.length; i++) {
            if (searchables[i] == null) {
                searchables[i] = item;
                return;
            }
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int foundCount = 0;

        for (Searchable item : searchables) {
            if (item != null &&
                    item.getSearchTerm().contains(query) &&
                    foundCount < 5) {
                results[foundCount] = item;
                foundCount++;
            }
        }
        return results;
    }
}
