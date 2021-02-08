import java.sql.SQLException;
import java.util.*;

public class RoomDetails {

    private static boolean exit = false;

    public static void showRoomType() throws SQLException {
        // lägga till felhantering

        while (!exit) {
            Menu.blob();
            System.out.println(Menu.ANSI_YELLOW + "Select Room: " + Menu.ANSI_RESET);
                List<Room> rooms = new ArrayList<>();

                Room room1 = new Room(Menu.ANSI_YELLOW + "[1]" + Menu.ANSI_RESET, "Standard Single Room", 1,true, true, 1440);
                Room room2 = new Room(Menu.ANSI_YELLOW + "[2]" + Menu.ANSI_RESET, "Deluxe Single Room", 1,true, true, 1820);
                Room room3 = new Room(Menu.ANSI_YELLOW + "[3]" + Menu.ANSI_RESET, "Standard Double Room", 2,true, true, 2200);
                Room room4 = new Room(Menu.ANSI_YELLOW + "[4]" + Menu.ANSI_RESET, "Deluxe Double Room", 3,true, true, 2980);
                Room room5 = new Room(Menu.ANSI_YELLOW + "[5]" + Menu.ANSI_RESET, "The Suite", 5,true, true, 5890);

                rooms = Arrays.asList(room1, room2, room3, room4, room5);

            //Lambda - Sort by cost
            Collections.max(rooms, (first, second) -> first.getCost() - second.getCost());

            int i = 1;
            rooms.stream().forEach(Room::callRoom);
            System.out.println(Menu.ANSI_YELLOW + "[6] " + Menu.ANSI_RESET + "Get all room details\n" + Menu.ANSI_YELLOW + "[7] " + Menu.ANSI_RESET + "Go back to main menu");

            int selection = Integer.parseInt(Menu.lineInput()); //TODO Referera till INT-input istället? vad är bäst för felhanteringen?

            switch (selection) {
                case 1:
                    System.out.println(room1.toString());
                    Menu.pressEnter();
                    break;
                case 2:
                    System.out.println(room2.toString());
                    Menu.pressEnter();
                    break;
                case 3:
                    System.out.println(room3.toString());
                    Menu.pressEnter();
                    break;
                case 4:
                    System.out.println(room4.toString());
                    Menu.pressEnter();
                case 5:
                    System.out.println(room5.toString());
                    Menu.pressEnter();
                    break;
                case 6:
                    // stream all roomdetails with foreach
                    rooms.forEach(System.out::println);
                default:
                    System.out.println("Do you want to continue? (y/n)");
                    String answer = Menu.lineInput();
                    if (answer.equals("y")) {
                        //startMethod();
                    } else {
                        exit = true;
                    }
            }

        }
    }
}
