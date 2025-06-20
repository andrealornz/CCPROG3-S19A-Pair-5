import java.util.ArrayList;

public class Account {
    private final String username;
    private final String password;
    private boolean activity;
    private Domain domain = Domain.getDomain();
    private Calendar defaultCalendar;
    private ArrayList<Calendar> privateCalendars;
    private ArrayList<Calendar> linkedCalendars;

    public Account (String newUsername, String newPassword) {
        this.username = newUsername;
        this.password = newPassword;
        this.activity = true;
        this.defaultCalendar = new Calendar(this.username, this.username);
        this.privateCalendars = new ArrayList<Calendar>();
        this.linkedCalendars = new ArrayList<Calendar>();
    }

    public String getUsername() {
        return this.username;
    }

    public void createPublicCalendar(String newName) {
        Calendar calendar = new Calendar(newName, this.username);
        domain.addCalendar(calendar);
    }
}