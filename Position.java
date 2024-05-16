/**Emhenya Supreme 
 * Interface for a position, which is an abstraction representing the location of a single element within a data structure.
 * @param <E> the type of element stored at this position
 */
public interface Position<E> {
    /**
     * Returns the element stored at this position.
     * @return the element stored at this position
     * @throws IllegalStateException if position is no longer valid
     */
    E getElement() throws IllegalStateException;
}
