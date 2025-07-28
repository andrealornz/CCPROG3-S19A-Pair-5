import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CalendarModel {
    private final ArrayList<Calendar> publicCalendarList;

    public CalendarModel() {
        this.publicCalendarList = new ArrayList<Calendar>();
    }

    public ArrayList<Calendar> getPublicCalendarList() {
        return this.publicCalendarList;
    }

    public ArrayList<Entry> getEntriesForDate(Calendar calendar, LocalDate date) {
        ArrayList<Entry> entriesForDate = new ArrayList<Entry>();
        for (Entry entry : calendar.getEntries()) {
            if (entry.getDate().equals(date)) {
                entriesForDate.add(entry);
            }
        }
        return entriesForDate;
    }

    public ArrayList<Entry> getEntriesForWeek(Calendar calendar, int week) {
        ArrayList<Entry> entriesForWeek = new ArrayList<Entry>();
        for (Entry entry : calendar.getEntries()) {
            if ((entry.getDate().getDayOfMonth() / 7) + 1 == week) {
                entriesForWeek.add(entry);
            }
        }
        return entriesForWeek;
    }

    /* TO EDIT: because priority and status have been changed to String
    public ArrayList<Entry> sortEntries(ArrayList<Entry> entries) { //terrible algorithm
        ArrayList<Entry> toBeSorted = new ArrayList<Entry>();
        ArrayList<Task> tasks = new ArrayList<Task>();
        ArrayList<Entry> sortedTasks = new ArrayList<Entry>();
        ArrayList<Entry> others = new ArrayList<Entry>();
        Task highest = new Task(LocalDate.now(), "", "", 0, false, "", "");
        for (Entry entry : entries) { //segregate tasks from other entries
            if (entry instanceof Task) {
                tasks.add((Task)entry);
            } else {
                others.add(entry);
            }
        }
        for (int i = 0; i < tasks.size(); i++) { //sort tasks by priority
            for (Task task : tasks) {
                if (task.getPriority() > highest.getPriority()) {
                    highest = task;
                }
            }
            sortedTasks.add(highest);
        }
        toBeSorted.addAll(sortedTasks);
        toBeSorted.addAll(others);
        return toBeSorted;
    } 
    */

    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details) {
        return new Journal(date, title, details);
    }

    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details, LocalTime startTime, LocalTime endTime) {
        return new Event(date, title, details, title, details, startTime, endTime);
    }

    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details, String priority, String status, String createdBy, String finishedBy) {
        return new Task(date, title, details, priority, status, createdBy, finishedBy);
    }

    public Entry createEntry(Calendar calendar, LocalDate date, String title, String details, String modality, String venue, String link, LocalTime startTime, LocalTime endTime) {
        return new Meeting(date, title, details, modality, venue, link, startTime, endTime);
    }

    public boolean deleteEntry(Calendar calendar, Entry entry) {
        return calendar.getEntries().remove(entry);
    }

    public boolean editEntry(Calendar calendar, Entry oldEntry, Entry newEntry) {
        return calendar.getEntries().add(newEntry) && deleteEntry(calendar, oldEntry);
    }
}