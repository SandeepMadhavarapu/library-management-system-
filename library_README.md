# Library Management System

A Java object-oriented library catalogue system demonstrating core OOP principles including abstract classes, interfaces, generics, custom exceptions, and JUnit 5 testing.

## Features

- Check items in and out of the catalogue
- Search by title (case-insensitive)
- View all available items at a glance
- Full exception handling for invalid operations
- Works generically with any borrowable item type (Books, Magazines, etc.)

## Project Structure

```
src/
├── Borrowable.java            # Interface — checkout/return contract
├── LibraryItem.java           # Abstract base class — shared item metadata
├── Book.java                  # Concrete item — extends LibraryItem, implements Borrowable
├── Magazine.java              # Concrete item — extends LibraryItem, implements Borrowable
├── Library.java               # Generic catalogue — Library<T extends LibraryItem & Borrowable>
├── ItemNotFoundException.java # Checked exception — bad ID lookup
├── ItemUnavailableException.java # Checked exception — invalid borrow/return state
├── Main.java                  # Demo driver
└── LibraryTest.java           # JUnit 5 unit tests (17 tests)
```

## Concepts Demonstrated

| Concept | Where |
|---|---|
| Abstract class | `LibraryItem` |
| Interface | `Borrowable` |
| Inheritance | `Book`, `Magazine` extend `LibraryItem` |
| Generics with bounded type | `Library<T extends LibraryItem & Borrowable>` |
| Custom checked exceptions | `ItemNotFoundException`, `ItemUnavailableException` |
| Encapsulation | Private fields + accessors throughout |
| `@Override` | `getSummary()`, `equals()`, `hashCode()`, `toString()` |
| JUnit 5 testing | `LibraryTest.java` — 17 test cases |

## Running the Demo

```bash
javac src/*.java
java -cp src Main
```

## Running Tests

Requires JUnit 5 on the classpath:

```bash
javac -cp .:junit-5.jar src/*.java
java  -cp .:junit-5.jar org.junit.platform.console.ConsoleLauncher --scan-classpath
```

## Sample Output

```
=== Sandeep's Book Library — Full Catalogue ===
  Book | The Pragmatic Programmer | 352 pages | Genre: Technology | Available
  Book | Clean Code | 431 pages | Genre: Technology | Available
  Book | Dune | 412 pages | Genre: Science Fiction | Available
  Book | Sapiens | 443 pages | Genre: History | Available

Checking out B001...
Available books after checkout:
  Clean Code
  Dune
  Sapiens

Returning B001...
B001 is available again: true

Caught expected error: "Clean Code" is already checked out.

Search results for 'code':
  [B002] Clean Code (2008) — Robert C. Martin

Caught expected error: No item found with ID: ZZZZ
```

## Requirements

- Java 17+
- JUnit 5 (for tests only)
