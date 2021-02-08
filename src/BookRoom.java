public class BookRoom implements Comparable <BookRoom>{

    private final int roomNumber;
    private final String roomCategory;
    private final int roomPrice;

    public BookRoom(int roomNumber, String roomCategory, int roomPrice) {
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.roomPrice = roomPrice;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", roomCategory='" + roomCategory + '\'' +
                ", roomPrice=" + roomPrice +
                '}';
    }

    @Override
    public int compareTo(BookRoom o) {
        return 0;
    }

    public void print() {
        System.out.println(Menu.ANSI_YELLOW + "PETIT HOTEL ELITE room option: " + Menu.ANSI_RESET);
    }

}