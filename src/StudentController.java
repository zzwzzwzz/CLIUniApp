import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StudentController {
    private Database database;
    private List<Student> students;
    private String name;

    // Text color
    public static final String RESET = "\033[0m";      // RESET
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;96m";    // CYAN

    public static void main(String[] args) {
        StudentController controller = new StudentController(null);
        controller.menu();
    }

    public StudentController(Scanner scanner) {
        database = new Database();
        database.initialize();
        students = database.readStudents();
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.printf(CYAN + "%-8sStudent System (l/r/x): " + RESET,"");
            String choice = scanner.nextLine().trim();

            // choice.toLowerCase() make sure the input choice is valid lowercase
            switch (choice.toLowerCase()) {
                case "l":
                    login(scanner);
                    break;
                case "r":
                    register(scanner);
                    break;
                case "x":
                    exit = true;
                    break;
                default:
                    System.out.printf(RED + "%-8sInvalid choice. Please try again." + RESET,"").println();
            }
        }
    }

    public boolean login(Scanner scanner) {
        System.out.printf(GREEN + "%-8sStudent Sign In" + RESET,"").println();

        // Input email and password for login
        System.out.printf("%-8sEmail: ","");
        String email = scanner.nextLine();
    
        System.out.printf("%-8sPassword: ","");
        String password = scanner.nextLine();
    
        List<Student> students = database.readStudents();
        boolean loggedIn = false;
    
        for (Student student : students) {
            if (student.getEmail().equals(email) && student.getPassword().equals(password)) {
                // Student is found and the provided email and password match
                loggedIn = true;
                System.out.printf(YELLOW + "%-8sWelcome, %s!" + RESET, student.getName()).println();
                break; // Successful login, exit the loop
            }
        }
    
        if (!loggedIn) {
            System.out.printf(RED + "%-8sIncorrect email or password." + RESET,"").println();
        }
        
        return loggedIn;
    }

    public void register(Scanner scanner) {

        System.out.printf(GREEN + "%-8sStudent Sign Up" + RESET,"").println();

        while (true) {
            // Email and password input from the registering student
            System.out.printf("%-8sEmail: ","");
            String email = scanner.nextLine();
    
            System.out.printf("%-8sPassword: ","");
            String password = scanner.nextLine();
    
            // To check if both email and password are the right format
            if (isValidEmail(email) && isValidPassword(password)) {
                // To check if the student with the email already exists
                boolean exists = false;
                for (Student student : students) {
                    if (student.getEmail().equals(email)) {
                        exists = true;
                        System.out.printf(RED + "Student" + ((Student) students).getName() + "already exists" + RESET, "").println();
                        break;
                    }
                }

                // /* Check if the entered email already exists */
                // boolean exists = Emailcontains(email);
                // if (exists) {
                //     Student[] students = database.readObjects();
                //     /* If a duplicate student is found */
                //     int index = getEmailIndex(email);
                //     /* Create a container to store data of the duplicate student found */
                //     Student stu2 = students[index];
                //     System.out.println("Student " + stu2.getName() + " already exists");
                //     continue;
                // }

                // If the students doesn't exist, create a new student to the database
                if (!exists) {

                    // Input name from the registering students
                    System.out.printf("%-8sName: ", "");
                    String name = scanner.nextLine();
                    System.out.printf(YELLOW + "%-8sEnrolling student %s" + RESET, "", name).println();

                    // Create the new student and add them to the database
                    Student newStudent = new Student(name, email, password, students);
                    students.add(newStudent);
                    Database.writeStudents(students);
                }
                break;

            } else {
                System.out.printf(RED + "%-8sIncorrect email or password format" + RESET,"").println();
            }
        }
    }

    // // Implement this method to find the index of the student with the given email.
    // private int getEmailIndex(String email) {
    //     return 0;
    // }

    // /* Check if this student exists based on the email */
    // public static boolean Emailcontains(String inputEmail) {
    //     /* Check if this student's email exists */
    //     Database db = new Database();
    //     Student[] students = db.readObjects();
    //     for (Student s : students) {
    //         String email = s.getEmail();
    //         if (email.equals(inputEmail)) {
    //             return true; // Found a duplicate email
    //         }
    //     }
    //     return false; // Not found, return false
    // }


    // Define email verification method
    // Email must have . before @ and must end with university.com
    public static final String EMAIL_REGEX = "^[A-Za-z]+\\.[A-Za-z]+@university\\.com$"; 
    public static boolean isValidEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    /**
     * Define password verification methods
     * 
     * Password must start with an uppercase letter, 
     * have a minimum length of 6 characters, 
     * and be followed by at least 3 digits.
     */
    public static final String PASSWORD_REGEX = "^[A-Z][A-Za-z]{6,}[0-9]{3,}$"; 
    public static boolean isValidPassword(String password) {
        return password.matches(PASSWORD_REGEX);
    }
}