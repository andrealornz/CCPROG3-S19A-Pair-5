import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Calendar {
    // attributes
    private String name;
    private String owner;
    private boolean access; // (true) public (false) private
    private ArrayList<Entry> entries;
    private static ArrayList<Calendar> publicCalendarList;

    // class constructor
    public Calendar(String name, String owner, boolean access) {
        this.name = name;
        this.owner = owner;
        this.access = access;
        this.entries = new ArrayList<Entry>();

        if (access) { // immediately adds to public calendar list
            getPublicCalendarList().add(this); //leaking warning occurs at end of constructor so its fine
        }
    }
    
    // getters & setters
    public static ArrayList<Calendar> getPublicCalendarList() {
        if (publicCalendarList == null) {
            Calendar.publicCalendarList = new ArrayList<Calendar>();
        }
        return publicCalendarList;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return this.owner;
    }

    public boolean getAccess() {
        return this.access;
    }

    public void setAccess(boolean access) {
        // private to public 
        if (!this.access && access) {
            getPublicCalendarList().add(this);
        }
        // public to private
        else if (this.access && !access) {
            getPublicCalendarList().remove(this);
    }
    
    this.access = access;
    }

    public ArrayList<Entry> getEntries() {
        if (entries == null) {
            this.entries = new ArrayList<Entry>();
        }
        return entries;
    }

    // methods
    public static void removeFromPublicList(Calendar calendar) {
        if (getPublicCalendarList().contains(calendar)) {
            getPublicCalendarList().remove(calendar);
        }
    }

    public void createEntry(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details) {
        Entry newEntry = new Entry(title, date, startTime, endTime, details);
        entries.add(newEntry); 
    }

    public void deleteEntry(Entry entry) {
        if (this.entries.contains(entry)) {
            entries.remove(entry);
        }
    }

    public ArrayList<Entry> getEntriesForDate(LocalDate date) {
        ArrayList<Entry> entriesForDate = new ArrayList<Entry>();

        for (Entry entry : entries) {
            if (entry.getDate().equals(date)) {
                entriesForDate.add(entry);
            }
        }

        return entriesForDate;
    }
}
