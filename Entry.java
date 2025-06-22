import java.time.LocalDate;
import java.time.LocalTime;

public class Entry {
    // attributes
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String details;

    // constructor
    public Entry(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
    }

    // getters & setters
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
