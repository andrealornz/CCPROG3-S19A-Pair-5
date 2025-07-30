import java.util.ArrayList;

public class AccountModel {
    private final ArrayList<Account> accountList;
    private Account currentAccount;
    private CalendarModel calendarModel;

    public AccountModel(CalendarModel calendarModel) {
        this.accountList = new ArrayList<Account>();
        this.calendarModel = calendarModel;
    }

    public ArrayList<Account> getAccountList() {
        return this.accountList;
    }

    public Account getCurrentAccount() {
        return this.currentAccount;
    }

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    public boolean createAccount(String username, String password) {
        boolean success = true;

        for (Account account : this.accountList) {
            if (account.getUsername().equals(username)) {
                success = false;
            }
        }
        
        if (success) {
            success = this.accountList.add(new Account(username, password));
        }
        
        return success;
    }

    public boolean loginAccount(String username, String password) {
        boolean success = false;
        for (Account account : this.accountList) {
            if ((account.getUsername().equals(username) && account.getPassword().equals(password)) && account.getActivity() == true) {
                this.currentAccount = account;
                success = true;
            }
        }
        return success;
    }

    public void logoutAccount() {
        this.currentAccount = null;
    }

    public void deleteAccount() {
        this.currentAccount.setActivity(false);
        this.logoutAccount();
    }

    private boolean isCalendarNameUnique(String name) {
        boolean isUnique = true;
        for (Account account : this.accountList) {
            for (Calendar calendar : account.getCalendars()) {
                if (calendar.getName().equals(name)) {
                    isUnique = false;
                }
            }
        }
        return isUnique;
    }

    public boolean acceptCalendar(Calendar calendar) {
        boolean success = false;
        if (this.currentAccount != null) {
            boolean alreadyHas = false;
            for (Calendar userCalendar : this.currentAccount.getCalendars()) {
                if (userCalendar.getName().equals(calendar.getName())) {
                    alreadyHas = true;
                }
            }
            if (!alreadyHas) {
                success = this.currentAccount.getCalendars().add(calendar);
            }
        }
        return success;
    }

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

    public boolean removeCalendar(Calendar calendar) {
        return this.currentAccount.getCalendars().remove(calendar);
    }

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

    public ArrayList<Calendar> getOwnedCalendars() {
        if (this.currentAccount == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Calendar> ownedCalendars = new ArrayList<>();
        String currentUsername = this.currentAccount.getUsername();
        
        for (Calendar calendar : this.currentAccount.getCalendars()) {
            if (calendar.getOwner().equals(currentUsername)) {
                ownedCalendars.add(calendar);
            }
        }
        
        return ownedCalendars;
    }

    public ArrayList<Calendar> getAddedCalendars() {
        if (this.currentAccount == null) {
            return new ArrayList<>();
        }
        
        ArrayList<Calendar> addedCalendars = new ArrayList<>();
        String currentUsername = this.currentAccount.getUsername();
        
        for (Calendar calendar : this.currentAccount.getCalendars()) {
            if (!calendar.getOwner().equals(currentUsername)) {
                addedCalendars.add(calendar);
            }
        }
        
        return addedCalendars;
    }

    public ArrayList<String> getOwnedCalendarNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Calendar calendar : getOwnedCalendars()) {
            names.add(calendar.getName());
        }
        return names;
    }

    public ArrayList<String> getAddedCalendarNames() {
        ArrayList<String> names = new ArrayList<>();
        for (Calendar calendar : getAddedCalendars()) {
            names.add(calendar.getName());
        }
        return names;
    }

    public ArrayList<String> getAllPublicCalendarNames() {
        ArrayList<String> publicCalendarNames = new ArrayList<>();
        
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

    public ArrayList<String> getAllFamilyCalendarNames() {
        ArrayList<String> familyCalendarNames = new ArrayList<>();
        
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