//Emhenya Supreme 

import java.util.ArrayList;
import java.util.List;

/**
 * An abstract class representing a binary tree structure. This class extends AbstractTree
 * and implements the BinaryTree interface.
 *
 * @param <E> The type of elements held in the binary tree.
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {

    /**
     * Returns the sibling of a given position.
     *
     * @param p The position whose sibling is to be found.
     * @return The sibling of the given position, or null if no sibling exists.
     */
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        if (parent == null) return null;
        if (p == left(parent)) return right(parent);
        else return left(parent);
    }

    /**
     * Returns the number of children of a given position.
     *
     * @param p The position whose children are to be counted.
     * @return The number of children of the given position.
     */
    public int numChildren(Position<E> p) {
        int count = 0;
        if (left(p) != null) count++;
        if (right(p) != null) count++;
        return count;
    }

    /**
     * Returns an iterable collection of the children of a given position.
     *
     * @param p The position whose children are to be returned.
     * @return An iterable collection of the children of the given position.
     */
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<>(2);
        if (left(p) != null) snapshot.add(left(p));
        if (right(p) != null) snapshot.add(right(p));
        return snapshot;
    }
}

    