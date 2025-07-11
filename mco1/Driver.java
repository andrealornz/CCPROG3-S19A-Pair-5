import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the Driver class that handles all displays and validating user inputs
 */
public class Driver {
    private static Scanner sc = new Scanner(System.in);

    // helper methods

    /**
     * displays the main menu and gets the user's input
     * @return the user's input
     */
    public static int displayMainMenu() {
        int choice = 0;
        boolean flag = true;

        while (flag) {
            System.out.println("\n=== Main Calendar Menu ===\n");
            System.out.println("Welcome, " + Account.getCurrentAccount().getUsername() + "!");
            System.out.println("[1] View Calendars"); // shows a list of the user's calendars and offers options
            System.out.println("[2] Add Calendar"); // creates (public/private) or adds a calendar (from public list)
            System.out.println("[3] Account Settings"); // allows account deletion, logging out, and exiting application
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
                sc.nextLine();
                flag = false;
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.");
                sc.nextLine();
            }
        }
        
        return choice;
    }

    /**
     * handles displaying the view calendar option from the main menu, displaying the user's calendar list and gives options for a selected calendar (view/delete)
     */
    public static void handleViewCalendars() {
        boolean flag1 = true;

        while (flag1) { // loops until the user wants to go back to the main menu
            Account currentAccount = Account.getCurrentAccount();
            System.out.println("\n== " + currentAccount.getUsername() + "'s Calendars ==");

            // stores and displays user's owned calendars
            ArrayList<Calendar> userCalendars = new ArrayList<>();
            for (Calendar calendar : currentAccount.getCalendarList()) {
                if (calendar.getOwner().equals(currentAccount.getUsername())) { // if calendar owner is the same as the user
                    userCalendars.add(calendar);
                }
            }
            System.out.println("Owned Calendars:");
            for (int i = 0; i < userCalendars.size(); i++) {
                Calendar calendar = userCalendars.get(i);
                String access = calendar.getAccess() ? "Public" : "Private"; // dispalys access type
                System.out.println("[" + (i + 1) + "] " + calendar.getName() + " (" + access + ")");
            }

            // stores and displays user's added calendars from the public calendar list
            ArrayList<Calendar> addedCalendars = new ArrayList<>();
            for (Calendar calendar : currentAccount.getCalendarList()) {
                if (!calendar.getOwner().equals(currentAccount.getUsername())) { // if the calendar owner is different from the user
                    addedCalendars.add(calendar);
                }
            }
            if (addedCalendars.isEmpty()) { // if the user does not have any added calendar
                System.out.println("\nAdded Calendars:");
                System.out.println("[EMPTY]");
            } else {
                System.out.println("\nAdded Calendars:");
                int index = 1 + userCalendars.size(); // continues the count from the user's owned calendars
                for (int i = 0; i < addedCalendars.size(); i++) {
                    Calendar calendar = addedCalendars.get(i);
                    System.out.println("[" + (index + i) + "] " + calendar.getName() + " (by " + calendar.getOwner() + ")"); // displays public calendar owner
                }
            }

            ArrayList<Calendar> allCalendars = new ArrayList<>(); // stores all the user's calendars (including default calendar)
            allCalendars.addAll(userCalendars);
            allCalendars.addAll(addedCalendars);

            boolean flag2 = true;
            while (flag2) { // loops until user selects a valid input
                System.out.print("\nSelect a calendar number [0 to cancel]: ");
                if (sc.hasNextInt()) { // checks if the next input is an integer
                    int choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 0) { // goes back to main menu
                        flag1 = false;
                        flag2 = false;
                    } else if (choice >= 1 && choice <= allCalendars.size()) { // checks if input is within the valid numbers
                        Calendar selectedCalendar = allCalendars.get(choice - 1); // turns the input into its corresponding index

                        boolean flag3 = true;
                        while (flag3) { // loops until user selects a valid input
                            // displays options for the selected calendar
                            System.out.println("\n== Options for \"" + selectedCalendar.getName() + "\" Calendar ==");
                            System.out.println("[1] View Calendar"); // allows viewing of calendars and offers multiple options
                            System.out.println("[2] Delete Calendar"); // deletes a calendar
                            System.out.println("[3] Back to Calendar List");
                            System.out.print("Enter your choice: ");
                            
                            if (sc.hasNextInt()) { // checks if the next input is an integer
                                choice = sc.nextInt();
                                sc.nextLine();
                                
                                switch (choice) {
                                    case 1:
                                        handleViewCalendar(selectedCalendar);
                                        flag3 = false;
                                        flag2 = false;
                                        break;
                                    case 2:
                                        handleDeleteCalendar(selectedCalendar);
                                        flag3 = false;
                                        flag2 = false;
                                        break;
                                    case 3:
                                        flag3 = false;
                                        flag2 = false; // allows calendar reselection
                                        break;
                                    default:
                                        System.out.println("\nInvalid choice. Please try again.");
                                }
                            } else {
                                System.out.println("\nInvalid input. Please enter a valid number.");
                                sc.nextLine(); // clears invalid input
                            }
                        }
                    } else {
                        System.out.println("\nInvalid choice. Please try again.");
                    }
                } else {
                    System.out.println("\nInvalid input. Please enter a valid number.");
                    sc.nextLine(); // clears invalid input
                }
            }
        }
    }
    
    /**
     * handles the view specific calendar option from the view calendar menu, gives options to view specific months and years, and options for entries
     * @param calendar the calendar instance to be viewed
     */
    public static void handleViewCalendar(Calendar calendar) {
        boolean flag = true;
        YearMonth currentMonth = YearMonth.now(); // default display is the current month and year

        while (flag) { // loops until the user chooses to go back to the calendar list
            calendar.displayMonthlyView(currentMonth); // displays the actual calendar

            System.out.println("\n== Options for \"" + calendar.getName() + "\" Calendar ==");
            System.out.println("[1] View Date Entries"); // allows viewing of date entries, adding, editing, and deleting
            System.out.println("[2] Next Month"); // cycles to the next month
            System.out.println("[3] Previous Month"); // cycles to the previous month
            System.out.println("[4] Specific Month"); // selects a specific month and year to view
            System.out.println("[5] Back to Calendar List");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) { // checks if the next input is an integer
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        handleViewDateEntries(calendar, currentMonth);
                        break;
                    case 2:
                        currentMonth = currentMonth.plusMonths(1); // adds 1 to current month
                        break;
                    case 3:
                        currentMonth = currentMonth.minusMonths(1); // subtracts 1 to current month
                        break;
                    case 4:
                        currentMonth = handleSpecificMonth();
                        break;
                    case 5:
                        flag = false; // goes back to the calendar list
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.");
                sc.nextLine(); // clears invalid input
            }
        }
    }

    /**
     * handles the view specific date entries under the view specific calendar menu, selecting a specific date to manage entries
     * @param calendar the calendar instance to be managed
     * @param currentMonth the month and year to be managed
     */
    public static void handleViewDateEntries(Calendar calendar, YearMonth currentMonth) {
        boolean flag = true;

        while (flag) { // loops until the user inputs a valid input
            System.out.println("\n== View Date Entries ==");
            System.out.print("Enter Date [dd]: ");

            if (sc.hasNextInt()) { // checks if the next input is an integer
                int day = sc.nextInt();
                sc.nextLine();

                if (day >= 1 && day <= currentMonth.lengthOfMonth()) { // input has to be 1 to the number of days in that month
                    LocalDate selectedDate = currentMonth.atDay(day);
                    handleManageEntries(calendar, selectedDate); // passes the calendar instance and the specific day for the month and year
                    flag = false;
                } else {
                    System.out.println("\nInvalid day. Please enter a day between 1 and " + currentMonth.lengthOfMonth() + ".");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.");
                sc.nextLine(); // clears invalid input
            }
        }
    }
    
    /**
     * handles viewing and managing entries for a specific day of a month and year, allows adding, editing, and deleting entires
     * @param calendar the calendar instance to be managed
     * @param date the specific date to be managed
     */
    public static void handleManageEntries(Calendar calendar, LocalDate date) {
        ArrayList<Entry> entries = calendar.getEntriesForDate(date); // array for the entries of a specific date

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy"); // formats the date
        System.out.println("\n== Entries for " + date.format(formatter) + " ==");

        if (entries.isEmpty()) { // if entries are empty
            System.out.println("[EMPTY]");
        } else {
            // sorts the entries array depending on the start time
            for (int i = 0; i < entries.size() - 1; i++) {
                for (int j = 0; j < entries.size() - 1 - i; j++) {
                    if (entries.get(j).getStartTime().isAfter((entries.get(j + 1).getStartTime()))) {
                        Entry temp = entries.get(j);
                        entries.set(j, entries.get(j + 1));
                        entries.set(j + 1, temp);
                    }
                }
            }

            // displays the sorted entries
            for (int i = 0; i < entries.size(); i++) {
                Entry entry = entries.get(i);
                System.out.println("[" + (i + 1) + "] " + entry.getTitle());
                System.out.println("    Time: " + entry.getStartTime() + " - " + entry.getEndTime());
                System.out.println("    Details: " + entry.getDetails());
                if (i < entries.size() - 1) {
                    System.out.println();
                }
            }
        }

        int choice;
        boolean flag1 = true;
        while (flag1) { // loops until user decides to go back to the calendar
            System.out.println("\n== Manage Entries ==");
            System.out.println("[1] Add Entry");
            System.out.println("[2] Edit Entry");
            System.out.println("[3] Delete Entry");
            System.out.println("[4] Back to Calendar");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) { // checks if the next input is an integer
                choice = sc.nextInt();
                sc.nextLine();

                String title = "";
                String newTitle = "";
                String timeInput; // time input scanner reference
                LocalTime startTime = null;
                LocalTime endTime = null;
                LocalTime newStartTime = null;
                LocalTime newEndTime = null;

                boolean flag2 = true;
                switch (choice) {
                    case 1: //add entry
                        System.out.println("\n== Add Entry ==");
                        LocalDate dDate = LocalDate.parse(date.toString()); 
                        while (flag2) { // loops until title is valid
                            System.out.print("Title: ");
                            title = sc.nextLine();
                            if (title.trim().isEmpty()) { // checks if title is empty
                                System.out.println("\nEntry title cannot be empty. Please try again\n");
                            } else {
                                flag2 = false;
                            }
                        }
                        flag2 = true;
                        while (flag2) { // loops until user enters a valid time
                            System.out.print("Start Time [hh:mm]: ");
                            timeInput = sc.nextLine();
                            if (isValidTime(timeInput)) { // checks time format validity
                                startTime = LocalTime.parse(timeInput);
                                flag2 = false;
                            } else {
                                  System.out.println("\nInvalid time format. Please try again.\n");
                            }
                        }
                        flag2 = true;
                        while (flag2) { // loops until user enters a valid time
                            System.out.print("End Time [hh:mm]: ");
                            timeInput = sc.nextLine();
                            if (isValidTime(timeInput)) { // checks time format validity
                                endTime = LocalTime.parse(timeInput);
                                if (endTime.isAfter(startTime)) { // checks if end time comes after start time
                                    flag2 = false;
                                } else {
                                    System.out.println("\nEnd time must be after start time. Please try again.\n");
                                }
                            } else {
                                  System.out.println("\nInvalid time format. Please try again.\n");
                            }
                        }
                        System.out.print("Details: ");
                        String details = sc.nextLine();
                        if (calendar.createEntry(title, date, startTime, endTime, details)) { // create entry from calendar instance
                            System.out.println("\nSuccessfully created entry \"" + title + "\" from " + startTime + " to " + endTime);
                        } else {
                            System.out.println("\nInvalid entry. Please try again.");
                        }
                        flag1 = false;
                        break;
                    case 2: //edit entry
                        System.out.println("\n== Edit Entry ==");
                        System.out.println("Entry to be Edited: "); // finds entry with the title
                        dDate = LocalDate.parse(date.toString());
                        System.out.print("Title: ");
                        String currentTitle = sc.nextLine();
                        if (calendar.deleteEntry(dDate, currentTitle)) { // deletes chosen entry first
                            while (flag2) { // loops until title is valid
                                System.out.print("New Title: ");
                                newTitle = sc.nextLine();
                                if (newTitle.trim().isEmpty()) { // checks if title is empty
                                    System.out.println("\nEntry title cannot be empty. Please try again\n");
                                } else {
                                    flag2 = false;
                                }
                            }
                            flag2 = true;
                            while (flag2) { // loops until user enters a valid time
                                System.out.print("New Start Time [hh:mm]: ");
                                timeInput = sc.nextLine();
                                if (isValidTime(timeInput)) { // checks time format validity
                                    newStartTime = LocalTime.parse(timeInput);
                                    flag2 = false;
                                } else {
                                    System.out.println("\nInvalid time format. Please try again.\n");
                                }
                            }
                            flag2 = true;
                            while (flag2) { // loops until user enters a valid time
                                System.out.print("New End Time [hh:mm]: ");
                                timeInput = sc.nextLine();
                                if (isValidTime(timeInput)) { // checks time format validity
                                    newEndTime = LocalTime.parse(timeInput);
                                    if (newEndTime.isAfter(newStartTime)) { // checks if end time comes after start time
                                        flag2 = false;
                                    } else {
                                        System.out.println("\nEnd time must be after start time. Please try again.\n");
                                    }
                                } else {
                                    System.out.println("\nInvalid time format. Please try again.\n");
                                }
                            }
                            System.out.print("New Details: ");
                            String newDetails = sc.nextLine();
                            if (calendar.createEntry(newTitle, dDate, newStartTime, newEndTime, newDetails)) { // creates new entry with new details
                                System.out.println("\nSuccessfully edited entry to " + newTitle);
                            } else {
                                System.out.println("\nCould not edit entry. Please try again.");
                            }
                        } else {
                            System.out.println("\nCould not find entry. Please try again.");
                        }
                        flag1 = false;
                        break;
                    case 3:
                        System.out.println("\n== Delete Entry ==");
                        System.out.println("Entry to delete: "); // finds entry with the title
                        dDate = LocalDate.parse(date.toString());
                        System.out.print("Title: ");
                        String dTitle = sc.nextLine();

                        flag2 = true;
                        while (flag2) {
                            System.out.println("\nAre you sure you want to proceed?");
                            System.out.println("[1] Yes");
                            System.out.println("[2] No");
                            System.out.print("Enter your choice: ");

                            if (sc.hasNextInt()) {
                                choice = sc.nextInt();
                                sc.nextLine();

                                if (choice == 1) {
                                    if (calendar.deleteEntry(dDate, dTitle)) {
                                        System.out.println("\nSuccessfully deleted entry \"" + dTitle + "\"");
                                    } else {
                                        System.out.println("\nCould not delete entry. Pleaes try again.");
                                    }
                                    flag2 = false;
                                    flag1 = false;
                                } else if (choice == 2) {
                                    flag2 = false;
                                    flag1 = false;
                                } else {
                                    System.out.println("\nInvalid choice. Please try again.");
                                }                              
                            } else {
                                System.out.println("\nInvalid input. Please enter a valid day number.");
                                sc.nextLine(); // clears invalid input
                            }
                        }
                        break;
                    case 4:
                        flag1 = false; // goes back to the calendar display
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                        break;
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid day number.");
                sc.nextLine(); // clears invalid input
            }
        }
    }

    /**
     * checks if a given start time and end time for the entires are valid
     * @param time the time to be checked for validity
     * @return true if time is valid
     */
    public static boolean isValidTime(String time) { 
        boolean valid = true;

        if (time == null || time.length() != 5 || time.charAt(2) != ':') { // checks length and if there is a separator
            valid = false;
        } else {
            String[] parts = time.split(":");
            if (parts.length == 2) {  // if both parts are exactly 2 digits
                if (parts[0].length() == 2 && parts[1].length() == 2) { // if all characters are digits
                    boolean allDigits = true;
                    String combined = parts[0] + parts[1];
                    for (int i = 0; i < combined.length() && allDigits; i++) {
                        if (!Character.isDigit(combined.charAt(i))) { // if the characters are not all digits
                            allDigits = false;
                        }
                    }
                    
                    if (allDigits) { // converts digit characters into integers
                        int hours = Integer.parseInt(parts[0]);
                        int minutes = Integer.parseInt(parts[1]);
                        valid = (hours >= 0 && hours <= 23 && minutes >= 0 && minutes <= 59);
                    } else {
                        valid = false;
                    }
                } else {
                    valid = false;
                }
            } else {
                valid = false;
            }
        }
        return valid;
    }

    /**
     * gets the input for selecting a specific month and year to be viewed on the calendar
     * @return the inputted valid month and year
     */
    public static YearMonth handleSpecificMonth() {
        boolean flag1 = true;
        YearMonth specificMonth = YearMonth.now();

        while (flag1) { // loops until user inputs a valid month
            System.out.println("\n== View Specific Month == ");
            System.out.print("Enter Month [1 - 12]: ");
            if (sc.hasNextInt()) {
                int month = sc.nextInt();
                sc.nextLine();

                if (month >= 1 && month <= 12) { // if month is 1 to 12
                    boolean flag2 = true;
                    while (flag2) {
                        System.out.print("Enter Year: "); 
                        if (sc.hasNextInt()) {
                            int year = sc.nextInt();
                            sc.nextLine();

                            if (year >= 1 && year <= 9999) {
                                specificMonth = YearMonth.of(year, month); // passes input values
                                flag2 = false;
                                flag1 = false;
                            } else {
                                System.out.println("Invalid year. Please try again.");
                            }
                        } else {
                            System.out.println("Invalid input. Please enter a valid year number.");
                            sc.nextLine();
                        }
                    }
                } else {
                    System.out.println("\nInvalid month. Please enter a month between 1 and 12.\n");
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number.\n");
                sc.nextLine();
            }
        }
        return specificMonth;
    }

    /**
     * handles deleting the selected calendar from the view calendars menu
     * @param calendar the calendar instance to be deleted
     */
    public static void handleDeleteCalendar(Calendar calendar) {
        Account currentAccount = Account.getCurrentAccount();
        
        System.out.println("\n== Delete Calendar ==");
    
        if (calendar.getName().equals(currentAccount.getUsername())) {
            System.out.println("\nDefault calendar cannot be deleted.");
        } else {
            boolean flag = true;

            while (flag) {
                System.out.println("Are you sure you want to proceed?");
                System.out.println("[1] Yes");
                System.out.println("[2] No");
                System.out.print("Enter your choice: ");

                if (sc.hasNextInt()) {
                    int choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 1) {
                        boolean success = false;

                        if (calendar.getOwner().equals(currentAccount.getUsername())) { // owned by the account
                            success = currentAccount.deleteCalendar(calendar);

                            if (success) {
                                System.out.println("\nCalendar deleted successfully.");
                                flag = false;
                            } else {
                                System.out.println("\nFailed to delete calendar.");
                                flag = false;
                            }
                        } else { // owned by other accounts
                            success = currentAccount.removeCalendar(calendar);
                            
                            if (success) {
                                System.out.println("\nCalendar removed successfully.");
                                flag = false;
                            } else {
                                System.out.println("\nFailed to delete calendar.");
                                flag = false;
                            }
                        }
                    } else if (choice == 2) {
                        flag = false;
                    } else {
                        System.out.println("\nInvalid choice. Please try again\n");
                    }
                } else {
                    System.out.println("\nInvalid input. Please enter a valid number\n");
                    sc.nextLine(); // clears invalid input
                }
                
            }
        }
    }

    /**
     * handles the add calendars option from the main manu, gives the option to create new calendars or add from existing ones
     */
    public static void handleAddCalendars() {
        int choice;
        boolean flag = true;

        while (flag) {
            System.out.println("\n== Add to " + Account.getCurrentAccount().getUsername() + "'s Calendars ==");
            System.out.println("[1] Create New Calendar"); // create a user's own public or private calendar
            System.out.println("[2] Add Existing Public Calendar"); // add from the public calendar list
            System.out.println("[3] Back to Main Menu");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
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
                }
            } else {
                System.out.println("\nInvalid input. Please enter a valid number");
                sc.nextLine(); // clears invalid input
            }
        }
    }

    /**
     * handles the create new calendar option from the add calendars menu, allows the user to create their own calendars
     */
    public static void handleCreateNewCalendar() {
        boolean flag = true;
        String newName = "";
        boolean isPublic = true;
        String displayAccess = "";
        System.out.println("\n== Create New Calendar ==");

        while (flag) { // loops until user enters a valid calendar name
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

        while (flag) { // loops until user enters a valid number
            System.out.println("Set Calendar Access: ");
            System.out.println("[1] Private");
            System.out.println("[2] Public");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
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
            } else {
                System.out.println("\nInvalid input. Please enter a valid number\n");
                sc.nextLine(); // clears invalid input
            }
        }

        Calendar newCalendar = Account.getCurrentAccount().createCalendar(newName, isPublic);
        System.out.println("\n[" + displayAccess + "] Calendar \"" + newCalendar.getName() + "\" is created successfully!");
    }

    /**
     * handles the add existing calendar option from the add calendars menu, displaying avaiable public calendars to be linked to the account
     */
    public static void handleAddExistingCalendar() {
        System.out.println("\n== Add Existing Public Calendar ==");

        ArrayList<Calendar> availablePublicCalendars = new ArrayList<>(); // an array for storing avaiable public calendars that the account does not own
        Account currentAccount = Account.getCurrentAccount();

        // get all public calendars that the account does not own
        for (Calendar calendar : Calendar.getPublicCalendarList()) {
            if (!calendar.getOwner().equals(currentAccount.getUsername()) &&
                !currentAccount.getCalendarList().contains(calendar)) {
                availablePublicCalendars.add(calendar);
            }
        }
        boolean flag = true;

        if (availablePublicCalendars.isEmpty()) { // if there are no available public calendars to add
            System.out.println("\nNo public calendars available to add.");
        } else {
            System.out.println("\nAvailable Public Calendars:"); // displays a list of available public calendars
            for (int i = 0; i < availablePublicCalendars.size(); i++) {
                Calendar calendar = availablePublicCalendars.get(i);
                System.out.println("[" + (i + 1) + "] " + calendar.getName() + " (by " + calendar.getOwner() + ")"); // diplays the owner
            }
            while (flag) { // loops until the user enters a valid input
                System.out.print("\nEnter calendar number to add [0 to cancel]: ");

                if (sc.hasNextInt()) {
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
                } else {
                    System.out.println("\nInvalid input. Please enter a valid number");
                    sc.nextLine(); // clears invalid input
                }
            }
        }
    }

    /**
     * handles the account settings option from the main menu, allows logging out, account deletion, and exiting application
     * @return returns an exit flag for when a user wants to exit the application
     */
    public static boolean handleAccountSettings() {
        int choice;
        boolean flag1 = true;
        boolean flag2 = true;
        boolean exit = false;

        while (flag1) { // loops until the user enters a valid input
            flag2 = true;
            System.out.println("\n== " + Account.getCurrentAccount().getUsername() + "'s Account Settings ==");
            System.out.println("[1] Logout Account");
            System.out.println("[2] Delete Account");
            System.out.println("[3] Back to Main Menu");
            System.out.println("[4] Exit Application");
            System.out.print("Enter your choice: ");

            if (sc.hasNextInt()) {
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

                            if (sc.hasNextInt()) {
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
                            } else {
                                System.out.println("\nInvalid input. Please enter a valid number");
                                sc.nextLine(); // clears invalid input
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

                            if (sc.hasNextInt()) {
                                choice = sc.nextInt();
                                sc.nextLine();

                                if (choice == 1) {
                                    System.out.println("\nAccount deletion successful. We hope to see you again " + Account.getCurrentAccount().getUsername() + "!");
                                    
                                    for (Calendar calendar : Account.getCurrentAccount().getCalendarList()) {
                                        if (!calendar.getAccess()) { // if calendar is private
                                            Account.getCurrentAccount().deleteCalendar(calendar); // delete private calendars from account
                                        }
                                    } Account.getCurrentAccount().getCalendarList().remove(Account.getCurrentAccount().getDefaultCalendar()); // delete default calendar
                                    Account.getCurrentAccount().deleteAccount(); // set activity to false
                                    Account.logoutAccount();
                                    flag2 = false;
                                    flag1 = false;
                                } else if (choice == 2) {
                                    flag2 = false;
                                } else {
                                    System.out.println("\nInvalid choice. Please try again.");
                                }
                            } else {
                                System.out.println("\nInvalid input. Please enter a valid number");
                                sc.nextLine(); // clears invalid input
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

                            if (sc.hasNextInt()) {
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
                            } else {
                                System.out.println("\nInvalid input. Please enter a valid number");
                                sc.nextLine(); // clears invalid input
                            }
                        }
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please try again.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number");
                sc.nextLine(); // clears invalid input
            }
            
        }
        return exit;
    }

    /**
     * the main method
     * handles the account sign in options and manages whether an account is logged in or not
     * @param args (not used)
     */
    public static void main(String[] args) {
        boolean flag = true;
        boolean flag2 = true;
        boolean exit;
        int choice;
        boolean success;
        String username = "";
        String password = "";

        while(flag) {
            while(Account.getCurrentAccount() == null && flag) { // no account logged in
                // login menu
                System.out.println("\n=== Welcome to your Digital Calendar! ===\n");
                System.out.println("Sign-in Options:");
                System.out.println("[1] Login with Existing Account"); 
                System.out.println("[2] Create New Account");
                System.out.println("[3] Exit Application");
                System.out.print("Enter your choice: ");

                if (sc.hasNextInt()) {
                    choice = sc.nextInt();
                    sc.nextLine();

                    if (choice == 1) {
                        System.out.println();
                        System.out.println("== Login with Existing Account ==");
                        flag2 = true;
                        while (flag2) {
                            System.out.print("Username: ");
                            username = sc.nextLine();
                            if (username.trim().isEmpty()) { // checks if username is empty
                                System.out.println("\nUsername cannot be empty. Please try again\n");
                            } else {
                                flag2 = false;
                            }
                        }
                        flag2 = true;
                        while (flag2) {
                            System.out.print("Password: ");
                            password = sc.nextLine();
                            if (password.trim().isEmpty()) { // checks if password is empty
                                System.out.println("\nPassword cannot be empty. Please try again\n");
                            } else {
                                flag2 = false;
                            }
                        }
                        success = Account.loginAccount(username, password); // sets into current account
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
                        flag2 = true;
                        while (flag2) {
                            System.out.print("Username: ");
                            username = sc.nextLine();
                            if (username.trim().isEmpty()) { // checks if username is empty
                                System.out.println("\nUsername cannot be empty. Please try again\n");
                            } else {
                                flag2 = false;
                            }
                        }
                        flag2 = true;
                        while (flag2) {
                            System.out.print("Password: ");
                            password = sc.nextLine();
                            if (password.trim().isEmpty()) { // checks if password is empty
                                System.out.println("\nPassword cannot be empty. Please try again\n");
                            } else {
                                flag2 = false;
                            }
                        }
                        success = Account.createAccount(username, password); // creates account instance
                        if (success) {
                            System.out.println();
                            System.out.println("Account created successfully. You may now login with your provided credentials.");
                        } else {
                            System.out.println();
                            System.out.println("Username already exists. Please choose a different username.");
                        }

                    } else if (choice == 3) { // exits application
                        flag2 = true;

                        while (flag2) {
                            System.out.println("\n== Exit Application ==");
                            System.out.println("Are you sure you want to proceed?");
                            System.out.println("[1] Yes");
                            System.out.println("[2] No");
                            System.out.print("Enter your choice: ");

                            if (sc.hasNextInt()) {
                                choice = sc.nextInt();
                                sc.nextLine();

                                if (choice == 1) { // proceeds to exit application
                                    System.out.println("\nThank you for using our Digital Calendar!");
                                    flag2 = false;
                                    flag = false;
                                } else if (choice == 2){
                                    flag2 = false;
                                } else {
                                    System.out.println("\nInvalid choice. Please try again.");
                                }
                            } else {
                                System.out.println("\nInvalid input. Please enter a valid number");
                                sc.nextLine(); // clears invalid input
                            }
                        }
                    } else {
                        System.out.println("\nInvalid choice. Please try again.");
                    }    
                } else {
                    System.out.println("\nInvalid input. Please enter a valid number");
                    sc.nextLine(); // clears invalid input
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
        sc.close(); //close the scanner
    }
}
