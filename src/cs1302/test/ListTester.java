package cs1302.test;

import cs1302.adt.StringList;
import cs1302.adt.Node;
import cs1302.oracle.OracleStringList;
import cs1302.p2.BaseStringList;
import cs1302.p2.ArrayStringList;
import cs1302.p2.LinkedStringList;

/**
 * Driver of Project 2. Has a multitude of test cases
 * that ensures there are no runtime errors.
 *
 * <p>
 * {@inheritDoc}
 */
public class ListTester {

    /**
     * n5().
     */
    public static void n5() {
        Node head = null;
        Node newNode = new Node("a");
        newNode.setNext(head);
        head = newNode;
        System.out.println(head.hasNext()); // false
        System.out.println(newNode.hasNext()); // false
        System.out.println();

        newNode = new Node("b");
        newNode.setNext(head);
        head = newNode;
        System.out.println(head.hasNext()); // true
        System.out.println(newNode.hasNext()); // true
        System.out.println("Working");
    } // n5

    public static void main(String[] args) {
        //sl1(); success
        //sl2(); success
        //sl3(); success
        //sl4(); success
        //n1();
        //n2();
        //n3();
        //testIsEmpty(); success
        //testSize(); success
        //testSize2(); success
        //testIsEmpty2(); success
        //addItemList();
        //makeString(); success
        //reverseTest();
        //mms();
        arrayTester();
        //linkedTester();
        addItemList();
        //n4();
        //n5();
        lt2();
        System.out.println("good");
    } // main

    /**
     * lt2().
     */
    public static void lt2() {
        StringList test = new LinkedStringList();
        test.add(0, "a");
        test.add(1, "b");
        test.add(2, "c");
        test.add(0, "d");
        System.out.println(test); // [d, a, b, c]
        System.out.println(test.reverse()); // [c, b, a, d]
        System.out.println(test.slice(0, 4)); // [d, a, b, c]
        System.out.println(test.slice(1, 3)); // [a, b]
    } //lt2

    /**
     * n4().
     */
    public static void n4() {
        Node head = new Node("a");   // head → Node(a, null)
        System.out.println(head.hasNext()); // false
        head = new Node("b", head); // head → Node(b) → Node(a, null)
        System.out.println(head.hasNext()); // true
        System.out.printf("head → %s\n", head.asString());
    } // n4

    /**
     * linkedTester().
     */
    public static void linkedTester() {
        StringList sl = new LinkedStringList();
        System.out.println(sl); // []
        sl.add(0, "a");
        System.out.println(sl); // [a]
        sl.add(1, "b");
        System.out.println(sl); // [a, b]
        System.out.println(sl.size()); // 2
        sl.clear();
        System.out.println(sl); // []
        System.out.println(sl.isEmpty()); // true
        System.out.println();
        sl.add(0, "a");
        System.out.println(sl); // [a]
        sl.add(1, "b");
        System.out.println(sl); // [a, b]
        sl.add(2, "c");
        System.out.println(sl); // [a, b, c]
        sl.add(1, "d");
        System.out.println(sl); // [a, d, b, c]
        System.out.println(sl.get(0)); // a
        System.out.println(sl.get(3)); // c
        System.out.println();
        System.out.println(sl.contains(0, "d")); // true
        System.out.println(sl.contains(2, "d")); // false
        sl.remove(3);
        System.out.println(sl); // [a, d, b]
        sl.remove(1);
        System.out.println(sl); // [a, b]
        System.out.println(sl.reverse()); // [b, a]
    } // linkedTester

    /**
     * mms().
     */
    public static void mms() {
        StringList sl = new ArrayStringList();
        sl.add(0, "a");
        sl.add(1, "b");
        sl.add(2, "c");
        System.out.println(sl);
        sl.add(3, "d");
        System.out.println(sl);
        sl.add(4, "e");
        sl.add(5, "f");
        sl.add(6, "g");
        System.out.println(sl);
        System.out.println(sl.size());
    } // mms

    /**
     * arrayTester().
     */
    public static void arrayTester() {
        StringList sl = new ArrayStringList();
        sl.add(0, "a");
        System.out.println(sl); // [a]
        sl.add(1, "b");
        System.out.println(sl); // [a, b]
        sl.add(0, "c");
        System.out.println(sl); // [c, a, b]
        sl.remove(0);
        System.out.println(sl); // [a, b]
        sl.add(2, "d");
        System.out.println(sl); // [a, b, d]
        sl.add(0, "e");
        System.out.println(sl); // [e, a, b, d]
        System.out.println(sl.reverse()); // [d, b, a, e]
        System.out.println(sl.slice(1, 3));   // [e, d]
        System.out.println(sl.contains(0, "a")); // true
        System.out.println(sl.contains(2, "a")); // false
        sl.clear();
        System.out.println(sl); // []
    } // arrayTester


    /**
     * addItemList().
     */
    public static void addItemList() {
        // first list
        StringList list1 = new ArrayStringList();
        list1.add(0, "a");
        list1.add(1, "b");
        System.out.println(list1);
        // second list
        StringList list2 = new LinkedStringList();
        list2.add(0, "0");
        list2.add(1, "1");
        System.out.println(list2);

        // insert second list into first list
        list1.add(1, list2);
        System.out.println(list1); // [a, 0, 1, b]

        // insert modified first list into itself
        list1.add(1, list1);
        System.out.println(list1); // [a, a, 0, 1, b, 0, 1, b]
    } // addItemList

    /**
     * reverseTest().
     */
    public static void reverseTest() {
        StringList sl = new LinkedStringList();
        sl.add(0,"a");
        sl.add(1, "b");
        sl.add(2, "c");
        System.out.println(sl);
        System.out.println(sl.reverse());
    } // reverseTest

    /**
     * makeString().
     */
    public static void makeString() {

        StringList sl = new ArrayStringList();

        String result1 = sl.makeString("~", "!", "#");
        String result2 = sl.makeString(null, null, null);
        sl.add(0, "a");
        sl.add(1, "b");
        sl.add(2, "c");
        String result3 = sl.makeString("~", "!", "#");
        String result4 = sl.makeString(null, null, null);
        String result5 = sl.toString();

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
        System.out.println(result5);
    } // makeString

    /**
     * testIsEmpty2().
     */
    public static void testIsEmpty2() {
        StringList sl = new LinkedStringList();
        sl.add(0,"a");

        // Testing isEmpty on an empty list
        if (!sl.isEmpty()) {
            System.out.println("isEmpty: Test Passed");
        } else {
            System.out.println("isEmpty: Test Failed");
            System.exit(0);
        } // if
    } // testIsEmpty

    /**
     * testSize2().
     */
    public static void testSize2() {
        StringList sl = new LinkedStringList();
        sl.add(0,"a");
        sl.add(0,"b");
        sl.remove(0);
        // Testing size on an empty list
        if (sl.size() == 1) {
            System.out.println("size: Test Passed");
        } else {
            System.out.println("size: Test Failed");
            System.exit(0);
        } // if
    } // testSize2

    /**
     * StringList Example: New, Empty String List.
     */
    public static void sl1() {
        // setup
        StringList list = new LinkedStringList();

        // test by printing
        System.out.println(list.size());    // 0
        System.out.println(list.isEmpty()); // true

        // test by asserting (requires -ea option when running java)
        assert list.size() == 0 : "size() is not 0!";
        assert list.isEmpty() : "isEmpty() is false!";
    } // sl1

    /**
     * StringList Example: Non-Empty String List.
     */
    public static void sl2() {
        // setup
        StringList list = new LinkedStringList();
        list.add(0, "a");
        list.add(1, "b");
        list.add(2, "c");

        // test by printing
        System.out.println(list.size());    // 3
        System.out.println(list.isEmpty()); // false
        System.out.println(list.get(0));    // a
        System.out.println(list.get(1));    // b
        System.out.println(list.get(2));    // c

        // test by asserting (requires -ea option when running java)
        assert list.size() == 3 : "size() is not 3!";
        assert !list.isEmpty() : "isEmpty() is true!";
        assert list.get(0).equals("a") : "get(0) is not \"a\"!";
        assert list.get(1).equals("b") : "get(1) is not \"b\"!";
        assert list.get(2).equals("c") : "get(2) is not \"c\"!";
    } // sl2

    /**
     * StringList Example: No Empty Items in a String List.
     */
    public static void sl3() {
        // setup
        StringList list = new LinkedStringList();

        // test by calling
        list.add(0, ""); // Exception java.lang.IllegalArgumentException

        // test by asserting (requires -ea option when running java)
        try {
            list.add(0, "");
            assert false : "add(0, \"\") did not throw an IllegalArgumentException";
        } catch (IllegalArgumentException iae) {
            assert true;
        } // try
    } // sl3

    /**
     * StringList Example: No Gaps in a String List.
     */
    public static void sl4() {
        // setup
        StringList list = new LinkedStringList();
        list.add(0, "a");
        list.add(1, "b");

        // test by printing
        list.add(3, "c"); // Exception java.lang.IndexOutOfBoundsException

        // test by asserting (requires -ea option when running java)
        try {
            list.add(3, "c");
            assert false : "add(3, \"c\") did not throw an IndexOutOfBoundsException";
        } catch (IndexOutOfBoundsException ioobe) {
            assert true;
        } // try
    } // sl4

    /**
     * Node Example 1.
     */
    public static void n1() {
        Node head = new Node("a");   // head → Node(a, null)
        head = new Node("b", head);  // head → Node(b) → Node(a, null)
        System.out.printf("head → %s\n", head.asString());
    } // n1

    /**
     * Node Example 2.
     */
    public static void n2() {
        Node head = new Node("a");   // head → Node(a, null)
        head.setNext(new Node("b")); // head → Node(a) → Node(b, null)
        System.out.printf("head → %s\n", head.asString());
    } // n2

    /**
     * Node Example 3.
     */
    public static void n3() {
        Node node1 = new Node("a");    // node1 → Node(a, null)
        Node node2 = new Node("b");    // node2 → Node(b, null)
        Node node3 = new Node("c");    // node3 → Node(c, null)
        Node head = node1;             //  head → Node(a, null)
        head.setNext(node2);           //  head → Node(a) → Node(b, null)
        head.getNext().setNext(node3); //  head → Node(a) → Node(b) → Node(c, null)
        System.out.printf("head → %s\n", head.asString());
    } // n3

    /**
     * testIsEmpty().
     */
    public static void testIsEmpty() {
        StringList sl = new LinkedStringList();

        // Testing isEmpty on an empty list
        if (sl.isEmpty()) {
            System.out.println("isEmpty: Test Passed");
        } else {
            System.out.println("isEmpty: Test Failed");
            System.exit(0);
        } // if
    } // testIsEmpty

    /**
     * testSize().
     */
    public static void testSize() {
        StringList sl = new LinkedStringList();
        // Testing size on an empty list
        if (sl.size() == 0) {
            System.out.println("size: Test Passed");
        } else {
            System.out.println("size: Test Failed");
            System.exit(0);
        } // if
    } // testSize

} // ListTester
