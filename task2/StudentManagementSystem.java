import java.util.*;

public class StudentManagementSystem{
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Student Record Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume newline
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks = sc.nextDouble();

                    students.add(new Student(id, name, marks));
                    System.out.println("✅ Student added successfully!");
                    break;

                case 2:
                    // View Students
                    System.out.println("\n--- Student Records ---");
                    if (students.isEmpty()) {
                        System.out.println("No records found.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    // Update Student
                    System.out.print("Enter ID of student to update: ");
                    int updateId = sc.nextInt();
                    boolean found = false;
                    for (Student s : students) {
                        if (s.id == updateId) {
                            sc.nextLine();
                            System.out.print("Enter new name: ");
                            s.name = sc.nextLine();
                            System.out.print("Enter new marks: ");
                            s.marks = sc.nextDouble();
                            System.out.println("✅ Record updated!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Student not found!");
                    }
                    break;

                case 4:
                    // Delete Student
                    System.out.print("Enter ID of student to delete: ");
                    int deleteId = sc.nextInt();
                    found = false;
                    Iterator<Student> iterator = students.iterator();
                    while (iterator.hasNext()) {
                        Student s = iterator.next();
                        if (s.id == deleteId) {
                            iterator.remove();
                            System.out.println("✅ Student deleted!");
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        System.out.println("❌ Student not found!");
                    }
                    break;

                case 5:
                    // Exit
                    System.out.println("Exiting... 👋");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

