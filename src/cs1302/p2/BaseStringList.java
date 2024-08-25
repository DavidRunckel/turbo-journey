package cs1302.p2;

import cs1302.adt.StringList;

/**
 * The abstract class and parent class of the 2 StringLists Array and Linked.
 * The methods contained in here sometimes use methods from the child classes.
 *
 * <p>
 * {@inheritDoc}
 */
public abstract class BaseStringList implements StringList {

    // instance variable
    protected int size;

    /**
     * Constructs an empty string list.
     */
    public BaseStringList() {
        this.size = 0;
    } // BaseStringList

    /**
     * Returns the number of items in this string list.
     *
     * @return the number of items in this string list.
     */
    @Override
    public int size() {
        return this.size;
    } // size

    /**
     * Returns true if this string list has no items. More formally, a string list
     * is empty if, and only if, its size is zero.
     *
     * @return true if this string list is empty; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    } // isEmpty

    /**
     * Returns a string representation of this string list that begins with start
     * and ends with end, with every string in the string list separated by sep.
     *
     * @param start - specified starting string
     * @param sep - the specified separator string
     * @param end - the specified ending string
     * @return a string representation of this string list that begins with start
     * and ends with end, with every string in the string list separated by sep
     */
    @Override
    public String makeString(String start, String sep, String end) {

        // The looped portion of this code will called the .get() method from
        // either the Array or Linked Lists and because they return Strings, it can
        // be added to the make String.
        String make = "";
        make += start;
        for (int i = 0; i < this.size; i++) {
            if (i < this.size - 1) {
                make += this.get(i) + sep;
            } else {
                make += this.get(i);
            } // if-else
        } // for
        make += end;
        return make;
    } // makeString

    /**
     * Returns makeString("[", ", ", "]").
     *
     * @override toString in class Object
     * @return makeString("[", ", ", "]")
     */
    @Override
    public String toString() {
        return this.makeString("[", ", ", "]");
    } // toString

    /**
     * Returns true if start >= 0 and there exists an item at or after the start index
     * that equals the target string. If no such item exists, then false is returned.
     * Unlike the add methods that throws exceptions when called with bad arguments,
     * this contains method simply returns false instead.
     *
     * @param start - the index from which to start the search
     * @param target - the item to search for
     * @return true if there exists an item at or after start such that item.equals(target),
     * or false if there is no such occurrence
     */
    @Override
    public boolean contains(int start, String target) {

        // the for loop compares the String of target to each String in the list
        // after the starting position. The .get() method works different for
        // both array and linked lists but it will still read it through as if
        // the index's are correct.
        if (start >= 0) {
            for (int i = start; i < this.size; i++) {
                if (target.equals(this.get(i))) {
                    return true;
                } // if
            } // for
        } // if
        return false;
    } // contains

    /**
     * Inserts multiple items into this string list at the specified index position.
     * The relative order of inserted items is preserved. If items were already at that
     * or subsequent positions, then those items are shifted to the right
     * (i.e., itemList.size() is added to their indices).
     *
     * @param index - index at which the specified items are to be inserted
     * @param itemList - string list of items to be inserted
     * @return true if this list changed as a result of the call
     * @throws NullPointerException - if itemList is null
     * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index > size())
     */
    @Override
    public boolean add(int index, StringList itemList) {
        if (itemList == null) {
            throw new NullPointerException("itemList is null");
        } // if
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if

        // Adds each item in the itemList in order from where the initial
        // index was in the main list and increases the main list's size.
        // originalSize protects against self-reference as does list.
        int originalSize = itemList.size();
        StringList list = itemList.slice(0, itemList.size());
        if (this == itemList) {
            for (int i = 0; i < originalSize; i++) {
                this.add(index + i, list.get(i));
            } // for
        } else {
            for (int i = 0; i < originalSize; i++) {
                this.add(index + i, itemList.get(i));
            } // for
        }
        return !itemList.isEmpty();
    } // add

} // BaseStringList
