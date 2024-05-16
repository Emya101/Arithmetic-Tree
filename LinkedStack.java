//Emhenya Supreme 
/**
 * A class representing a stack data structure implemented using a singly linked list.
 *
 * @param <E> The type of elements held in the stack.
 */
public class LinkedStack<E> implements Stack<E> {

    // Create a singly linked list instance to store elements.
    public SinglyLinkedList<E> list = new SinglyLinkedList<>();

    /**
     * Constructs an empty LinkedStack.
     */
    public LinkedStack() {}

    /**
     * Returns the number of elements in the stack.
     *
     * @return The number of elements in the stack.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Checks if the stack is empty.
     *
     * @return True if the stack contains no elements, false otherwise.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Pushes an element onto the top of the stack.
     *
     * @param element The element to be pushed onto the stack.
     */
    public void push(E element) {
        this.list.addFirst(element);
    }

    /**
     * Returns the element at the top of the stack without removing it.
     *
     * @return The element at the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E top() {
        return this.list.first();
    }

    /**
     * Removes and returns the element at the top of the stack.
     *
     * @return The element removed from the top of the stack.
     * @throws EmptyStackException if the stack is empty.
     */
    public E pop() {
        return this.list.removeFirst();
    }
}

