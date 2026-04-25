/**
 * LibraryItem.java
 *
 * Abstract base class representing any item that can be held
 * in a library catalogue. Concrete subclasses must implement
 * {@link #getSummary()} to describe themselves.
 */
public abstract class LibraryItem {

    private final String id;
    private final String title;
    private final String creator;
    private final int    year;

    /**
     * @param id      unique catalogue identifier
     * @param title   title of the item
     * @param creator author, editor, or publisher
     * @param year    year of publication
     * @throws IllegalArgumentException if any string argument is null/blank
     *                                  or year is not positive
     */
    public LibraryItem(String id, String title, String creator, int year) {
        if (id == null || id.isBlank())      throw new IllegalArgumentException("ID cannot be blank.");
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be blank.");
        if (creator == null || creator.isBlank()) throw new IllegalArgumentException("Creator cannot be blank.");
        if (year <= 0)                        throw new IllegalArgumentException("Year must be positive.");

        this.id      = id;
        this.title   = title;
        this.creator = creator;
        this.year    = year;
    }

    // ------------------------------------------------------------------ //
    //  Accessors                                                           //
    // ------------------------------------------------------------------ //

    public String getId()      { return id; }
    public String getTitle()   { return title; }
    public String getCreator() { return creator; }
    public int    getYear()    { return year; }

    // ------------------------------------------------------------------ //
    //  Abstract                                                            //
    // ------------------------------------------------------------------ //

    /**
     * @return a one-line human-readable description of this item
     */
    public abstract String getSummary();

    // ------------------------------------------------------------------ //
    //  Object overrides                                                    //
    // ------------------------------------------------------------------ //

    @Override
    public String toString() {
        return String.format("[%s] %s (%d) — %s", id, title, year, creator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof LibraryItem other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
