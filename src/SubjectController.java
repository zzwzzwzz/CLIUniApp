import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.In;

public class SubjectController {
    private Database database;
    private Scanner scanner;
    private List<Subject> subjects;
    private String email;
    private String password;
    private Student loggedInStudent; // This variable is to keep track of the logged-in student
    
    // Text color
    public static final String RESET = "\033[0m";      // RESET
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;96m";    // CYAN

    public SubjectController(Database database, Scanner scanner, Student student) {
        this.database = database;
        this.scanner = scanner;
        this.subjects = student.getSubjects();
        this.loggedInStudent = student; // Initialize
    }

    private void change() {

        // Need to writeStudents into the database
    }

    private void enrol() {
        // To check if enrolled subjects are less than 4
        if (subjects.size() < 4) {
            Subject subject = new Subject(subjects);
            System.out.printf(YELLOW + "%-16sEnrolling in subject-" + subject.getSubjectID(), "").println();
            subjects.add(subject);
            System.out.printf("%-16sYou are now enrolled in " + subjects.size() + " out of 4 subjects" + RESET, "").println();
        } else {
            System.out.printf(RED + "%-16sStudents are allowed to enrolled in 4 subjects only" + RESET, "").println();
        }
        database.replaceStudent(loggedInStudent);
    }

    private void remove() {
        System.out.printf("%-16sRemove Subject by ID: ","");
        int subjectID = Integer.parseInt(scanner.nextLine());
        boolean removed = subjects.removeIf(subject -> subject.getSubjectID() == subjectID);

        if (removed) {
            System.out.printf(YELLOW + "%-16sDropping subject-" + subjectID, "").println();
            System.out.printf("%-16sYou are now enrolled in " + subjects.size() + " out of 4 subjects" + RESET, "").println();
        } else {
            System.out.printf(RED + "%-16sInvaid subject ID " + subjectID + RESET, "").println();
        }
        database.replaceStudent(loggedInStudent);
    }

    private void show() {
        System.out.printf(YELLOW + "%-16sShowing %d subjects" + RESET, "", subjects.size()).println();
        
        for (Subject subject : subjects) {
            System.out.println(subject.toString());
        }
    }

    private char readMenu() {
        System.out.printf(CYAN + "%-16sStudent Course Menu (c/e/r/s/x): " + RESET,"");
        return In.nextChar();
    }

    public void menu() {
        char c;
        while ((c = readMenu()) != 'x') {
            switch (c) {
                case 'c':
                    change();
                    break;
                case 'e':
                    enrol();
                    break;
                case 'r':
                    remove();
                    break;
                case 's':
                    show();
                    break;
                default:
                    System.out.printf(RED + "%-16sInvalid choice. Please try again." + RESET,"").println();
            }
        }
    }

}
