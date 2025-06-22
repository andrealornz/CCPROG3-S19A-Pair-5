import java.util.ArrayList;

public class Account {
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
        this.defaultCalendar = new Calendar(this.username, this.username);
        this.calendarList = new ArrayList<Calendar>();
    }

    // getters, setters
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

    // methods

    /*
        returns true if it successfully creates a new account after searching accountList if it already exists
        @param newUsername
        @param newPassword 
    */
    public static boolean createAccount(String newUsername, String newPassword) {
        boolean success = true;
        if (accountList.isEmpty()) { //add acc if accountList is empty
            accountList.add(new Account(newUsername, newPassword));
        } else {
            for (Account acc : accountList) { //add acc if it doesn't already exist in accountList
                if (acc.getUsername().equals(newUsername)) {
                    success = false;
                } else {
                    accountList.add(new Account(newUsername, newPassword));
                }
            }
        }
        return success;
    }

    public Calendar createCalendar(String newName) {
        return new Calendar(newName, this.username);
    }

    /*
        returns true if it sets current session (currentAccount) to account with matching credentials
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