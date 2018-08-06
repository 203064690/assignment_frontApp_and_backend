package hotelReservation.controller;

import hotelReservation.domain.Room;
import hotelReservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path="/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @RequestMapping("/all")
    public @ResponseBody
    Iterable<Room> getAllcustomer()
    {
        return roomService.getAllRooms();
    }

    @RequestMapping(path = "/add")
    public @ResponseBody String createRoom (@RequestParam int roomNumber, @RequestParam String roomType, @RequestParam String roomView, @RequestParam double roomPrice){
        boolean created;
        created = roomService.createRoom(roomNumber, roomType, roomView, roomPrice);
        if(created==true)
            return "Room Booked";
        else
            return "Room not booked";
    }

    @PostMapping("/update")
    public @ResponseBody String updateCustomer ( @RequestBody Room room){
        boolean created, created2;
        created = roomService.updateRoom(room);

        if(created==true)
            return "Room Booked";
        else
            return "Room not booked";
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteUser (@PathVariable int roomNumber){
        boolean created;
        created = roomService.deleteRoom(roomNumber);
        if(created==true)
            return "Room Deleted";
        else
            return "Room not deleted";
    }
}
