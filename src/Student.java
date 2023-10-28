import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student implements Serializable {
    private int studentID;
    private String name;
    private String email;
    private String password;
    private List<Subject> subjects;
    private List<Student> students;

    public Student(int studentID, String name, String email, String password) {
        this.studentID = studentID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.subjects = new ArrayList<>();
    }

    private int studentID() {
        Random r = new Random();
        int studentID = r.nextInt(999999)+1;

        while (match(studentID))
            studentID = r.nextInt(999999)+1;
        return studentID;
    }

    public boolean match(int studentID) {
        for (Student student : students) {
            if (student.match(studentID))
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

    public List<Subject> getSubjects() {
        return this.subjects;
    }

}