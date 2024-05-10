
import javax.swing.JOptionPane;

public class Hotel {
   

    int Floors;
    int RoomsPerFloor;
    Room[][] rooms;

    Hotel(int Floors, int RoomsPerFloor) {

        this.SetFloors(Floors);
        this.SetRoomsPerFloor(RoomsPerFloor);
        rooms = new Room[Floors][RoomsPerFloor];
        for (int i = 0; i < Floors; i++) {
            for (int j = 0; j < RoomsPerFloor; j++) {
                rooms[i][j] = new Room(((i + 1) * 100) + (j + 1));
            }
        }
    }

    void SetFloors(int Floors) {
        this.Floors = Floors;
    }

    void SetRoomsPerFloor(int RoomsPerFloor) {
        this.RoomsPerFloor = RoomsPerFloor;
    }

    void ShowAllRooms() {
        String roomInfo = "";
        for (Room[] m : rooms) {
            for (Room k : m) {
                roomInfo += k.getRoomInfo() + "\n";
            }
        }
        JOptionPane.showMessageDialog(null, roomInfo, "All Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    void ShowAvailableRooms() {
        String roomInfo = "";
        for (Room[] m : rooms) {
            for (Room k : m) {
                if (!k.Booked) {
                    roomInfo += k.getRoomInfo() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, roomInfo, "Available Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    void ShowBookedRooms() {
        String roomInfo = "";
        for (Room[] m : rooms) {
            for (Room k : m) {
                if (k.Booked) {
                    roomInfo += k.getRoomInfo() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, roomInfo, "Booked Rooms", JOptionPane.INFORMATION_MESSAGE);
    }

    void SearchByNumOfBeds(int NumOfBeds) {
        String roomInfo = "";
        for (Room[] m : rooms) {
            for (Room k : m) {
                if (k.NumOfBeds == NumOfBeds) {
                    roomInfo += k.getRoomInfo() + "\n";
                }
            }
        }
        JOptionPane.showMessageDialog(null, roomInfo, "Search by Beds", JOptionPane.INFORMATION_MESSAGE);
    }

    void Reservation(int RoomID) {
        for (Room[] m : rooms) {
            for (Room k : m) {
                if (k.ID == RoomID) {
                    k.Reservation();
                    break;
                }
            }
        }
    }
}
