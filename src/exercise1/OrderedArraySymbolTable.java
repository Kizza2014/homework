package exercise1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OrderedArraySymbolTable<K extends Comparable<K>, V>
        implements OrderedSymbolTable<K, V> {
    K[] keys;
    V[] values;
    int n;
    static final int DEFAULT_INITIAL_CAPACITY = 16;

    public OrderedArraySymbolTable() {
        this.keys = (K[]) new Comparable[DEFAULT_INITIAL_CAPACITY];
        this.values = (V[]) new Object[DEFAULT_INITIAL_CAPACITY];
        this.n = 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void put(K key, V value) {
        int idx = findIndex(key);
        if (idx >= 0) {
            values[idx] = value;
        } else {
            if (n >= keys.length) {
                allocateMore();
            }
            int pos = rank(key);
            for (int i = n; i > pos; i--) {
                keys[i] = keys[i - 1];
                values[i] = values[i - 1];
            }
            keys[pos] = key;
            values[pos] = value;
            n++;
        }
    }

    @Override
    public boolean contains(K key) {
        return findIndex(key) >= 0;
    }

    private void allocateMore() {
        this.keys = Arrays.copyOf(keys, keys.length * 2);
        this.values = Arrays.copyOf(values, values.length * 2);
    }

    @Override
    public void delete(K key) {
        if (isEmpty()) {
            return;
        }

        int index = findIndex(key);

        if (index >= 0) {
            for (int i = index; i < n - 1; i++) {
                keys[i] = keys[i + 1];
                values[i] = values[i + 1];
            }

            keys[n - 1] = null;
            values[n - 1] = null;
            n--;
        }
    }



    private int findIndex(K key) {
        return binarySearch(key, keys, 0, n - 1);
    }

    private int binarySearch(K key, K[] keys, int left, int right) {
        if (left == right && keys[left].equals(key)) {
            return left;
        }

        if (left < right) {
            int mid = (left + right) / 2;
            if (key.equals(keys[mid])) {
                return mid;
            } else {
                if (key.compareTo(keys[mid]) < 0) {
                    return binarySearch(key, keys, left, mid - 1);
                } else {
                    return binarySearch(key, keys, mid + 1, right);
                }
            }
        }
        return -1;
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

    @Override
    public K min() {
        if (isEmpty()) {
            return null;
        }
        return keys[0];
    }

    @Override
    public K max() {
        if (isEmpty()) {
            return null;
        }
        return keys[n - 1];
    }

    @Override
    public K floor(K key) {
        int rank = rank(key);
        return (rank > 0) ? keys[rank - 1] : null;
    }

    @Override
    public K ceiling(K key) {
        int rank = rank(key);
        if (rank == 0 && key.compareTo(keys[0]) < 0) {
            return keys[0];
        }
        return (rank < n - 1) ? keys[rank + 1] : null;
    }

    @Override
    public int rank(K key) {
        if (isEmpty()) {
            return 0;
        }

        int i = n;
        while (i > 0 && key.compareTo(keys[i - 1]) <= 0) {
            i--;
        }
        if (i == 0) {
             return (key.compareTo(keys[0]) > 0) ? 1 : 0;
        } else {
            return i;
        }
    }

    @Override
    public K select(int k) {
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("rank is out of bound.");
        }
        return keys[k];
    }

    @Override
    public void deleteMin() {
        delete(keys[0]);
    }

    @Override
    public void deleteMax() {
        delete(keys[n - 1]);
    }

    @Override
    public Iterable<K> keys() {
        return Arrays.asList(Arrays.copyOf(keys, n));
    }

    @Override
    public Iterable<K> keys(K u, K v) {

        int left  = rank(u);
        int right = rank(v);
        return ((List<K>) keys()).subList(left, right);
    }

    @Override
    public int size(K u, K v) {
        int left = rank(u);
        if (left == 0 && u.compareTo(keys[0]) < 0) {
            left--;
        }
        int right = rank(v);
        return right - left - 1;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('{');

        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res.append(keys[i] + " : " + values[i]);
            } else {
                res.append(", " + keys[i] + " : " + values[i]);
            }
        }
        res.append('}');
        return res.toString();
    }
}
