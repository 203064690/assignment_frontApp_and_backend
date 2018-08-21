package hotelReservation.controller;

import hotelReservation.domain.Customer;
import hotelReservation.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dylanb on 2018/08/06.
 */
@RestController    // This means that this class is a Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value={"/view_customer"}, method = RequestMethod.GET)
    public ModelAndView getAllCustomer(){
        List<Customer> allCustomers = new ArrayList<>();
        Iterable<Customer> allCustomers2 = customerService.getAllCustomers();
        for(Customer customer : allCustomers2){
            allCustomers.add(customer);
        }


        ModelAndView model = new ModelAndView();
        model.setViewName("reports/cust_report");
        model.addObject("reports", allCustomers);
        model.addObject("msg", "Customer");
        return model;
    }

    //To display login screen
    @RequestMapping(value= {"/add_customer"}, method =RequestMethod.GET)
    public ModelAndView customer(){

        ModelAndView model = new ModelAndView();
        model.setViewName("customer/add_customer");
        return model;
    }

    //To register user and return to Login screen
    @RequestMapping(value= {"/add_customer"}, method=RequestMethod.POST)
    public ModelAndView createCustomer(@ModelAttribute("customer") Customer customer, BindingResult bindingResult) {
        Customer customerExists = customerService.getCustomer(customer.getIDNumber());
        System.out.println(customer.toString());
        String created = null;
        if(customerExists != null) {
            bindingResult.rejectValue("cust_ID","Customer already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("customer/add_customer");
            model.addObject("cust_ID", "Customer already exists!");
            return model;
        } else {
            created = customerService.saveCustomer(customer);
            Customer customerDetails = customerService.getCustomer(customer.getIDNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Customer has been registered successfully!");
            model.addObject("customer", customerDetails);
            System.out.println(customer.toString());
            System.out.println(created);
            return model;
        }
    }

    //To view delete screen
    @RequestMapping(value= {"/delete_customer"}, method = RequestMethod.GET)
    public ModelAndView viewDelete(){

        ModelAndView model = new ModelAndView();
        model.setViewName("customer/delete_customer");
        return model;
    }

    //To delete room
    @RequestMapping(value= {"/delete_customer"}, method=RequestMethod.POST)
    public ModelAndView deleteCustomer(@ModelAttribute("Customer") Customer customer, BindingResult bindingResult) {
        String result;
        Customer studentExists = customerService.getCustomer(customer.getIDNumber());
        if(studentExists == null) {
            bindingResult.rejectValue("cust_ID", "Customer does not exist");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("customer/delete_customer");
            model.addObject("cust_ID", "Customer does not exist");
            return model;
        }

        else {

            result =customerService.deleteCustomer(customer.getIDNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Customer was deleted!");
            return model;
        }

    }
    //To view edit room
    @RequestMapping(value= {"/edit_customer"}, method = RequestMethod.GET)
    public ModelAndView viewEditRoom(){

        ModelAndView model = new ModelAndView();
        model.setViewName("customer/edit_customer");
        return model;
    }

    //To update Room
    @RequestMapping(value= {"/edit_customer"}, method=RequestMethod.POST)
    public ModelAndView updateRoom(@ModelAttribute("Customer") Customer customer, BindingResult bindingResult) {
        String result;
        Customer roomExists = customerService.getCustomer(customer.getIDNumber());
        if(roomExists == null) {
            bindingResult.rejectValue("msg", "Customer does not exist");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("customer/edit_customer");
            model.addObject("msg", "Customer does not exist");
            return model;
        }

        else {
            result = customerService.updateCustomer(customer);
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Customer was updated!");
            System.out.println(result);
            return model;
        }

    }




}
