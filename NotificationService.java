package tracker;

public class NotificationService {
    private final StudentManager studentManager;

    public NotificationService(StudentManager studentManager) {
        this.studentManager = studentManager;
    }

    public void sendNotifications() {
        int notifiedCount = 0;
        for (Student student : studentManager.getStudents()) {
            for (Course course : Course.values()) {
                if (student.isCourseCompleted(course) && !student.isCourseNotified(course)) {
                    System.out.printf("To: %s\nRe: Your Learning Progress\nHello, %s %s! You have accomplished our %s course!\n",
                            student.getEmailAddress(),
                            student.getFirstName(),
                            student.getLastName(),
                            course.getDisplayName());
                    student.markCourseAsNotified(course);
                    notifiedCount++;
                }
            }
        }
        System.out.println("Total " + notifiedCount + " students have been notified.");
    }
}
