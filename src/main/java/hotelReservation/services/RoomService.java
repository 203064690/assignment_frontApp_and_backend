package hotelReservation.services;

import hotelReservation.domain.Room;

import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface RoomService {
    public List<Room> getAllRooms();
    public boolean createRoom(int roomNumber, String roomType, String roomView, double roomPrice);
    public boolean updateRoom(Room room);
    public boolean deleteRoom(int roomNumber);
}
