import java.sql.SQLException;
import java.util.Scanner;

public class Menu {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void welcome(){
        System.out.println(ANSI_BLACK_BACKGROUND + ANSI_WHITE + "\t\t  ---  ===  PETIT HOTEL ELITE®  ===  ---   \t\t\t" + ANSI_RESET);
        System.out.println("\n\t\t\t\t  █▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀█\n" +
                "\t\t\t\t  █░░" + ANSI_YELLOW + "╦─╦╔╗╦─╔╗╔╗╔╦╗╔╗" + ANSI_RESET + "░░█\n" +
                "\t\t\t\t  █░░" + ANSI_YELLOW + "║║║╠─║─║─║║║║║╠─" + ANSI_RESET + "░░█\n" +
                "\t\t\t\t  █░░" + ANSI_YELLOW + "╚╩╝╚╝╚╝╚╝╚╝╩─╩╚╝" + ANSI_RESET + "░░█\n" +
                "\t\t\t\t  █▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄█\n\t\t");
        System.out.println("\t\t\t\t༄  Version (0.1.005)  ❦\n");
    }     //Current version av library + welcome-menyn

    public static void logo(){

        System.out.println(ANSI_YELLOW + "               _       _       _             _           _   \n" +
                "              ( )_  _ ( )_    ( )           ( )_        (_ ) \n" +
                " _ _      __  | ,_)(_)| ,_)   | |__     _   | ,_)   __   | | \n" +
                "( '_`\\  /'__`\\| |  | || |     |  _ `\\ /'_`\\ | |   /'__`\\ | | \n" +
                "| (_) )(  ___/| |_ | || |_    | | | |( (_) )| |_ (  ___/ | | \n" +
                "| ,__/'`\\____)`\\__)(_)`\\__)   (_) (_)`\\___/'`\\__)`\\____)(___)\n" +
                "| |                                                          \n" +
                "(_)                                                          " + ANSI_RESET);
    }

    public static void blob(){
        System.out.println(ANSI_BLACK_BACKGROUND + "  --- ===  ░░░░  ░░░░  ░░░  ░░░  ░░░░  ░░░░  === ---  " + ANSI_RESET + "\n");
    }

    public static void startMenu() throws SQLException {
            System.out.println(ANSI_BLACK_BACKGROUND + ANSI_WHITE +" --- ---== ==== ===== START MENU ===== ==== ==--- --- " + ANSI_RESET);
            Management.connect();
    }

    public static void pressEnter() {
        System.out.println(ANSI_GREEN +" -=  PRESS ENTER TO CONTINUE  =- " + ANSI_RESET);
        lineInput();
    }

    public static int intInput(){
        int userInput;
        try{
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextInt();
            return userInput;
        } catch (Exception allExceptions){
            System.out.println("\t\nError: please insert a correct number!");
            return userInput = 20;
        }
    }     //Detta är int-metoden för inmatning i menyerna INKL felhantering

    public static String lineInput(){
        String userInput;
        try{
            Scanner sc = new Scanner(System.in);
            userInput = sc.nextLine();
            return userInput;
        } catch (Exception allExceptions){
            System.out.println("\t\nError: please insert a valid Text-line");
            return userInput = "null";
        }
    }     //Detta är String-metoden för inmatning i menyerna INKL felhantering

}