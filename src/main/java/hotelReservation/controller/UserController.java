package hotelReservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hotelReservation.domain.User;
import hotelReservation.services.UserService;

/**
 * Hello world!
 *
 */

@RestController
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/user/all")
    public @ResponseBody Iterable<User> getAlluser()
    {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/user/get/{emailAddress}")
    public String getUserPost (@PathVariable String emailAddress){
        User getUser = null;
        String created;
        getUser =userService.getUser(emailAddress);
        created =getUser.toString();
        return created;
    }

    @RequestMapping(value = "/login.html", method = RequestMethod.POST) // Map ONLY Post Requests
    public String addUser (@RequestBody User userName) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        String created, created2;

       // created = userService.newUser(name, email);
        userService.PostnewUser(userName);
        return created2 = userName.getEmailAddress();
    }

/*
    @RequestMapping(path = "/user/add")
    public @ResponseBody String addNewUser (@RequestParam String email
            , @RequestParam String password, @RequestParam String recoveryQuestion, @RequestParam String recoveryAnswer){
        String created;
        created = userService.newUser(email, password, recoveryQuestion, recoveryAnswer);
        return created;
    }
*/
    @RequestMapping(value = "/user/delete/{emailAddress}", method = RequestMethod.DELETE)
    public String deleteUser (@PathVariable String emailAddress){
        String created;
        created = userService.deleteUser(emailAddress);
        return created;
    }

    @RequestMapping(path = "/user/change_password/")
    public @ResponseBody String updateUser (@RequestParam Long id, @RequestParam String password){
        String created;
        created = userService.ChangePassword(password, id);
        return created;
    }

}
