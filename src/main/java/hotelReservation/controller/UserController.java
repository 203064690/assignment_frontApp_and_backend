package hotelReservation.controller;

import hotelReservation.domain.User;
import hotelReservation.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Hello world!
 *
 */

@Controller
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
        User user = new User();
        model.addObject("user", user);
        model.setViewName("user/register");

        return model;
    }

    @RequestMapping(value= {"/login"}, method=RequestMethod.POST)
    public ModelAndView register(User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.getUser(user.getEmailAddress());
        System.out.println(userExists.toString());
        if(userExists != null) {
            bindingResult.getModel();
            model.addObject("user", userExists);
            model.setViewName("home/home");
            return model;
        }
        else{
            bindingResult.reject("user", "User does not exist. Please register");
            model.setViewName("user/login");
            return model;
        }

    }
    //To register user and return to Login screen
    @RequestMapping(value= {"/register"}, method=RequestMethod.POST)
    public ModelAndView createUser(User user, BindingResult bindingResult) {
        ModelAndView model = new ModelAndView();
        User userExists = userService.getUser(user.getEmailAddress());
        String created = null;
        if(userExists != null) {
            bindingResult.rejectValue("emailAddress","This email already exists!");
        }
        if(bindingResult.hasErrors()) {
            model.setViewName("user/register");
        } else {
            created = userService.saveUser(user);
            model.addObject("msg", "User has been registered successfully!");
            model.addObject("user", user);
            model.setViewName("user/login");
        }
        System.out.println(user.toString());
        System.out.println(created);
        return model;
    }

    @RequestMapping(value= {"/home/home"}, method=RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView model = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUser(auth.getName());

        model.addObject("userName", user.getEmailAddress() + " " + user.getRecoveryQuestion());
        model.setViewName("home/home");
        return model;
    }

    @RequestMapping(value= {"/access_denied"}, method=RequestMethod.GET)
    public ModelAndView accessDenied() {
        ModelAndView model = new ModelAndView();
        model.setViewName("errors/access_denied");
        return model;
    }

    @GetMapping(path = "/user/get/{emailAddress}")
    public String getUserPost (@PathVariable String emailAddress){
        User getUser = null;
        String created;
        getUser =userService.getUser(emailAddress);
        created =getUser.toString();
        return created;
    }
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
