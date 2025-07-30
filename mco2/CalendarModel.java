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

    private LocalTime getStartTime(Entry entry) {
        LocalTime time = LocalTime.MIDNIGHT;
        if (entry instanceof Event) {
            time = ((Event) entry).getStartTime();
        } else if (entry instanceof Meeting) {
            time = ((Meeting) entry).getStartTime();
        }
        return time;
    }

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