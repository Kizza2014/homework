package exercise2;

public interface SymbolTable<K, V> {
    void put(K key, V value);
    V get(K key);
    void delete(K key);
    boolean isEmpty();
    int size();
    Iterable<K> keys();
    boolean contains(K key);
}
