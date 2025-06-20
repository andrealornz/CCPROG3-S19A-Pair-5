import java.util.ArrayList;

public class Domain {
    private static Domain instance;
    private ArrayList<Calendar> calendarList;

    private Domain () {
        this.calendarList = new ArrayList<Calendar>();
    }
    
    public static Domain getDomain() {
        if (instance == null) {
            instance = new Domain();
        }
        return instance;
    }

    public ArrayList<Calendar> getCalendarList() {
        return this.calendarList;
    }

    public void addCalendar(Calendar newCalendar) {
        calendarList.add(newCalendar);
    }
}