package exercise2;

import java.util.Iterator;

public class MyLinkedList<E> implements Iterable<E> {
    class Node {
        E payload;
        Node next;

        public Node(E payload) {
            this.payload = payload;
            this.next = null;
        }
    }
    Node head;
    int n;

    public MyLinkedList() {
        this.head = null;
        this.n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void set(int index, E payload) {
        checkBoundary(index, n - 1);
        Node node = getNodeByIndex(index);
        node.payload = payload;
    }

    public int indexOf(E payload) {
        Node cur = head;
        int idx = 0;
        while (cur != null) {
            if (cur.payload.equals(payload)) {
                return idx;
            }
            cur = cur.next;
            idx++;
        }
        return -1;
    }

    public E get(int index) {
        return getNodeByIndex(index).payload;
    }

    public void insert(int index, E payload) {
        checkBoundary(index, n);
        if (index == 0) {
            insertFirst(payload);
        } else {
            Node prevNode = getNodeByIndex(index - 1);
            Node newNode = new Node(payload);
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            n++;
        }
    }

    public void insertFirst(E payload) {
        Node newNode = new Node(payload);
        newNode.next = head;
        head = newNode;
        n++;
    }

    public void insertLast(E payload) {
        if (isEmpty()) {
            head = new Node(payload);
        } else {
            Node lastNode = getNodeByIndex(n - 1);
            lastNode.next = new Node(payload);
        }
        n++;
    }

    public void delete(int index) {
        checkBoundary(index, n - 1);
        if (index == 0) {
            head = head.next;
        } else {
            Node prevNode = getNodeByIndex(index - 1);
            prevNode.next = prevNode.next.next;
        }
        n--;
    }

    private Node getNodeByIndex(int index) {
        checkBoundary(index, n - 1);
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private void checkBoundary(int index, int limit) {
        if (index < 0 || index > limit) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new MyLinkedListIterator();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Iterator<E> it = this.iterator();
        res.append("[");
        if (it.hasNext()) {
            res.append(it.next());
        }
        while (it.hasNext()) {
            res.append(", " + it.next());
        }
        res.append("]");
        return res.toString();
    }

    class MyLinkedListIterator implements Iterator<E> {
        Node cur;
        private MyLinkedListIterator() {
            this.cur = MyLinkedList.this.head;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public E next() {
            E payload = null;
            if (hasNext()) {
                payload = cur.payload;
                cur = cur.next;
            }
            return payload;
        }
    }
}
