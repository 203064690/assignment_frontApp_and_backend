package hotelReservation.repositories;

import hotelReservation.domain.Booking;
import org.springframework.data.repository.CrudRepository;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface BookingRepo extends CrudRepository<Booking, Long> {

}
