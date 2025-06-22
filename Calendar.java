import java.util.ArrayList;

public class Calendar {
    private String name;
    private String owner;
    private static ArrayList<Calendar> publicCalendarList;

    // class constructor
    public Calendar(String newName, String newOwner) {
        this.name = newName;
        this.owner = newOwner;
    }

    // getters, setters
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
}