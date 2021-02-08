import java.sql.*;

public class TestMain {
    private static boolean exit = false;

    public static void main(String[] args) throws SQLException {

        //Det första vi vill presentera är antingen startMenu eller Receptions View.

        Menu.logo();
        Menu.welcome();


        while (!exit) {

            Menu.blob();
            System.out.println(Menu.ANSI_YELLOW + "Select Service: \n[1]" + Menu.ANSI_RESET + " Receptionist View\n" +
                    Menu.ANSI_YELLOW + "[2]" + Menu.ANSI_RESET + " Customer View\n" +
                    Menu.ANSI_YELLOW + "[3]" + Menu.ANSI_RESET +" Exit");

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