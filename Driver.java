import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Driver {
    private static Scanner sc = new Scanner(System.in);

    // helper methods
    public static void printSeparator(){
        System.out.println("--------------------------------------------------");
    }

    public static void displayMainMenu() {
        int choice;

        System.out.println("=== Main Calendar Menu ===");
        System.out.println("Welcome, " + Account.getCurrentAccount().getUsername() + "!");
        System.out.println("[1] View Calendars"); // from list of user's saved calendars (includes editing & deleting)
        System.out.println("[2] Add Calendar"); // creates (public/private) or adds a calendar (from public list)
        System.out.println("[3] Manage Entries"); // allows adding, editing, & deleting entries
        System.out.println("[4] Account Settings"); // allows account deletion & logging out
    }

    // main method
    public static void main(String[] args) {
        int choice;
        boolean success;
        String username;
        String password;

        while(true) {
            if (Account.getCurrentAccount() == null) { // check if a user is logged in
                // login menu
                System.out.println("=== Welcome to your Digital Calendar! ===");
                System.out.println("Sign-in Options:");
                System.out.println("[1] Login with Existing Account");
                System.out.println("[2] Create New Account");
                System.out.println("[3] Exit Application");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.print("Username: ");
                    username = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();

                    success = Account.loginAccount(username, password);
                    if (success) {
                        System.out.println("Login successful. Welcome " + username + "!");
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }

                } else if (choice == 2) {
                    System.out.print("Enter new username: ");
                    username = sc.nextLine();
                    System.out.print("Enter new password: ");
                    password = sc.nextLine();

                    success = Account.createAccount(username, password);
                    if (success) {
                        System.out.println("Account created successfully. Welcome " + username + "!");
                    } else {
                        System.out.println("Username already exists. Please choose a different username.");
                    }

                } else if (choice == 3) {
                    System.out.println("Thank you for using Digital Calendar!");
                    System.exit(0);

                } else {
                    System.out.println("Invalid choice. Please try again.");
                }

            } else { // user is already logged in
                
            }
            
        }
    }
}