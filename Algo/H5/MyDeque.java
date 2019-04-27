/**
 * Represents an own implementation of a Deque containing Strings.
 * The Deque is based on a DoubleLinkedList.
 * Since a Deque can't modify the elements between the fist and the last element, it's DoubleLinkedList does not
 * support modification in between these elements, to keep the code simple.
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class MyDeque {
    /**
     * represents a Node of the DoubleLinkedList used as the Deque.
     */
    private class MyDequeNode{
        /**
         * the node's value.
         */
        String value;
        /**
         * the previous node.
         */
        MyDequeNode previous;
        /**
         * the next node.
         */
        MyDequeNode next;

        /**
         * Sole constructor. Used to immediately set members.
         * @param val the node's value.
         * @param prev the previous node.
         * @param nex the next node.
         */
        MyDequeNode (String val, MyDequeNode prev, MyDequeNode nex){
            value = val;
            previous = prev;
            next = nex;
        }
    }
    /**
     * the first element of the Deque.
     */
    private MyDequeNode root;
    /**
     * the last element of the Deque.
     */
    private MyDequeNode last;

    /**
     * Adds a value to the beginning of the Deque.
     * @param value the value to be added.
     */
    public void addFirst(String value){
        MyDequeNode newNode = new MyDequeNode(value, null, root);
        if (root == null){
            last = newNode;
        } else {
            root.previous = newNode;
        }
        root = newNode;
    }

    /**
     * Adds a value to the end of the Deque.
     * @param value the value to be added.
     */
    public void addLast(String value){
        MyDequeNode newNode = new MyDequeNode(value, last, null);
        if (last == null){
            root = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
    }

    /**
     * Removes the first element of the Deque and returns its value.
     * Retuns null, if Deque is empty.
     * @return the first elements value, or null if Deque is empty.
     */
    public String removeFirst(){
        if (root == null){
            return null;
        } else {
            String ret = root.value;
            root = root.next;
            if (root == null){  //last element was removed
                last = null;
            } else {
                root.previous = null;
            }
            return ret;
        }
    }

    /**
     * Removes the last element of the Deque and returns its value.
     * Returns null, if Deque is empty.
     * @return the last elements value, or null if Deque is empty.
     */
    public String removeLast(){
        if (last == null){
            return null;
        } else {
            String ret = last.value;
            last = last.previous;
            if (last == null){  //last element was removed
                root = null;
            } else {
                last.next = null;
            }
            return ret;
        }
    }
}
