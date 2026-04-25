/**
 * Main.java
 *
 * Demonstrates the Library Management System by creating a small
 * catalogue, performing checkouts, returns, and searches.
 */
public class Main {

    public static void main(String[] args) {

        // --- Build the catalogue ---------------------------------------- //

        Library<Book> bookLibrary = new Library<>("Sandeep's Book Library");

        bookLibrary.addItem(new Book("B001", "The Pragmatic Programmer",
                "David Thomas", 2019, "Technology", 352));
        bookLibrary.addItem(new Book("B002", "Clean Code",
                "Robert C. Martin", 2008, "Technology", 431));
        bookLibrary.addItem(new Book("B003", "Dune",
                "Frank Herbert", 1965, "Science Fiction", 412));
        bookLibrary.addItem(new Book("B004", "Sapiens",
                "Yuval Noah Harari", 2011, "History", 443));

        Library<Magazine> magLibrary = new Library<>("Sandeep's Magazine Archive");

        magLibrary.addItem(new Magazine("M001", "National Geographic",
                "National Geographic Society", 2024, "Vol. 245, No. 3"));
        magLibrary.addItem(new Magazine("M002", "MIT Technology Review",
                "MIT", 2024, "Vol. 127, No. 1"));

        // --- Print catalogues ------------------------------------------- //

        bookLibrary.printCatalogue();
        System.out.println();
        magLibrary.printCatalogue();
        System.out.println();

        // --- Check out and return ---------------------------------------- //

        try {
            System.out.println("Checking out B001...");
            bookLibrary.checkOut("B001");
            System.out.println("Available books after checkout:");
            bookLibrary.getAvailableItems()
                       .forEach(b -> System.out.println("  " + b.getTitle()));
            System.out.println();

            System.out.println("Returning B001...");
            bookLibrary.returnItem("B001");
            System.out.println("B001 is available again: " +
                    bookLibrary.findById("B001").isAvailable());
            System.out.println();

            // Intentionally trigger ItemUnavailableException
            bookLibrary.checkOut("B002");
            bookLibrary.checkOut("B002");   // already checked out

        } catch (ItemUnavailableException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        } catch (ItemNotFoundException e) {
            System.out.println("Item not found: " + e.getMessage());
        }

        System.out.println();

        // --- Search ---------------------------------------------------- //

        System.out.println("Search results for 'code':");
        bookLibrary.searchByTitle("code")
                   .forEach(b -> System.out.println("  " + b));

        System.out.println();

        // --- Trigger ItemNotFoundException ------------------------------ //

        try {
            bookLibrary.findById("ZZZZ");
        } catch (ItemNotFoundException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}
