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
            bindingResult.rejectValue("msg","Customer already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("customer/add_customer");
            model.addObject("msg", "Customer already exists!");
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

    @GetMapping(path = "/customer/get/{cust_ID}")
    public String getCustomer (@PathVariable String cust_ID){
        Customer getCustomer = null;
        String created;
        getCustomer =customerService.getCustomer(cust_ID);
        created =getCustomer.toString();
        return created;
    }

/*
    @RequestMapping(path = "/customer/add")
    public @ResponseBody String createCustomer (@RequestParam String id
            , @RequestParam String firstName, @RequestParam String lastName){
        String created;
        created = customerService.createCustomer(id, firstName, lastName);
        return created;
    }
*/
    @RequestMapping(path = "/customer/delete/{id}")
    public String deleteCustomer (@PathVariable String id){
        String created;
        created = customerService.deleteCustomer(id);
        return created;
    }

    @RequestMapping("/customer/update")
    public @ResponseBody String updateCustomer ( @RequestBody Customer customer){
        String created, created2;
        created = customerService.updateCustomer(customer);
        created2 = customer.getIDNumber() + " "+ customer.getFirstnames()+ " "+ customer.getLastname();
        return created2;
    }




}
