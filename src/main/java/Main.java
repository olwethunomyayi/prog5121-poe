import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("=== REGISTRATION ===");
        System.out.print("First name: ");
        String first = in.nextLine();
        System.out.print("Last name: ");
        String last = in.nextLine();

        Login login = new Login(first, last);

        System.out.print("Username (must contain _ and be max 5 chars): ");
        String username = in.nextLine();
        System.out.print("Password: ");
        String password = in.nextLine();
        System.out.print("Cell phone (e.g. +27838968976): ");
        String cell = in.nextLine();

        System.out.println(login.registerUser(username, password, cell));
        System.out.println(login.returnCellPhoneMessage(cell));

        if (!login.checkUserName(username) || !login.checkPasswordComplexity(password)) {
            System.out.println("Registration incomplete. Please run the program again.");
            return;
        }

        System.out.println("\n=== LOGIN ===");
        System.out.print("Username: ");
        String u = in.nextLine();
        System.out.print("Password: ");
        String p = in.nextLine();

        boolean ok = login.loginUser(u, p);
        System.out.println(login.returnLoginStatus(ok));
    }
}
