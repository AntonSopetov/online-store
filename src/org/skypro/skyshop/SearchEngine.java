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

    public Searchable findBestResult(String search) throws BestResultNotFound {
        Searchable bestResult = null;
        int maxCount = -1;

        for (Searchable item : searchables) {
            if (item != null) {
                int count = 0;
                int index = 0;

                while (index != -1) {
                    index = item.getSearchTerm().indexOf(search, index);
                    if (index != -1) {
                        count++;
                        index += search.length();
                    }
                }

                if (count > maxCount) {
                    maxCount = count;
                    bestResult = item;
                }
            }
        }

        if (bestResult == null) {
            throw new BestResultNotFound(search);
        }

        return bestResult;
    }
}
