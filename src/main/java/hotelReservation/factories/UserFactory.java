package hotelReservation.factories;

import hotelReservation.domain.User;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class UserFactory
{
    public static User createUser(String emailAddress,
                                  String pass,
                                  String recoveryQuestion,
                                  String recoveryAnswer)
    {
        User user = new User
                .Builder( emailAddress )
                .password( pass )
                .recoveryQuestion(recoveryQuestion)
                .recoveryAnswer(recoveryAnswer)
                .build();

        return user;
    }

}
