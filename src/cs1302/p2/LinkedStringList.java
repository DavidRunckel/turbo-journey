package cs1302.p2;

import cs1302.adt.StringList;
import cs1302.adt.Node;

/**
 * Creates a LinkedStringList object that extends BaseStringList.
 * The methods that are called from an LSL utilize nodes to create and modify the list.
 *
 * <p>
 * {@inheritDoc}
 */
public class LinkedStringList extends BaseStringList {

    // instance variable
    private Node head;

    /**
     * Constructs an empty string list.
     */
    public LinkedStringList() {
        this.head = null;
        this.size = 0;
    } // LinkedStringList

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
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if

        // Core of method. The firt portion of the if-statement is utilized when
        // the added item is added at the end of the list.
        // The else-statement is for when an addition is made at a position where
        // a node already exists and everything has to shift over.
        if (index == this.size) {
            Node newNode = new Node(item);
            newNode.setNext(this.head);
            this.head = newNode;
            this.size++;
        } else {
            Node newNode = new Node(item);
            Node shift = this.head;
            for (int i = index; i < this.size - 1; i++) {
                shift = shift.getNext();
            } // for
            if (shift.hasNext() == true) {
                newNode.setNext(shift.getNext());
                shift.setNext(newNode);
            } else {
                shift.setNext(newNode);
            } // if-else
            this.size++;
        } // if-else
        return true;
    } // add

    /**
     * Removes all of the items from this string list.
     * The string list will be empty after this method returns.
     */
    @Override
    public void clear() {
        this.size = 0;
    } // clear

    /**
     * Returns the item at the specified index position in this string list.
     *
     * @param index - index of the item to return
     * @return the item at the specified position in this string list
     * @throws IndexOutOfBoundsException - if index is out of range (index < 0 || index >= size())
     */
    @Override
    public String get(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("index is out of range");
        } // if

        // Core of method. The getter node simply reads through the list till it reaches the index
        // and then returns the item at that position.
        Node getter = this.head;
        for (int i = this.size - 1; i > index; i--) {
            getter = getter.getNext();
        } // for
        return getter.getItem();
    } // get

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

        // Body of the method. The top of the if-statement executes if the index
        // that is being removed is at the top of the node chain. It will simply assign
        // head to the node one after the one it was assigned to jumping the chain by 1.
        // The else statement is where it has to read through the node chain to reach a deeper
        // index. It will use a temporary node called remover that stops one node prior to where
        // the index node is. This is because to remove a node from the list, you have to remove any
        // connection to the chain so you set the next value of the prior node to the node
        // after the index node jumping it in the chain and removing it.
        String r = this.get(index);
        if (index + 1 == this.size) {
            this.head = this.head.getNext();
        } else {
            Node remover = this.head;
            for (int i = this.size - 1; i > index + 1; i--) {
                remover = remover.getNext();
            } // for
            remover.setNext(remover.getNext().getNext());
        } // if-else
        this.size--;
        return r;
    } // remove

    /**
     * Returns a new string list that contains the items from this string list in reverse order.
     * The returned string list must be an object of the same class as the calling object.
     *
     * @return a new string list with items from this list in reverse order
     */
    @Override
    public StringList reverse() {

        // Creates a temporary node reverser that is at the top of the node-chain. Then,
        // the LSL called reversed will add each item as the reverser node reads through
        // the main node chain. This places the nodes in reversed order with the original nodes
        // latest addition as the earlist in the reversed LSL.
        LinkedStringList reversed = new LinkedStringList();
        Node reverser = this.head;
        for (int i = 0; i < this.size; i++) {
            reversed.add(i, reverser.getItem());
            // protects against a pain-in-the-ass runtime error
            if (i < this.size - 1) {
                reverser = reverser.getNext();
            } // if
        } // for
        return reversed;
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
     * @return a new string list with the items from this list from
     * start (inclusive) to stop (exclusive)
     * @throws IndexOutOfBoundsException - for an illegal endpoint index value
     * (start < 0 || stop > size() || start > stop)
     */
    @Override
    public StringList slice(int start, int stop) {
        if (start < 0 || stop > this.size || start > stop) {
            throw new IndexOutOfBoundsException("illegal endpoint index value");
        } // if

        // Core of method part 1. This does the same as reverse() which is needed to
        // properly move through the original node-chain as if it was ordered earliest-latest
        // instead of lastest-earliest addition. This is primarily to make the .getNext() method
        // read through the node-chain the way I wanted it to.
        LinkedStringList reversed = new LinkedStringList();
        Node reverser = this.head;
        for (int i = 0; i < this.size; i++) {
            reversed.add(i, reverser.getItem());
            // protects against a pain-in-the-ass runtime error
            if (i < this.size - 1) {
                reverser = reverser.getNext();
            } // if
        } // for

        // Core of the method part 2. This is where the new LSL is created called sliced.
        // The temporary node slicer reads through the reversed node-chain till it gets to
        // the starting position. Because of the first part of this method, it read's similar
        // to how you'd read through an array in order of earliest addition to lastest addition.
        // This made coding the math for the loops much easier than having to work backwards through
        // the node instead of down it using .getNext(). The second part of this portion adds each
        // node to the new LSL called sliced.
        LinkedStringList sliced = new LinkedStringList();
        Node slicer = reversed.head;
        for (int i = 0; i < start; i++) {
            slicer = slicer.getNext();
        } // for
        for (int j = 0; j < stop - start; j++) {
            sliced.add(j, slicer.getItem());
            if (j < stop - start - 1) {
                slicer = slicer.getNext();
            } // if
        } // for
        return sliced;
    } // slice

} //LinkedStringList
