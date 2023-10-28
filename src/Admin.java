import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.io.Serializable;

public class Admin implements Serializable {
    private Database database;
    private Scanner scanner;
    private List<Student> students;

    // Text color
    public static final String RESET = "\033[0m";      // RESET
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;96m";    // CYAN

    public static void main(String[] args) {
        Database database = new Database(); // Create a Database instance
        Scanner scanner = new Scanner(System.in); // Create a Scanner for user input
        // Create an Admin instance with the Database and Scanner
        new Admin(database, scanner).adminMenu();
    }

    public Admin(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    // Clear all the students from database
    private void clearAll() {
        System.out.printf(YELLOW + "%-8sClearing students database" + RESET,"").println();
        System.out.printf(RED + "%-8sAre you sure you want to clear the database (Y)ES/(N)O: " + RESET,"");
        
        String clearStudent = scanner.hasNext() ? scanner.next().toUpperCase() : "N"; // Make sure the input is uppercase letter
        // Clear all data if the input is Y
        if (clearStudent.equals("Y")){
            // Only clear the list if it's not null
            if (students != null) {
                students.clear(); 
            }
            Database.clearAll(); 
            System.out.printf(YELLOW + "%-8sStudents data cleared" + RESET,"").println();
        }
    }

    private void groupbyGrade() {
        Map<String, List<Student>> groups = new HashMap<>();
        // This groupingBy function will organize based on the grade that the student in the list who has the grade
        groups = students.stream().collect(Collectors.groupingBy(Student :: getAveGrade));
        groups.forEach((k,v) -> System.out.printf("%4s --> %-12s %n", k, v));
    }

    private void partition() {
        Map<Boolean, List<Student>> partitioned = new HashMap<>();
        partitioned = students.stream().collect(Collectors.partitioningBy(s -> s.getAveMark() >= 50));
        // Print the map
        partitioned.forEach((k,v) -> System.out.printf("%4s --> %-12s %n", k ? "PASS" : "FAIL", v));
    }

    private void removeStudent() {
        System.out.printf("%-8sRemove by ID: ","");
        int studentID = Integer.parseInt(scanner.nextLine());
        boolean removed = database.removeStudent(studentID);
        if (removed) {
            System.out.printf(YELLOW + "%-8sRemoving student " + studentID + " Account" + RESET,"").println();
        } else {
            System.out.printf(RED + "%-8sStudent " + studentID + " does not exist","").println();
        }
    }

    private void showStudents() {
        List<Student> students = database.readStudents();
        System.out.printf(YELLOW + "%-8sStudent List" + RESET,"").println();
        if (students.isEmpty()) {
            System.out.printf("%-16s< Nothing to Display >","").println();
        } else {
            students.forEach(student -> System.out.println(student.toString()));
        }
    }

    public void adminMenu() {
        char choice;
        do {
            System.out.printf(CYAN + "%-8sAdmin System (c/g/p/r/s/x): " + RESET,"");
            choice = scanner.nextLine().charAt(0);
            switch (choice) {
                case 'c':
                    clearAll();
                    break;
                case 'g':
                    groupbyGrade();
                    break;
                case 'p':
                    partition();
                    break;
                case 'r':
                    removeStudent();
                    break;
                case 's':
                    showStudents();
                    break;
                case 'x':
                    break;
                default:
                    System.out.printf(RED + "%-8sInvalid choice. Please try again." + RESET,"").println();
            }
        } while (choice != 'x');
    }

}