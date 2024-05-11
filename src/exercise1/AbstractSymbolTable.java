package exercise1;

public abstract class AbstractSymbolTable<K extends Comparable<K>, V>
        implements SymbolTable<K, V> {
    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public void delete(K key) {
        put(key, null);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
}
