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
        System.out.println("[4] Account Settings"); // allows account deletion, logging out, and exiting application
        System.out.print("Enter your choice: ");
        choice = sc.nextInt();
        sc.nextLine();

        return choice;
    }

    public static void handleViewCalendars() {
        Account currentAccount = Account.getCurrentAccount();
        System.out.println("\n== " + currentAccount.getUsername() + "'s Calendars ==");

        // display owned calendars
        ArrayList<Calendar> userCalendars = new ArrayList<>();
        for (Calendar calendar : currentAccount.getCalendarList()) {
            if (calendar.getOwner().equals(currentAccount.getUsername())) {
                userCalendars.add(calendar);
            }
        }

        System.out.println("Owned Calendars:");
        for (int i = 0; i < userCalendars.size(); i++) {
            Calendar calendar = userCalendars.get(i);
            String access = calendar.getAccess() ? "Public" : "Private";
            System.out.println("[" + (i + 1) + "] " + calendar.getName() + " (" + access + ")");
        }

        // display added calendars
        ArrayList<Calendar> addedCalendars = new ArrayList<>();
        for (Calendar calendar : currentAccount.getCalendarList()) {
            if (!calendar.getOwner().equals(currentAccount.getUsername())) {
                addedCalendars.add(calendar);
            }
        }

        System.out.println("\nAdded Calendars:");
        int index = 1 + userCalendars.size(); // continue the count
        for (int i = 0; i < addedCalendars.size(); i++) {
            Calendar calendar = addedCalendars.get(i);
            System.out.println("[" + (index + i) + "] " + calendar.getName() + " (by " + calendar.getOwner() + ")");
        }
    }

    public static void handleAddCalendars() {
        int choice;
        boolean flag = true;

        while (flag) {
            System.out.println("\n== Add to " + Account.getCurrentAccount().getUsername() + "'s Calendars ==");
            System.out.println("[1] Create New Calendar");
            System.out.println("[2] Add Existing Public Calendar");
            System.out.println("[3] Back to Main Menu");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    handleCreateNewCalendar();
                    break;
                case 2:
                    handleAddExistingCalendar();
                    break;
                case 3:
                    flag = false;
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        }
    }

    public static void handleCreateNewCalendar() {
        boolean flag = true;
        String newName = "";
        boolean isPublic = true;
        String displayAccess = "";
        System.out.println("\n== Create New Calendar ==");

        while (flag) {
            System.out.print("Enter Calendar Name: ");
            newName = sc.nextLine();

            if(newName.equals(Account.getCurrentAccount().getUsername())) {
                System.out.println("\nCannot create a calendar with your username. Please try again.\n");
            } else if (newName.trim().isEmpty()) {
                System.out.println("\nCalendar name cannot be empty. Please try again.\n");
            } else {
                flag = false;
            }
        }
        flag = true;

        while (flag) {
            System.out.println("Set Calendar Access: ");
            System.out.println("[1] Private");
            System.out.println("[2] Public");
            System.out.print("Enter your choice: ");
            int newAccess = sc.nextInt();
            sc.nextLine();

            if (newAccess == 1) { // private
                isPublic = false;
                displayAccess = "Private";
                flag = false;
            } else if (newAccess == 2) { // public
                isPublic = true;
                displayAccess = "Public";
                flag = false;
            } else {
                System.out.println("\nInvalid choice. Please try again.\n");
            }
        }

        Calendar newCalendar = Account.getCurrentAccount().createCalendar(newName, isPublic);
        System.out.println("\n[" + displayAccess + "] Calendar \"" + newCalendar.getName() + "\" is created successfully!");
    }

    public static void handleAddExistingCalendar() {
        System.out.println("\n== Add Existing Public Calendar ==");

        ArrayList<Calendar> availablePublicCalendars = new ArrayList<>();
        Account currentAccount = Account.getCurrentAccount();

        // get all public calendars that the account does not own
        for (Calendar calendar : Calendar.getPublicCalendarList()) {
            if (!calendar.getOwner().equals(currentAccount.getUsername()) &&
                !currentAccount.getCalendarList().contains(calendar)) {
                availablePublicCalendars.add(calendar);
            }
        }
        boolean flag = true;

        if (availablePublicCalendars.isEmpty()) {
            System.out.println("\nNo public calendars available to add.");
        } else {
            System.out.println("\nAvailable Public Calendars:");
            for (int i = 0; i < availablePublicCalendars.size(); i++) {
                Calendar calendar = availablePublicCalendars.get(i);
                System.out.println("[" + (i + 1) + "] " + calendar.getName() + " (by " + calendar.getOwner() + ")");
            }
            while (flag) {
                System.out.print("\nEnter calendar number to add [0 to cancel]: ");
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice != 0) {
                    if (choice >= 1 && choice <= availablePublicCalendars.size()) {
                        Calendar selectedCalendar = availablePublicCalendars.get(choice - 1);

                        boolean success = currentAccount.acceptCalendar(selectedCalendar);
                        if (success) {
                            System.out.println("\nCalendar \"" + selectedCalendar.getName() + "\" added successfully!");
                            flag = false;
                        } else {
                            System.out.println("\nFailed to add calendar. Calendar might already be in your list.");
                            flag = false;
                        } 
                    } else {
                        System.out.println("\nInvalid choice. Please try again.");
                    }
                } else{
                    flag = false;
                }
            }
        }
    }

    public static void handleManageEntries() {

    }

    public static boolean handleAccountSettings() {
        int choice;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean exit = false;

        while (flag1) {
            flag2 = true;
            System.out.println("\n== " + Account.getCurrentAccount().getUsername() + "'s Account Settings ==");
            System.out.println("[1] Logout Account");
            System.out.println("[2] Delete Account");
            System.out.println("[3] Back to Main Menu");
            System.out.println("[4] Exit Application");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    while (flag2) {
                        System.out.println("\n== Logout Account ==");
                        System.out.println("Are you sure you want to proceed?");
                        System.out.println("[1] Yes");
                        System.out.println("[2] No");
                        System.out.print("Enter your choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();

                        if (choice == 1) {
                            System.out.println("\nLogout successful. See you again soon " + Account.getCurrentAccount().getUsername() + "!");
                            Account.logoutAccount();
                            flag2 = false;
                            flag1 = false;
                        } else if (choice == 2) {
                            flag2 = false;
                        } else {
                            System.out.println("\nInvalid choice. Please try again.");
                        }
                    }
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
                            Account.logoutAccount();
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
                case 4:
                    while (flag2) {
                        System.out.println("\n== Exit Application ==");
                        System.out.println("Are you sure you want to proceed?");
                        System.out.println("[1] Yes");
                        System.out.println("[2] No");
                        System.out.print("Enter your choice: ");
                        choice = sc.nextInt();
                        sc.nextLine();

                        if (choice == 1) {
                            System.out.println("\nThank you " + Account.getCurrentAccount().getUsername() + " for using our Digital Calendar!");
                            exit = true;
                            flag2 = false;
                            flag1 = false;
                        } else if (choice == 2) {
                            flag2 = false;
                        } else {
                            System.out.println("\nInvalid choice. Please try again.");
                        }
                    }
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
                    break;
            }
        }
        return exit;
    }

    // main method
    public static void main(String[] args) {
        boolean flag = true;
        boolean exit;
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
                    case 4:
                        exit = handleAccountSettings();
                        if (exit) {
                            flag = false;
                            break;  
                        } else 
                            break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            }
        }

        sc.close();
    }
}
