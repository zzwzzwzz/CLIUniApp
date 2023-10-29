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

    public Admin(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    // Clear all the students from database
    private void clearAll() {
        System.out.printf(YELLOW + "%-8sClearing students database" + RESET,"").println();
        System.out.printf(RED + "%-8sAre you sure you want to clear the database (Y)ES/(N)O: " + RESET,"");
        
        // Make sure the input is uppercase letter, to read a single character
        String clearStudent = scanner.hasNext() ? 
            scanner.nextLine().substring(0,1).toUpperCase() : 
            "N"; 
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

    private static void groupbyGrade( ) {
        Database db = new Database();
        List<Student> students=db.readStudents();
        System.out.printf(YELLOW + "%-8sGrade Grouping" + RESET,"").println();;
        if (students.size()<=0)
        {
            System.out.printf("%-16s< Nothing to display >","").println();;
            return;
        }
        int x,y,z,k,h;
        x=0;
        y=0;
        z=0;
        k=0;
        h=0;
        for (Student student:students)
        {
            if(student.getSubjects()==null)
            {
                x++;
            }
            else
            {
                double t=0;
                for (Subject subject:student.getSubjects()) {
                        t+=subject.getMark();
                }
                if(t/student.getSubjects().size()>=85)
                {
                    y++;
                }
                else if(t/student.getSubjects().size()>=75)
                {
                    z++;
                }
                else if(t/student.getSubjects().size()>65)
                {
                    k++;
                }
                else if(t/student.getSubjects().size()>50)
                {
                    h++;
                }
            }
        }
        if(x>0)
        {
            System.out.printf("%-8sZ  --> [","");
            boolean flag=true;
            for (Student student:students)
            {
                double t=0;

                if(student.getSubjects()==null)
                {
                    if(flag)
                    {
                        System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + "0.00");
                        flag=false;
                    }
                    else
                    {
                        System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + "0.00");
                    }
                }
                else {

                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if (t / student.getSubjects().size() < 50) {
                        if(flag)
                        {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                            flag=false;
                        }
                        else
                        {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        }
                     }
                }
            }
            System.out.println("]");
        }
        if(y>0)
        {
            boolean flag=true;
            System.out.printf("%-8sHD  --> [","");
            for (Student student:students)
            {
                double t=0;

                if(student.getSubjects()==null)
                {

                }
                else {

                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if (t / student.getSubjects().size() >=85) {
                        if(flag)
                        {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  HD - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                            flag=false;
                        }
                        else
                        {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  HD - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        }  
                    }
                }
            }
            System.out.println("]");
        }
        if(z>0)
        {
            boolean flag=true;
            System.out.printf("%-8sD  --> [","");
            for (Student student:students)
            {
                double t=0;
                if(student.getSubjects()==null)
                {
                }
                else {
                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if (t / student.getSubjects().size() >=75) {
                        if(flag)
                        {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  D - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                            flag=false;
                        }
                        else
                        {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + String.format("%.2f",t / student.getSubjects().size()) );
                        }
                    }
                }
            }
            System.out.println("]");
        }
        if(k>0)
        {
            boolean flag=true;
            System.out.printf("%-8sC  --> [","");
            for (Student student:students)
            {
                double t=0;
                if(student.getSubjects()==null)
                {
                }
                else {
                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if (t / student.getSubjects().size() >=65) {
                        if(flag)
                        {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  C - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                            flag=false;
                        }
                        else
                        {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  C - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        }
                    }
                }
            }
            System.out.println("]");
        }

        if(h>0)
        {
            boolean flag=true;
            System.out.printf("%-8sP  --> [","");
            for (Student student:students)
            {
                double t=0;
                if(student.getSubjects()==null)
                {
                }
                else {
                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if (t / student.getSubjects().size() >=50) {
                        if(flag)
                        {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  P - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                            flag=false;
                        }
                        else
                        {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  P - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        } 
                    }
                }
            }
            System.out.println("]");
        }
    }

    private static void partition() {
        Database db = new Database();
        List<Student> students=db.readStudents();
        System.out.printf(YELLOW + "%-8sPASS/FALL Partition" + RESET,"").println();
        if (students.size()<=0)
        {
            System.out.printf("%-8sFAIL --> []","").println();
            System.out.printf("%-8sPASS --> []","").println();
            return;
        }
        int x,y;
        x=0;
        y=0;
        for (Student student:students)
        {
            if(student.getSubjects()==null)
            {
                x++;
            }
            else
            {
                double t=0;
                for (Subject subject:student.getSubjects()) {
                    t+=subject.getMark();
                }
                if(t/student.getSubjects().size()>=50)
                {
                    y++;
                }
                else
                {
                    x++;
                }
            }
        }
        if(x>0)
        {
            boolean flag=true;
            System.out.printf("%-8sFALL  --> [","");
            for (Student student:students)
            {
                double t = 0;

                if(student.getSubjects()==null)
                {
                    if(flag)
                    {
                        System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + "0.00");
                        flag=false;
                    }
                    else
                    {
                        System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + "0.00");
                    }
                }
                else {
                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if (t / student.getSubjects().size() < 50)
                    {
                        if(flag)
                        {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );                            
                            flag=false;
                        }
                        else
                        {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  Z - MARK: " + String.format("%.2f", t / student.getSubjects().size())) ;
                        }
                    }
                }
            }
            System.out.println("]");
        }
        if(y>0)
        {
            boolean flag=true;
            System.out.printf("%-8sPASS  --> [","");
            for (Student student:students)
            {
                double t=0;
                if(student.getSubjects()==null)
                {
                }
                else
                {
                    for (Subject subject : student.getSubjects()) {
                        t += subject.getMark();
                    }
                    if(flag)
                    {
                        if (t / student.getSubjects().size() >= 85) {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  HD - MARK: " + String.format("%.2f", t / student.getSubjects().size()));
                        } else if (t / student.getSubjects().size() >= 75) {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  D - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        } else if (t / student.getSubjects().size() >= 65) {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  C - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        } else if (t / student.getSubjects().size() >= 50) {
                            System.out.print(student.getName() + " :: " + student.getStudentID() + " --> GRADE:  P - MARK: " + String.format("%.2f", t / student.getSubjects().size()));
                        }
                        flag=false;
                    }
                    else
                    {
                        if (t / student.getSubjects().size() >= 85) {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  HD - MARK: " + String.format("%.2f", t / student.getSubjects().size()));
                        } else if (t / student.getSubjects().size() >= 75) {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  D - MARK: " + String.format("%.2f", t / student.getSubjects().size()));
                        } else if (t / student.getSubjects().size() >= 65) {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  C - MARK: " + String.format("%.2f", t / student.getSubjects().size()) );
                        } else if (t / student.getSubjects().size() >= 50) {
                            System.out.print(", "+student.getName() + " :: " + student.getStudentID() + " --> GRADE:  P - MARK: " + String.format("%.2f", t / student.getSubjects().size()));
                        }
                    }
                }
            }
            System.out.println("]");
        }
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
            // forEach iterates through each student in the students collection
            // For each student, it calls the toString method to print a string of the student to the console
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