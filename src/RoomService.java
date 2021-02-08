import java.io.*;
import java.util.Scanner;
import java.util.function.Function;

public class RoomService {

    private static Scanner input = new Scanner(System.in);
    private static String again;
    private static int choose = 0, quantity = 0, total = 0;
    private static final int sandwich = 140;
    private static final int pasta = 160;
    private static final int noodles = 170;
    private static final int beverage = 40;

    public static void orderRoomService() {

        try {
            System.out.println("=======================");
            System.out.println("="+Menu.ANSI_YELLOW+"        MENU         "+Menu.ANSI_RESET+"=");
            System.out.println("="+Menu.ANSI_YELLOW+"  PETIT HOTEL ELITE  "+Menu.ANSI_RESET+"=");
            System.out.println("=======================");
            System.out.println("1. Sandwich\t     " + sandwich + "SEK");
            System.out.println("2. Pasta\t     " + pasta + "SEK");
            System.out.println("3. Noodles\t     " + noodles + "SEK");
            System.out.println("4. Beverages\t  " + beverage + "SEK");
            System.out.println("=======================");

            System.out.println("Enter your room number: ");
            int roomNumber = Integer.parseInt(input.nextLine());

            System.out.println("Enter number of chosen product: ");
            choose = Integer.parseInt(input.nextLine());

            switch (choose) {
                case 1:
                    System.out.println("You have chosen a sandwich.\nHow many would you like? ");
                    quantity = Integer.parseInt(input.nextLine());
                    total = total + (quantity * sandwich);
                    System.out.println("Would you like to buy something else? (y/n) ");
                    again = input.nextLine();

                    if (again.equals("y")) {
                        orderRoomService();
                    } else {
                        System.out.println("Do you want to see the receipt? (y/n) ");
                        String answer = input.nextLine();
                        if (answer.equals("y")) {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            try {
                                FileInputStream file = new FileInputStream("roomservice" + roomNumber + ".txt");
                                int i = 0;
                                while ((i = file.read()) != -1) {
                                    System.out.print((char) i);
                                }
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("You have chosen pasta.\nHow many would you like? ");
                    quantity = Integer.parseInt(input.nextLine());
                    total = total + (quantity * pasta);
                    System.out.println("Would you like to buy something else? (y/n) ");
                    again = input.nextLine();

                    if (again.equals("y")) {
                        orderRoomService();
                    } else {
                        System.out.println("Do you want to see the receipt? (y/n) ");
                        String answer = input.nextLine();
                        if (answer.equals("y")) {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            try {
                                FileInputStream file = new FileInputStream("roomservice" + roomNumber + ".txt");
                                int i = 0;
                                while ((i = file.read()) != -1) {
                                    System.out.print((char) i);
                                }
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("You have chosen noodles.\nHow many would you like? ");
                    quantity = Integer.parseInt(input.nextLine());
                    total = total + (quantity * noodles);
                    System.out.println("Would you like to buy something else? (y/n) ");
                    again = input.nextLine();

                    if (again.equals("y")) {
                        orderRoomService();
                    } else {
                        System.out.println("Do you want to see the receipt? (y/n) ");
                        String answer = input.nextLine();
                        if (answer.equals("y")) {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            try {
                                FileInputStream file = new FileInputStream("roomservice" + roomNumber + ".txt");
                                int i = 0;
                                while ((i = file.read()) != -1) {
                                    System.out.print((char) i);
                                }
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("You have chosen beverages.\nHow many would you like? ");
                    quantity = Integer.parseInt(input.nextLine());
                    total = total + (quantity * beverage);
                    System.out.println("Would you like to buy something else? (y/n) ");
                    again = input.nextLine();

                    if (again.equals("y")) {
                        orderRoomService();
                    } else {
                        System.out.println("Do you want to see the receipt? (y/n) ");
                        String answer = input.nextLine();
                        if (answer.equals("y")) {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            try {
                                FileInputStream file = new FileInputStream("roomservice" + roomNumber + ".txt");
                                int i = 0;
                                while ((i = file.read()) != -1) {
                                    System.out.print((char) i);
                                }
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        } else {
                            Function<Integer, Integer> add = n -> n;
                            int result = add.apply(total);
                            try {
                                FileOutputStream file = new FileOutputStream("roomservice" + roomNumber + ".txt");
                                String s = ("==================================" +
                                        "\nTotal room service cost: " + result + "SEK" +
                                        "\nRoom number: " + roomNumber +
                                        "\n==================================");
                                byte b[] = s.getBytes();
                                file.write(b);
                                file.close();
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid option, please try again");
        }
    }
}
