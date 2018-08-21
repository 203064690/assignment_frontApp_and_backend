package hotelReservation.controller;

import hotelReservation.domain.Room;
import hotelReservation.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@RestController    // This means that this class is a Controller
public class RoomController {

    @Autowired
    private RoomService roomService;

    //To display login screen
    @RequestMapping(value= {"/add_room"}, method =RequestMethod.GET)
    public ModelAndView customer(){

        ModelAndView model = new ModelAndView();
        model.setViewName("rooms/add_room");
        return model;
    }

    //To register user and return to Login screen
    @RequestMapping(value= {"/add_room"}, method=RequestMethod.POST)
    public ModelAndView createRoomr(@ModelAttribute("Room") Room room, BindingResult bindingResult) {
        Room roomExists = roomService.getRoom(room.getRoomNumber());
        System.out.println(room.toString());
        boolean created;
        if(roomExists != null) {
            bindingResult.rejectValue("room_number","Room number already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("rooms/add_room");
            model.addObject("room_number", "Room number already exists!");
            return model;
        } else {
            created = roomService.createRoom(room);
            Room roomDetails = roomService.getRoom(room.getRoomNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Room has been added successfully!");
            model.addObject("room", roomDetails);
            return model;
        }
    }

    //To view delete screen
    @RequestMapping(value= {"/delete_room"}, method = RequestMethod.GET)
    public ModelAndView viewDelete(){

        ModelAndView model = new ModelAndView();
        model.setViewName("rooms/delete_room");
        return model;
    }

    //To delete room
    @RequestMapping(value= {"/delete_room"}, method=RequestMethod.POST)
    public ModelAndView deleteStudent(@ModelAttribute("student") Room room, BindingResult bindingResult) {

        Room studentExists = roomService.getRoom(room.getRoomNumber());
        if(studentExists == null) {
            bindingResult.rejectValue("room_number", "Room does not exist");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("rooms/delete_room");
            model.addObject("room_number", "Room does not exist");
            return model;
        }

        else {

            roomService.deleteRoom(room.getRoomNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Room was deleted!");
            return model;
        }

    }
    //To view edit room
    @RequestMapping(value= {"/edit_room"}, method = RequestMethod.GET)
    public ModelAndView viewEditRoom(){

        ModelAndView model = new ModelAndView();
        model.setViewName("rooms/edit_room");
        return model;
    }

    //To update Room
    @RequestMapping(value= {"/edit_room"}, method=RequestMethod.POST)
    public ModelAndView updateRoom(@ModelAttribute("Room") Room room, BindingResult bindingResult) {
        boolean result;
        Room roomExists = roomService.getRoom(room.getRoomNumber());
        if(roomExists == null) {
            bindingResult.rejectValue("msg", "Room does not exist");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("/edit_room");
            model.addObject("msg", "Room does not exist");
            return model;
        }

        else {
            result = roomService.updateRoom(room);
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Room was udated!");
            System.out.println(result);
            return model;
        }

    }
}
