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

    public static Booking createBooking(String ref_num,
                                        List<Room> rooms,
                                        List<ServicesAndAddOns> servicesAndAddOnslist,
                                        Date hireDate)
    {
        Booking booking = new Booking
                .Builder(ref_num)
                .rooms(rooms)
                .services_and_addons(servicesAndAddOnslist)
                .hireDate(hireDate)
                .build();
        return booking;
    }

}
