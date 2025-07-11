import java.time.LocalDate;
import java.time.LocalTime;

/**
 * This is the Entry class, its instances are owned by a Calendar
 */
public class Entry {
    // attributes
    private String title;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String details;

    /**
     * the constructor wherein providing the entry title, date, start time, end time, and specific details creates an Entry instance 
     * @param title the inputted entry title
     * @param date the inputted entry date
     * @param startTime the inputted start time
     * @param endTime the inputted end title
     * @param details the inputted specific details
     */
    public Entry(String title, LocalDate date, LocalTime startTime, LocalTime endTime, String details) {
        this.title = title;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
    }

    // getters & setters
    /**
     * retrieves the title of an entry instance
     * @return the title
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * sets a new title for an entry instance
     * @param title the new title to be set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * retrieves the date of an entry instance
     * @return the date
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * sets a new date for an entry instance
     * @param date the new date to be set
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * retrieves the start time of an entry instance
     * @return the start time
     */
    public LocalTime getStartTime() {
        return this.startTime;
    }

    /**
     * sets a new start time for an entry instance
     * @param startTime the new start time to be set
     */
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    /**
     * retrieves the end time of an entry instance
     * @return the end time
     */
    public LocalTime getEndTime() {
        return this.endTime;
    }

    /**
     * sets a new end time for an entry instance
     * @param endTime the new end time to be set
     */
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    /**
     * retrieves the details of an entry instance
     * @return the details
     */
    public String getDetails() {
        return this.details;
    }
    
    /**
     * sets the new details for an entry instance
     * @param details the new details to be set
     */
    public void setDetails(String details) {
        this.details = details;
    }
}