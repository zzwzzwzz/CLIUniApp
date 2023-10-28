import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import utils.In;

public class SubjectController {
    private List<Subject> subjects;
    private List<Subject> students;
    private Student loggedInStudent;

    // Text color
    public static final String RESET = "\033[0m";      // RESET
    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String YELLOW = "\033[0;33m";  // YELLOW
    public static final String CYAN = "\033[0;96m";    // CYAN

    public static void main(String[] args) {
        new SubjectController().menu();
    }

    public SubjectController() {
        this.subjects = new ArrayList<>();
    }

    private boolean match(int subjectID) {
        for (Subject subject : subjects) {
            if (subject.match(subjectID))
                return true;
        }
        return false;
    }

    private int subjectID() {
        Random r = new Random();
        int subjectID = r.nextInt(999)+1;

        while (match(subjectID))
            subjectID = r.nextInt(999)+1;
        return subjectID;
    }

    private int mark() {
        Random r = new Random();
        return r.nextInt(76) + 25; // Make sure the random mark is >=25 and <=100
    }
    

    private void change() {
        // System.out.println("\033[93mUpdating Password\033[0m");
        // System.out.print("New Password: ");
        // String newPwd = sc.next();

        // lo6:while (true){
        //     System.out.print("Confirm Password: ");
        //     String confirmPwd = sc.next();
        //     if (!newPwd.matches(passwordPattern)) {
        //         System.out.println("\033[91mIncorrect password format!\033[0m");
        //         break lo6;
        //     }
        //     else if(!newPwd.equals(confirmPwd)){
        //         System.out.println("\033[91mPassword does not match - try again\033[0m");
        //     }
        //     else if (newPwd.equals(confirmPwd) && newPwd.matches(passwordPattern) && confirmPwd.matches(passwordPattern)){
        //         for (int j = 0; j < students.size(); j++) {
        //             if(students.get(j).getEmail().equals(studentEmail)){
        //                 students.get(j).setPassword(newPwd);
        //                 Database.writeObjectsToFile(students);
        //             }
        //         }
        //         System.out.println("\033[93mPassword changed\033[0m");
        //         break lo6;
        //     }
        // }
    }

    public void login(Student student) {
        this.loggedInStudent = student;
    }

    private void enrol() {
        if (students.isEmpty()) {
            System.out.println("No students available for enrollment.");
            return;
        }
    
        if (loggedInStudent == null) {
            System.out.println("You must log in to enroll in subjects.");
        return;
        }
    
        if (loggedInStudent.getSubjects().size() >= 4) {
            System.out.println("You are already enrolled in the maximum number of subjects (4).");
            return;
        }
    
        // Display available subjects
        System.out.println("Available subjects:");
        for (Subject subject : subjects) {
            System.out.println(subject);
        }
    
        // Prompt the student to enter the subject ID they want to enroll in
        System.out.print("Enter the subject ID to enroll: ");
        int subjectID = In.nextInt();
    
        // Find the subject with the specified subjectID
        Subject selectedSubject = findSubjectById(subjectID);
        if (selectedSubject == null) {
            System.out.println("Subject with ID " + subjectID + " does not exist.");
            return;
        }
    
        // Enroll the student in the subject
        loggedInStudent.enrollSubject(selectedSubject);
    
        // Update the student's enrollment in the database (you should implement this)
        // For example, you need to update the student's subjects list in the database
    
        System.out.println("Enrolled in Subject-" + subjectID);
    }
    
    // Helper function to find a subject by ID
    private Subject findSubjectById(int subjectID) {
        for (Subject subject : subjects) {
            if (subject.getSubjectID() == subjectID) {
                return subject;
            }
        }
        return null;
    }

    // private void enrol() {
    //     // for (int i = 0; i < 4; i++) {
    //     //         int subjectNo = students.get(i).getSubjects().size()+1;
    //     //         if(subjectNo <=4){
    //     //             students.get(i).enrollSubject();
    //     //             Database.writeStudents(students);
    //     //             System.out.println("\033[93mEnrolling in Subject-"+students.get(i).getSubjects().get(subjectNo-1).getSubjectID()+"\033[0m");
    //     //             System.out.println("\033[93mYou are now  enrolled in "+subjectNo+" out of 4 subjects\033[0m");
    //     //         }else {
    //     //             System.out.println("\033[91mStudents are allowed to enrol in 4 subjects only\033[0m");
    //     //     }
    //     // }
        
    //     // subjects.add(new Subject(subjectID(), mark()));
    // }

    private void remove() {
        // System.out.printf("Remove Subject By ID: ");
        // int removeSubjectID = sc.nextInt();
        // boolean subjectFound = false;
        // for (int j = 0; j < students.size(); j++) {
        //     if (students.get(j).getEmail().equals(studentEmail)) {
        //         List<Subject> subjects = students.get(j).getSubjects();
        //         Iterator<Subject> iterator = subjects.iterator();

        //         while (iterator.hasNext()) {
        //             Subject subject = iterator.next();
        //             if (removeSubjectID == subject.getSubjectID()) {
        //                 // 找到匹配的课程记录，使用迭代器删除它
        //                 iterator.remove();
        //                 Database.writeObjectsToFile(students);
        //                 System.out.println("\033[93mDropping Subject-"+removeSubjectID+"\033[0m");
        //                 System.out.println("\033[93mYou are now enrolled in "+students.get(j).getSubjects().size()+" out of 4 subjects\033[0m");
        //                 subjectFound = true;
        //                 break;
        //             }
        //         }
        //     }
        // }
        // if(!subjectFound){
        //     System.out.println("\033[91mSubject ID " + removeSubjectID + " not found\033[0m");
        // }
    }

    private void show() {
        for (Subject subject : subjects) {
            System.out.println(subject);
        }
        // for (int j = 0; j < students.size(); j++) {
        //     if(students.get(j).getEmail().equals(studentEmail)){
        //         int countSubject = students.get(j).getSubjects().size();
        //         System.out.println("\033[93mShowing "+countSubject+" subjects\033[0m");
        //         for (Subject subject : students.get(j).getSubjects()) {
        //             System.out.println("[ Subject::"+subject.getSubjectID()+" -- mark = "+subject.getMark()+" -- grade ="+subject.getGrade()+" ]");
        //         }
        //     }
        // }
    }

    private char readMenu() {
        System.out.printf(CYAN + "%-16sStudent Course Menu (c/e/r/s/x): " + RESET,"");
        return In.nextChar();
    }

    private void menu() {
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
