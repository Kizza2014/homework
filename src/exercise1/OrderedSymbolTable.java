package exercise1;

public interface OrderedSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K, V> {
    K min();
    K max();
    K floor(K key);
    K ceiling(K key);
    int rank(K key);
    K select(int k);
    void deleteMin();
    void deleteMax();
    int size(K u, K v);
    Iterable<K> keys(K u, K v);
}
