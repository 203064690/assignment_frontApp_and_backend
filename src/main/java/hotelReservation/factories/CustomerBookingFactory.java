package hotelReservation.factories;

import hotelReservation.domain.CustomerBooking;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class CustomerBookingFactory {

    public static CustomerBooking createCustomerBooking(String referenceNumber,
                                                        String idNumber,
                                                        String firstNames,
                                                        String lastName)
    {
        CustomerBooking customerBooking = new CustomerBooking
                .Builder(referenceNumber)
                .idNumber(idNumber)
                .firstNames(firstNames)
                .lastName(lastName)
                .build();
        return customerBooking;
    }
}
