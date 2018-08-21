package hotelReservation.services;

import hotelReservation.domain.Booking;
import hotelReservation.domain.Room;
import hotelReservation.domain.ServicesAndAddOns;

import java.util.Date;
import java.util.List;

/**
 * Created by student on 2015/09/13.
 */
public interface BookingService {
    public List<Booking> getAllBookings();
    public Booking getBooking(String ID_number);
    public String createBooking(String referenceNumber, List<Room> roomList, List<ServicesAndAddOns> servicesAndAddOnsList, Date hireDate);
    public String deleteBooking(String referenceNumber);
}
/*
    No updating just remove room from room list
    Same with Add Ons
 */
