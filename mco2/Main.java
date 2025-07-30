public class Main {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        CalendarModel calendarModel = new CalendarModel();
        AccountModel accountModel = new AccountModel(calendarModel);

        Controller controller = new Controller(mainView, accountModel, calendarModel);
    }
}