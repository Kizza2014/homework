package exercise2;

import java.util.Arrays;
import java.util.Iterator;

public class SeparateChainSymbolTable<K, V> extends AbstractHashSymbolTable<K, V> {
    private MyLinkedList<K>[] keys;
    private MyLinkedList<V>[] values;
    private int n;
    private static final int DEFAULT_CAPACITY = 16;

    public SeparateChainSymbolTable(int initialCapacity) {
        this.keys = (MyLinkedList<K>[]) new MyLinkedList[initialCapacity];
        this.values = (MyLinkedList<V>[]) new MyLinkedList[initialCapacity];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = new MyLinkedList<>();
            values[i] = new MyLinkedList<>();
        }
        this.n = 0;
    }

    public SeparateChainSymbolTable() {
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
    public void put(K key, V value) {
        int index = hash(key);
        int indexInChain = keys[index].indexOf(key);
        if (indexInChain >= 0) {
            values[index].set(indexInChain, value);
        } else {
            keys[index].insertLast(key);
            values[index].insertLast(value);
            n++;
        }
    }

    public void allocateMore() {
        this.keys = Arrays.copyOf(this.keys, this.keys.length * 2);
        this.values = Arrays.copyOf(this.values, this.values.length * 2);
    }

    @Override
    public void delete(K key) {
        int index = hash(key);
        int indexInChain = keys[index].indexOf(key);
        if (indexInChain >= 0) {
            keys[index].delete(indexInChain);
            values[index].delete(indexInChain);
            n--;
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        int indexInChain = keys[index].indexOf(key);
        if (indexInChain >= 0) {
            return values[index].get(indexInChain);
        } else {
            return null;
        }
    }

    @Override
    public boolean contains(K key) {
        int index = hash(key);
        return keys[index].indexOf(key) >= 0;
    }

    @Override
    public Iterable<K> keys() {
        return new KeySet();
    }

    private int hash(K key) {
        return key.hashCode() % this.keys.length;
    }

    private class KeySet implements Iterable<K> {
        @Override
        public Iterator<K> iterator() {
            return new KeySetIterator();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("[");
            Iterator<K> it = KeySet.this.iterator();
            if (it.hasNext()) {
                res.append(it.next());
            }
            while (it.hasNext()) {
                res.append(", " + it.next());
            }
            res.append("]");
            return res.toString();
        }
    }

    private class KeySetIterator implements Iterator<K> {
        int current;
        int length;
        Iterator<K> it;

        KeySetIterator() {
            this.length = SeparateChainSymbolTable.this.keys.length;
            current = 0;
            if (SeparateChainSymbolTable.this.keys[current].size() == 0) {
                current = findNext(current);
            }
            this.it = SeparateChainSymbolTable.this.keys[current].iterator();
        }

        @Override
        public boolean hasNext() {
            return current < SeparateChainSymbolTable.this.keys.length;
        }

        @Override
        public K next() {
            K key = null;
            if (hasNext()) {
                key = it.next();
                if (!it.hasNext()) {
                    current = findNext(current);
                    if (current < length) {
                        it = SeparateChainSymbolTable.this.keys[current].iterator();
                    }
                }
            }
            return key;
        }

        private int findNext(int pos) {
            for (int i = pos + 1; i < length; i++) {
                if (SeparateChainSymbolTable.this.keys[i].size() > 0) {
                    return i;
                }
            }
            return length;
        }
    }
}
