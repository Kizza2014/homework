package exercise1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ArraySymbolTable<K extends Comparable<K>, V>
        extends AbstractSymbolTable<K, V>{
    static final int DEFAULT_INITIAL_CAPACITY = 16;
    K[] keys;
    V[] values;

    int size;
    int n;

    public ArraySymbolTable() {
        this.keys = (K[]) new Comparable[DEFAULT_INITIAL_CAPACITY];
        this.values = (V[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.n = 0;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int idx = findIndex(key);

        if (idx == -1) {
            if (n >= keys.length) {
                allocateMore();
            }

            keys[n] = key;
            values[n] = value;
            n++;
            size++;
        } else {
            if (value == null) {
                size--;
            }
            values[idx] = value;
        }
    }

    private void allocateMore() {
        this.keys = Arrays.copyOf(keys, keys.length * 2);
        this.values = Arrays.copyOf(values, values.length * 2);
    }

    @Override
    public V get(K key) {
        if (isEmpty()) {
            return null;
        }
        int idx = findIndex(key);
        if (idx == -1) {
            return null;
        }
        return values[idx];
    }

    private int findIndex(K key) {
        for (int i = 0; i < n; i++) {
            if (key.equals(keys[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterable<K> keys() {
        ArrayList<K> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (values[i] != null) {
                res.add(keys[i]);
            }
        }
        return res;
    }
}
