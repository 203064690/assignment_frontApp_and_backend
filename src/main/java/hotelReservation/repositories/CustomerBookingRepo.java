package hotelReservation.repositories;

import hotelReservation.domain.CustomerBooking;
import org.springframework.data.repository.CrudRepository;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface CustomerBookingRepo extends CrudRepository<CustomerBooking, Long> {

}
