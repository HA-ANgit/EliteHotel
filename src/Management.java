import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.SequenceInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
            /*finally {
                connection.close();
            }*/
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

        checkOutMethod(roomNumber);

        /*PreparedStatement statement = connection.prepareStatement("SELECT * FROM hotel.booked WHERE roomNum = " + roomNumber  + "      INTO OUTFILE 'accommodation"+roomNumber+".txt'"+
                "     FIELDS TERMINATED BY ','");
        statement.executeQuery();*/
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

    public static void checkOutMethod(int roomNumber) throws SQLException {
        boolean exit = false;
        System.out.println("Check out room " + Menu.ANSI_RED + roomNumber + Menu.ANSI_RESET + "?\n");
        System.out.println("Do you want to continue? (y/n)");
        String answer = Menu.lineInput();

        if (answer.equals("y")) {
            ResultSet rs = sqlStatement.executeQuery("SELECT * FROM hotel.booked WHERE roomNum = '" + roomNumber  + "';"); //Roomnumber skickas in via kallandet på metoden.
            rs.next();
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String roomType = rs.getString("roomType");
            int roomPrice = Integer.parseInt(rs.getString("cost"));
            String bookingId = rs.getString("bookingId");
            Date checkIn = rs.getDate("checkIn");
            LocalDate checkOut = LocalDate.now();
            long daysLeft = 0;
            daysLeft = checkOut.until(checkIn.toLocalDate(), ChronoUnit.DAYS);
            long totalCost = (roomPrice * daysLeft);
            //java.sql.Date.valueOf( localDate );

            sqlStatement.executeUpdate("UPDATE hotel.bookRoom SET checkOut = CURRENT_TIMESTAMP WHERE bookingId = '" + bookingId + "';");
            sqlStatement.executeUpdate("UPDATE room SET available = '1' WHERE roomNum = '" + roomNumber + "';");

            if (sqlStatement.getUpdateCount() > 0) {
                System.out.println("=========================================");
                System.out.println("Room Checked Out: " + firstName + " " + lastName);
                System.out.println("Room category: "+ roomType);
                System.out.println("Room price: " + totalCost + " SEK");
                System.out.println("Room number: " + roomNumber);
                System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
                System.out.println("=========================================");

                try {
                    //lägg till sökvägen för att hitta file1
                    FileInputStream file1 = new FileInputStream("accommodation" + roomNumber + ".txt");

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
               //System.out.println("\nEnter total accommodation cost: ");
                //int roomBill = Integer.parseInt(Menu.lineInput());

                System.out.println("Enter total room service cost: ");
                int foodBill = Integer.parseInt(Menu.lineInput());

                //binary function tar in två argument, i detta fall kvittokostnaderna och genom method reference klassen,
                //adderar den båda summorna
                BiFunction<Integer, Integer, Integer> adder = MethodReference::add;
                int result = adder
                        .apply((int) totalCost, foodBill);

                //sparar det nya kvittot med den sammanlagda summan
                try {
                    FileOutputStream file = new FileOutputStream("accommodation" + roomNumber + ".txt");
                    String s = ("=========================================" +
                            "\nRoom Checked Out: " + firstName + " " + lastName+
                            "\nRoom category: "+ roomType+
                            "\nRoom price: " + totalCost + "SEK"+
                            "\nRoom number: " + roomNumber+
                            "\nPETIT HOTEL ELITE\n"+
                            "=========================================");
                    byte b[] = s.getBytes();
                    file.write(b);
                    file.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

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
        } else {
            exit = true;
        }
    }

}
