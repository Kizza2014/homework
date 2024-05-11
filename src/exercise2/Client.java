package exercise2;

public class Client {
    public static void main(String[] args) {
        testLinearProbingHashing();
    }

    public static void testLinearProbingHashing() {
        SymbolTable<Integer, String> st = new LinearProbingSymbolTable<>();
        System.out.println("Put:");
        Integer a = 802;
        st.put(1,"a");
        st.put(27, "b");
        st.put(427,"c");
        st.put(401, "d");
        st.put(206, "e");
        st.put(802, "f");
        System.out.println(st);

        System.out.println("\nGet:");
        System.out.println(st.get(802));
        System.out.println(st.get(1));
        System.out.println(st.get(27));

        System.out.println("\nContains:");
        System.out.println(st.contains(1));
        System.out.println(st.contains(802));
        System.out.println(st.contains(10));
        System.out.println(st.contains(27));
        System.out.println(st.contains(206));

        System.out.println("\nKeys:");
        System.out.println(st.keys());

        System.out.println("Delete:");
        st.delete(1);
        st.delete(27);
        st.delete(206);
        System.out.println(st);
    }

    public static void testSeparateChainHashing() {
        SymbolTable<Integer, String> st = new SeparateChainSymbolTable<>();
        System.out.println("Put:");
        st.put(1, "a");
        st.put(2, "b");
        st.put(17, "c");
        st.put(34, "d");
        st.put(14, null);
        st.put(802, "f");
        System.out.println(st);
        System.out.println(st.keys());

        System.out.println("\nContains:");
        System.out.println(st.contains(2));
        System.out.println(st.contains(34));
        System.out.println(st.contains(50));
        System.out.println(st.contains(17));
        System.out.println(st.contains(14));
        System.out.println(st.contains(802));

        System.out.println("\nGet:");
        System.out.println(st.get(17));
        System.out.println(st.get(0));

        System.out.println("\nDelete:");
        st.delete(0);
        System.out.println(st);
        st.delete(1);
        st.delete(14);
        System.out.println(st);
        System.out.println(st.keys());
    }

    public static void testLinkedList() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.insert(0, 1);
        list.insert(1, 2);
        list.insert(0, 3);
        System.out.println(list);

        list.delete(0);
        list.delete(1);
        System.out.println(list);
    }
}
