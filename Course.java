package tracker;

public enum Course {

    JAVA(600, "Java"),
    DSA(400, "DSA"),
    DATABASES(480, "Databases"),
    SPRING(550, "Spring");

    private final int totalPointsToPass;
    private final String displayName;
    // private int enrolledStudents;

    Course(int totalPointsToPass, String displayName) {
        this.totalPointsToPass = totalPointsToPass;
        this.displayName = displayName;
    }

    public int getTotalPointsToPass() {
        return totalPointsToPass;
    }

    public String getDisplayName() {
        return displayName;
    }
}
