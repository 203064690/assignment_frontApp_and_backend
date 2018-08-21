package hotelReservation.controller;

import hotelReservation.domain.CustomerBooking;
import hotelReservation.services.CustomerBookingService;
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
public class CustomerBookingController {
    @Autowired
    private CustomerBookingService customerBookingService;

    @RequestMapping("/customeBooking/all")
    public @ResponseBody
    Iterable<CustomerBooking> getAllcustomerBooking()
    {
        return customerBookingService.getAllCustomerBookings();
    }



    @RequestMapping(path = "/customeBooking/add")
    public @ResponseBody String createCustomerBooking (@RequestParam String referenceNumber, @RequestParam String idNumber, @RequestParam String firstNames, @RequestParam String lastName){
        Boolean created;
        created = customerBookingService.createCustomerBooking(referenceNumber, idNumber, firstNames, lastName);
        if(created==true)
            return "Customer Booking created";
        else
            return "Customer Booking not created";
    }

    @RequestMapping("/customeBooking/update")
    public @ResponseBody String updateCustomerBooking ( @RequestBody String referenceNumber){
        Boolean created, created2;
        created = customerBookingService.updateCustomerBooking(referenceNumber);
        if(created==true)
            return "Customer Booking updated";
        else
            return "Customer Booking not updated";
    }

    @RequestMapping(path = "/customeBooking/delete/{referenceNumber}")
    public String deleteCustomerBooking (@PathVariable String referenceNumber){
        boolean created;
        created = customerBookingService.deleteCustomerBooking(referenceNumber);
        if(created==true)
            return "Customer Booking deleted";
        else
            return "Customer Booking not deleted";
    }
}
