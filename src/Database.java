import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final String filename = "students.data";

    public static boolean fileExists() {
        File file = new File(filename);
        return file.exists();
    }

    public void initialize() {
        if (!fileExists()) {
            try {
                File file = new File(filename);
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

    public static void clearAll() {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}