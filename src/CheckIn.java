import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CheckIn {

    private static boolean exit = false;
    private static Scanner input = new Scanner(System.in);
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
            String firstName = input.nextLine();
            System.out.println("Enter your last name: ");
            String lastName = input.nextLine();
            System.out.println("Enter your phone number: ");
            int phoneNum = Integer.parseInt(input.nextLine());
            System.out.println("Which room category would you like to book? ");
            System.out.println("1. Deluxe Double Room");
            System.out.println("2. Deluxe Single Room");
            System.out.println("3. Standard Double Room");
            System.out.println("4. Standard Single Room");
            System.out.println("5. Suite");
            System.out.println("6. Exit");
            int select = Integer.parseInt(input.nextLine());

            switch (select) {
                //Deluxe Double Room
                case 1:
                    try {
                        Connection con = sqlStatement.getConnection();
                        Statement stmt = con.createStatement();
                        do {
                            printRoomList(bookRoomDDR);
                            System.out.println("Enter room number for selected room: ");
                            int selectedRoomDDR = Integer.parseInt(input.nextLine());

                            String SQL = "SELECT roomNum FROM inhouseguest WHERE roomNum =" + selectedRoomDDR + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                            } else {
                                System.out.println("This room is available\n");
                                Menu.pressEnter();
                                System.out.println("Enter room price: ");
                                int roomPrice = Integer.parseInt(input.nextLine());
                                sqlStatement.executeUpdate("INSERT INTO inhouseguest(roomNum, cost, firstName, lastName) VALUES('" + selectedRoomDDR + "', '" + roomPrice + "', '" + firstName + "','" + lastName + "')");
                                sqlStatement.executeUpdate("INSERT INTO customer(phoneNum, firstName, lastName, lastUpdate) VALUES('" + phoneNum + "', '" + firstName + "', '" + lastName + "','"+datetime+"');");
                                if (true) {
                                    PreparedStatement state = connection.prepareStatement("UPDATE bookroom SET checkIn = ? WHERE roomNum = ?");
                                    state.setDate(1, Date.valueOf(date));
                                    state.setInt(2, selectedRoomDDR);
                                    state.executeUpdate();
                                    System.out.println("=========================================");
                                    System.out.println("Room is booked under: " + firstName + " " + lastName);
                                    System.out.println("Room category: Deluxe Double Room");
                                    System.out.println("Room price: " + roomPrice + "SEK");
                                    System.out.println("Room number: " + selectedRoomDDR);
                                    System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
                                    System.out.println("=========================================");
                                    Management.connect();
                                }
                            }
                            Menu.pressEnter();
                            System.out.println("Do you want to search for another room? (y/n)");
                            answer = input.nextLine();
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
                            int selectedRoomDSR = Integer.parseInt(input.nextLine());

                            String SQL = "SELECT roomNum FROM inhouseguest WHERE roomNum =" + selectedRoomDSR + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                            } else {
                                System.out.println("This room is available\n");
                                Menu.pressEnter();
                                System.out.println("Enter room price: ");
                                int roomPrice = Integer.parseInt(input.nextLine());
                                sqlStatement.executeUpdate("INSERT INTO inhouseguest(roomNum, cost, firstName, lastName) VALUES('" + selectedRoomDSR + "', '" + roomPrice + "', '" + firstName + "','" + lastName + "')");
                                sqlStatement.executeUpdate("INSERT INTO customer(phoneNum, firstName, lastName, lastUpdate) VALUES('" + phoneNum + "', '" + firstName + "', '" + lastName + "','"+datetime+"');");
                                if (true) {
                                    PreparedStatement state = connection.prepareStatement("UPDATE bookroom SET checkIn = ? WHERE roomNum = ?");
                                    state.setDate(1, Date.valueOf(date));
                                    state.setInt(2, selectedRoomDSR);
                                    state.executeUpdate();
                                    System.out.println("=========================================");
                                    System.out.println("Room is booked under: " + firstName + " " + lastName);
                                    System.out.println("Room category: Deluxe Single Room");
                                    System.out.println("Room price: " + roomPrice + "SEK");
                                    System.out.println("Room number: " + selectedRoomDSR);
                                    System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
                                    System.out.println("=========================================");
                                    Management.connect();
                                }
                            }
                            Menu.pressEnter();
                            System.out.println("Do you want to search for another room? (y/n)");
                            answer = input.nextLine();
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
                            int selectedRoomSDR = Integer.parseInt(input.nextLine());

                            String SQL = "SELECT roomNum FROM inhouseguest WHERE roomNum =" + selectedRoomSDR + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                            } else {
                                System.out.println("This room is available\n");
                                Menu.pressEnter();
                                System.out.println("Enter room price: ");
                                int roomPrice = Integer.parseInt(input.nextLine());
                                sqlStatement.executeUpdate("INSERT INTO inhouseguest(roomNum, cost, firstName, lastName) VALUES('" + selectedRoomSDR + "', '" + roomPrice + "', '" + firstName + "','" + lastName + "')");
                                sqlStatement.executeUpdate("INSERT INTO customer(phoneNum, firstName, lastName, lastUpdate) VALUES('" + phoneNum + "', '" + firstName + "', '" + lastName + "','"+datetime+"');");
                                if (true) {
                                    PreparedStatement state = connection.prepareStatement("UPDATE bookroom SET checkIn = ? WHERE roomNum = ?");
                                    state.setDate(1, Date.valueOf(date));
                                    state.setInt(2, selectedRoomSDR);
                                    state.executeUpdate();
                                    System.out.println("=========================================");
                                    System.out.println("Room is booked under: " + firstName + " " + lastName);
                                    System.out.println("Room category: Standard Double Room");
                                    System.out.println("Room price: " + roomPrice + "SEK");
                                    System.out.println("Room number: " + selectedRoomSDR);
                                    System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
                                    System.out.println("=========================================");
                                    Management.connect();
                                }
                            }
                            Menu.pressEnter();
                            System.out.println("Do you want to search for another room? (y/n)");
                            answer = input.nextLine();
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
                            int selectedRoomSSR = Integer.parseInt(input.nextLine());

                            String SQL = "SELECT roomNum FROM inhouseguest WHERE roomNum =" + selectedRoomSSR + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                            } else {
                                System.out.println("This room is available\n");
                                Menu.pressEnter();
                                System.out.println("Enter room price: ");
                                int roomPrice = Integer.parseInt(input.nextLine());
                                sqlStatement.executeUpdate("INSERT INTO inhouseguest(roomNum, cost, firstName, lastName) VALUES('" + selectedRoomSSR + "', '" + roomPrice + "', '" + firstName + "','" + lastName + "')");
                                sqlStatement.executeUpdate("INSERT INTO customer(phoneNum, firstName, lastName, lastUpdate) VALUES('" + phoneNum + "', '" + firstName + "', '" + lastName + "','"+datetime+"');");
                                if (true) {
                                    PreparedStatement state = connection.prepareStatement("UPDATE bookroom SET checkIn = ? WHERE roomNum = ?");
                                    state.setDate(1, Date.valueOf(date));
                                    state.setInt(2, selectedRoomSSR);
                                    state.executeUpdate();
                                    System.out.println("=========================================");
                                    System.out.println("Room is booked under: " + firstName + " " + lastName);
                                    System.out.println("Room category: Standard Single Room");
                                    System.out.println("Room price: " + roomPrice + "SEK");
                                    System.out.println("Room number: " + selectedRoomSSR);
                                    System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
                                    System.out.println("=========================================");
                                    Management.connect();
                                }
                            }
                            Menu.pressEnter();
                            System.out.println("Do you want to search for another room? (y/n)");
                            answer = input.nextLine();
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
                            int selectedRoomSSR = Integer.parseInt(input.nextLine());

                            String SQL = "SELECT roomNum FROM inhouseguest WHERE roomNum =" + selectedRoomSSR + "";
                            ResultSet rs = stmt.executeQuery(SQL);
                            if (rs.next()) {
                                System.out.println("This room is occupied\n");
                            } else {
                                System.out.println("This room is available\n");
                                Menu.pressEnter();
                                System.out.println("Enter room price: ");
                                int roomPrice = Integer.parseInt(input.nextLine());
                                sqlStatement.executeUpdate("INSERT INTO inhouseguest(roomNum, cost, firstName, lastName) VALUES('" + selectedRoomSSR + "', '" + roomPrice + "', '" + firstName + "','" + lastName + "')");
                                sqlStatement.executeUpdate("INSERT INTO customer(phoneNum, firstName, lastName, lastUpdate) VALUES('" + phoneNum + "', '" + firstName + "', '" + lastName + "','"+datetime+"');");
                                if (true) {
                                    PreparedStatement state = connection.prepareStatement("UPDATE bookroom SET checkIn = ? WHERE roomNum = ?");
                                    state.setDate(1, Date.valueOf(date));
                                    state.setInt(2, selectedRoomSSR);
                                    state.executeUpdate();
                                    System.out.println("=========================================");
                                    System.out.println("Room is booked under: " + firstName + " " + lastName);
                                    System.out.println("Room category: Suite");
                                    System.out.println("Room price: " + roomPrice + "SEK");
                                    System.out.println("Room number: " + selectedRoomSSR);
                                    System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE" + Menu.ANSI_RESET);
                                    System.out.println("=========================================");
                                    Management.connect();
                                }
                            }
                            Menu.pressEnter();
                            System.out.println("Do you want to search for another room? (y/n)");
                            answer = input.nextLine();
                        } while (answer.equals("y"));
                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                    }
                    break;
                default:
                    System.out.println("Do you want to go back to the main menu? (y/n)");
                    String answer1 = input.nextLine();
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

    public static <T extends BookRoom> void printRoomList(List<T> rooms) {

        for (T room : rooms) {
            room.print();
            System.out.println(room + "\n");
        }
    }
}