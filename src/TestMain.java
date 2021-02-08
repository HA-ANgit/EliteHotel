import java.sql.*;

public class TestMain {
    private static boolean exit = false;

    public static void main(String[] args) throws SQLException {

        //Det första vi vill presentera är antingen startMenu eller Receptions View.

        Menu.logo();
        Menu.welcome();


        while (!exit) {

            Menu.blob();
            System.out.println(Menu.ANSI_YELLOW + "Select Service: " + Menu.ANSI_RESET + "\n1. Customer View\n" +
                    "2. Management View\n" +
                    "3. Exit");

            int selection = Menu.intInput();

            switch (selection) {
                case 1:
                    Reception.reception();
                    break;
                case 2:
                    Menu.startMenu();
                    break;
                default:
                    exit = true;
            }
        }
    }
}