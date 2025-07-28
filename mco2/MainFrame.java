import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class MainFrame {
    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;

    // views
    private SignInView signInView;
    private LogInView logInView;
    private SignUpView signUpView;
    private MainMenuView mainMenuView;
    private AccountSettingsView accountSettingsView;
    private CalendarsView calendarsView;
    private NewCalendarsView newCalendarsView;
    private CreateCalendarView createCalendarView;
    private AddCalendarView addCalendarView;
    private NewPersonalCalendarView newPersonalCalendarView;
    private NewFamilyCalendarView newFamilyCalendarView;
    private NewPublicCalendarView newPublicCalendarView;
    private CalendarMonthlyView calendarMonthlyView;
    // weekly view
    // daily view
    private AddEntryView addEntryView;
    private TaskEntryView taskEntryView;
    private EventEntryView eventEntryView;
    private MeetingEntryView meetingEntryView;
    private JournalEntryView journalEntryView;

    public MainFrame() {
        this.frame = new JFrame("The Digital Calendar");
        this.frame.setSize(1080, 720);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new GridBagLayout());
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cardLayout = new CardLayout();
        this.cards = new JPanel(cardLayout);

        // add all panels
        this.signInView = new SignInView();
        cards.add(signInView.getPanel(), "SIGNIN");

        this.logInView = new LogInView();
        cards.add(logInView.getPanel(), "LOGIN");

        this.signUpView = new SignUpView();
        cards.add(signUpView.getPanel(), "SIGNUP");

        this.mainMenuView = new MainMenuView();
        cards.add(mainMenuView.getPanel(), "MAIN");

        this.accountSettingsView = new AccountSettingsView();
        cards.add(accountSettingsView.getPanel(), "SETTINGS");

        this.calendarsView = new CalendarsView();
        cards.add(calendarsView.getPanel(), "VIEW_CALENDARS");

        this.newCalendarsView = new NewCalendarsView();
        cards.add(newCalendarsView.getPanel(), "NEW_CALENDAR");

        this.createCalendarView = new CreateCalendarView();
        cards.add(createCalendarView.getPanel(), "CREATE_NEW");

        this.addCalendarView = new AddCalendarView();
        cards.add(addCalendarView.getPanel(), "ADD_EXISTING");

        this.newPersonalCalendarView = new NewPersonalCalendarView();
        cards.add(newPersonalCalendarView.getPanel(), "NEW_PERSONAL");

        this.newFamilyCalendarView = new NewFamilyCalendarView();
        cards.add(newFamilyCalendarView.getPanel(), "NEW_FAMILY");

        this.newPublicCalendarView = new NewPublicCalendarView();
        cards.add(newPublicCalendarView.getPanel(), "NEW_PUBLIC");

        this.calendarMonthlyView = new CalendarMonthlyView();
        cards.add(calendarMonthlyView.getPanel(), "MONTHLY");

        // weekly view

        // daily view

        this.addEntryView = new AddEntryView();
        cards.add(addEntryView.getPanel(), "ADD_ENTRY");

        this.taskEntryView = new TaskEntryView();
        cards.add(taskEntryView.getPanel(), "TASK");

        this.eventEntryView = new EventEntryView();
        cards.add(eventEntryView.getPanel(), "EVENT");

        this.meetingEntryView = new MeetingEntryView();
        cards.add(meetingEntryView.getPanel(), "MEETING");

        this.journalEntryView = new JournalEntryView();
        cards.add(journalEntryView.getPanel(), "JOURNAL");

        this.frame.add(cards);
        this.frame.setVisible(true);
    }

    public void showCard(String name) {
        this.cardLayout.show(this.cards, name);
    }

    // getters
    public SignInView getSignInView() {
        return this.signInView;
    }

    public LogInView getLogInView() {
        return this.logInView;
    }

    public SignUpView getSignUpView() {
        return this.signUpView;
    }

    public MainMenuView getMainMenuView() {
        return this.mainMenuView;
    }

    public AccountSettingsView getAccountSettingsView() {
        return this.accountSettingsView;
    }

    public CalendarsView getCalendarsView() {
        return this.calendarsView;
    }

    public NewCalendarsView getNewCalendarsView() {
        return this.newCalendarsView;
    }

    public CreateCalendarView getCreateCalendarView() {
        return this.createCalendarView;
    }

    public AddCalendarView getAddCalendarView() {
        return this.addCalendarView;
    }

    public NewPersonalCalendarView getNewPersonalCalendarView() {
        return this.newPersonalCalendarView;
    }

    public NewFamilyCalendarView getNewFamilyCalendarView() {
        return this.newFamilyCalendarView;
    }

    public NewPublicCalendarView getNewPublicCalendarView() {
        return this.newPublicCalendarView;
    }

    public CalendarMonthlyView getCalendarMonthlyView() {
        return this.calendarMonthlyView;
    }

    // weekly

    // daily

    public AddEntryView getAddEntryView() {
        return this.addEntryView;
    }

    public TaskEntryView getTaskEntryView() {
        return this.taskEntryView;
    }

    public EventEntryView getEventEntryView() {
        return this.eventEntryView;
    }

    public MeetingEntryView getMeetingEntryView() {
        return this.meetingEntryView;
    }

    public JournalEntryView getJournalEntryView() {
        return this.journalEntryView;
    }
}