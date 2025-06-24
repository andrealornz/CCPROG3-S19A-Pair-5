import java.net.SecureCacheResponse;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Calendar {
    // attributes
    private String name;
    private final String owner;
    private boolean access; // (true) public (false) private
    private final ArrayList<Entry> entries;
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

    /*
        setter method adds and removes this calendar instance from public calendar list according to given access
        returns true if successful
        @param access
     */
    public boolean setAccess(boolean access) {
        boolean success = false;
        // private to public 
        if (!this.access && access) {
            success = getPublicCalendarList().add(this);
            this.access = access;
        }
        // public to private
        else if (this.access && !access) {
            success = removeFromPublicList(this);
            this.access = access;
        }
        
        return success;
    }

    public ArrayList<Entry> getEntries() {
        return this.entries;
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

    // methods

    /*
        searches publicCalendarList and each account (except for the owner) for given calendar instance and removes it.
        returns true if successful
        @param calendar
     */
    public static boolean removeFromPublicList(Calendar calendar) {
        boolean success = false;
        if (getPublicCalendarList().contains(calendar) && calendar.getAccess()) { //check if publicCalendarList has given calendar and given calendar is public access
            getPublicCalendarList().remove(calendar); //remove calendar from publicCalendarList
            for (Account acc : Account.getAccountList()) { //loop through accounts in accountList
                if (!calendar.getOwner().equals(acc.getUsername())) { //non-owners only
                    acc.removeCalendar(calendar); //remove calendar from each account's own calendarList
                }
            }
            success = true;
        }
        return success;
    }

    /*
        searches if any entry in entry list has same title as given title params and creates new entry if false.
        returns true if successful
        @param title
        @param date
        @param startTime
        @param endTime
        @param details
     */
    public boolean createEntry(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details) {
        boolean success = true;
        for (Entry entry : entries) { //search for entries with same title
            if (entry.getTitle().equals(title)) {
                success = false;
            }
        }
        if (success == true) { //create new entry
            entries.add(new Entry(title, date, startTime, endTime, details));
        }
        return success;
    }

    /*
        searches entry list for given entry and removes it. returns true if successful
        @param entry
     */
    public boolean deleteEntry(Entry entry) {
        boolean success = false;
        if (this.entries.contains(entry)) {
            entries.remove(entry);
            success = true;
        }
        return success;
    }

}
