package tracker;

public class Main {
    public static void main(String[] args) {
        // Execute run() UI utility method
        UI.run();



        /**
         * TODO: Add Unique ID (number or string)
         * TODO: One acct per student (use Set). Can have same name but email addr must be unique
         * TODO: incorporate course: Java, Data Structures and Algorithms (DSA), Databases, and Spring
         * - Student can take any (or all) course, complete tasks, pass tests, and submit homework for points.
         * - Points are added to student score
         * - Points must be >= zero
         * - Student updates must be done via ID (no name or email for security purposes)
         */

        // FIXME: !!! Assumed that each student is taking all 4 course. Points must = 4 values
        // XXX:  Test test test
        // HACK: Test test test
        // Review:Test test test
        // bug: Test test test
        // Note: Test test test
        // WTF: Test test test


        /**
         * Objectives:
         * (1) Check if the provided email has been already used when adding information about students.
         *     If so, respond with the following message: This email is already taken.
         *
         * (2) Recognize the new list command to print the Students: a header followed by the student IDs.
         *     The students must be listed in the order they were added. Remember, each ID must be unique.
         *     If there are no students to list, print No students found.
         *
         * (3) Recognize the new add points and print the following message in response: Enter an id and
         *     points or 'back' to return. After that, the program must read learning progress data in the
         *     following format: studentId number number number number.
         *     The numbers correspond to the courses (Java, DSA, Databases, Spring).
         *     Number is a non-negative integer number. If there is no student with the specified ID,
         *     the program should print No student is found for id=%s. where %s is the invalid ID.
         *     Also, if any of the numbers are missing, or there is an extra number or any of the numbers
         *     do not meet the requirements mentioned above, the program should print Incorrect points format.
         *     If the learning progress data is entered in the correct format, and the specified user exists,
         *     the program should update the student's record and print Points updated. Once back is entered,
         *     the program must stop reading learning progress data.
         *
         * (4) Recognize the find command and print the following message: Enter an id or 'back' to return.
         *     After that, if an ID is entered, the program should either print details of the student with
         *     the specified ID in this format: id points: Java=%d; DSA=%d; Databases=%d; Spring=%dwhere %d
         *     is the respective number of points earned by the student. If the ID cannot be found,
         *     print the error message: No student is found for id=%s. where %s is the invalid ID.
         */

    }
}
