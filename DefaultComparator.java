  //Emhenya Supreme 
  import java.util.Comparator;

/**
 * A default implementation of a comparator using natural ordering.
 * This comparator assumes that the elements being compared implement the Comparable interface.
 *
 * @param <K> The type of elements being compared, which must implement Comparable<K>.
 */
public class DefaultComparator<K extends Comparable<K>> implements Comparator<K> {

    /**
     * Compares two elements using their natural ordering.
     *
     * @param a The first element to be compared.
     * @param b The second element to be compared.
     * @return A negative integer, zero, or a positive integer as the first element is less than, equal to,
     *         or greater than the second element, respectively.
     * @throws NullPointerException if either a or b is null and this comparator does not permit null arguments.
     * @throws ClassCastException if the arguments' types prevent them from being compared by this comparator.
     */
    @Override
    public int compare(K a, K b) {
        return a.compareTo(b);
    }
}
