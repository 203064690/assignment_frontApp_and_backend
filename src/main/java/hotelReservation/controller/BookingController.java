package hotelReservation.controller;

import hotelReservation.domain.Booking;
import hotelReservation.domain.Room;
import hotelReservation.domain.ServicesAndAddOns;
import hotelReservation.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path="/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @RequestMapping("/all")
    public @ResponseBody
    Iterable<Booking> getAllbooking()
    {
        return bookingService.getAllBookings();
    }



    @RequestMapping(path = "/add")
    public @ResponseBody String createBooking(@RequestParam String referenceNumber, @RequestParam List<Room> roomList, @RequestParam List<ServicesAndAddOns> servicesAndAddOnsList, @RequestParam Date hireDate){
        String created;
        created = bookingService.createBooking(referenceNumber, roomList, servicesAndAddOnsList, hireDate);
        return created;
    }


    @RequestMapping(path = "/delete/{id}")
    public String deleteUser (@PathVariable String id){
        String created;
        created = bookingService.deleteBooking(id);
        return created;
    }
}
