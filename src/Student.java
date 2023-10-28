import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Student implements Serializable {
    private int studentID;
    private String name;
    private String email;
    private String password;
    private double aveMark;
    private String aveGrade;
    private List<Subject> subjects;
    private List<Student> students;

    public Student(String name, String email, String password, List<Student> students) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.students = students;
        this.subjects = new ArrayList<>();
        this.studentID = Integer.parseInt(studentID());
        this.aveMark = 0.0;
        this.aveGrade = null;
    }

    // Generate random studentID, 1 <= studentID <= 999999, unique and formatted as 6-digits width.
    // IDs less than 6-digits width should be completed with zeroes from the left.
    private String studentID() {
        Random r = new Random();
        int studentID = r.nextInt(999999)+1;

        while (alreadyExists(studentID))
            studentID = r.nextInt(999999)+1;
            return String.format("%06d", studentID);
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

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void removeSubject(Subject subject) {
        subjects.remove(subject);
    }

    public double getAveMark() {
        return aveMark;
    }

    public void setAveMark(double aveMark) {
        this.aveMark = aveMark;
    }

    public String getAveGrade() {
        return aveGrade;
    }

    public void setAveGrade(String aveGrade) {
        this.aveGrade = aveGrade;
    }

    public void enrollSubject(Subject selectedSubject) {
    }
}