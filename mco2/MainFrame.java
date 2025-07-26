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
}