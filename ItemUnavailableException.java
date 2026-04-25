/**
 * ItemUnavailableException.java
 *
 * Thrown when a borrow or return operation cannot be completed
 * because of the item's current availability state.
 */
public class ItemUnavailableException extends Exception {

    /**
     * @param message description of why the operation failed
     */
    public ItemUnavailableException(String message) {
        super(message);
    }
}
