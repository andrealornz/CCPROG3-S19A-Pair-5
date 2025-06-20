import java.util.ArrayList;

public class Account {
    private final String username;
    private final String password;
    private boolean activity;
    private Domain domain = Domain.getDomain();
    private Calendar defaultCalendar;
    private ArrayList<Calendar> privateCalendars;
    private ArrayList<Calendar> linkedCalendars;

    public Account (String username, String password) {
        this.username = username;
        this.password = password;
        this.activity = true;
        this.defaultCalendar = new Calendar(this.username, this.username);
        this.privateCalendars = new ArrayList<Calendar>();
        this.linkedCalendars = new ArrayList<Calendar>();
    }

    public String getUsername() {
        return this.username;
    }

    public void createPublicCalendar(String name, String owner) {
        Calendar calendar = new Calendar(name, owner);
        domain.addCalendar(calendar);
    }
}