import java.util.ArrayList;

public abstract class Calendar {
    private final String name;
    private final String owner;
    private final ArrayList<Entry> entries;

    public Calendar(String name, String owner) {
        this.name = name;
        this.owner = owner;
        this.entries = new ArrayList<Entry>();
    }

    public String getName() {
        return this.name;
    }

    public String getOwner() {
        return this.owner;
    }

    public ArrayList<Entry> getEntries() {
        return this.entries;
    }
}