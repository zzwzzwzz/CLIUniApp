import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private int ID;
    private String name;
    private String email;
    private String password;
    private List<Subject> enrolledSubjects;

    public Student(int ID, String name, String email, String password) {
        // Initialize fields
    }

    // Constructors, getters, setters, and methods for user stories 101, 201, 202, 203, 301, 601, 602, and 603
}