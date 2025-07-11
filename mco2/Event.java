import java.time.LocalDate;
import java.time.LocalTime;

public class Event extends Entry {
    private String venue;
    private String organizer;
    private LocalTime startTime;
    private LocalTime endTime;

    public Event(LocalDate date, String title, String details, String venue, String organizer, LocalTime startTime, LocalTime endTime) {
        super(date, title, details);
        this.venue = venue;
        this.organizer = organizer;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getOrganizer() {
        return this.organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public LocalTime getStartTime() {
        return this.startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return this.endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}