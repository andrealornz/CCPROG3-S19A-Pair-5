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

    /*
        returns true if it successfully creates a new account after searching accountList if it already exists
        @param newUsername
        @param newPassword 
     */
    public static boolean createAccount(String newUsername, String newPassword) {
        boolean accExists = false;
        boolean success = false;
        for (Account acc : accountList) { 
            if (acc.getUsername().equals(newUsername)) { 
                accExists = true; 
            }
        }
        if (accExists != true) {
            accountList.add(new Account(newUsername, newPassword)); // waits for loop to terminate before creating account
            success = true;
        }
        return success;
    }

    /*
        creates calendar inside of either public list or user's calendar list then returns new calendar
        with set name and set access if given name isnt username
        @param newName
        @param newAccess
     */
    public Calendar createCalendar(String newName, boolean newAccess) {
        Calendar newCalendar = null;
        if (!newName.equals(this.username)) { //check if newName doesnt match username
            newCalendar = new Calendar(newName, this.username, newAccess);
            this.calendarList.add(newCalendar);
        }
        return newCalendar;
    }

    /*
        adds given calendar into calendarList and returns true if successful
        @param calendar
     */
    public boolean acceptCalendar(Calendar calendar) {
        boolean success = false;
        if (calendar.getAccess() && !this.calendarList.contains(calendar)) { //check if access is public and isnt already in calendarList
            this.calendarList.add(calendar);
            success = true;
        }
        return success;
    }

    /*
        removes given calendar from calendarList if public and returns true if successful
        @param calendar
     */
    public boolean removeCalendar(Calendar calendar) {
        boolean success = false;
        if (this.calendarList.contains(calendar) && calendar.getAccess()) { //check if calendar is in calendarList and is public
            success = this.calendarList.remove(calendar);
        }
        return success;
    }

    /*
        deletes given calendar reference from publicCalendarList and each account's own calendarList 
        except for default calendar only if owned by account and returns true if successful
        @param calendar
     */
    public boolean deleteCalendar(Calendar calendar) {
        boolean success = false;
        if (calendar.getOwner().equals(this.username) && !calendar.getName().equals(this.username)) { //check if calendar is owned by account and not the default calendar
            success = Calendar.removeFromPublicList(calendar) && this.calendarList.remove(calendar); //delete calendar from own account, other's accounts, and public list
        }
        return success;
    }

    /*
        sets current session (currentAccount) to account with matching credentials and returns true if successful
        @param inputUsername
        @param inputPassword
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

    /*
        sets current sessions (currentAccount) to null
     */
    public static void logoutAccount() {
        currentAccount = null;
    }

    public void deleteAccount() {
        this.activity = false;
    }
}
