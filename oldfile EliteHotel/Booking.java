import java.sql.*;

public class Management {
    private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Jonnydepp2121";
    private static Statement sqlStatement = null;

    public static void connect (){
        boolean exit = false;
        while (!exit) {
            try (Connection connection = DriverManager.getConnection(url, user, password)) {    // Skapar connection-object och ansluter till bibliotek

                System.out.println("Successful database connection..");

                sqlStatement = connection.createStatement();
                Menu.blob();
                System.out.println(Menu.ANSI_YELLOW + "Select Service: " + Menu.ANSI_RESET + "\n1. Display room details\n" +
                        "2. Display room availability\n" +
                        "3. Book\n" +
                        "4. Order food\n" +
                        "5. Checkout\n" +
                        "6. Exit");

                int selection = Menu.intInput();

                switch (selection) {
                    case 1:
                        RoomDetails.showRoomType();
                        break;
                    case 2:
                        Booking.selectTable("* FROM available");
                        break;
                    case 3:
                        //Book a room method
                        break;
                    case 4:
                        //Room Service
                        break;
                    case 5:
                        //Check Out a customer
                        break;
                    default:
                        exit = true;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("connection error!");
            }
        }
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

        while(result.next()) {
            System.out.println();
            for (String columnName : columnNames) {             // hämtar data för resp kolumner för samtliga rader
                String value = result.getString(columnName);

                if(value == null)
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

    private static String PadRight(String string) {
        int totalStringLength = 30;
        int charsToPadd = totalStringLength - string.length();

        // incase the string is the same length or longer than our maximum lenght
        if(string.length() >= totalStringLength)
            return string;

        StringBuilder stringBuilder = new StringBuilder(string);
        for (int i = 0; i < charsToPadd; i++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }     // fyll ut varje sträng med blankspace till max 30 tecken

}