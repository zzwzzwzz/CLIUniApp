import java.io.Serializable;

public class Subject implements Serializable {
    private int subjectID;
    private int mark;
    private String grade;

    public Subject(int subjectID, int mark) {
        this.subjectID = subjectID;
        this.mark = mark;
        this.grade = grade(mark);
    }

    private String grade(int mark) {
        return mark >= 85 ? "HD" : 
               mark >= 75 ? "D" : 
               mark >= 65 ? "C" : 
               mark >= 50 ? "P" :
               "Z";
    }

    public boolean match(int subjectID) {
        return this.subjectID == subjectID;
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

    @Override
    public String toString() {
        return String.format("Subject-%03d [%2d --> %2s]",subjectID,mark,grade);
    }
}