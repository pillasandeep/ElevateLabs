// Library.java
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {
    private List<Book> books;
    private List<User> users;

    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // Book management
    public void addBook(Book book) {
        if (book == null) return;
        if (books.contains(book)) {
            System.out.println("Book already exists (same ISBN).");
            return;
        }
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }

    public void removeBook(String isbn) {
        Optional<Book> opt = findByISBN(isbn);
        if (opt.isPresent()) {
            books.remove(opt.get());
            System.out.println("Book removed: " + isbn);
        } else {
            System.out.println("Book not found: " + isbn);
        }
    }

    // User management
    public void registerUser(User user) {
        if (user == null) return;
        if (users.stream().anyMatch(u -> u.getUserId() == user.getUserId())) {
            System.out.println("User ID already registered.");
            return;
        }
        users.add(user);
        System.out.println("Registered user: " + user.getName());
    }

    public Optional<User> findUserById(int userId) {
        return users.stream().filter(u -> u.getUserId() == userId).findFirst();
    }

    // Search
    public List<Book> searchByTitle(String title) {
        return books.stream()
            .filter(b -> b.getTitle().toLowerCase().contains(title.toLowerCase()))
            .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.stream()
            .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
            .collect(Collectors.toList());
    }

    public Optional<Book> findByISBN(String isbn) {
        return books.stream().filter(b -> b.getISBN().equals(isbn)).findFirst();
    }

    // Issue book: checks availability, updates book and user lists
    public boolean issueBook(String isbn, int userId) {
        Optional<Book> ob = findByISBN(isbn);
        Optional<User> ou = findUserById(userId);

        if (!ob.isPresent()) {
            System.out.println("Issue failed: Book not found.");
            return false;
        }
        if (!ou.isPresent()) {
            System.out.println("Issue failed: User not found.");
            return false;
        }

        Book book = ob.get();
        User user = ou.get();

        if (!book.isAvailable()) {
            System.out.println("Issue failed: Book is already issued.");
            return false;
        }

        // Issue operation
        book.markIssued();
        user.borrowBook(book);
        System.out.println("Issued '" + book.getTitle() + "' to " + user.getName());
        return true;
    }

    // Return book: updates book and user lists
    public boolean returnBook(String isbn, int userId) {
        Optional<Book> ob = findByISBN(isbn);
        Optional<User> ou = findUserById(userId);

        if (!ob.isPresent()) {
            System.out.println("Return failed: Book not found.");
            return false;
        }
        if (!ou.isPresent()) {
            System.out.println("Return failed: User not found.");
            return false;
        }

        Book book = ob.get();
        User user = ou.get();

        if (user.returnBook(book)) {
            book.markReturned();
            System.out.println("Returned '" + book.getTitle() + "' from " + user.getName());
            return true;
        } else {
            System.out.println("Return failed: This user did not borrow that book.");
            return false;
        }
    }

    // Utility: list all books
    public void listAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in library.");
            return;
        }
        System.out.println("Books in Library:");
        for (Book b : books) System.out.println(" - " + b);
    }

    // Utility: list all users
    public void listAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        System.out.println("Registered Users:");
        for (User u : users) System.out.println(" - " + u);
    }
}
