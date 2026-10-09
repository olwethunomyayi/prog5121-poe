import java.util.regex.Pattern;

public class Login {

    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    // Regex-based cell phone checker: "+" + country code (1-3 digits) + number of no more than 10 digits.
    // Reference: adapt this to your own research and cite it properly (e.g. IIE Harvard style),
    // such as: Oracle (2024) Pattern (Java Platform SE docs), https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/regex/Pattern.html
    private static final Pattern CELL_PATTERN = Pattern.compile("^\\+\\d{1,3}\\d{1,10}$");

    public Login(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /** Username must contain an underscore and be no more than 5 characters long. */
    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    /** Password: at least 8 chars, a capital letter, a number and a special character. */
    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            } else if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    /** Cell phone must start with an international code (e.g. +27) and be correctly sized. */
    public boolean checkCellPhoneNumber(String cellPhone) {
        return cellPhone != null && CELL_PATTERN.matcher(cellPhone).matches();
    }

    /** Returns the cell phone message for the given number. */
    public String returnCellPhoneMessage(String cellPhone) {
        if (checkCellPhoneNumber(cellPhone)) {
            return "Cell number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an international code; "
                + "please correct the number and try again.";
    }

    /** Validates the details, stores them if valid, and returns the registration message(s). */
    public String registerUser(String username, String password, String cellPhone) {
        boolean userOk = checkUserName(username);
        boolean passOk = checkPasswordComplexity(password);

        String userMsg = userOk
                ? "Username successfully captured."
                : "Username is not correctly formatted; please ensure that your username "
                  + "contains an underscore and is no more than five characters in length.";
        String passMsg = passOk
                ? "Password successfully captured."
                : "Password is not correctly formatted; please ensure that the password "
                  + "contains at least eight characters, a capital letter, a number, and a special character.";

        if (userOk && passOk) {
            this.username = username;
            this.password = password;
            this.cellPhone = cellPhone;
        }
        return userMsg + "\n" + passMsg;
    }

    /** Checks the entered login details against the stored registration details. */
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return username != null && password != null
                && username.equals(enteredUsername)
                && password.equals(enteredPassword);
    }

    /** Returns the login status message. */
    public String returnLoginStatus(boolean loggedIn) {
        if (loggedIn) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        }
        return "Username or password incorrect, please try again.";
    }
}
