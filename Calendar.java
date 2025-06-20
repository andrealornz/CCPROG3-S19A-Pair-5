public class Calendar {
    private String name;
    private String owner;

    public Calendar(String newName, String newOwner) {
        this.name = newName;
        this.owner = newOwner;
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }
}