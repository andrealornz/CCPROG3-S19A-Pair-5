import java.time.LocalDate;

public class Journal extends Entry {
    public Journal(LocalDate date, String title, String details) {
        super(date, title, details);
    }
}