import javax.security.auth.login.AccountException;

public class Main {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();

        mainFrame.showCard("MONTHLY");

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
         */
    }
}