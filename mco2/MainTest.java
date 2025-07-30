import javax.security.auth.login.AccountException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Calendar;

public class MainTest {
    /* public static void main(String[] args) {

        MainFrame mainView = new MainFrame();
        mainView.showCard("WEEKLY");
        
        // Create sample calendar and model for testing
        Personal testCalendar = new Personal("Test Calendar", "testuser");
        CalendarModel model = new CalendarModel();
        
        // Create sample entries for this week
        LocalDate today = LocalDate.now();
        
        // Create a task for today
        Task task1 = new Task(today, "MCO2 Deadline", "Submit project", "High", "Pending", "Andrea", "Andrea");
        
        // Create an event for tomorrow
        Event event1 = new Event(today.plusDays(1), "Team Meeting", "Weekly standup", 
                                "Conference Room A", "Andrea", LocalTime.of(10, 0), LocalTime.of(11, 0));
        
        // Create another task for day after tomorrow
        Task task2 = new Task(today, "Code Review", "Review pull requests", "Medium", "In Progress", "Andrea", "Andrea");
        
        // Create a meeting for the weekend
        Meeting meeting1 = new Meeting(today.plusDays(5), "Family Dinner", "Monthly family gathering", 
                                     "In-person", "Home", "N/A", LocalTime.of(18, 0), LocalTime.of(21, 0));
        
        // Create a journal entry
        Journal journal1 = new Journal(today.plusDays(3), "Daily Reflection", "Thoughts about the day...");
        
        // Add entries to calendar
        testCalendar.getEntries().add(task1);
        testCalendar.getEntries().add(event1);
        testCalendar.getEntries().add(task2);
        testCalendar.getEntries().add(meeting1);
        testCalendar.getEntries().add(journal1);
        
        // Populate the weekly view with entries
        mainFrame.getCalendarWeeklyView().populateWeeklyEntries(model, testCalendar);
        
        // Test clicking on day panels
        mainFrame.getCalendarWeeklyView().setDayPanelListener(e -> {
            System.out.println("Clicked date: " + e.getActionCommand());
        });
        
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
        DAILY
        ADD_ENTRY

        * these can be called again to edit entry
        TASK
        EVENT
        MEETING
        JOURNAL
         */
} 
