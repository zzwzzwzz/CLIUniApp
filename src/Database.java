import java.io.*;
import java.util.List;

public class Database {
    private static String filename = "students.data";

    public static boolean fileExists() {
        // Check if the file "students.data" exists
    }

    public static void createFile() {
        // Create the file "students.data" if it doesn't exist
    }

    public static List<Student> readStudents() {
        // Read objects (students) from the file "students.data"
    }

    public static void writeStudents(List<Student> students) {
        // Write objects (students) to the file "students.data"
    }

    public static void clearData() {
        // Clear the objects from the file "students.data"
    }
}