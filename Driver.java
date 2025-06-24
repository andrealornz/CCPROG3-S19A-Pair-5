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

    public static int displayMainMenu() {
        int choice;

        System.out.println("\n=== Main Calendar Menu ===\n");
        System.out.println("Welcome, " + Account.getCurrentAccount().getUsername() + "!");
        System.out.println("[1] View Calendars"); // from list of user's saved calendars (includes editing & deleting)
        System.out.println("[2] Add Calendar"); // creates (public/private) or adds a calendar (from public list)
        System.out.println("[3] Manage Entries"); // allows adding, editing, & deleting entries
        System.out.println("[4] Account Settings"); // allows account deletion & logging out
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }

    public static void handleViewCalendars() {

    }

    public static void handleAddCalendars() {

    }

    public static void handleManageEntries() {

    }

    public static void handleAccountSettings() {
        int choice;
        boolean flag1 = true;
        boolean flag2 = true;

        while (flag1) {
            System.out.println("\n== Account Settings ==");
            System.out.println("[1] Logout Account");
            System.out.println("[2] Delete Account");
            System.out.println("[3] Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\nLogout successful. See you again soon " + Account.getCurrentAccount().getUsername() + "!");
                    Account.logoutAccount();
                    flag1 = false;
                    break;
                case 2: 
                    while (flag2) {
                        System.out.println("\n== Delete Account ==");
                        System.out.println("Are you sure you want to proceed?");
                        System.out.println("[1] Yes");
                        System.out.println("[2] No");
                        System.out.print("Enter your choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();

                        if (choice == 1) {
                            System.out.println("\nAccount deletion successful. We hope to see you again " + Account.getCurrentAccount().getUsername() + "!");
                            Account.getCurrentAccount().deleteAccount();
                            flag2 = false;
                            flag1 = false;
                        } else if (choice == 2) {
                            flag2 = false;
                        } else {
                            System.out.println("\nInvalid choice. Please try again.");
                        }
                    }
                    break;
                case 3:
                    flag1 = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        }
    }

    // main method
    public static void main(String[] args) {
        boolean flag = true;
        int choice;
        boolean success;
        String username;
        String password;

        while(flag) {
            while(Account.getCurrentAccount() == null && flag) { // no account logged in
                // login menu
                System.out.println("\n=== Welcome to your Digital Calendar! ===\n");
                System.out.println("Sign-in Options:");
                System.out.println("[1] Login with Existing Account");
                System.out.println("[2] Create New Account");
                System.out.println("[3] Exit Application");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.println();
                    System.out.println("== Login with Existing Account ==");
                    System.out.print("Username: ");
                    username = sc.nextLine();
                    System.out.print("Password: ");
                    password = sc.nextLine();

                    success = Account.loginAccount(username, password);
                    if (success) {
                        System.out.println();
                        System.out.println("Login successful. You may now use your digital calendar.");
                    } else {
                        System.out.println();
                        System.out.println("Invalid username or password. Please try again.");
                    }

                } else if (choice == 2) {
                    System.out.println();
                    System.out.println("== Create New Account ==");
                    System.out.print("Enter new username: ");
                    username = sc.nextLine();
                    System.out.print("Enter new password: ");
                    password = sc.nextLine();

                    success = Account.createAccount(username, password);
                    if (success) {
                        System.out.println();
                        System.out.println("Account created successfully. You may now login with your provided credentials.");
                    } else {
                        System.out.println();
                        System.out.println("Username already exists. Please choose a different username.");
                    }

                } else if (choice == 3) {
                    System.out.println("\nThank you for using our Digital Calendar!");
                    flag = false;

                } else {
                    System.out.println("\nInvalid choice. Please try again.");
                }    
            }

            while(Account.getCurrentAccount() != null && flag) { // an account is logged in
                choice = displayMainMenu();

                switch (choice) {
                    case 1:
                        handleViewCalendars();
                        break;
                    case 2:
                        handleAddCalendars();
                        break;
                    case 3:
                        handleManageEntries();
                        break;
                    case 4:
                        handleAccountSettings();
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        sc.close();
    }
}
