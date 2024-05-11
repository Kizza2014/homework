package exercise1;

public class Client {
    public static void main(String[] args) {
        testLinkedListSymbolTable();
    }

    public static void testOrderedArraySymbolTable() {
        OrderedSymbolTable<Integer, String> st = new OrderedArraySymbolTable<>();

        System.out.println("Put:");
        st.put(1,"abc");
        st.put(2,"def");
        System.out.println(st);
        st.put(1,"a");
        System.out.println(st);
        st.put(2,"b");
        System.out.println(st);
        st.put(3, null);
        System.out.println(st);
        st.put(10,"c");
        st.put(20,"d");
        st.put(0,"e");

        System.out.println(st);

        System.out.println("Min, max:");
        System.out.println(st.get(st.min()));
        System.out.println(st.get(st.max()));

        System.out.println("Delete:");
        st.delete(0);
        System.out.println(st);
        System.out.println(st.get(st.min()));
        st.deleteMin();
        st.deleteMax();
        System.out.println(st);

        System.out.println("Floor, ceiling:");
        System.out.println(st.floor(3));
        System.out.println(st.floor(4));
        System.out.println(st.floor(0));
        System.out.println(st.floor(20));
        //ceiling
        System.out.println(st.ceiling(3));
        System.out.println(st.ceiling(0));
        System.out.println(st.ceiling(20));

        System.out.println("Select:");
        st.put(4,"f");
        System.out.println(st);
        System.out.println(st.select(3));
        System.out.println(st.select(0));
        System.out.println(st.select(2));

        System.out.println(st.size(0,20));

        System.out.println("Keys:");
        System.out.println(st.keys(0, 10));
        st.deleteMin();
        System.out.println(st.keys(0, 20));
    }

    public static void testLinkedListSymbolTable() {
        SymbolTable<Integer, String> st = new LinkedListSymbolTable<>();
        System.out.println("Put:");
        st.put(1,"a");
        st.put(2,"b");
        System.out.println(st);
        st.put(0,"c");
        st.put(20,"d");
        st.put(-1, "f");
        System.out.println(st);
        st.put(50,"g");
        st.put(-2,"h");
        System.out.println(st);

        System.out.println("Get:");
        System.out.println(st.get(2));
        System.out.println(st.get(-2));
        System.out.println(st.get(14));
        System.out.println(st.get(-3));

        System.out.println("Contains:");
        System.out.println(st.contains(20));
        System.out.println(st.contains(30));

        System.out.println("Delete:");
        st.delete(-1);
        st.delete(-10);
        System.out.println(st);
        st.delete(50);
        st.delete(20);
        st.delete(-2);
        System.out.println(st);

        System.out.println(st.keys());
    }
}
