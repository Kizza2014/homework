package exercise1;

import java.util.HashSet;
import java.util.Set;

public class LinkedListSymbolTable<K extends Comparable<K>, V>
        extends AbstractSymbolTable<K, V> {
    class Node {
        K key;
        V value;
        Node next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    Node head;
    Node tail;
    int n;

    public LinkedListSymbolTable() {
        this.head = null;
        this.tail = null;
        this.n = 0;
    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public void put(K key, V value) {
        if (value == null) {
            if (isEmpty()) {
                return;
            }

            if (head.key.compareTo(key) == 0) {
                head = head.next;
                if (head == null) {
                    tail = null;
                }
                n--;
                return;
            }

            Node cur = head;
            while (cur.next != null) {
                if (cur.next.key.compareTo(key) == 0) {
                    if (cur.next == tail) {
                        tail = cur;
                    }
                    cur.next = cur.next.next;
                    n--;
                    return;
                }
                cur = cur.next;
            }
        } else {
            Node node = findNode(key);
            if (node == null) {
                if (isEmpty()) {
                    head = new Node(key, value);
                    tail = head;
                    n++;
                    return;
                }
                tail.next = new Node(key, value);
                tail = tail.next;
                n++;
            } else {
                node.value = value;
            }
        }
    }

    private Node findNode(K key) {
        Node cur = head;
        while (cur != null) {
            if (key.compareTo(cur.key) == 0) {
                return cur;
            }
            cur = cur.next;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = findNode(key);
        return (node != null) ? node.value : null;
    }

    @Override
    public boolean contains(K key) {
        return findNode(key) != null;
    }

    @Override
    public Iterable<K> keys() {
        Set<K> res = new HashSet<>();
        Node cur = head;
        while (cur != null) {
            res.add(cur.key);
            cur = cur.next;
        }
        return res;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('{');
        Node cur = head;
        if (cur != null) {
            res.append(cur.key + " : " + cur.value);
            cur = cur.next;
        }
        while (cur != null) {
            res.append(", " + cur.key + " : " + cur.value);
            cur = cur.next;
        }
        res.append('}');
        return res.toString();
    }
}
