package exercise2;

public abstract class AbstractHashSymbolTable<K, V> implements SymbolTable<K, V> {
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("{");
        Iterable<K> keySet = this.keys();
        boolean ok = false;
        for (K key : keySet) {
            if (!ok) {
                res.append(key).append(" : ").append(this.get(key));
                ok = true;
            } else {
                res.append(", ").append(key).append(" : ").append(this.get(key));
            }
        }

        res.append("}");
        return res.toString();
    }
}
