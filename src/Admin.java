import java.util.List;
import java.util.Scanner;
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
        
        String clearStudent = scanner.next().toUpperCase(); // Make sure the input is uppercase letter
        // Clear all data if the input is Y
        if (clearStudent.equals("Y")){
            Database.clearAll();
            students.clear(); 
            System.out.printf(YELLOW + "%-8sStudents data cleared" + RESET,"").println();
        }
    }

    private void groupbyGrade() {
        // Implement the logic to group students by grade and display the results
        // You can retrieve the list of students from the database
    }

    private void partition() {
        // Implement the logic to partition students into pass/fail categories and display the results
        // You can retrieve the list of students from the database
    }

    private void removeStudent() {
        System.out.print("Enter the student's ID to remove: ");
        int studentID = Integer.parseInt(scanner.nextLine());
        boolean removed = database.removeStudent(studentID);
        if (removed) {
            System.out.println("Student with ID " + studentID + " has been removed.");
        } else {
            System.out.println("Student with ID " + studentID + " not found.");
        }
    }

    private void showStudents() {
        List<Student> students = database.readStudents();
        System.out.printf(YELLOW + "%-8sStudent List" + RESET,"").println();
        if (students.isEmpty()) {
            System.out.printf("%-16s< Nothing to Display >","").println();
        } else {
            students.forEach(student -> System.out.println(student.toString()));
            // for (int i = 0; i < students.size(); i++) {//遍历students数组,size()方法返回students数组的长度,i++每次循环加1
            //     System.out.println(students.get(i).getName()+" :: "+students.get(i).getStudentID()+" --> Email: "+students.get(i).getEmail());
            // }//打印出学生的名字/ID/email 通过get（）方法获取students数组里的元素
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