// Main.java
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        seedData(); // optional: adds some sample books/users
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = readInt("Choose an option: ");
            switch (choice) {
                case 1: addBook(); break;
                case 2: removeBook(); break;
                case 3: registerUser(); break;
                case 4: issueBook(); break;
                case 5: returnBook(); break;
                case 6: searchByTitle(); break;
                case 7: searchByAuthor(); break;
                case 8: library.listAllBooks(); break;
                case 9: library.listAllUsers(); break;
                case 0: exit = true; break;
                default: System.out.println("Invalid option."); break;
            }
            System.out.println();
        }
        System.out.println("Goodbye!");
        sc.close();
    }

    private static void showMenu() {
        System.out.println("=== Library Menu ===");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book (by ISBN)");
        System.out.println("3. Register User");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Search Book by Title");
        System.out.println("7. Search Book by Author");
        System.out.println("8. List All Books");
        System.out.println("9. List All Users");
        System.out.println("0. Exit");
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = sc.nextLine().trim();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    // Actions
    private static void addBook() {
        String title = readString("Title: ");
        String author = readString("Author: ");
        String isbn = readString("ISBN: ");
        library.addBook(new Book(title, author, isbn));
    }

    private static void removeBook() {
        String isbn = readString("ISBN to remove: ");
        library.removeBook(isbn);
    }

    private static void registerUser() {
        int id = readInt("User ID (int): ");
        String name = readString("Name: ");
        library.registerUser(new User(id, name));
    }

    private static void issueBook() {
        String isbn = readString("ISBN to issue: ");
        int userId = readInt("User ID: ");
        library.issueBook(isbn, userId);
    }

    private static void returnBook() {
        String isbn = readString("ISBN to return: ");
        int userId = readInt("User ID: ");
        library.returnBook(isbn, userId);
    }

    private static void searchByTitle() {
        String title = readString("Title keyword: ");
        List<Book> res = library.searchByTitle(title);
        if (res.isEmpty()) System.out.println("No books found.");
        else res.forEach(b -> System.out.println(" - " + b));
    }

    private static void searchByAuthor() {
        String author = readString("Author keyword: ");
        List<Book> res = library.searchByAuthor(author);
        if (res.isEmpty()) System.out.println("No books found.");
        else res.forEach(b -> System.out.println(" - " + b));
    }

    // Seed sample data to test quickly
    private static void seedData() {
        library.addBook(new Book("Effective Java", "Joshua Bloch", "9780134685991"));
        library.addBook(new Book("Clean Code", "Robert C. Martin", "9780132350884"));
        library.registerUser(new User(1, "Sandeep"));
        library.registerUser(new User(2, "Anita"));
    }
}
