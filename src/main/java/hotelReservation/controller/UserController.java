package hotelReservation.controller;

import hotelReservation.domain.User;
import hotelReservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    //To display login screen
    @RequestMapping(value= {"/", "/login"}, method =RequestMethod.GET)
    public ModelAndView login(){

        ModelAndView model = new ModelAndView();
        model.setViewName("user/login");
        return model;
    }

    @RequestMapping(value= {"/register"}, method=RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/register");

        return model;
    }
    @RequestMapping(value= {"/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        model.setViewName("home/home");

        return model;
    }

    @RequestMapping(value= {"/login"}, method=RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {

        User userExists = userService.getUser(user.getEmailAddress(), user.getPassword());
        //System.out.println(userExists.toString());
        if(userExists == null) {
            bindingResult.rejectValue("emailAddress", "User does not exist. Please register");

        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("user/login");
            model.addObject("emailAddress", "User does not exist, Please register");
            return model;
        }

        else {
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("user", userExists);
            model.addObject("msg", "You are now logged in!");
            return model;
        }

    }
    //To register user and return to Login screen
    @RequestMapping(value= {"/register"}, method=RequestMethod.POST)
    public ModelAndView createUser(@ModelAttribute("User") User user, BindingResult bindingResult) {
        User userExists = userService.getUser(user.getEmailAddress(), user.getPassword());
        String created = null;
        if(userExists != null) {
            bindingResult.rejectValue("emailAddress","This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("user/register");
            model.addObject("emailAddress", "This email already exists!");
            return model;
        } else {
            created = userService.saveUser(user);
            User userDetails = userService.getUser(user.getEmailAddress(), user.getPassword());
            ModelAndView model = new ModelAndView("user/login");
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", userDetails.toString());
            System.out.println(user.toString());
            System.out.println(created);
            return model;
        }
    }
/*
    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home(String emailAddress, String password) {
        ModelAndView model = new ModelAndView();
        User user = userService.getUser(emailAddress, password);

        model.addObject("userName", user.getEmailAddress() + " " + user.getRecoveryQuestion());
        model.setViewName("home/home");
        return model;
    }
*/
    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }
/*
    @GetMapping(path = "/user/get/{emailAddress}")
    public String getUserPost (@PathVariable String emailAddress){
        User getUser = null;
        String created;
        getUser =userService.getUser(emailAddress);
        created =getUser.toString();
        return created;
    }
    */
/*
    @RequestMapping(value = "/login", method = RequestMethod.POST) // Map ONLY Post Requests
    public String addUser (@RequestBody User userName) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        String created, created2;

       // created = userService.newUser(name, email);
        userService.saveUser(userName);
        return created2 = userName.getEmailAddress();
    }
*/
/*
    @RequestMapping(path = "/user/add")
    public @ResponseBody String addNewUser (@RequestParam String email
            , @RequestParam String password, @RequestParam String recoveryQuestion, @RequestParam String recoveryAnswer){
        String created;
        created = userService.newUser(email, password, recoveryQuestion, recoveryAnswer);
        return created;
    }
*/
/*
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
    */

}
