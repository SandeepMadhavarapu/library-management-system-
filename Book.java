/**
 * Book.java
 *
 * A {@link LibraryItem} representing a physical or digital book.
 * Books can be checked out and returned by a single patron at a time.
 */
public class Book extends LibraryItem implements Borrowable {

    private final String genre;
    private final int    pages;
    private boolean      available;

    /**
     * @param id     unique catalogue identifier
     * @param title  book title
     * @param author author's name
     * @param year   publication year
     * @param genre  literary genre (e.g. "Fiction", "Biography")
     * @param pages  total page count
     */
    public Book(String id, String title, String author,
                int year, String genre, int pages) {
        super(id, title, author, year);
        if (pages <= 0) throw new IllegalArgumentException("Page count must be positive.");
        this.genre     = genre;
        this.pages     = pages;
        this.available = true;
    }

    // ------------------------------------------------------------------ //
    //  Borrowable                                                          //
    // ------------------------------------------------------------------ //

    @Override
    public void checkOut() throws ItemUnavailableException {
        if (!available) {
            throw new ItemUnavailableException(
                "\"" + getTitle() + "\" is already checked out.");
        }
        available = false;
    }

    @Override
    public void returnItem() throws ItemUnavailableException {
        if (available) {
            throw new ItemUnavailableException(
                "\"" + getTitle() + "\" was not checked out.");
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
        return String.format("Book | %s | %d pages | Genre: %s | %s",
                getTitle(), pages, genre, available ? "Available" : "Checked out");
    }

    // ------------------------------------------------------------------ //
    //  Accessors                                                           //
    // ------------------------------------------------------------------ //

    public String getGenre() { return genre; }
    public int    getPages() { return pages; }
}
