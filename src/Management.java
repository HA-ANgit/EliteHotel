import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.function.BiFunction;
import java.sql.SQLException;

public class Management {
    private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Jonnydepp2121";
    private static Statement sqlStatement = null;
    private static Connection connection = null;
    private static boolean exit = false;
    private static LocalDate date = LocalDate.now();
    private static LocalDateTime datetime = LocalDateTime.now();

    public static void connect() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);

        while (!exit) {
            try {
                System.out.println("\nSuccessful database connection..");

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
                        Management.selectTable("* FROM available");
                        break;
                    case 3:
                        CheckIn.bookARoom();
                        break;
                    case 4:
                        RoomService.orderRoomService();
                        break;
                    case 5:
                        checkout();
                        break;
                    default:
                        exit = true;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Connection error!");
            }
            finally {
                connection.close();
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

    public static void checkout() throws SQLException {
        System.out.println("Enter guest room number: ");
        int roomNumber = Integer.parseInt(Menu.lineInput());

        PreparedStatement statement = connection.prepareStatement("SELECT 'roomNum', 'firstName', 'lastName', 'cost' UNION ALL SELECT * FROM hms.inhouseguest WHERE roomNum = "+roomNumber+
                "     INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/accommodation"+roomNumber+".txt'"+
                "     FIELDS TERMINATED BY ','");
        statement.executeQuery();

        try {
            //lägg till sökvägen för att hitta file1
            FileInputStream file1 = new FileInputStream("C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/accommodation" + roomNumber + ".txt");
            FileInputStream file2 = new FileInputStream("roomservice" + roomNumber + ".txt");
            //skriver ut båda streams samtidigt
            SequenceInputStream input = new SequenceInputStream(file1, file2);
            int j;
            while ((j = input.read()) != -1) {
                System.out.print((char) j);
            }
            input.close();
            file1.close();
            file2.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        //Fyller i kostnaderna från respektive kvitto som skrivits ut
        System.out.println("\nEnter total accommodation cost: ");
        int roomBill = Integer.parseInt(Menu.lineInput());

        System.out.println("Enter room service cost: ");
        int foodBill = Integer.parseInt(Menu.lineInput());

        //binary function tar in två argument, i detta fall kvittokostnaderna och genom method reference klassen,
        //adderar den båda summorna
        BiFunction<Integer, Integer, Integer> adder = MethodReference::add;
        int result = adder
                .apply(roomBill, foodBill);

        //sparar det nya kvittot med den sammanlagda summan
        try {
            FileOutputStream file = new FileOutputStream("totalhotelcost" + roomNumber + ".txt");
            String s = ("==================================\n" +
                    "Total cost: " + result + "SEK" +
                    "\nThank you for staying with us!" +
                    "\nBest regards,\n"+ Menu.ANSI_YELLOW +"PETIT HOTEL ELITE"+ Menu.ANSI_RESET +
                    "\n==================================");
            byte b[] = s.getBytes();
            file.write(b);
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("==================================");
        System.out.println("Guest is checked out from room: " + roomNumber);
        System.out.println("Checkout date: " + LocalDate.now());

        sqlStatement.executeUpdate("DELETE FROM inhouseguest WHERE roomNum =" + roomNumber + ";");
        PreparedStatement state = connection.prepareStatement("UPDATE bookroom SET checkOut = ? WHERE roomNum = ?");
        state.setDate(1, Date.valueOf(date));
        state.setInt(2, roomNumber);
        state.executeUpdate();

        //skriver ut det nya kvittot med den sammanlagda summan
        try {
            FileInputStream file = new FileInputStream("totalhotelcost" + roomNumber + ".txt");
            int i = 0;
            while ((i = file.read()) != -1) {
                System.out.print((char) i);
            }
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static String PadRight(String string) {
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

}
