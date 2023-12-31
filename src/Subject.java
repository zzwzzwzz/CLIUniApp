import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Subject implements Serializable {
    private int subjectID;
    private int mark;
    private String grade;
    private List<Subject> subjects;

    public Subject(List<Subject> subjects) {
        this.subjects = new ArrayList<>();
        this.subjectID = subjectID();
        this.mark = mark();
        this.grade = grade(mark);
    }

    // ID randomly generated 1 <= ID <= 999
    private int subjectID() {
        Random r = new Random();
        int subjectID = r.nextInt(999)+1;

        while (alreadyExists(subjectID))
            subjectID = r.nextInt(999)+1;
        return subjectID;
    }
    
    // To make sure the subjectID is unique
    public boolean alreadyExists(int subjectID) {
        for (Subject subject : subjects) {
            if (subject.subjectID == subjectID)
                return true;
        }
        return false;
    }
    
    private int mark() {
        Random r = new Random();
        // Make sure the random mark is >=25 and <=100
        return r.nextInt(76) + 25; 
    }

    private String grade(int mark) {
        return mark >= 85 ? "HD" : 
               mark >= 75 ? "D" : 
               mark >= 65 ? "C" : 
               mark >= 50 ? "P" :
               "Z";
    }

    public int getSubjectID() {
        return this.subjectID;
    }

    public int getMark() {
        return this.mark;
    }

    public String getGrade() {
        return this.grade;
    }

    // %-16s means 16 spaces
    // %03d subjectIDs are formatted as 3-digits width, less than 3-digits staring with zeroes from the left
    // %3d formating mark as 3 digit integer, %2s formating grade as 2 digit string
    @Override
    public String toString() {
        return String.format("%-16s[ Subject::%03d -- mark =%3d -- grade =  %2s ]", "", subjectID, mark, grade);
    }
}