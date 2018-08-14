package hotelReservation.services.Impl;

import hotelReservation.domain.User;
import hotelReservation.factories.UserFactory;
import hotelReservation.repositories.UserRepo;
import hotelReservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo repositoryUser;
/*
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
*/
    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<User>();

        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            allUsers.add(user);
        }
        return allUsers;
    }


    @Override
    public User getUser(String email) {

        String strUserLogIn = "";
        String strUser = "";
        int countUser = 0;
        User gotUser = null;

        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if (user.getEmailAddress().equalsIgnoreCase(email))
            {
                countUser = countUser + 1;
                gotUser = user;
            }

        }
        if (countUser != 0)
        {
            strUser = "Username: Found";
        }
        else
        {
            strUser = "Username: Not Found";
        }


        strUserLogIn = strUser + "\n";

        return gotUser;

    }
/*
    @Override
    public String newUser(String emailAddress, String password, String recoveryQuestion, String recoveryAnswer) {

        int count = 0;
        boolean blnCreateUser;
        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if (user.getEmailAddress().equalsIgnoreCase(emailAddress))
            {
                count = count + 1;
            }
        }

        if (count == 0)
        {
            User user = UserFactory.createUser(emailAddress, password, recoveryQuestion, recoveryAnswer);
            repositoryUser.save(user);
            blnCreateUser = true;
        }
        else
        {
            blnCreateUser = false;
        }


        if(blnCreateUser==true)
            return "User saved";
        else
            return "User not saved";
    }
*/
    @Override
    public String saveUser(User userName) {

        int count = 0;
        boolean blnCreateUser;
        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if (user.getEmailAddress().equalsIgnoreCase(userName.getEmailAddress()))
            {
                count = count + 1;
            }
        }

        if (count == 0)
        {
            //userName.setPassword(bCryptPasswordEncoder.encode(userName.getPassword()));
            User user = UserFactory.createUser(userName.getEmailAddress(), userName.getPassword(), userName.getRecoveryQuestion(), userName.getRecoveryAnswer());
            repositoryUser.save(user);
            blnCreateUser = true;
        }
        else
        {
            blnCreateUser = false;
        }

        if(blnCreateUser==true)
            return "User saved";
        else
            return "User not saved";
    }

    @Override
    public String deleteUser(String emailAddress) {
        Long userID = 0L;
        boolean blnDeleteUser = false;

        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if(user.getEmailAddress().equalsIgnoreCase(emailAddress))
            {
                userID = user.getID();
                blnDeleteUser = true;
            }
        }
        if(blnDeleteUser == true)
        {
            repositoryUser.deleteById(userID);
        }

        if(blnDeleteUser==true)
            return "User deleted";
        else
            return "User cannot be deleted";

    }

    @Override
    public Long ForgottenPassword(String emailAddress) {

        Long userID = 0L;
        int count = 0;
        boolean blnUserFound;
        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if (user.getEmailAddress().equalsIgnoreCase(emailAddress))
            {
                user.getID();
            }
        }

        return userID;
    }

    @Override
    public String RecoveryQuestion(Long ID)
    {
        String userRecoverQ = "";
        boolean recoverQUser = false;

        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if(user.getID().equals(ID))
            {
                userRecoverQ = user.getRecoveryQuestion();
                recoverQUser = true;
            }
        }

        if(recoverQUser==true)
            return userRecoverQ;
        else
            return "Question cannot be retrieved";
    }

    //This function is called after the recoveryQuestion function returns a true value
    @Override
    public boolean RecoveryAnswer(String recoveryAnswer, Long ID) {
        boolean blnCorrectAnswer = false;
        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if (user.getRecoveryAnswer().equalsIgnoreCase(recoveryAnswer))
                blnCorrectAnswer = true;
             else
                blnCorrectAnswer = false;
        }

        return blnCorrectAnswer;
    }

    @Override
    public String ChangePassword(String password, Long ID)
    {
        boolean blnPasswordChange = false;
        User user1 = null;
        Iterable<User> users = repositoryUser.findAll();
        for (User user : users) {
            if(user.getID().equals(ID))
            {
                user1 = user;
            }
        }



        User newUser = new User.Builder(user1.getEmailAddress())
                .ID(ID)
                .password(password)
                .recoveryQuestion(user1.getRecoveryQuestion())
                .recoveryAnswer(user1.getRecoveryAnswer())
                .build();
        repositoryUser.save(newUser);
        blnPasswordChange = true;

        if(blnPasswordChange==true)
            return "Password changed";
        else
            return "Password cannot be changed";
    }
}
