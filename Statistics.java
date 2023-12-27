package tracker;

import java.util.*;
import java.util.stream.Collectors;

public class Statistics {
    private final StudentManager studentManager;

    public Statistics(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public String getCourseDetails(Course course) {
        StringBuilder details = new StringBuilder();
        details.append(course.getDisplayName()).append("\n");
        details.append("id\t\tpoints\tcompleted\n");

        for (Student student : studentManager.getStudents()) {
            int points = student.getCoursePoints(course);
            if (points > 0) {
                double completionPercentage = 100.0 * points / course.getTotalPointsToPass();
                details.append(student.getStudentID())
                        .append("\t\t")
                        .append(points)
                        .append("\t")
                        .append(String.format("%.1f", completionPercentage))
                        .append("%\n");
            }
        }
        return details.toString();
    }

    // Method to calculate the most popular course
    public String calculateMostPopularCourse() {
        List<Course> mostPopularCourses = new ArrayList<>();
        int maxEnrolled = 0;

        for (Course course : Course.values()) {
            int enrolled = studentManager.getEnrollmentCount(course);
            if (enrolled > maxEnrolled) {
                mostPopularCourses.clear();
                mostPopularCourses.add(course);
                maxEnrolled = enrolled;
            } else if (enrolled == maxEnrolled) {
                mostPopularCourses.add(course);
            }
        }

        boolean hasEnrollments = mostPopularCourses.stream()
                .anyMatch(course -> studentManager.getEnrollmentCount(course) > 0);
        return hasEnrollments ? formatCourseList(mostPopularCourses) : "n/a";
    }


    // Method to calculate the least popular course
    public String calculateLeastPopularCourse() {
        List<Course> leastPopularCourses = new ArrayList<>();
        int minEnrolled = Integer.MAX_VALUE;

        for (Course course : Course.values()) {
            int enrolled = studentManager.getEnrollmentCount(course);
            if (enrolled < minEnrolled) {
                leastPopularCourses.clear();
                leastPopularCourses.add(course);
                minEnrolled = enrolled;
            } else if (enrolled == minEnrolled) {
                leastPopularCourses.add(course);
            }
        }

        boolean hasEnrollments = leastPopularCourses.stream()
                .anyMatch(course -> studentManager.getEnrollmentCount(course) > 0);
        return hasEnrollments ? formatCourseList(leastPopularCourses) : "n/a";
    }

    // Method to calculate the course with the highest activity
    public String calculateHighestActivityCourse() {
        return calculateActivityCourse(true);  // true for highest activity
    }

    // Method to calculate the course with the lowest activity
    public String calculateLowestActivityCourse() {
        return calculateActivityCourse(false);  // false for lowest activity
    }

    // Method to calculate the easiest course
    public String calculateEasiestCourse() {
        return calculateCourseDifficulty(true);  // true for easiest (highest average)
    }

    // Method to calculate the hardest course
    public String calculateHardestCourse() {
        return calculateCourseDifficulty(false);  // false for hardest (lowest average)
    }


    private String calculateActivityCourse(boolean highest) {
        List<Course> activityCourses = new ArrayList<>();
        int activityLevel = highest ? 0 : Integer.MAX_VALUE;

        for (Course course : Course.values()) {
            int activityCount = studentManager.getActivityCount(course);
            if ((highest && activityCount > activityLevel) ||
                    (!highest && activityCount < activityLevel && activityCount > 0)) {
                activityCourses.clear();
                activityCourses.add(course);
                activityLevel = activityCount;
            } else if (activityCount == activityLevel) {
                activityCourses.add(course);
            }
        }

        boolean hasActivity = activityCourses.stream()
                .anyMatch(course -> studentManager.getActivityCount(course) > 0);
        return hasActivity ? formatCourseList(activityCourses) : "n/a";
    }

    private String calculateCourseDifficulty(boolean easiest) {
        Course targetCourse = null;
        double targetAverage = easiest ? 0 : Double.MAX_VALUE;

        for (Course course : Course.values()) {
            double average = studentManager.getAveragePoints(course);
            if ((easiest && average > targetAverage) || (!easiest && average < targetAverage && average > 0)) {
                targetCourse = course;
                targetAverage = average;
            }
        }

        return targetCourse != null ? targetCourse.getDisplayName() : "n/a";
    }

    private String formatCourseList(List<Course> courses) {
        return courses.stream()
                .map(Course::getDisplayName)
                .collect(Collectors.joining(", "));
    }

}
