import java.time.LocalDate;

public class Task extends Entry {
    private int priority;
    private boolean status;
    private String createdBy;
    private String finishedBy;

    public Task (LocalDate date, String title, String details, int priority, boolean status, String createdBy, String finishedBy) {
        super(date, title, details);
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
        this.finishedBy = finishedBy;
    }

    public int getPriority() {
        return this.priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCreatedBy() {
        return this.createdBy;
 
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getFinishedBy() {
        return this.finishedBy;
    }

    public void setFinishedBy(String finishedBy) {
        this.finishedBy = finishedBy;
    }
}