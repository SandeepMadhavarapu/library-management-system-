# Library Management System

An object-oriented **Library Management System** written in Java. It models real-world borrowing workflows — cataloguing items, checking them out, returning them, and searching — using a clean type hierarchy and a fully checked error model. Built to demonstrate core OOP design: abstract classes, interfaces, bounded generics, checked exceptions, and a JUnit 5 test suite.

## Features

- **Generic, type-safe catalogue** — `Library<T extends LibraryItem & Borrowable>` holds any item that is both a library item *and* borrowable, so the same catalogue works for books, magazines, or any future item type without casting.
- **Borrow / return workflow** — check out and return items by ID, with availability tracked per item.
- **Search & lookup** — find items by unique ID or search titles by keyword (case-insensitive).
- **Robust error handling** — custom checked exceptions (`ItemNotFoundException`, `ItemUnavailableException`) make failure cases explicit and impossible to ignore.
- **Tested** — 17-test JUnit 5 suite covering catalogue management, lookup, borrowing rules, and exception paths.

## Design

| Type | Role |
|------|------|
| `LibraryItem` | Abstract base class — shared metadata (ID, title, author, year) and item summary |
| `Borrowable` | Interface — `checkOut()`, `returnItem()`, `isAvailable()` contract |
| `Book` | Concrete item — adds genre and page count |
| `Magazine` | Concrete item — adds issue information |
| `Library<T>` | Generic catalogue bounded to `LibraryItem & Borrowable` |
| `ItemNotFoundException` | Thrown when an ID is not in the catalogue |
| `ItemUnavailableException` | Thrown when checking out an already-borrowed item (or returning one that isn't out) |

The bounded type parameter `T extends LibraryItem & Borrowable` is the core idea: the catalogue can call both item-metadata methods and borrow/return operations uniformly, with no casts and full compile-time safety.

## Getting started

Requires **Java 11 or later**.

```bash
# Clone
git clone https://github.com/SandeepMadhavarapu/library-management-system-.git
cd library-management-system-

# Compile
javac *.java

# Run the demo
java Main
```

`Main.java` builds a small book and magazine catalogue, then demonstrates checkouts, returns, searches, and the two exception paths.

## Example usage

```java
Library<Book> library = new Library<>("City Library");

library.addItem(new Book("B001", "Clean Code", "Robert C. Martin", 2008, "Technology", 431));

library.checkOut("B001");                 // borrow
library.returnItem("B001");               // return
library.searchByTitle("clean");           // keyword search
library.getAvailableItems();              // everything currently on the shelf
```

## Running the tests

The suite lives in `LibraryTest.java` (JUnit 5). With the JUnit 5 console launcher or your IDE's test runner:

```bash
# Example with the JUnit Platform Console Launcher on the classpath
javac -cp junit-platform-console-standalone.jar *.java
java -jar junit-platform-console-standalone.jar --class-path . --scan-class-path
```

Or simply run `LibraryTest` from IntelliJ / VS Code / Eclipse.

## Concepts demonstrated

- **Abstraction** — `LibraryItem` base class and `Borrowable` interface
- **Generics with bounded type parameters** — `Library<T extends LibraryItem & Borrowable>`
- **Checked exceptions** — explicit, recoverable error handling
- **Streams & lambdas** — filtering and searching the catalogue
- **Unit testing** — JUnit 5 coverage of behavior and edge cases
