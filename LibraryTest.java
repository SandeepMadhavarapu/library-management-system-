import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * LibraryTest.java
 *
 * Unit tests for the Library Management System covering:
 *  - normal checkout / return flows
 *  - exception cases (item not found, already checked out)
 *  - catalogue search
 *  - generic type safety with both Book and Magazine
 */
class LibraryTest {

    private Library<Book>     bookLib;
    private Library<Magazine> magLib;
    private Book     cleanCode;
    private Book     dune;
    private Magazine natGeo;

    @BeforeEach
    void setUp() {
        bookLib = new Library<>("Test Book Library");
        magLib  = new Library<>("Test Magazine Library");

        cleanCode = new Book("B001", "Clean Code", "Robert C. Martin",
                             2008, "Technology", 431);
        dune      = new Book("B002", "Dune", "Frank Herbert",
                             1965, "Science Fiction", 412);
        natGeo    = new Magazine("M001", "National Geographic",
                                 "NGS", 2024, "Vol. 245");

        bookLib.addItem(cleanCode);
        bookLib.addItem(dune);
        magLib.addItem(natGeo);
    }

    // ------------------------------------------------------------------ //
    //  Catalogue size                                                      //
    // ------------------------------------------------------------------ //

    @Test
    void testCatalogueSizeAfterAdding() {
        assertEquals(2, bookLib.getCatalogueSize());
        assertEquals(1, magLib.getCatalogueSize());
    }

    // ------------------------------------------------------------------ //
    //  Checkout                                                            //
    // ------------------------------------------------------------------ //

    @Test
    void testCheckOutMakesItemUnavailable() throws Exception {
        bookLib.checkOut("B001");
        assertFalse(cleanCode.isAvailable());
    }

    @Test
    void testCheckOutAlreadyCheckedOutThrows() throws Exception {
        bookLib.checkOut("B001");
        assertThrows(ItemUnavailableException.class,
                () -> bookLib.checkOut("B001"));
    }

    // ------------------------------------------------------------------ //
    //  Return                                                              //
    // ------------------------------------------------------------------ //

    @Test
    void testReturnMakesItemAvailable() throws Exception {
        bookLib.checkOut("B001");
        bookLib.returnItem("B001");
        assertTrue(cleanCode.isAvailable());
    }

    @Test
    void testReturnItemNotCheckedOutThrows() {
        assertThrows(ItemUnavailableException.class,
                () -> bookLib.returnItem("B001"));
    }

    // ------------------------------------------------------------------ //
    //  Lookup                                                              //
    // ------------------------------------------------------------------ //

    @Test
    void testFindByIdReturnsCorrectItem() throws Exception {
        Book found = bookLib.findById("B002");
        assertEquals("Dune", found.getTitle());
    }

    @Test
    void testFindByIdNotFoundThrows() {
        assertThrows(ItemNotFoundException.class,
                () -> bookLib.findById("ZZZZ"));
    }

    // ------------------------------------------------------------------ //
    //  Search                                                              //
    // ------------------------------------------------------------------ //

    @Test
    void testSearchByTitleReturnsMatches() {
        List<Book> results = bookLib.searchByTitle("code");
        assertEquals(1, results.size());
        assertEquals("Clean Code", results.get(0).getTitle());
    }

    @Test
    void testSearchByTitleCaseInsensitive() {
        List<Book> results = bookLib.searchByTitle("DUNE");
        assertEquals(1, results.size());
    }

    @Test
    void testSearchByTitleNoMatchReturnsEmpty() {
        List<Book> results = bookLib.searchByTitle("xyz123");
        assertTrue(results.isEmpty());
    }

    // ------------------------------------------------------------------ //
    //  Available items                                                     //
    // ------------------------------------------------------------------ //

    @Test
    void testGetAvailableItemsAllAvailable() {
        assertEquals(2, bookLib.getAvailableItems().size());
    }

    @Test
    void testGetAvailableItemsAfterCheckout() throws Exception {
        bookLib.checkOut("B001");
        List<Book> available = bookLib.getAvailableItems();
        assertEquals(1, available.size());
        assertEquals("Dune", available.get(0).getTitle());
    }

    // ------------------------------------------------------------------ //
    //  Magazine (generic type check)                                       //
    // ------------------------------------------------------------------ //

    @Test
    void testMagazineCheckOutAndReturn() throws Exception {
        magLib.checkOut("M001");
        assertFalse(natGeo.isAvailable());
        magLib.returnItem("M001");
        assertTrue(natGeo.isAvailable());
    }

    // ------------------------------------------------------------------ //
    //  Constructor validation                                              //
    // ------------------------------------------------------------------ //

    @Test
    void testBookInvalidPagesThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Book("B999", "Bad Book", "Author", 2020, "Fiction", 0));
    }

    @Test
    void testLibraryBlankNameThrows() {
        assertThrows(IllegalArgumentException.class,
                () -> new Library<>(""));
    }
}
