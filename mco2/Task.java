import java.time.LocalDate;

public class Task extends Entry {
    private String priority;
    private String status;
    private String createdBy;
    private String finishedBy;

    public Task (LocalDate date, String title, String details, String priority, String status, String createdBy, String finishedBy) {
        super(date, title, details);
        this.priority = priority;
        this.status = status;
        this.createdBy = createdBy;
        this.finishedBy = finishedBy;
    }

    public String getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
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