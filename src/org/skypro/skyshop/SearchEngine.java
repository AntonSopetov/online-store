package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.article.Article;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.*;
import java.util.Comparator;
import java.util.TreeSet;

public class SearchEngine {
    private Set<Searchable> searchableProducts = new HashSet<>();

    public SearchEngine(int capacity) {
    }

    public void add(Searchable item) {
        searchableProducts.add(item);
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int foundCount = 0;

        for (Searchable item : searchableProducts) {
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

        for (Searchable item : searchableProducts) {
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

    public List<Product> findByNamePart(String part) {
        List<Product> results = new ArrayList<>();
        for (Searchable item : searchableProducts) {
            if (item instanceof Product &&
                    item.getName().toLowerCase().contains(part.toLowerCase())) {
                results.add((Product) item);
            }
        }
        return results;
    }

    public List<Product> findByPriceRange(double min, double max) {
        List<Product> results = new ArrayList<>();
        for (Searchable item : searchableProducts) {
            if (item instanceof Product p) {
                if (p.getPrice() >= min && p.getPrice() <= max) {
                    results.add(p);
                }
            }
        }
        return results;
    }

    private final Comparator<Searchable> longToShortComparator = new Comparator<Searchable>() {
        @Override
        public int compare(Searchable item1, Searchable item2) {
            int lengthCompare = Integer.compare(
                    item2.getName().length(),
                    item1.getName().length()
            );

            if (lengthCompare != 0) {
                return lengthCompare;
            }

            return item1.getName().compareTo(item2.getName());
        }
    };

    public TreeSet<Searchable> searchByNameSorted(String query) {
        TreeSet<Searchable> result = new TreeSet<>(longToShortComparator);

        for (Searchable item : searchableProducts) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                result.add(item);
            }
        }
        return result;
    }
}
