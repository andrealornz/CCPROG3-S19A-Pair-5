import java.time.LocalDate;
import java.time.LocalTime;

public class Meeting extends Entry {
    private String modality;
    private String venue;
    private String link;
    private LocalTime startTime;
    private LocalTime endTime;

    public Meeting(LocalDate date, String title, String details, String modality, String venue, String link, LocalTime startTime, LocalTime endTime) {
        super(date, title, details);
        this.modality = modality;
        this.venue = venue;
        this.link = link;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getModality() {
        return this.modality;
    }

    public void setModality(String modality) {
        this.modality = modality;
    }

    public String getVenue() {
        return this.venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String link) {
        this.link = link;
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