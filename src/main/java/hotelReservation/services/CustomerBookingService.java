package hotelReservation.services;

import hotelReservation.domain.CustomerBooking;

import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface CustomerBookingService {
    public List<CustomerBooking> getAllCustomerBookings();
    public boolean createCustomerBooking(String referenceNumber, String idNumber, String firstNames, String lastName);
    public boolean updateCustomerBooking(String referenceNumber);
    public boolean deleteCustomerBooking(String referenceNumber);

}
/*
    Function createCustomerBooking is called after a createBooking is called they are linked
    by their reference number
    For updating customer booking just update the Booking directly
 */