public class Family extends Calendar {
    private final String accessCode;

    public Family(String name, String owner, String accessCode) {
        super(name, owner);
        this.accessCode = accessCode;
    }

    public String getAccessCode() {
        return this.accessCode;
    }
}