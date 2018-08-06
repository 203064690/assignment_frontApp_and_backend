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

    @RequestMapping("/user")
    public @ResponseBody Iterable<User> getAlluser()
    {
        return userService.getAllUsers();
    }

    @RequestMapping("/user/")
    public String getUser(@RequestParam String username, @RequestParam String password){
        return userService.getUser(username, password);
    }

    @RequestMapping(value ="/user/add_post", method = RequestMethod.POST) // Map ONLY Post Requests
    public String addUser (@RequestBody User userName) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        String created, created2;

       // created = userService.newUser(name, email);
        userService.PostnewUser(userName);
        return created2 =userName.getID() + " " + userName.getEmailAddress();
    }


    @RequestMapping(path = "/user/add")
    public @ResponseBody String addNewUser (@RequestParam String email
            , @RequestParam String password, @RequestParam String recoveryQuestion, @RequestParam String recoveryAnswer){
        String created;
        created = userService.newUser(email, password, recoveryQuestion, recoveryAnswer);
        return created;
    }

    @RequestMapping(path = "/user/delete/{id}")
    public String deleteUser (@PathVariable Long id){
        String created;
        created = userService.deleteUser(id);
        return created;
    }

    @RequestMapping(path = "/user/change_password/")
    public @ResponseBody String updateUser (@RequestParam Long id, @RequestParam String password){
        String created;
        created = userService.ChangePassword(password, id);
        return created;
    }

}
