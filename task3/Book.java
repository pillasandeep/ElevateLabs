// Book.java
public class Book {
    private String title;
    private String author;
    private String ISBN;
    private boolean isAvailable;

    public Book(String title, String author, String ISBN) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.isAvailable = true;
    }

    // Getters / setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getISBN() { return ISBN; }
    public boolean isAvailable() { return isAvailable; }

    // Actions
    public void markIssued() { isAvailable = false; }
    public void markReturned() { isAvailable = true; }

    @Override
    public String toString() {
        return String.format("%s | %s | ISBN:%s | %s",
                             title, author, ISBN, (isAvailable ? "Available" : "Issued"));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Book)) return false;
        Book other = (Book) obj;
        return this.ISBN.equals(other.ISBN);
    }

    @Override
    public int hashCode() {
        return ISBN.hashCode();
    }
}
