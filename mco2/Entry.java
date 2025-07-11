import java.time.LocalDate;

public abstract class Entry {
    private final LocalDate date;
    private String title;
    private String details;

    public Entry(LocalDate date, String title, String details) {
        this.date = date;
        this.title = title;
        this.details = details;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return this.details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}