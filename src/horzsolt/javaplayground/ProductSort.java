package horzsolt.javaplayground;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ProductSort {

    /*In a warehouse, a manager would like to sort the items in the stock. Given an array of n item values, sort
    the array in ascending order, first by the number of items with a certain value, then by the values
    themselves.

     */
    @Test
    public void testSorter() {
        ProductSorter p = new ProductSorter();
        assertEquals(List.of(3, 6, 4, 4, 5, 5), p.itemsSort(List.of(4, 5, 6, 5, 4, 3)));
        assertEquals(List.of(1, 3, 4, 2, 2), p.itemsSort(List.of(3, 1, 2, 2, 4)));
        assertEquals(p.itemsSort(List.of(8, 5, 5, 5, 5, 1, 1, 1, 4, 4)), List.of(8, 4, 4, 1, 1, 1, 5, 5, 5, 5));
    }
}

class CustomComparator implements Comparator<CustomPair> {
    @Override
    public int compare(CustomPair o1, CustomPair o2) {
        return (o1.getValue().equals(o2.getValue())) ? o1.getKey().compareTo(o2.getKey()) : o1.getValue().compareTo(o2.getValue());
    }
}

class ProductSorter {
    public List<Integer> itemsSort(List<Integer> items) {

        final Map<Integer, Integer> frequency = new HashMap<>();
        final List<CustomPair> sortable = new ArrayList<>();

        items.forEach(item -> {
            int count = frequency.containsKey(item) ? frequency.get(item) : 0;
            frequency.put(item, count + 1);
        });

        items.forEach(item -> {
            sortable.add(new CustomPair(item, frequency.get(item)));
        });

        Collections.sort(sortable, new CustomComparator());
        return sortable.stream().map(s -> s.getKey()).collect(Collectors.toList());
    }
}

class CustomPair {
    private Integer key;
    private Integer value;

    CustomPair(Integer _key, Integer _value) {
        key = _key;
        value = _value;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" + getKey() + "," + getValue() + "]";
    }
}
