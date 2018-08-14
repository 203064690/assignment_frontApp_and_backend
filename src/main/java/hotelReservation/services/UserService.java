package hotelReservation.services;

import hotelReservation.domain.User;

import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface UserService {
    public List<User> getAllUsers();
    public User getUser(String email);
  //  public String newUser(String emailAddress, String password, String recoveryQuestion, String recoveryAnswer);
    public Long ForgottenPassword(String emailAddress);
    public String RecoveryQuestion(Long ID);
    public String saveUser(User userName);
    public boolean RecoveryAnswer(String recoveryAnswer, Long ID);
    public String ChangePassword(String password, Long ID);
    public String deleteUser(String emailAddress);
}
