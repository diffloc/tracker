package tracker;

import java.util.*;

public class StudentManager {
    // private final List<Student> students;
    private final Set<Student> students;
    private int nextStudentID;

    public StudentManager() {
        // this.students = new ArrayList<>();
        this.students = new LinkedHashSet<>();
        this.nextStudentID = 10000;
    }

    public boolean addStudent(Student student) {
        if (students.stream().anyMatch(s -> s.getEmailAddress().equalsIgnoreCase(student.getEmailAddress()))) {
            OutputPrinter.printEmailTaken();
            return false;
        } else {
            student.setStudentID(nextStudentID++);
            students.add(student);
            return true;
        }
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void outputStudents() {
        OutputPrinter.printStudentList(students);
    }

    public Student getStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null; // Return null if no student is found with the specified ID
    }

    public void outputStudentIDs() {
        OutputPrinter.printStudentIDs(students);
    }

    // Method to add or update course points for a student
    // public void addPoints(Student student, Course course, int points) {
    //     student.addCoursePoints(course, points);
    // }

    // TODO: Add additional methods for managing students, such as retrieving, updating, or deleting students
}
