package hotelReservation.factories;


import hotelReservation.domain.Booking;
import hotelReservation.domain.Room;
import hotelReservation.domain.ServicesAndAddOns;

import java.util.Date;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class BookingFactory{

    public static Booking createBooking(String reference_number,
                                        List<Room> rooms,
                                        List<ServicesAndAddOns> services_and_addons,
                                        Date hireDate)
    {
        Booking booking = new Booking
                .Builder(reference_number)
                .rooms(rooms)
                .services_and_addons(services_and_addons)
                .hireDate(hireDate)
                .build();
        return booking;
    }

}
