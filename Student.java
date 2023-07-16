package tracker;

public class Student {
    private String firstName;
    private String lastName;
    private String emailAddress;

    public Student(String firstName, String lastName, String emailAddress) {
        this.firstName = validateFirstName(firstName);
        this.lastName = validateLastName(lastName);
        this.emailAddress = validateEmail(emailAddress);
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
        return email;
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

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getEmailAddress();
    }
}
