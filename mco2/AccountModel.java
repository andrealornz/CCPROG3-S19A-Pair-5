import java.util.ArrayList;

public class AccountModel {
    private final ArrayList<Account> accountList;
    private Account currentAccount;

    public AccountModel() {
        this.accountList = new ArrayList<Account>();
    }

    public ArrayList<Account> getAccountList() {
        return this.accountList;
    }

    public Account getCurreAccount() {
        return this.currentAccount;
    }

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
    }

    public boolean createAccount(String username, String password) {
        return this.accountList.add(new Account(username, password));
    }

    public boolean loginAccount(String username, String password) {
        boolean success = false;
        for (Account account : this.accountList) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
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
        this.accountList.remove(this.currentAccount);
        this.logoutAccount();
    }

    public boolean acceptCalendar(Calendar calendar) { //just pass a constructor funcction
        return this.currentAccount.getCalendars().add(calendar);
    }

    public boolean acceptCalendar(Family family, String accessCode) {
        return (family.getAccessCode().equals(accessCode)) ? this.currentAccount.getCalendars().add(family) : false;
    }

    public boolean createCalendar(String name, String type, String accessCode) {
        boolean success = true;
        Calendar calendar = switch (type) {
            case "Personal" -> new Personal(name, this.currentAccount.getUsername());
            case "Public" -> new Public(name, this.currentAccount.getUsername());
            case "Family" -> new Family(name, this.currentAccount.getUsername(), accessCode);
            default -> new Personal(name, this.currentAccount.getUsername());
        };
        acceptCalendar(calendar);
        return success;
    }

    public boolean removeCalendar(Calendar calendar) {
        return this.currentAccount.getCalendars().remove(calendar);
    }

    public boolean deleteCalendar(Calendar calendar) {
        boolean success = false;
        for (Account account : this.accountList) {
            success = (account.getCalendars().remove(calendar)) || success; //true if at least one instance was deleted
        }
        return success;
    }
}