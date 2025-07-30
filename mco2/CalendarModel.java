import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * This is the CalendarModel class for storing and managing public calendar data
 */
public class CalendarModel {
    // attributes
    private final ArrayList<Calendar> publicCalendarList;

    // class constructor
    /**
     * creates a new calendar model instance
     */
    public CalendarModel() {
        this.publicCalendarList = new ArrayList<Calendar>();
    }

    /**
     * returns the list of public calendars
     * @return list of public calendars
     */
    public ArrayList<Calendar> getPublicCalendarList() {
        return this.publicCalendarList;
    }

    /**
     * returns a list of entries from the given calendar that match the given date
     * @param calendar the calendar to search
     * @param date the date to filter by
     * @return list of entries for the date
     */
    public ArrayList<Entry> getEntriesForDate(Calendar calendar, LocalDate date) {
        ArrayList<Entry> entriesForDate = new ArrayList<Entry>();
        for (Entry entry : calendar.getEntries()) {
            if (entry.getDate().equals(date)) {
                entriesForDate.add(entry);
            }
        }
        return entriesForDate;
    }

    /**
     * returns a list of entries from the given calendar that fall on the given week of the month
     * @param calendar the calendar to search
     * @param week the week of the month to filter by
     * @return list of entries for the week
     */
    public ArrayList<Entry> getEntriesForWeek(Calendar calendar, int week) {
        ArrayList<Entry> entriesForWeek = new ArrayList<Entry>();
        for (Entry entry : calendar.getEntries()) {
            if ((entry.getDate().getDayOfMonth() / 7) + 1 == week) {
                entriesForWeek.add(entry);
            }
        }
        return entriesForWeek;
    }

    /**
     * sorts entries in the given calendar by type, priority, time, and title
     * @param calendar the calendar to sort
     */
    public void sortEntries(Calendar calendar) {
        calendar.getEntries().sort((e1, e2) -> {
            boolean e1IsJournal = e1 instanceof Journal;
            boolean e2IsJournal = e2 instanceof Journal;
            boolean e1IsTask = e1 instanceof Task;
            boolean e2IsTask = e2 instanceof Task;
            boolean e1IsTimed = e1 instanceof Event || e1 instanceof Meeting;
            boolean e2IsTimed = e2 instanceof Event || e2 instanceof Meeting;

            int result = 0;

            // journal first
            if (e1IsJournal && !e2IsJournal) {
                result = -1;
            } else if (!e1IsJournal && e2IsJournal) {
                result = 1;
            } else if (e1IsTask && !e2IsTask && !e2IsJournal) {
                result = -1;
            } else if (!e1IsTask && e2IsTask && !e1IsJournal) {
                result = 1;
            } else if (e1IsTask && e2IsTask) {
                String priority1 = ((Task) e1).getPriority();
                String priority2 = ((Task) e2).getPriority();

                if (!priority1.equalsIgnoreCase(priority2)) {
                    if (priority1.equalsIgnoreCase("high")) {
                        result = -1;
                    } else if (priority2.equalsIgnoreCase("high")) {
                        result = 1;
                    } else if (priority1.equalsIgnoreCase("medium")) {
                        result = -1;
                    } else if (priority2.equalsIgnoreCase("medium")) {
                        result = 1;
                    }
                } else {
                    result = e1.getTitle().compareToIgnoreCase(e2.getTitle());
                }
            } else if (e1IsTimed && e2IsTimed) {
                LocalTime time1 = getStartTime(e1);
                LocalTime time2 = getStartTime(e2);

                if (!time1.equals(time2)) {
                    result = time1.compareTo(time2);
                } else {
                    result = e1.getTitle().compareToIgnoreCase(e2.getTitle());
                }
            }

            return result;
        });
    }

    /**
     * converts a string priority to an integer value
     * @param priority the priority string
     * @return numeric value of the priority
     */
    private int getPriorityValue(String priority) {
        int value = 0;
        if (priority.equalsIgnoreCase("high")) {
            value = 3;
        } else if (priority.equalsIgnoreCase("medium")) {
            value = 2;
        } else if (priority.equalsIgnoreCase("low")) {
            value = 1;
        }
        return value;
    }

    /**
     * returns the start time of the entry if it is an event or meeting
     * @param entry the entry to extract time from
     * @return start time or midnight
     */
    private LocalTime getStartTime(Entry entry) {
        LocalTime time = LocalTime.MIDNIGHT;
        if (entry instanceof Event) {
            time = ((Event) entry).getStartTime();
        } else if (entry instanceof Meeting) {
            time = ((Meeting) entry).getStartTime();
        }
        return time;
    }

    /**
     * creates a journal entry with given data
     * @param calendar the calendar to add to
     * @param date the entry date
     * @param title the entry title
     * @param details the entry details
     * @return created journal entry
     */
    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details) {
        return new Journal(date, title, details);
    }

    /**
     * creates an event entry with given data
     * @param calendar the calendar to add to
     * @param date the entry date
     * @param title the entry title
     * @param details the entry details
     * @param startTime the start time
     * @param endTime the end time
     * @return created event entry
     */
    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details, LocalTime startTime, LocalTime endTime) {
        return new Event(date, title, details, title, details, startTime, endTime);
    }

    /**
     * creates a task entry with given data
     * @param calendar the calendar to add to
     * @param date the entry date
     * @param title the entry title
     * @param details the entry details
     * @param priority the task priority
     * @param status the task status
     * @param createdBy who created the task
     * @param finishedBy who finished the task
     * @return created task entry
     */
    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details, String priority, String status, String createdBy, String finishedBy) {
        return new Task(date, title, details, priority, status, createdBy, finishedBy);
    }

    /**
     * creates a meeting entry with given data
     * @param calendar the calendar to add to
     * @param date the entry date
     * @param title the entry title
     * @param details the entry details
     * @param modality the meeting modality
     * @param venue the meeting venue
     * @param link the meeting link
     * @param startTime the start time
     * @param endTime the end time
     * @return created meeting entry
     */
    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details, String modality, String venue, String link, LocalTime startTime, LocalTime endTime) {
        return new Meeting(date, title, details, modality, venue, link, startTime, endTime);
    }

    /**
     * deletes the specified entry from the calendar
     * @param calendar the calendar to remove from
     * @param entry the entry to delete
     * @return true if removed
     */
    public boolean deleteEntry(Calendar calendar, Entry entry) {
        return calendar.getEntries().remove(entry);
    }

    /**
     * replaces an old entry with a new entry in the calendar
     * @param calendar the calendar to modify
     * @param oldEntry the old entry
     * @param newEntry the new entry
     * @return true if added and removed successfully
     */
    public boolean editEntry(Calendar calendar, Entry oldEntry, Entry newEntry) {
        return calendar.getEntries().add(newEntry) && deleteEntry(calendar, oldEntry);
    }
}
