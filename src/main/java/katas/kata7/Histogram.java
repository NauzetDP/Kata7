package katas.kata7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Histogram<T> {
    
    private final String dimension;
    private List<Filter> filters;
    private final Map<T,Integer> valuesHistogram = new HashMap<>();

    public Histogram(String dimension, List<Filter> filters) {
        this.dimension = dimension;
        this.filters = filters;
    }
    
    public Histogram(String dimension) {
        this.dimension = dimension;
    }

    public String getDimension() {
        return dimension;
    }

    public List<Filter> getFilters() {
        return filters;
    }
    
    public Map<T,Integer> getMap () {
        return valuesHistogram;
    }

    public Integer get(T key) {
        return valuesHistogram.get(key);
    }
    
    public Set<T> keySet() {
        return valuesHistogram.keySet();
    }
    
    public void increment(T key) {
        valuesHistogram.put(key, valuesHistogram.containsKey(key) ? valuesHistogram.get(key) + 1 : 1);
    }
}