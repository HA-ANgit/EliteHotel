import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Reception {
  private static final String url = "jdbc:mysql://localhost:3306/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "Jonnydepp2121";
    private static Scanner input = new Scanner(System.in);
    private static Statement sqlStatement = null;
    private static Connection connection = null;
    private static boolean exit = false;

    public static void reception() throws SQLException {

       try (Connection connection = DriverManager.getConnection(url, user, password)) {

            System.out.println("Anslutningen lyckades!\n");
            sqlStatement = connection.createStatement();

            while (!exit) {
                
 System.out.println("=======================");
            System.out.println("=      RECEPTION        =");
            System.out.println("=  PETIT HOTEL ELITE  =");
            System.out.println("======================= \n");
                             
                System.out.println("Enter your choice: \n");
                System.out.println("1. Display room details:\n ");
                System.out.println("2. Display room available \n ");
                System.out.println("3. Searching Customer details \n");
                System.out.println("4. Upgrade and delete Customer details) \n "); //Vad för detaljer vill vi kunna uppgradera? Tar nu bara bort kund
                System.out.println("5. Booking or upgrade a booking \n");    //Ska detta vara i samma meny?
                System.out.println("6. Ordering Food for Particular Room )\n");
                System.out.println("7. Check out for customer and showing bill) \n ");
                System.out.println("9. Exit \n");

                int selection = input.nextInt();

                switch (selection) {
                    case 1:
                        RoomDetails.showRoomType();
                        break;
                    case 2:
                        Management.selectTable("* FROM available");
                        break;
                    case 3:
                        SearchCustomer();
                        break;
                    case 4:
                        DeleteCustomer();
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        Management.selectTable("* FROM customer"); //Vad gör denna metod?
                        break;
                    default:
                        exit = true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
        
         private static void StoreCustomer() throws SQLException {

             System.out.println("What is the customers room number \n");
             
             int RoomNumber = input.nextInt();
             
           input.nextLine();
             
             
             
             System.out.println("What kind of room has customer choiced? Alternatives are downbelow \n");
             
             
             
             System.out.println("1. Luxury Double Room\n" +                         //Mellanslag funkar inte när man matar in på databasen
"2.Deluxe Double1 Room\n" +
"3. Luxury Single Room\n" +
"4.Deluxe Single Room \n");
             
             
             int RoomInfo = input.nextInt();
             
              input.nextLine();
             
             
            System.out.println("\n");
             System.out.println("Enter Customer FirstName: \n");
             
             String FirstName = input.nextLine();
             
             System.out.println("Enter Customer LastName: \n");
             
             String LastName = input.nextLine();

             System.out.println("Enter Customer contact number: ");
             
             int CustomerPhone =input.nextInt();
             
             input.nextLine();
             
             System.out.println("INSERT INTO Customer_info(RoomNumber,RoomInfo,FirstName,LastName,CustomerPhone) VALUES('" + RoomNumber + "','" + RoomInfo + "', '" + FirstName + "','" + LastName + "', '" + CustomerPhone + "' );");
     sqlStatement.executeUpdate("INSERT INTO Customer_info(RoomNumber,RoomInfo,FirstName,LastName,CustomerPhone) VALUES('" + RoomNumber + "','" + RoomInfo + "', '" + FirstName + "','" + LastName + "', '" + CustomerPhone + "' );");
     System.out.println("\n");
    }
    
          private static void DeleteCustomer() throws SQLException {
   
        

        System.out.println("Give the customers roomNumber to delete:");
        int RoomNumber = input.nextInt();
        
input.nextLine();

        int result = sqlStatement.executeUpdate("DELETE FROM Customer_info WHERE RoomNumber =" + RoomNumber + ";");

        if (result == 1) {
            System.out.println("Customer with RoomNumber: " + RoomNumber + " are now gone!");
        } else {
            System.err.println("Remove of customer failed!,try again");
        }
                 
        
        
              System.out.println("\n");
              
              System.out.println("Press Enter to update to a new customer \n ");
              input.nextLine();
              
        StoreCustomer();
}

           private static int SearchCustomer() throws SQLException {
           Menu.pressEnter();
      System.out.println("Add the customerID number: ");
        int roomNumber = input.nextInt();
        input.nextLine();

ResultSet result1 = sqlStatement.executeQuery("SELECT * FROM customer Where CustomerId = " + roomNumber + ";");

        while (result1.next()) {

            int columnCount = result1.getMetaData().getColumnCount();
            // Hämtar alla kolmnnamn
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = result1.getMetaData().getColumnName(i + 1);
            }

            // Lägg kolumnnamnen i string array
            for (String columnName : columnNames) {
                System.out.print(PadRight(columnName));
            }

            while (result1.next()) {
                System.out.println();
                // Hämtar data för alla kolumner för den nuvarande raden
                for (String columnName : columnNames) {
                   String value = result1.getString(columnName);

                    if (value == null)
                       value = "null";
                    
   System.out.print(PadRight(value));
               }
                
          if (columnCount == 0) {
            System.out.println("\nNo result!");
        } else {
        }
            }
        System.out.println();
           }
      return 0;
        

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
            System.out.print( PadRight(columnName));
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

}