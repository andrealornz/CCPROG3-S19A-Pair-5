import javax.security.auth.login.AccountException;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        mainFrame.showCard("ADD_TASK");

        /* testing entry editing
        Task entry = new Task(LocalDate.of(2025, 7, 30), "MCO2 Deadline", "Submit!!!", "High", "Pending", "Andrea", "Andrea");
        mainFrame.getAddTaskEntryView().populateFields(entry);
        */

        /* testing buttons
        mainFrame.getSignInView().setLogInBtnListener(e -> {
            mainFrame.showCard("LOGIN");
        });

        mainFrame.getLogInView().setCancelBtnListener(e -> {
            mainFrame.showCard("SIGNIN");
        });


        mainFrame.getSignInView().setSignUpBtnListener(e -> {
            mainFrame.showCard("SIGNUP");
        });

        mainFrame.getSignUpView().setCancelBtnListener(e -> {
            mainFrame.showCard("SIGNIN");
        });
        */
        
        /* testing calendar panels clickability
        mainFrame.getCalendarMonthlyView().setDayPanelListener(e -> {
            System.out.println("Clicked date: " + e.getActionCommand());
        });
        */

        /*
        Card Names:
        SIGNIN
        LOGIN
        SIGNUP
        MAIN
        SETTINGS
        VIEW_CALENDARS
        NEW_CALENDAR
        CREATE_NEW
        ADD_EXISTING
        NEW_PERSONAL
        NEW_FAMILY
        NEW_PUBLIC
        MONTHLY
        WEEKLY
        ADD_ENTRY

        * these can be called again to edit entry
        TASK
        EVENT
        MEETING
        JOURNAL
         */
    }
}