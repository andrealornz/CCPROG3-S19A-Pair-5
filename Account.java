import java.util.ArrayList;

public class Account {
    // attributes
    private static ArrayList<Account> accountList = new ArrayList<Account>(); //array list of all accounts in system
    private static Account currentAccount = null; //current account in session
    private final String username;
    private final String password;
    private boolean activity;
    private final Calendar defaultCalendar;
    private ArrayList<Calendar> calendarList; //array list of account's private and linked public calendars

    // class constructor
    public Account (String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        this.activity = true;
        this.defaultCalendar = new Calendar(this.username, this.username, false); //create default calendar named after user
        this.calendarList = new ArrayList<Calendar>();
        getCalendarList().add(this.defaultCalendar);
    }

    // getters, setters
    public static ArrayList<Account> getAccountList() {
        return accountList;
    }

    public static Account getCurrentAccount() {
        return currentAccount;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() { //would ironically be a massive security risk
        return this.password;
    }

    public boolean getActivity() {
        return this.activity;
    }

    public void setActivity(boolean newActivity) {
        this.activity = newActivity;
    }

    public Calendar getDefaultCalendar() {
        return this.defaultCalendar;
    }

    public ArrayList<Calendar> getCalendarList() {
        return this.calendarList;
    }

    // methods

    /**
     * creates a new account after searching accountList if it doesn't already exists
     * prerequisite: username and password are not empty
     * @param newUsername Inputted new username
     * @param newPassword Inputted new password
     * @return true if successful
     */
    public static boolean createAccount(String newUsername, String newPassword) {
        boolean accExists = false;
        boolean success = false;
        for (Account acc : accountList) { //search accountList for existing users with same input username
            if (acc.getUsername().equals(newUsername)) { 
                accExists = true; 
            }
        }
        if (accExists != true) { //no users with same input username
            accountList.add(new Account(newUsername, newPassword)); // waits for loop to terminate before creating account
            success = true;
        }
        return success;
    }

    /**
     * creates calendar inside of either public list or user's calendar list then returns new calendar
     * with set name and set access if given name isnt username
     * prerequisite: newName is not empty
     * @param newName Inputted calendar name
     * @param newAccess true if public, false if private
     * @return calendar Calendar object
     */
    public Calendar createCalendar(String newName, boolean newAccess) {
        Calendar newCalendar = null;
        if (!newName.equals(this.username)) { //check if newName doesnt match username
            newCalendar = new Calendar(newName, this.username, newAccess);
            this.calendarList.add(newCalendar);
        }
        return newCalendar;
    }

    /**
     * adds given calendar into calendarList
     * prerequisite: calendar instance exists
     * @param calendar instance to be added
     * @return true if successful
     */
    public boolean acceptCalendar(Calendar calendar) {
        boolean success = false;
        if (calendar.getAccess() && !this.calendarList.contains(calendar)) { //check if access is public and isnt already in calendarList
            this.calendarList.add(calendar);
            success = true;
        }
        return success;
    }

    /**
     * removes given public calendar from calendarList
     * prerequisite: calendar instance exists
     * @param calendar
     * @return true if successful
     */
    public boolean removeCalendar(Calendar calendar) {
        boolean success = false;
        if (this.calendarList.contains(calendar) && calendar.getAccess()) { //check if calendar is in calendarList and is public
            success = this.calendarList.remove(calendar);
        }
        return success;
    }

    /**
     * deletes given calendar reference from publicCalendarList and each account's own calendarList 
     * except for default calendar only if owned by account
     * prerequisite: calendar instance exists
     * @param calendar
     * @return true if successful
     */
    public boolean deleteCalendar(Calendar calendar) {
        boolean success = false;
        if (calendar.getOwner().equals(this.username) && !calendar.getName().equals(this.username)) { //check if calendar is owned by account and not the default calendar
            if (calendar.getAccess()) // if public
                success = Calendar.removeFromPublicList(calendar) && this.calendarList.remove(calendar); //delete calendar from own account, other's accounts, and public list
            else // if private
                success = this.calendarList.remove(calendar);
        }
        return success;
    }

    /**
     * sets current session (currentAccount) to account with matching credentials
     * prerequisite: input username and password are not empty
     * @param inputUsername
     * @param inputPassword
     * @return true if successful
     */
    public static boolean loginAccount(String inputUsername, String inputPassword) {
        boolean success = false;
        for (Account acc : accountList) { //search accountList for account with matching credentials
            if (acc.getUsername().equals(inputUsername) && acc.getPassword().equals(inputPassword) && acc.getActivity() == true) {
                currentAccount = acc;
                success = true;
            }
        }
        return success;
    }

    /**
     * sets current session's (currentAccount) to null
     * @return void
     */
    public static void logoutAccount() {
        currentAccount = null;
    }
    
    /**
     * sets current session's (currentAccount) activity to false
     * @return void
     */
    public void deleteAccount() {
        this.activity = false;
    }
}
