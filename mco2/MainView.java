import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

public class MainView {
    private JFrame frame;
    private JPanel cards;
    private CardLayout cardLayout;
    private JPanel usernamePanel;
    private JLabel usernameLbl;

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
    private CalendarWeeklyView calendarWeeklyView;
    private AddEntryView addEntryView;
    private TaskEntryView taskEntryView;
    private EventEntryView eventEntryView;
    private MeetingEntryView meetingEntryView;
    private JournalEntryView journalEntryView;

    public MainView() {
        this.frame = new JFrame("The Digital Calendar");
        this.frame.setSize(1080, 720);
        this.frame.setLocationRelativeTo(null);
        this.frame.setLayout(new BorderLayout());
        this.frame.setResizable(false);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.usernamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        this.usernamePanel.setPreferredSize(new Dimension(1080, 30));

        this.usernameLbl = new JLabel("");
        this.usernameLbl.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 5));
        this.usernameLbl.setFont(new Font("Century Gothic", 0, 14));
        this.usernameLbl.setForeground(new Color(51, 51, 51));
        this.usernamePanel.add(usernameLbl);

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
        cards.add(newPersonalCalendarView.getPanel(), "PERSONAL");

        this.newFamilyCalendarView = new NewFamilyCalendarView();
        cards.add(newFamilyCalendarView.getPanel(), "FAMILY");

        this.newPublicCalendarView = new NewPublicCalendarView();
        cards.add(newPublicCalendarView.getPanel(), "PUBLIC");

        this.calendarMonthlyView = new CalendarMonthlyView();
        cards.add(calendarMonthlyView.getPanel(), "MONTHLY");

        this.calendarWeeklyView = new CalendarWeeklyView();
        cards.add(calendarWeeklyView.getPanel(), "WEEKLY");

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

        this.frame.add(usernamePanel, BorderLayout.SOUTH);
        this.frame.add(cards, BorderLayout.CENTER);
        this.frame.setVisible(true);
    }

    public void showCard(String name) {
        this.cardLayout.show(this.cards, name);
    }

    // helper methods
    public void setUsernameLabel(String username) {
        if (username == null || username.isEmpty()) {
            this.usernameLbl.setText("");
        } else {
            this.usernameLbl.setText(username);
        }
    }

    public void setFrameTitle(String title) {
        this.frame.setTitle(title);
    }

    public void clearUsernameLabel() {
        this.usernameLbl.setText("");
    }

    // getters
    public JFrame getFrame() {
        return this.frame;
    }

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

    public CalendarWeeklyView getCalendarWeeklyView() {
        return this.calendarWeeklyView;
    }

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