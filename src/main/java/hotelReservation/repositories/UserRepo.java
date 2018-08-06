package hotelReservation.repositories;

import hotelReservation.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface UserRepo extends CrudRepository<User, Long> {

}
