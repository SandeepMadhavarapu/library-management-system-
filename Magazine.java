/**
 * Magazine.java
 *
 * A {@link LibraryItem} representing a periodical magazine issue.
 * Magazines can be borrowed for in-library reading only.
 */
public class Magazine extends LibraryItem implements Borrowable {

    private final String issue;       // e.g. "Vol. 12, No. 3"
    private boolean      available;

    /**
     * @param id        unique catalogue identifier
     * @param title     magazine title
     * @param publisher publisher name
     * @param year      publication year
     * @param issue     volume/issue descriptor
     */
    public Magazine(String id, String title, String publisher,
                    int year, String issue) {
        super(id, title, publisher, year);
        if (issue == null || issue.isBlank())
            throw new IllegalArgumentException("Issue descriptor cannot be blank.");
        this.issue     = issue;
        this.available = true;
    }

    // ------------------------------------------------------------------ //
    //  Borrowable                                                          //
    // ------------------------------------------------------------------ //

    @Override
    public void checkOut() throws ItemUnavailableException {
        if (!available) {
            throw new ItemUnavailableException(
                "\"" + getTitle() + "\" (" + issue + ") is already in use.");
        }
        available = false;
    }

    @Override
    public void returnItem() throws ItemUnavailableException {
        if (available) {
            throw new ItemUnavailableException(
                "\"" + getTitle() + "\" (" + issue + ") was not checked out.");
        }
        available = true;
    }

    @Override
    public boolean isAvailable() { return available; }

    // ------------------------------------------------------------------ //
    //  LibraryItem                                                         //
    // ------------------------------------------------------------------ //

    @Override
    public String getSummary() {
        return String.format("Magazine | %s | Issue: %s | %s",
                getTitle(), issue, available ? "Available" : "In use");
    }

    // ------------------------------------------------------------------ //
    //  Accessor                                                            //
    // ------------------------------------------------------------------ //

    public String getIssue() { return issue; }
}
