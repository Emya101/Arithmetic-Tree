//Emhenya Supreme 

import java.util.Iterator;

/**
 * An abstract class representing a tree structure. This class implements the Tree interface
 * and provides basic functionalities for tree-based operations.
 *
 * @param <E> The type of elements held in the tree.
 */
public abstract class AbstractTree<E> implements Tree<E> {

    /**
     * Checks if a given position is internal.
     *
     * @param p The position to check.
     * @return True if the position is internal, false otherwise.
     */
    public boolean isInternal(Position<E> p) {
        return numChildren(p) > 0;
    }

    /**
     * Checks if a given position is external (a leaf).
     *
     * @param p The position to check.
     * @return True if the position is external, false otherwise.
     */
    public boolean isExternal(Position<E> p) {
        return numChildren(p) == 0;
    }

    /**
     * Checks if a given position is the root of the tree.
     *
     * @param p The position to check.
     * @return True if the position is the root, false otherwise.
     */
    public boolean isRoot(Position<E> p) {
        return p == root();
    }

    /**
     * Checks if the tree is empty.
     *
     * @return True if the tree is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Computes the depth of a given position in the tree.
     *
     * @param p The position whose depth is to be computed.
     * @return The depth of the given position.
     */
    public int depth(Position<E> p) {
        if (isRoot(p))
            return 0;
        else
            return 1 + depth(parent(p));
    }

    /**
     * Computes the height of a given position in the tree.
     *
     * @param p The position whose height is to be computed.
     * @return The height of the given position.
     */
    public int height(Position<E> p) {
        int h = 0;
        for (Position<E> c : children(p))
            h = Math.max(h, 1 + height(c));
        return h;
    }

    /**
     * Returns an iterator over the elements of the tree.
     *
     * @return An iterator over the elements of the tree.
     */
    public Iterator<E> iterator() {
        return new ElementIterator();
    }

    /**
     * Nested class to implement an iterator for the elements of the tree.
     */
    protected class ElementIterator implements Iterator<E> {
        private Iterator<Position<E>> posIterator = positions().iterator();

        public boolean hasNext() {
            return posIterator.hasNext();
        }

        public E next() {
            return posIterator.next().getElement();
        }

        public void remove() {
            posIterator.remove();
        }
    }
}
