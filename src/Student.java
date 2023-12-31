import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student implements Serializable {
    // Use our own serialVersionUID instead of automated generated UID
    // This one is actually a bug solution
    private static final long serialVersionUID = -190677127981620175L;

    private int studentID;
    private String name;
    private String email;
    private String password;
    private List<Student> students;
    private List<Subject> subjects;

    public Student(String name, String email, String password, List<Student> students) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.students = students;
        this.subjects = new ArrayList<>();
        this.studentID = studentID();
    }

    // Generate random studentID, 1 <= studentID <= 999999, unique and formatted as 6-digits width.
    // IDs less than 6-digits width should be completed with zeroes from the left.
    private int studentID() {
        Random r = new Random();
        int studentID = r.nextInt(999999)+1;

        while (alreadyExists(studentID))
            studentID = r.nextInt(999999)+1;
        return studentID;
    }

    public boolean alreadyExists(int studentID) {
        for (Student student : students) {
            if (student.studentID == studentID)
                return true;
        }
        return false;
    }

    public int getStudentID() {
        return this.studentID;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Subject> getSubjects() {
        return this.subjects;
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    @Override
    public String toString() {
        return String.format("%-8s%-10s :: %06d --> Email: %s ", "", name, studentID, email);
    }
}