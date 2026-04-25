/**
 * ItemNotFoundException.java
 *
 * Thrown when a catalogue lookup fails to find a matching item.
 */
public class ItemNotFoundException extends Exception {

    private final String itemId;

    /**
     * @param itemId the ID that could not be located
     */
    public ItemNotFoundException(String itemId) {
        super("No item found with ID: " + itemId);
        this.itemId = itemId;
    }

    /** @return the ID that triggered the exception */
    public String getItemId() { return itemId; }
}
