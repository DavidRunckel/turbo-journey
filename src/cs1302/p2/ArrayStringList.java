package cs1302.p2;

import cs1302.adt.StringList;

/**
 * ArrayStringList creates an ASL object which extends BaseStringList.
 * When using any of the methods to modify a list, it is done through arrays.
 *
 * <p>
 * {@inheritDoc}
 */
public class ArrayStringList extends BaseStringList {

    // instance variable
    private String[] items;

    /**
     * Constructs an empty string list.
     */
    public ArrayStringList() {
        this.items = new String[3];
        this.size = 0;
    } // ArrayStringList

    /**
     * Inserts an item into this string list at the specified index position.
     * If an item was already at that position, then that item and subsequent items
     * are shifted to the right (i.e., one is added to their indices).
     *
     * @param index - index at which the specified string is to be inserted
     * @param item - item to be inserted
     * @return true if this list changed as a result of the call
     * @throws NullPointerException - if item is null
     * @throws IllegalArgumentException - if item is empty
     * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index > size())
     */
    @Override
    public boolean add(int index, String item) {
        // 3 if-statements that throw exceptions
        if (item.equals(null)) {
            throw new NullPointerException("item is null");
        } // if
        if (item.length() == 0) {
            throw new IllegalArgumentException("item is empty");
        } // if
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if

        // will increase the size of the array if it's full using the method
        // makeMoreSpace().
        if (this.items.length == this.size) {
            makeMoreSpace();
        } // if

        // for loop will move everything down by 1 in the array if the add
        // is accessing a position with something already stored there.
        // if index is the same as size, will skip the loop.
        for (int i = this.size; i > index; i--) {
            this.items[i] = this.items[i - 1];
        } // for
        this.items[index] = item;
        this.size++;
        return true;
    } // add

    /**
     * Creates a larger array and copies all the items in the full array over.
     */
    private void makeMoreSpace() {
        String[] temp = new String[this.items.length * 3 / 2];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.items[i];
        } // for
        this.items = temp;
    } // makeMoreSpace

    /**
     * Returns the item at the specified index position in this string list.
     *
     * @param index - index of the item to return
     * @return the item at the specified position in this string list
     * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
     */
    @Override
    public String get(int index) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if
        return this.items[index];
    } // get

    /**
     * Removes all of the items from this string list.
     * The string list will be empty after this method returns.
     */
    @Override
    public void clear() {
        this.size = 0;
    } // clear

    /**
     * Removes the item at the specified index position in this string list.
     * Any items in the string list that were after the removed string
     * are shifted to the left (i.e., one is subtracted from their indices).
     *
     * @param index - index of the item to remove
     * @return the string that was removed
     * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
     */
    @Override
    public String remove(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if

        // String remove stores the original value of that index. Then the loop moves
        // all the items to the right of that index left to fill in the metaphorical "gap".
        String remove = this.items[index];
        for (int i = index; i < this.size - 1; i++) {
            this.items[i] = this.items[i + 1];
        } // for
        this.size--;
        return remove;
    } // remove

    /**
     * Returns a new string list that contains the items from this string list in reverse order.
     * The returned string list must be an object of the same class as the calling object.
     *
     * @return a new string list with items from this list in reverse order
     */
    @Override
    public StringList reverse() {

        // created a new StringList called reverse that stores all the items
        // in the one created in main and will copy the items down in reverse order.
        ArrayStringList reverse = new ArrayStringList();
        reverse.items = new String[this.items.length];
        for (int i = 0; i < this.size; i++) {
            reverse.items[i] = this.items[this.size - 1 - i];
            reverse.size++;
        } // for
        return reverse;
    } // reverse

    /**
     * Returns a new string list that contains the items from this list between
     * the specified start index (inclusive) and stop index (exclusive).
     * This method does not modify the calling object. If start and stop are in bounds and equal,
     * then the returned string list is empty.
     * The returned string list must be an object of the same class as the calling object.
     *
     * @param start - left endpoint (inclusive) of the slice
     * @param stop - right endpoint (exclusive) of the slice
     * @return a new string list with the items from this list
     * from start (inclusive) to stop (exclusive)
     * @throws IndexOutOfBoundsException - for an illegal endpoint index value
     * (start < 0 || stop > size() || start > stop)
     */
    @Override
    public StringList slice(int start, int stop) {
        if (start < 0 || stop > this.size || start > stop) {
            throw new IndexOutOfBoundsException("illegal endpoint index value");
        } // if

        // creates a new ArrayList called slice that stores the items starting at the start
        // point in main at index 0, 1, 2, etc. in slice.
        ArrayStringList slice = new ArrayStringList();
        slice.items = new String[this.items.length];
        for (int i = 0; i < stop - start; i++) {
            slice.items[i] = this.items[start + i];
            slice.size++;
        } // for
        return slice;
    } // slice

} // ArrayStringList
