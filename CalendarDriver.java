import java.util.ArrayList;

public class CalendarDriver {
    private ArrayList<Account> accounts;
    private Account session;
    public static void main(String[] args) {
        Account myAcc = new Account("jeff", "password");
        myAcc.createPublicCalendar("cal1");
        System.out.println(Domain.getDomain().getCalendarList().get(0).getName()); //testing
    }
}