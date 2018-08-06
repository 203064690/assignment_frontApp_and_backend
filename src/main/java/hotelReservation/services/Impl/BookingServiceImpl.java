package hotelReservation.services.Impl;

import hotelReservation.domain.Booking;
import hotelReservation.domain.Room;
import hotelReservation.domain.ServicesAndAddOns;
import hotelReservation.repositories.BookingRepo;
import hotelReservation.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingRepo repositoryBooking;

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> allBookings = new ArrayList<Booking>();

        Iterable<Booking> bookings = repositoryBooking.findAll();
        for (Booking booking : bookings) {
            allBookings.add(booking);
        }
        return allBookings;
    }

    @Override
    public String createBooking(String referenceNumber, List<Room> roomList, List<ServicesAndAddOns> servicesAndAddOnsList, Date hireDate) {
        int count = 0;
        boolean blnCreateBooking;

        List<Room> tempRoomList = roomList;
        List<ServicesAndAddOns> tempServicesAndAddOnsList = servicesAndAddOnsList;

        Iterable<Booking> bookings = repositoryBooking.findAll();
        for (Booking booking : bookings) {
           if(booking.getReferenceNumber().equalsIgnoreCase(referenceNumber))
           {
               count = count + 1;
           }
        }
        if(count == 0)
        {
            Booking booking = new Booking.Builder(referenceNumber)
                    .rooms(tempRoomList)
                    .services_and_addons(tempServicesAndAddOnsList)
                    .hireDate(hireDate)
                    .build();
            repositoryBooking.save(booking);

            blnCreateBooking = true;
        }
        else
        {
            blnCreateBooking = false;
        }
        if(blnCreateBooking==true)
            return "Booking created";
        else
            return "Booking cannot be created";
    }

    @Override
    public String deleteBooking(String referenceNumber) {
        Long bookingID = 0L;
        boolean blnDeleteCustomerBooking = false;

        Iterable<Booking> bookings = repositoryBooking.findAll();
        for (Booking booking : bookings) {
            if(booking.getReferenceNumber().equalsIgnoreCase(referenceNumber))
            {
                bookingID = booking.getID();
                blnDeleteCustomerBooking = true;
            }
        }
        if(blnDeleteCustomerBooking == true)
        {
            repositoryBooking.deleteById(bookingID);
        }
        if(blnDeleteCustomerBooking==true)
            return "Booking deleted";
        else
            return "Booking cannot be deleted";
    }
}
