import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final String filename = "students.data";

    public void initialize() {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                List<Student> students = new ArrayList<>();
                writeStudents(students);
            } catch (IOException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Error creating file", ex);
            }
        }
    }

    public List<Student> readStudents() {
        List<Student> students = new ArrayList<>();
        try (ObjectInputStream objectIn = new ObjectInputStream(new FileInputStream(filename))) {
            students = (List<Student>) objectIn.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "File Not Found", ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Reading Error", ex);
        }
        return students;
    }

    public void writeStudents(List<Student> students) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filename))) {
            objectOut.writeObject(students);
        } catch (IOException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, "Writing Error", ex);
        }
    }

    public boolean removeStudent(int studentID) {
        return false;
    }
}

// import java.io.*;
// import java.util.List;

// public class Database {
//     private static String filename = "students.data";

//     public static boolean fileExists() {
//         // Check if the file "students.data" exists
//     }

//     public static void createFile() {
//         // Create the file "students.data" if it doesn't exist
//     }

//     public static List<Student> readStudents() {
//         // Read objects (students) from the file "students.data"
//     }

//     public static void writeStudents(List<Student> students) {
//         // Write objects (students) to the file "students.data"
//     }

//     public static void clearData() {
//         // Clear the objects from the file "students.data"
//     }
// }