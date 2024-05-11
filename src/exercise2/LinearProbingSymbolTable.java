package exercise2;

import java.util.ArrayList;
import java.util.Arrays;

public class LinearProbingSymbolTable<K, V>
                    extends AbstractHashSymbolTable<K, V> {
    private K[] keys;
    private V[] values;
    private int n;
    private static final int DEFAULT_CAPACITY = 400;

    public LinearProbingSymbolTable(int initialCapacity) {
        this.keys = (K[]) new Object[initialCapacity];
        this.values = (V[]) new Object[initialCapacity];
        this.n = 0;
    }

    public LinearProbingSymbolTable() {
        this(DEFAULT_CAPACITY);
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
    public V get(K key) {
        int index = indexOf(key);
        if (index >= 0) {
            return values[index];
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return indexOf(key) >= 0;
    }

    @Override
    public void put(K key, V value) {
        int index = indexOf(key);
        if (index >= 0) {
            values[index] = value;
        } else {
            if (n < keys.length) {
                int hashValue = hash(key);
                while (keys[hashValue] != null) {
                    hashValue = rehash(hashValue);
                }
                keys[hashValue] = key;
                values[hashValue] = value;
                n++;
            } else {
                throw new IllegalStateException("Out of capacity.");
            }
        }
    }

    private int indexOf(K key) {
        int index = hash(key);
        int temp = index;
        while (!key.equals(keys[index])) {
            index = rehash(index);
            if (index == temp) {
                return -1;
            }
        }
        return index;
    }

    @Override
    public void delete(K key) {
        int index = indexOf(key);
        if (index >= 0) {
            keys[index] = null;
            values[index] = null;
            n--;
        }
    }

    @Override
    public Iterable<K> keys() {
        ArrayList<K> res = new ArrayList<>();
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] != null) {
                res.add(keys[i]);
            }
        }
        return res;
    }

    private int hash(K key) {
        return key.hashCode() % keys.length;
    }

    private int rehash(int hashCode) {
        return (hashCode + 1) % keys.length;
    }

    public void allocateMore() {
        this.keys = Arrays.copyOf(this.keys, this.keys.length * 2);
        this.values = Arrays.copyOf(this.values, this.values.length * 2);
    }
}
