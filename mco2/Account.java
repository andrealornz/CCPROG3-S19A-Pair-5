import java.util.ArrayList;

public class Account {
    private final String username;
    private final String password;
    private boolean activity;
    private final ArrayList<Calendar> calendars;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.activity = true;
        this.calendars = new ArrayList<Calendar>();
        this.calendars.add(new Personal(this.username, this.username));
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean getActivity() {
        return this.activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public ArrayList<Calendar> getCalendars() {
        return this.calendars;
    }
}