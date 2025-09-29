// User.java
import java.util.ArrayList;
import java.util.List;

public class User {
    private int userId;
    private String name;
    private List<Book> borrowedBooks;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public List<Book> getBorrowedBooks() { return borrowedBooks; }

    // Add book to user's borrowed list
    public boolean borrowBook(Book book) {
        if (book == null) return false;
        borrowedBooks.add(book);
        return true;
    }

    // Remove book from user's borrowed list
    public boolean returnBook(Book book) {
        return borrowedBooks.remove(book);
    }

    public void listBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("No borrowed books.");
            return;
        }
        System.out.println("Borrowed books:");
        for (Book b : borrowedBooks) {
            System.out.println(" - " + b);
        }
    }

    @Override
    public String toString() {
        return String.format("User[%d] %s (Borrowed: %d)", userId, name, borrowedBooks.size());
    }
}
