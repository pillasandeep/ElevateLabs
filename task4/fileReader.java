import java.io.*;
import java.util.Scanner;

public class NotesApp {

    private static final String FILE_NAME = "notes.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Notes App ---");
            System.out.println("1. Add a Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addNote(scanner);
                case 2 -> viewNotes();
                case 3 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 3);

        scanner.close();
    }

    // Method to add note
    private static void addNote(Scanner scanner) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // append = true
            System.out.print("Enter your note: ");
            String note = scanner.nextLine();
            writer.write(note + System.lineSeparator());
            System.out.println("✅ Note saved successfully!");
        } catch (IOException e) {
            System.out.println("⚠ Error writing to file: " + e.getMessage());
        }
    }

    // Method to read notes
    private static void viewNotes() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            System.out.println("\n--- Saved Notes ---");
            while ((line = reader.readLine()) != null) {
                System.out.println("- " + line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("⚠ No notes found. Add a note first!");
        } catch (IOException e) {
            System.out.println("⚠ Error reading file: " + e.getMessage());
        }
    }
}
