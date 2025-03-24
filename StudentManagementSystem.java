import java.io.*;
import java.util.*;

class Student {
    String id, name, course;
    int age;

    public Student(String id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + course;
    }
}

public class StudentManagementSystem {
    static final String FILE_NAME = "students.txt";
    static List<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadStudents();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    saveStudents();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void addStudent(Scanner scanner) {
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course: ");
        String course = scanner.nextLine();

        students.add(new Student(id, name, age, course));
        saveStudents();
        System.out.println("Student added successfully!");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("\nStudent Records:");
            for (Student s : students) {
                System.out.println(s.id + " - " + s.name + " (Age: " + s.age + ", Course: " + s.course + ")");
            }
        }
    }

    static void deleteStudent(Scanner scanner) {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine();
        students.removeIf(s -> s.id.equals(id));
        saveStudents();
        System.out.println("Student deleted successfully!");
    }

    static void loadStudents() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                students.add(new Student(data[0], data[1], Integer.parseInt(data[2]), data[3]));
            }
        } catch (IOException e) {
            System.out.println("No previous records found.");
        }
    }

    static void saveStudents() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student data.");
        }
    }
}
