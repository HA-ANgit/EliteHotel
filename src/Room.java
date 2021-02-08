import java.util.Comparator;

public class Room {

    private String roomType;
    private String roomId;
    private int numBeds;
    private boolean ac;
    private boolean breakfast;
    private int cost;

    public Room(String roomId, String roomType, int numBeds, boolean ac, boolean breakfast, int cost) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.numBeds = numBeds;
        this.ac = ac;
        this.breakfast = breakfast;
        this.cost = cost;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return Menu.ANSI_YELLOW + "Details" + Menu.ANSI_RESET +
                Menu.ANSI_YELLOW + "\nRoom Type: " + Menu.ANSI_RESET + roomType +
                Menu.ANSI_YELLOW + "\nNumber of Beds: " + Menu.ANSI_RESET + numBeds +
                Menu.ANSI_YELLOW + "\nAC: " + Menu.ANSI_RESET + ac +
                Menu.ANSI_YELLOW + "\nBreakfast: " + Menu.ANSI_RESET + breakfast +
                Menu.ANSI_YELLOW + "\nDaily Cost: " + Menu.ANSI_RESET + cost +
                "\n";
    }

    public static Comparator<Room> compareCost = new Comparator<Room>() {
        @Override
        public int compare(Room room1, Room room2) {
            return (room1.cost -(room2.cost));
        }
    };

    public void callRoom () {
        System.out.println(roomId + " " + roomType);
    }
}

