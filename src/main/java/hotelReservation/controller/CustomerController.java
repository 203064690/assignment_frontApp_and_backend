package hotelReservation.controller;

import hotelReservation.domain.Customer;
import hotelReservation.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by dylanb on 2018/08/06.
 */
@RestController    // This means that this class is a Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/customer/all")
    public Iterable<Customer> getAllcustomer()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping(path = "/customer/get/{cust_ID}")
    public String getCustomer (@PathVariable String cust_ID){
        Customer getCustomer = null;
        String created;
        getCustomer =customerService.getCustomer(cust_ID);
        created =getCustomer.toString();
        return created;
    }

    @RequestMapping(value ="/customer/add_post", method = RequestMethod.POST) // Map ONLY Post Requests
    public String addCustomer (@RequestBody Customer customer) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        String created;

        // created = userService.newUser(name, email);
        return created = customerService.PostNewCustomer(customer);

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
