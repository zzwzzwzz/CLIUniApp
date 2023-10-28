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
        File file = new File(filename);

        if (file.exists() && file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
                while (true) {
                    try {
                        Student student = (Student) ois.readObject();
                        students.add(student);
                    } catch (EOFException e) {
                        break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    public static void writeStudents(List<Student> students) {
        try (ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream(filename))) {
            for (Student student : students) {
                objectOut.writeObject(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearAll() {
        try (PrintWriter writer = new PrintWriter(filename)) {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean removeStudent(int studentID) {
        return false;
    }
    public Student[] readObjects() {
        return null;
    }

    public void writeObject(Student student) {
    }

}