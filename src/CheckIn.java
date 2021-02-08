import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CheckIn {

    private static boolean exit = false;
    private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Jonnydepp2121";
    private static Connection connection = null;
    private static Statement sqlStatement = null;
    private static LocalDate date = LocalDate.now();
    private static LocalDateTime datetime = LocalDateTime.now();
    private static String answer;

    public static void bookARoom() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        try {
            sqlStatement = connection.createStatement();
            //skapa en arraylist för vardera rumskaegori
            List<BookRoom> bookRoomDDR = new ArrayList<>();
            List<BookRoom> bookRoomDSR = new ArrayList<>();
            List<BookRoom> bookRoomSDR = new ArrayList<>();
            List<BookRoom> bookRoomSSR = new ArrayList<>();
            List<BookRoom> suite = new ArrayList<>();

            //skapar objekt för vardera rum
            BookRoom r1 = new BookRoom(101, "Standard Single Room", 1440);
            BookRoom r2 = new BookRoom(106, "Deluxe Double Room", 2980);
            BookRoom r3 = new BookRoom(108, "Deluxe Double Room", 2980);
            BookRoom r4 = new BookRoom(201, "Standard Single Room", 1440);
            BookRoom r5 = new BookRoom(203, "Standard Double Room", 2200);
            BookRoom r6 = new BookRoom(204, "Deluxe Single Room", 1820);
            BookRoom r7 = new BookRoom(205, "Deluxe Double Room", 2980);
            BookRoom r8 = new BookRoom(206, "Deluxe Single Room", 1820);
            BookRoom r9 = new BookRoom(207, "Deluxe Double Room", 2980);
            BookRoom r10 = new BookRoom(208, "Standard Double Room", 2200);
            BookRoom r11 = new BookRoom(302, "The Suite", 5890);

            // Deluxe Double Room
            bookRoomDDR.add(r2);
            bookRoomDDR.add(r3);
            bookRoomDDR.add(r7);
            bookRoomDDR.add(r9);

            // Deluxe Single Room
            bookRoomDSR.add(r6);
            bookRoomDSR.add(r8);

            // Standard Double Room
            bookRoomSDR.add(r5);
            bookRoomSDR.add(r10);

            // Standard Single Room
            bookRoomSSR.add(r1);
            bookRoomSSR.add(r4);

            // Suite
            suite.add(r11);

            //Kan lägga till flera features på kunden som bokar rummet ex, telefonnummer
            System.out.println("Enter your first name: ");
            String firstName = Menu.lineInput();
            System.out.println("Enter your last name: ");
            String lastName = Menu.lineInput();
            System.out.println("Enter your phone number: ");
            int phoneNum = Integer.parseInt(Menu.lineInput());
            System.out.println("Which room category would you like to book? ");
            System.out.println("1. Deluxe Double Room");
            System.out.println("2. Deluxe Single Room");
            System.out.println("3. Standard Double Room");
            System.out.println("4. Standard Single Room");
            System.out.println("5. Suite");
            System.out.println("6. Exit");
            int select = Integer.parseInt(Menu.lineInput());

            switch (select) {
                //Deluxe Double Room
                case 1:
                    try {
                        Connection con = sqlStatement.getConnection();
                        Statement stmt = con.createStatement();
                        do {
                            printRoomList(bookRoomDDR);
                            System.out.println("Enter room number for selected room: ");
                            int selectedRoom = Integer.parseInt(Menu.lineInput());

                            String SQL = "SELECT roomNum FROM hotel.booked WHERE roomNum =" + selectedRoom + ";";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                                System.out.println("Do you want to search for another room? (y/n)");
                                answer = Menu.lineInput();
                            } else {
                                checkInMethod(4, selectedRoom, phoneNum, firstName, lastName);
                                }
                            Management.connect();
                        } while (answer.equals("y"));
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                //Deluxe Single Room
                case 2:
                    try {
                        Connection con = sqlStatement.getConnection();
                        Statement stmt = con.createStatement();
                        do {
                            printRoomList(bookRoomDSR);
                            System.out.println("Enter room number for selected room: ");
                            int selectedRoom = Integer.parseInt(Menu.lineInput());

                            String SQL = "SELECT roomNum FROM hotel.booked WHERE roomNum =" + selectedRoom + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                                System.out.println("Do you want to search for another room? (y/n)");
                                answer = Menu.lineInput();
                            } else {
                                checkInMethod(2, selectedRoom, phoneNum, firstName, lastName);
                            }
                            Management.connect();
                        } while (answer.equals("y"));
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                //Standard Double Room
                case 3:
                    try {
                        Connection con = sqlStatement.getConnection();
                        Statement stmt = con.createStatement();
                        do {
                            printRoomList(bookRoomSDR);
                            System.out.println("Enter room number for selected room: ");
                            int selectedRoom = Integer.parseInt(Menu.lineInput());

                            String SQL = "SELECT roomNum FROM hotel.booked WHERE roomNum =" + selectedRoom + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                                System.out.println("Do you want to search for another room? (y/n)");
                                answer = Menu.lineInput();
                            } else {
                                checkInMethod(3, selectedRoom, phoneNum, firstName, lastName);
                            }
                            Management.connect();
                        } while (answer.equals("y"));
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                // Standard Single Room
                case 4:
                    try {
                        Connection con = sqlStatement.getConnection();
                        Statement stmt = con.createStatement();
                        do {
                            printRoomList(bookRoomSSR);
                            System.out.println("Enter room number for selected room: ");
                            int selectedRoom = Integer.parseInt(Menu.lineInput());

                            String SQL = "SELECT roomNum FROM hotel.booked WHERE roomNum =" + selectedRoom + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                                System.out.println("Do you want to search for another room? (y/n)");
                                answer = Menu.lineInput();
                            } else {
                                checkInMethod(1, selectedRoom, phoneNum, firstName, lastName);
                            }
                            Management.connect();
                        } while (answer.equals("y"));
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                //Suite
                case 5:
                    try {
                        Connection con = sqlStatement.getConnection();
                        Statement stmt = con.createStatement();
                        do {
                            printRoomList(suite);
                            System.out.println("Enter room number for selected room: ");
                            int selectedRoom = Integer.parseInt(Menu.lineInput());

                            String SQL = "SELECT roomNum FROM hotel.booked WHERE roomNum =" + selectedRoom + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                            } else {
                                checkInMethod(5, selectedRoom, phoneNum, firstName, lastName);
                            }
                            Management.connect();
                        } while (answer.equals("y"));
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Do you want to go back to the main menu? (y/n)");
                    String answer1 = Menu.lineInput();
                    if (answer1.equals("y")) {
                        Management.connect();
                    } else {
                        bookARoom();
                    }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void checkInMethod(int roomCategory, int selectedRoom, int phoneNum, String firstName, String lastName) throws SQLException {

        System.out.println("This room is available\n");
        Menu.pressEnter();

        ResultSet rs = sqlStatement.executeQuery("SELECT * FROM hotel.roomCategory WHERE categoryId = '" + roomCategory + "';");
        rs.next();
        String roomPrice = rs.getString("COST");
        String roomType = rs.getString("roomType");

        sqlStatement.executeUpdate("INSERT INTO customer(phoneNum, firstName, lastName, lastUpdate) VALUES('" + phoneNum + "','" + firstName + "','" + lastName + "', CURRENT_TIMESTAMP);");
        rs = sqlStatement.executeQuery("SELECT MAX(customerId) FROM hotel.customer;");
        rs.next();
        String customerId = rs.getString("MAX(customerId)");
        sqlStatement.executeUpdate("INSERT INTO bookRoom(roomNum, checkIn, lastUpdate, customerId) VALUES('" + selectedRoom + "', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, '" + customerId + "');");
        sqlStatement.executeUpdate("UPDATE room SET available = '0' WHERE roomNum = '" + selectedRoom + "';");

        if (sqlStatement.getUpdateCount() > 0) {
            System.out.println("=========================================");
            System.out.println("Room is booked under: " + firstName + " " + lastName);
            System.out.println("Room category: "+ roomType);
            System.out.println("Room price: " + roomPrice + "SEK");
            System.out.println("Room number: " + selectedRoom);
            System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
            System.out.println("=========================================");
        }
    }

    public static <T extends BookRoom> void printRoomList(List<T> rooms) {

        for (T room : rooms) {
            room.print();
            System.out.println(room + "\n");
        }
    }
}