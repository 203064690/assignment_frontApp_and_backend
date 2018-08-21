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
    public Room getRoom(int roomNumber);
    public boolean createRoom(Room room);
    public boolean updateRoom(Room room);
    public boolean deleteRoom(int roomNumber);
}
