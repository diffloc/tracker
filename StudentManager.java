package tracker;

import java.util.*;

public class StudentManager {
    private final Set<Student> students;
    private final Map<Course, Set<Integer>> courseEnrollments = new HashMap<>();
    private final Map<Course, Integer> courseActivityCounters = new HashMap<>();
    private final Map<Course, Integer> courseTotalPoints = new HashMap<>();
    private final Map<Course, Integer> courseSubmissionCounts = new HashMap<>();
    private int nextStudentID;

    public StudentManager() {
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

    public void updateStudentPoints(int studentID, Map<Course, Integer> coursePoints) {
        Student student = getStudentByID(studentID);
        if (student != null) {
            for (Map.Entry<Course, Integer> entry : coursePoints.entrySet()) {
                Course course = entry.getKey();
                int newPoints = entry.getValue();
                int existingPoints = student.getCoursePoints(course);
                int totalPoints = existingPoints + newPoints;

                if (!student.isCourseCompleted(course) && totalPoints >= course.getTotalPointsToPass()) {
                    student.completeCourse(course);  // Mark the course as completed
                }

                if (existingPoints == 0 && newPoints > 0) {
                    enrollStudentInCourse(studentID, course);
                }
                if (newPoints > 0) {
                    incrementActivityCounter(course);
                }
                student.addCoursePoints(course, newPoints);
                updateCourseStats(course, newPoints);
            }
        }
    }

    private void enrollStudentInCourse(int studentID, Course course) {
        courseEnrollments.computeIfAbsent(course, k -> new HashSet<>()).add(studentID);
    }

    public int getEnrollmentCount(Course course) {
        return courseEnrollments.getOrDefault(course, Collections.emptySet()).size();
    }

    private void incrementActivityCounter(Course course) {
        courseActivityCounters.merge(course, 1, Integer::sum);
    }

    public int getActivityCount(Course course) {
        return courseActivityCounters.getOrDefault(course, 0);
    }

    private void updateCourseStats(Course course, int points) {
        if (points > 0) {
            courseTotalPoints.merge(course, points, Integer::sum);
            courseSubmissionCounts.merge(course, 1, Integer::sum);
        }
    }

    public double getAveragePoints(Course course) {
        if (!courseSubmissionCounts.containsKey(course) || courseSubmissionCounts.get(course) == 0) {
            return 0;
        }
        return (double) courseTotalPoints.getOrDefault(course, 0) / courseSubmissionCounts.get(course);
    }
}
