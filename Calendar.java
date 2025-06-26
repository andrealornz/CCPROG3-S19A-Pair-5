import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;

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
        searches entry list for entry with given date and title and removes it. returns true if successful
        @param date
        @param title
     */
    public boolean deleteEntry(LocalDate date, String title) {
        boolean success = false;

        Entry entryToRemove = null;
        for (Entry entry : this.getEntries()) {
            if (entry.getDate().equals(date) && entry.getTitle().equals(title)) {
                entryToRemove = entry;
            }
        }
        
        if (entryToRemove != null) {
            success = this.getEntries().remove(entryToRemove);
        }

        return success;
    }

    /* 
        displays the monthly calendar view for the selected month
        @param currentMonth - the month to be viewed
    */
    public void displayMonthlyView(YearMonth currentMonth) {
        Account currentAccount = Account.getCurrentAccount();
        System.out.println("\n== " + currentAccount.getUsername() + "'s \"" + this.name + "\" Calendar ==");

        String monthName = currentMonth.getMonth().toString(); // get month name as string
        System.out.println("\n\t\t\t\t\t\t" + monthName + " " + currentMonth.getYear()); // displays month and year

        System.out.println("+--------------+--------------+--------------+--------------+--------------+--------------+--------------+");
        System.out.println("|     Sun      |     Mon      |     Tue      |     Wed      |     Thu      |     Fri      |     Sat      |");
        System.out.println("+--------------+--------------+--------------+--------------+--------------+--------------+--------------+");

        LocalDate firstDay = currentMonth.atDay(1); // first day of the month
        int daysInMonth = currentMonth.lengthOfMonth(); // total days in the month
        int startDayOfWeek = firstDay.getDayOfWeek().getValue() % 7; // starting day of the week (0 = sunday, 1 = monday, ...)

        // a grid to track which day is in a cell
        int[][] dayGrid = new int[6][7];
        int currentDay = 1;
        
        // fills the grid with numbers
        for (int week = 0; week < 6; week++) {
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                if ((week == 0 && dayOfWeek < startDayOfWeek) || currentDay > daysInMonth) {
                    dayGrid[week][dayOfWeek] = 0; // no day
                } else {
                    dayGrid[week][dayOfWeek] = currentDay;
                    currentDay++;
                }
            }
        }

        // displays each week by row (there are 4 rows per day to make space for the entries)
        for (int week = 0; week < 6 && dayGrid[week][0] > 0 || (week < 6 && hasAnyDay(dayGrid[week])); week++) {
            // first row displays day numbers
            System.out.print("|");
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) {
                int day = dayGrid[week][dayOfWeek];
                if (day == 0) {
                    System.out.print("              |"); // empty cell or no day
                } else {
                    System.out.printf("  %-2d          |", day); // day number
                }
            }
            System.out.println();

            // sorts entires for all days in the week
            ArrayList<Entry>[] sortedEntries = new ArrayList[7]; // stores sorted entries for a specific day
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) { // loops days of the week
                int day = dayGrid[week][dayOfWeek];
                if (day > 0) { // if there is a day in the grid
                    LocalDate date = currentMonth.atDay(day);
                    ArrayList<Entry> entriesForDay = this.getEntriesForDate(date);
                    if (!entriesForDay.isEmpty()) {  // if the day has entries
                        sortedEntries[dayOfWeek] = new ArrayList<>(entriesForDay); // stores the entries for a specific date
                        sortedEntries[dayOfWeek].sort((e1, e2) -> e1.getStartTime().compareTo(e2.getStartTime()));
                    } else { // if the day has no entries
                        sortedEntries[dayOfWeek] = new ArrayList<>();
                    }
                } else {
                    sortedEntries[dayOfWeek] = new ArrayList<>();
                }
            }

            // second row displays the first row of entry titles
            System.out.print("|");
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) { // loops days of the week
                int day = dayGrid[week][dayOfWeek];
                if (day == 0) {
                    System.out.print("              |"); // empty cell
                } else {
                    if (!sortedEntries[dayOfWeek].isEmpty()) { // if a day has entries
                        String entryTitle = sortedEntries[dayOfWeek].get(0).getTitle(); // get title
                        if (entryTitle.length() > 12) { // shorten long entry titles
                            entryTitle = entryTitle.substring(0, 9) + "...";
                        }
                        System.out.printf(" %-13s|", entryTitle);
                    } else {
                        System.out.print("              |"); // no entry for the date
                    }
                }
            }
            System.out.println();

            // third row displays the second row of entry titles
            System.out.print("|");
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) { // loops days of the week
                int day = dayGrid[week][dayOfWeek];
                if (day == 0) {
                    System.out.print("              |"); // empty cell
                } else {
                    if (sortedEntries[dayOfWeek].size() > 1) { // if there is more than 1 entry in a day
                        String entryTitle = sortedEntries[dayOfWeek].get(1).getTitle(); // gets title
                        if (entryTitle.length() > 12) { // shortens long entry titles
                            entryTitle = entryTitle.substring(0, 9) + "...";
                        }
                        System.out.printf(" %-13s|", entryTitle);
                    } else {
                        System.out.print("              |"); // no entry for the date
                    }
                }
            }
            System.out.println();

            // fourth row displays third row of entry titles
            System.out.print("|");
            for (int dayOfWeek = 0; dayOfWeek < 7; dayOfWeek++) { // loops days of the week
                int day = dayGrid[week][dayOfWeek];
                if (day == 0) {
                    System.out.print("              |"); // empty cell
                } else {
                    if (sortedEntries[dayOfWeek].size() > 2) { // if there is more than 2 entries in a day
                        if (sortedEntries[dayOfWeek].size() == 3) { // if there is exactly 3 entries in a day
                            String entryTitle = sortedEntries[dayOfWeek].get(2).getTitle();
                            if (entryTitle.length() > 12) { // shortens long entry titles
                                entryTitle = entryTitle.substring(0, 9) + "...";
                            }
                            System.out.printf(" %-13s|", entryTitle);
                        } else { // if there is more than 3 entries in a day
                            int moreCount = sortedEntries[dayOfWeek].size() - 2; // counts how many more entries there are
                            System.out.printf(" +%d more      |", moreCount);
                        }
                    } else {
                        System.out.print("              |");
                    }
                }
            }
            System.out.println();

            if (week < 5 && hasAnyDay(dayGrid[week + 1])) { // checks if it is the last day of the week or not
                System.out.println("+--------------+--------------+--------------+--------------+--------------+--------------+--------------+");
            }
        }
        
        System.out.println("+--------------+--------------+--------------+--------------+--------------+--------------+--------------+");
    }

    // helper method
    // checks if a week contains any days
    private boolean hasAnyDay(int[] week) { 
        boolean dayFound = false;
        
        for (int day : week) { 
            if (day > 0) {
                dayFound = true;
            }
        }
        return dayFound;
    }
}
