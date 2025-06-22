public class Driver {
    public static void main(String[] args) {
        Account.createAccount("jeff", "password");
        Account.loginAccount("jeff", "password");
        if (Account.getCurrentAccount() != null) {
            System.out.println(Account.getCurrentAccount().getDefaultCalendar().getName());
        }
    }
}