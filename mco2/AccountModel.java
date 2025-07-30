import java.util.ArrayList;

/** 
 * This is the AccountModel class, the class containing all the methods for manipulating Account container objects
 */


public class AccountModel {
    // attributes
    private final ArrayList<Account> accountList;
    private Account currentAccount;
    private CalendarModel calendarModel;
    
    // class constructor
    /**
     * the constructor wherein providing a calendarModel intsance is provided to create a new AccountModel
     * @param calendarModel the inputted calendarModel
     */
    public AccountModel(CalendarModel calendarModel) {
        this.accountList = new ArrayList<Account>();
        this.calendarModel = calendarModel;
    }

    // getters, setters
    /** 
     * retrieves the final AccountModel's accountList
     * @return the accountList of the class
     */
    public ArrayList<Account> getAccountList() {
        return this.accountList;
    }
    
    /**
     * retrieves the current account in session
     * @return currentAccount
     */
    public Account getCurrentAccount() {
        return this.currentAccount;
    }

    /** 
     * sets the current account in session to the provided account
     * @param account the account to be the new account in session
     */
    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    //methods 
    
    /** 
     * create a new Account instance given that the provided username is unique
     * @param username the inputted username
     * @param password the inputted password
     * @return true if successful
     */
    public boolean createAccount(String username, String password) {
        boolean success = true;
        // check for accounts with same username
        for (Account account : this.accountList) {
            if (account.getUsername().equals(username)) {
                success = false;
            }
        }
        // create and add new account to accountList
        if (success) {
            success = this.accountList.add(new Account(username, password));
        }
        
        return success;
    }

    /** 
     * attempts to set current account in session according to provided credentials
     * @param username the inputted username
     * @param passord the inputted password
     * @return true if successul
     */
    public boolean loginAccount(String username, String password) {
        boolean success = false;
        for (Account account : this.accountList) {
            // find non-deactivated account with matching username and password
            if ((account.getUsername().equals(username) && account.getPassword().equals(password)) && account.getActivity() == true) {
                this.currentAccount = account;
                success = true;
            }
        }
        return success;
    }

    /** 
     * clears current account from session
     */
    public void logoutAccount() {
        this.currentAccount = null;
    }

    /** 
     * deactivates current account from session before logging out
     */
    public void deleteAccount() {
        this.currentAccount.setActivity(false);
        this.logoutAccount();
    }

    /** 
     * compares each calendar in each account for matching names
     * @param name the inputted calendar name
     * @return true if no calendar with similar name is found
     */
    private boolean isCalendarNameUnique(String name) {
        boolean isUnique = true;
        // search through all accounts
        for (Account account : this.accountList) {
            // search through all calendars
            for (Calendar calendar : account.getCalendars()) {
                if (calendar.getName().equals(name)) {
                    isUnique = false;
                }
            }
        }
        return isUnique;
    }

    /** 
     * adds given calendar instance to current account's calendarList
     * @param calendar the inputted calendar
     * @return true if successful
     */
    public boolean acceptCalendar(Calendar calendar) {
        boolean success = false;
        // if session has account
        if (this.currentAccount != null) {
            boolean alreadyHas = false;
            // search through each calendar for similar instance
            for (Calendar userCalendar : this.currentAccount.getCalendars()) {
                if (userCalendar.getName().equals(calendar.getName())) {
                    alreadyHas = true;
                }
            }
            // add calendar if no similar instance found
            if (!alreadyHas) {
                success = this.currentAccount.getCalendars().add(calendar);
            }
        }
        return success;
    }

    /** 
     * adds given family calendar instance to current account's calendarList when accessCode is correct
     * @param family the inputted family calendar
     * @param accessCode the inputted access code
     * @return true if successful
     */
    public boolean acceptCalendar(Family family, String accessCode) {
        boolean success = false;
        if (family.getAccessCode().equals(accessCode)) {
            // Check if current user already has this calendar, not if it's globally unique
            boolean alreadyHas = false;
            if (this.currentAccount != null) {
                for (Calendar userCalendar : this.currentAccount.getCalendars()) {
                    if (userCalendar.getName().equals(family.getName())) {
                        alreadyHas = true;
                    }
                }
            }
            if (!alreadyHas) {
                success = this.currentAccount.getCalendars().add(family);
            }
        }
        return success;
    }

    /** 
     * creates new calendar of subclass and add to current account's calendarList provided its name, type, and access code (leave empty if not family calendar)
     * @param name the inputted calendar name
     * @param type the inputted type of calendar
     * @param accessCode optional inputted access code
     * @return true if successful
     */
    public boolean createCalendar(String name, String type, String accessCode) {
        boolean success = false;
        if (isCalendarNameUnique(name)) {
            Calendar calendar = switch (type) {
                case "Personal" -> new Personal(name, this.currentAccount.getUsername());
                case "Public" -> new Public(name, this.currentAccount.getUsername());
                case "Family" -> new Family(name, this.currentAccount.getUsername(), accessCode);
                default -> new Personal(name, this.currentAccount.getUsername());
            };
            success = acceptCalendar(calendar);

            if (success && calendar instanceof Public) { // if calendar is public, add to list
                calendarModel.getPublicCalendarList().add(calendar);
            }
        }
        return success;
    }

    /** 
     * removes calendar reference from current account's calendarList
     * @param calendar the inputted calendar
     * @return true if successful
     */
    public boolean removeCalendar(Calendar calendar) {
        return this.currentAccount.getCalendars().remove(calendar);
    }

    /** 
     * removes calendar reference from every account's calendarList
     * @param calendar the inputted calendar
     * @return true if successful
     */
    public boolean deleteCalendar(Calendar calendar) {
        boolean success = false;
        // default calendar cannot be deleted
        if (!calendar.getName().equals(this.currentAccount.getUsername())) {
            for (Account account : this.accountList) {
                success = (account.getCalendars().remove(calendar)) || success; //true if at least one instance was deleted
            }
        }
        return success;
    }

    /** 
     * retrieves ArrayList containing calendars owned by the current account in their calendarList
     * @return ArrayList containing all owned calendars
     */
    public ArrayList<Calendar> getOwnedCalendars() {
        // if no account in session
        if (this.currentAccount == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Calendar> ownedCalendars = new ArrayList<>();
        String currentUsername = this.currentAccount.getUsername();
        // check for owned calendars and add to ownedCalendars
        for (Calendar calendar : this.currentAccount.getCalendars()) {
            if (calendar.getOwner().equals(currentUsername)) {
                ownedCalendars.add(calendar);
            }
        }
        
        return ownedCalendars;
    }

    /** 
     * retrieves ArrayList containing calendars not owned by the current account in their calendarList
     * @return the ArrayList containing all added calendars
     */
    public ArrayList<Calendar> getAddedCalendars() {
        // if no account is in session
        if (this.currentAccount == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Calendar> addedCalendars = new ArrayList<>();
        String currentUsername = this.currentAccount.getUsername();
        // check for calendars not owned by current account
        for (Calendar calendar : this.currentAccount.getCalendars()) {
            if (!calendar.getOwner().equals(currentUsername)) {
                addedCalendars.add(calendar);
            }
        }
        
        return addedCalendars;
    }

    /** 
     * retrieves the names of all calendars owned by current account in their calendarList
     * @return ArrayList of names of owned calendars
     */
    public ArrayList<String> getOwnedCalendarNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Calendar calendar : getOwnedCalendars()) {
            names.add(calendar.getName());
        }
        return names;
    }

    /** 
     * retrieve the names of all calendars not owned by current account in their calendarList
     * @return ArrayList of names of added calendars
     */
    public ArrayList<String> getAddedCalendarNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Calendar calendar : getAddedCalendars()) {
            names.add(calendar.getName());
        }
        return names;
    }

    /**
     * retrieves the names of every public calendar
     * @return ArrayList of names of every public calendar in existence
     */
    public ArrayList<String> getAllPublicCalendarNames() {
        ArrayList<String> publicCalendarNames = new ArrayList<>();
        // check through every account's calendars
        for (Account account : this.accountList) {
            for (Calendar calendar : account.getCalendars()) {
                if (calendar instanceof Public && calendar.getOwner().equals(account.getUsername())) {
                    // if current account does not have the calendar added yet
                    boolean alreadyHas = false;
                    if (this.currentAccount != null) {
                        for (Calendar userCalendar : this.currentAccount.getCalendars()) {
                            if (userCalendar.getName().equals(calendar.getName())) {
                                alreadyHas = true;
                                break;
                            }
                        }
                    }
                    if (!alreadyHas) {
                        publicCalendarNames.add(calendar.getName());
                    }
                }
            }
        }
        
        return publicCalendarNames;
    }

    /** 
     * retrieves the names of every family calendar
     * @return ArrayList of names of every family calendar in existence
     */
    public ArrayList<String> getAllFamilyCalendarNames() {
        ArrayList<String> familyCalendarNames = new ArrayList<>();
        // check through each account's calendars
        for (Account account : this.accountList) {
            for (Calendar calendar : account.getCalendars()) {
                if (calendar instanceof Family && calendar.getOwner().equals(account.getUsername())) {
                    // Only add if current user doesn't already have this calendar
                    boolean alreadyHas = false;
                    if (this.currentAccount != null) {
                        for (Calendar userCalendar : this.currentAccount.getCalendars()) {
                            if (userCalendar.getName().equals(calendar.getName())) {
                                alreadyHas = true;
                                break;
                            }
                        }
                    }
                    if (!alreadyHas) {
                        familyCalendarNames.add(calendar.getName());
                    }
                }
            }
        }
        
        return familyCalendarNames;
    }

    /** 
     * searches every account for calendar with provided name
     * @param calendarName the inputted calendar name
     * @return found calendar with matching name
     */
    public Calendar findCalendarByNameFromAllAccounts(String calendarName) {
        Calendar foundCalendar = null;
        
        for (Account account : this.accountList) {
            for (Calendar calendar : account.getCalendars()) {
                if (calendar.getName().equals(calendarName) && calendar.getOwner().equals(account.getUsername())) {
                    foundCalendar = calendar;
                    break;
                }
            }
            if (foundCalendar != null) {
                break;
            }
        }
        
        return foundCalendar;
    }

    /** 
     * searches current account for calendar with provided name
     * @param calendarName the inputted calendar name
     * @return the found calendar with matching name
     */
    public Calendar findCalendarByName(String calendarName) {
        if (this.currentAccount == null) {
            return null;
        }
        
        for (Calendar calendar : this.currentAccount.getCalendars()) {
            if (calendar.getName().equals(calendarName)) {
                return calendar;
            }
        }
        return null;
    }
}
