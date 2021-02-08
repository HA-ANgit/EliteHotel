import java.sql.*;
import java.util.Scanner;

public class Reception {
    private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Jonnydepp2121";
    private static Scanner input = new Scanner(System.in);
    private static Statement sqlStatement = null;
    private static boolean exit = false;
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void reception() throws SQLException {

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            sqlStatement = connection.createStatement();

            while (!exit) {
                System.out.println("\n=======================");
                System.out.println("="+ANSI_YELLOW+"      RECEPTION      "+ANSI_RESET+"=");
                System.out.println("="+ANSI_YELLOW+"  PETIT HOTEL ELITE  "+ANSI_RESET+"=");
                System.out.println("======================= \n");

                System.out.println(ANSI_YELLOW + "Enter your choice: " + ANSI_RESET);
                System.out.println("1. Searching customer details");
                System.out.println("2. Delete customer details ");
                System.out.println("3. Book ");
                System.out.println("4. Order food");
                System.out.println("5. Checkout ");
                System.out.println("6. Exit ");

                int selection = input.nextInt();

                switch (selection) {
                    case 1:
                        searchCustomer();
                        break;
                    case 2:
                        deleteCustomer();
                        break;
                    case 3:
                        CheckIn.bookARoom();
                        break;
                    case 4:
                        RoomService.orderRoomService();
                        break;
                    case 5:
                        Management.checkout();
                        break;
                    default:
                        exit = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void searchCustomer() throws SQLException {
        try {
            selectTable("firstName, lastName FROM customer");

            System.out.println("Enter customer first name: ");
            String firstName = Menu.lineInput();
            System.out.println("Enter customer last name: ");
            String lastName = Menu.lineInput();

            selectTable("* FROM customer WHERE firstName ='" + firstName + "' AND lastName = '" + lastName + "';");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private static void deleteCustomer() throws SQLException {
        try {
            selectTable("* FROM customer");

            System.out.println("Enter customer ID: ");
            int customerId = input.nextInt();
            input.nextLine();

            int result = sqlStatement.executeUpdate("DELETE FROM customer WHERE customerId =" + customerId + ";");
            if (result == 1) {
                System.out.println("Customer with customer ID: " + customerId + " is now removed\n");
            } else {
                System.out.println("Remove of customer failed, try again");
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
    }

    private static String PadRight(String string) {
        // Används för att utskriften ska bli finare när man skriver ut en tabell (fyller ut)
        // Padding is the space that’s inside the element between the element and the border.
        // Fyll ut varje sträng med whitespaces till max 30 tecken
        int totalStringLength = 30;
        int charsToPadd = totalStringLength - string.length();

        // incase the string is the same length or longer than our maximum lenght
        if (string.length() >= totalStringLength)
            return string;

        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < charsToPadd; i++) {
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }

    public static void selectTable(String query) throws SQLException {

        ResultSet result = sqlStatement.executeQuery("SELECT " + query + ";");  //Får in tableName och skickar sedan detta till databas. Får tbx result

        int columnCount = result.getMetaData().getColumnCount();            // hämtar antalet kolumner för att sedan namnge dessa i en array
        String[] columnNames = new String[columnCount];
        for (int i = 0; i < columnCount; i++) {
            columnNames[i] = result.getMetaData().getColumnName(i + 1);
        }

        for (String columnName : columnNames) {         // lägg kolumnnamn i string array, skriver ut dessa med pad right-metoden
            System.out.print(Menu.ANSI_YELLOW + PadRight(columnName) + Menu.ANSI_RESET);
        }

        while (result.next()) {
            System.out.println();
            for (String columnName : columnNames) {             // hämtar data för resp kolumner för samtliga rader
                String value = result.getString(columnName);

                if (value == null)
                    value = "null";
                System.out.print(PadRight(value));
            }
        }
        if (columnCount == 0) {
            System.out.println("\nNo result!");
        } else {
        }
        System.out.println();
    } //Listar valt table från databasen

}