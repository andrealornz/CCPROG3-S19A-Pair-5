import java.util.ArrayList;

public class Account {
    private final String username;
    private final String password;
    private boolean activity;
    private Calendar defaultCalendar;
    private ArrayList<Calendar> privateCalendarList;

    public Account (String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        this.activity = true;
        this.defaultCalendar = new Calendar(this.username, this.username);
        this.privateCalendarList = new ArrayList<Calendar>();
    }

    public String getUsername() {
        return this.username;
    }

    public void createPublicCalendar(String newName) {
        Calendar calendar = new Calendar(newName, this.username);
        calendar.getLinkedAccounts().add(this.username);
        Calendar.getPublicCalendarList().add(calendar);
    }

    public void createPrivateCalendar(String newName) {
        Calendar calendar = new Calendar(newName, this.username);
        this.privateCalendarList.add(calendar);
    }
}