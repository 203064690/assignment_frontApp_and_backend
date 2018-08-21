package hotelReservation.controller;

import hotelReservation.domain.Booking;
import hotelReservation.domain.Room;
import hotelReservation.domain.ServicesAndAddOns;
import hotelReservation.services.BookingService;
import hotelReservation.services.RoomService;
import hotelReservation.services.ServicesAndAddOnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@Controller    // This means that this class is a Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ServicesAndAddOnsService servicesAndAddOnsService;

    @Autowired
    private RoomService roomService;

    @RequestMapping("/booking/all")
    public @ResponseBody
    Iterable<Booking> getAllbooking()
    {
        return bookingService.getAllBookings();
    }



    //To display login screen
    @RequestMapping(value= {"/add_booking"}, method =RequestMethod.GET)
    public ModelAndView rooms(){
        List<Room> allRoom = new ArrayList<>();
        Iterable<Room> allRoom2 = roomService.getAllRooms();
        for(Room room : allRoom2){
            allRoom.add(room);
        }

        List<ServicesAndAddOns> allServicesAndAddOns = new ArrayList<>();
        Iterable<ServicesAndAddOns> allServicesAndAddOns2 = servicesAndAddOnsService.getAllServicesAndAddOns();
        for(ServicesAndAddOns servicesAndAddOns : allServicesAndAddOns2){
            allServicesAndAddOns.add(servicesAndAddOns);
        }


        ModelAndView model = new ModelAndView();
        model.addObject("room_list", allRoom);
        model.addObject("services_list", allServicesAndAddOns);
        model.setViewName("booking/add_booking");
        return model;
    }

    //To register user and return to Login screen
    @RequestMapping(value= {"/add_booking"}, method=RequestMethod.POST)
    public ModelAndView createBooking(@ModelAttribute("Booking") Booking booking, BindingResult bindingResult) {
        System.out.println(booking.toString());
        Booking bookingExists = bookingService.getBooking(booking.getReferenceNumber());
        String created;
        if(bookingExists != null) {
            bindingResult.rejectValue("reference_number","Booking already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("booking/add_booking");
            model.addObject("reference_number", "Booking already exists!");
            return model;
        } else {
            created = bookingService.createBooking(booking.getReferenceNumber(), booking.getRooms(), booking.getServicesAndAddOns(), booking.getDate());
            Booking bookingDetails = bookingService.getBooking(booking.getReferenceNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Booking has been registered successfully!");
            model.addObject("booking", bookingDetails);
            return model;
        }
    }
}
