import java.util.ArrayList;

public class Calendar {
    private String name;
    private String owner;
    private ArrayList<String> linkedAccounts;
    private static ArrayList<Calendar> publicCalendarList;

    public Calendar(String newName, String newOwner) {
        this.name = newName;
        this.owner = newOwner;
        this.linkedAccounts = new ArrayList<String>();
    }

    public static ArrayList<Calendar> getPublicCalendarList() {
        if (publicCalendarList == null) {
            publicCalendarList = new ArrayList<Calendar>();
        }
        return publicCalendarList;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public ArrayList<String> getLinkedAccounts () {
        return this.linkedAccounts;
    }
}