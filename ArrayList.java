//Emhenya Supreme 3132969

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a dynamic array-based list.
 *
 * @param <E> The type of elements held in this list.
 */
public class ArrayList<E> implements Iterable<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size = 0;

    /**
     * Constructs an empty ArrayList with the default initial capacity.
     */
    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty ArrayList with the specified initial capacity.
     *
     * @param capacity The initial capacity of the ArrayList.
     */
    public ArrayList(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return The number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if this list is empty.
     *
     * @return True if this list contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element The element to be added to this list.
     */
    public void add(E element) {
        if (size == data.length) {
            resize(2 * data.length);
        }
        data[size++] = element;
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index The index of the element to be retrieved.
     * @return The element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public E get(int index) {
        checkIndex(index);
        return data[index];
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index   The index of the element to be replaced.
     * @param element The new element to be stored at the specified position.
     * @return The element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public E set(int index, E element) {
        checkIndex(index);
        E replacedElement = data[index];
        data[index] = element;
        return replacedElement;
    }

    /**
     * Removes the element at the specified position in this list.
     *
     * @param index The index of the element to be removed.
     * @return The element that was removed from the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public E remove(int index) {
        checkIndex(index);
        E removedElement = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removedElement;
    }

    /**
     * Increases the capacity of this ArrayList, if necessary, to ensure that it can hold
     * at least the number of elements specified by the minimum capacity argument.
     *
     * @param capacity The desired minimum capacity.
     */
    private void resize(int capacity) {
        data = Arrays.copyOf(data, capacity);
    }

    /**
     * Checks if the specified index is in the range of valid indices for this list.
     *
     * @param index The index to be checked.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }

    /**
     * Returns a string representation of this list.
     *
     * @return A string representation of this list.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @return An iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Nested class to implement an iterator for the elements of the ArrayList.
     */
    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        /**
         * Returns true if the iteration has more elements.
         *
         * @return True if the iteration has more elements, false otherwise.
         */
        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return The next element in the iteration.
         * @throws NoSuchElementException if the iteration has no more elements.
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return data[currentIndex++];
        }
    }
}

