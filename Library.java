import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Library.java
 *
 * A generic catalogue that holds any type of {@link LibraryItem}
 * that also implements {@link Borrowable}.
 *
 * <p>Type parameter {@code T} is bounded so the catalogue can
 * uniformly call both item-metadata methods and borrow/return
 * operations without casting.
 *
 * @param <T> a type that extends {@link LibraryItem} and implements
 *            {@link Borrowable}
 */
public class Library<T extends LibraryItem & Borrowable> {

    private final String   name;
    private final List<T>  catalogue;

    /**
     * @param name display name of this library branch
     */
    public Library(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Library name cannot be blank.");
        this.name      = name;
        this.catalogue = new ArrayList<>();
    }

    // ------------------------------------------------------------------ //
    //  Catalogue management                                                //
    // ------------------------------------------------------------------ //

    /**
     * Add an item to the catalogue.
     *
     * @param item item to add
     * @throws IllegalArgumentException if item is null
     */
    public void addItem(T item) {
        if (item == null) throw new IllegalArgumentException("Cannot add a null item.");
        catalogue.add(item);
    }

    /**
     * Remove an item from the catalogue by ID.
     *
     * @param id catalogue ID to remove
     * @throws ItemNotFoundException if no item with that ID exists
     */
    public void removeItem(String id) throws ItemNotFoundException {
        T found = findById(id);
        catalogue.remove(found);
    }

    // ------------------------------------------------------------------ //
    //  Lookup                                                              //
    // ------------------------------------------------------------------ //

    /**
     * Find an item by its unique catalogue ID.
     *
     * @param id the ID to look up
     * @return the matching item
     * @throws ItemNotFoundException if no match is found
     */
    public T findById(String id) throws ItemNotFoundException {
        for (T item : catalogue) {
            if (item.getId().equalsIgnoreCase(id)) return item;
        }
        throw new ItemNotFoundException(id);
    }

    /**
     * Return all items whose title contains the given keyword
     * (case-insensitive).
     *
     * @param keyword search string
     * @return list of matching items (may be empty)
     */
    public List<T> searchByTitle(String keyword) {
        String lower = keyword.toLowerCase();
        return catalogue.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(lower))
                .collect(Collectors.toList());
    }

    /**
     * @return all items currently available to borrow
     */
    public List<T> getAvailableItems() {
        return catalogue.stream()
                .filter(Borrowable::isAvailable)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------------ //
    //  Borrow / Return                                                     //
    // ------------------------------------------------------------------ //

    /**
     * Check out an item by ID.
     *
     * @param id catalogue ID of the item to borrow
     * @throws ItemNotFoundException    if the ID does not exist
     * @throws ItemUnavailableException if the item is already checked out
     */
    public void checkOut(String id) throws ItemNotFoundException, ItemUnavailableException {
        findById(id).checkOut();
    }

    /**
     * Return an item by ID.
     *
     * @param id catalogue ID of the item to return
     * @throws ItemNotFoundException    if the ID does not exist
     * @throws ItemUnavailableException if the item was not checked out
     */
    public void returnItem(String id) throws ItemNotFoundException, ItemUnavailableException {
        findById(id).returnItem();
    }

    // ------------------------------------------------------------------ //
    //  Display                                                             //
    // ------------------------------------------------------------------ //

    /** Print the full catalogue to standard output. */
    public void printCatalogue() {
        System.out.println("=== " + name + " — Full Catalogue ===");
        if (catalogue.isEmpty()) {
            System.out.println("  (empty)");
            return;
        }
        catalogue.forEach(item -> System.out.println("  " + item.getSummary()));
    }

    // ------------------------------------------------------------------ //
    //  Accessors                                                           //
    // ------------------------------------------------------------------ //

    public String  getName()         { return name; }
    public int     getCatalogueSize(){ return catalogue.size(); }
}
