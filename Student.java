package tracker;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Student {
    private String firstName;
    private String lastName;
    private String emailAddress;
    private int studentID;
    private EnumMap<Course, Integer> coursePoints;

    public Student(String firstName, String lastName, String emailAddress) {

        // Validates substring contents for valid and required characters
        this.firstName = validateFirstName(firstName);
        this.lastName = validateLastName(lastName);
        this.emailAddress = validateEmail(emailAddress);
        this.coursePoints = new EnumMap<>(Course.class);
    }

    private String validateFirstName(String firstName) {
        // Validate the first name using the specified rules
        // Only allow A-Z, a-z, ', and -
        String pattern = "^[A-Za-z'\\-]+$";
        if (!firstName.matches(pattern)) {
            throw new IllegalArgumentException("Incorrect first name.");
        }
        return firstName;
    }

    private String validateLastName(String lastName) {
        // Validate the last name using the specified rules
        // Only allow A-Z, a-z, ', and -
        String pattern = "^[A-Za-z'\\- ]+$";
        if (!lastName.matches(pattern)) {
            throw new IllegalArgumentException("Incorrect last name.");
        }
        return lastName;
    }

    private String validateEmail(String email) {
        // Validate the email using a regex pattern
        // Replace this pattern with a more comprehensive email validation pattern if needed
        String pattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.matches(pattern)) {
            throw new IllegalArgumentException("Incorrect email.");
        }
        return email.toLowerCase();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getCoursePointsString() {
        StringBuilder pointsString = new StringBuilder();

        for (Course course : Course.values()) {
            Integer points = coursePoints.getOrDefault(course, 0);
            pointsString.append(course).append("=").append(points).append("; ");
        }

        // Remove the trailing semicolon and space
        if (pointsString.length() > 0) {
            pointsString.setLength(pointsString.length() - 2);
        }

        return pointsString.toString();
    }

    // Method to add or update points for a course
    public void addCoursePoints(Map<Course, Integer> coursePoints) {
        for (Map.Entry<Course, Integer> entry : coursePoints.entrySet()) {
            Course course = entry.getKey();
            int points = entry.getValue();
            int existingPoints = this.coursePoints.getOrDefault(course, 0);
            this.coursePoints.put(course, existingPoints + points);
        }
    }

    // Method to get the points for a specific course
    public int getCoursePoints(Course course) {
        return coursePoints.getOrDefault(course, 0);
    }

    @Override
    public String toString() {
        return  getStudentID() + " " + getFirstName() + " " + getLastName() + " " + getEmailAddress();
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailAddress);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        return Objects.equals(emailAddress, other.emailAddress);
    }
}
