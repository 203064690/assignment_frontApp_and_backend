package hotelReservation.services.Impl;

import hotelReservation.domain.Room;
import hotelReservation.factories.RoomFactory;
import hotelReservation.repositories.RoomRepo;
import hotelReservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepo repositoryRoom;

    @Override
    public List<Room> getAllRooms() {
        List<Room> allRooms = new ArrayList<Room>();

        Iterable<Room> rooms = repositoryRoom.findAll();
        for (Room user : rooms) {
            allRooms.add(user);
        }
        return allRooms;
    }

    @Override
    public boolean createRoom(int roomNumber, String roomType, String roomView, double roomPrice) {
        int count = 0;
        boolean blnCreateRoom;
        Iterable<Room> rooms = repositoryRoom.findAll();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber)
            {
                count = count + 1;
            }
        }

        if (count == 0)
        {
            Room room = RoomFactory.createRoom(roomNumber, roomType, roomView, roomPrice);
            repositoryRoom.save(room);
            blnCreateRoom = true;
        }
        else
        {
            //False is generated if the room exists already
            blnCreateRoom = false;
        }
        return blnCreateRoom;
    }

    @Override
    public boolean updateRoom(Room roomNew) {
        boolean blnRoomUpdate = false;
        Long ID = 0L;
        Room rommUpdate = null;
        Iterable<Room> rooms = repositoryRoom.findAll();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNew.getRoomNumber())
            {
                blnRoomUpdate = true;
                ID = room.getID();
                rommUpdate = room;
            }
        }

        if (blnRoomUpdate == true)
        {
            Room newRoom = new Room.Builder(rommUpdate.getRoomNumber())
                    .ID(ID)
                    .room_type(roomNew.getRoomType())
                    .room_view(roomNew.getRoomView())
                    .room_price(roomNew.getRoomPrice())
                    .build();
            repositoryRoom.save(newRoom);
        }

        return blnRoomUpdate;
    }

    @Override
    public boolean deleteRoom(int roomNumber) {
        boolean blnRoomDelete = false;
        Long ID = 0L;
        Iterable<Room> rooms = repositoryRoom.findAll();
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber)
            {
                blnRoomDelete = true;
                ID = room.getID();
            }
        }

        if (blnRoomDelete == true)
        {
            repositoryRoom.deleteById(ID);
        }

        return blnRoomDelete;
    }
}
