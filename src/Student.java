import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private int studentID;
    private String name;
    private String email;
    private String password;
    private List<Subject> subjects;

    public Student(int studentID, String name, String email, String password) {
        this.studentID = studentID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.subjects = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    // Implement additional methods as needed for student behaviors
}