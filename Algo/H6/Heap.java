import java.util.ArrayList;
import java.util.Collections;

/**
 * An implementation of the class heap
 *
 * @author Lukas Szimtenings
 * @author Felix Szimtenings
 * @author Cedric Radtke
 */
public class Heap
{
    private ArrayList<Integer> heap;

    /**
     * Creates a new empty heap
     */
    public Heap()
    {
        heap = new ArrayList<>();
        heap.add(null);
    }

    /**
     * @return true if the Heap contains no elements, false otherwise
     */
    public boolean isEmpty()
    {
        return heap.size() == 1;
    }

    /**
     * Adds an element into the tree
     *
     * @param element element to be added
     */
    public void add(Integer element)
    {
        heap.add(element);
        upheap();
    }

    /**
     * returns and deletes the biggest element in the heap
     *
     * @return Biggest element
     */
    public Integer getMax()
    {
        Integer max = heap.get(1);
        Collections.swap(heap, 1, heap.size() - 1);
        heap.remove(heap.size() - 1);
        if (!isEmpty()) {
            downheap();
        }
        return max;
    }

    /**
     * Rotates the last element upwards into the parent as long as it is bigger than it's parent
     */
    private void upheap()
    {
        int elementIndex = heap.size() - 1;
        int parentIndex = getParentIndex(elementIndex);

        // While element ist bigger than parent switch element with parent
        // Also check if we are root
        while (elementIndex != 1 && heap.get(parentIndex) < heap.get(elementIndex)) {

            // Swap with parent
            Collections.swap(heap, parentIndex, elementIndex);

            // Move indicies one up
            elementIndex = parentIndex;
            parentIndex = getParentIndex(elementIndex);
        }
    }

    /**
     * Rotates the root element downheap as long as the element is smaller than one of it's children
     */
    private void downheap()
    {
        int elementIndex = 1;
        int elementValue = heap.get(elementIndex);

        Integer biggerChildIndex = getBiggerChildIndex(elementIndex);
        // Make sure we are not root already
        if (biggerChildIndex == null)
            return;
        int biggerChildValue = heap.get(biggerChildIndex);

        while (biggerChildValue > elementValue) {
            Collections.swap(heap, biggerChildIndex, elementIndex);

            elementIndex = biggerChildIndex;

            biggerChildIndex = getBiggerChildIndex(elementIndex);
            // we are root break
            if (biggerChildIndex == null) {
                break;
            }
            biggerChildValue = heap.get(biggerChildIndex);
        }
    }

    /**
     * finds the index of the bigger child
     *
     * @param parentIndex index of the node to investigate
     * @return index of the bigger child or null if node has no children.
     */
    private Integer getBiggerChildIndex(int parentIndex)
    {
        int lastElem = heap.size() - 1;

        int leftIndex = getLeftChildIndex(parentIndex);
        Integer leftValue;

        // Avoid indexOutOfBoundsException
        if (leftIndex > lastElem) {
            leftValue = null;
        } else {
            leftValue = heap.get(leftIndex);
        }

        int rightIndex = getRightChildIndex(parentIndex);
        Integer rightValue;

        // Avoid indexOutOfBoundsException
        if (rightIndex > lastElem) {
            rightValue = null;
        } else {
            rightValue = heap.get(rightIndex);
        }

        // Catch indices where there is no value
        if (rightValue == null && leftValue == null) {
            return null;
        } else if (rightValue == null) {
            return leftIndex;
        } else if (leftValue == null) {
            return rightIndex;
        }

        // Both children exist, return the bigger one
        if (rightValue > leftValue) {
            leftIndex = rightIndex;
        }
        return leftIndex;
    }

    public String toString()
    {
        return heap.subList(1, heap.size()).toString();
    }

    /**
     * Helper method returning the index of the parent
     *
     * @param index index of the element to investigate
     * @return parent index for the given index
     */
    private Integer getParentIndex(int index)
    {
        return index / 2;
    }

    /**
     * Helper method returning the index of the left child of an element
     *
     * @param index index of the element to investigate
     * @return left child index for the given index
     */
    private int getLeftChildIndex(int index)
    {
        return index * 2;
    }

    /**
     * Helper method returning the index of the right child of an element
     *
     * @param index index of the element to investigate
     * @return right child index for the given index
     */
    private int getRightChildIndex(int index)
    {
        return index * 2 + 1;
    }
}
