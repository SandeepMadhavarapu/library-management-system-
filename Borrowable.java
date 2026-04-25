/**
 * Borrowable.java
 *
 * Defines the contract for any library item that can be
 * checked out and returned by a patron.
 */
public interface Borrowable {

    /**
     * Mark this item as borrowed.
     *
     * @throws ItemUnavailableException if the item is already checked out
     */
    void checkOut() throws ItemUnavailableException;

    /**
     * Mark this item as returned and available again.
     *
     * @throws ItemUnavailableException if the item was not checked out
     */
    void returnItem() throws ItemUnavailableException;

    /**
     * @return {@code true} if the item is currently available to borrow
     */
    boolean isAvailable();
}
