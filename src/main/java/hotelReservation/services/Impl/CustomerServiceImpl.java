package hotelReservation.services.Impl;

import hotelReservation.domain.Customer;
import hotelReservation.factories.CustomerFactory;
import hotelReservation.repositories.CustomerRepo;
import hotelReservation.services.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo repositoryCustomer;

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();

        Iterable<Customer> customers = repositoryCustomer.findAll();
        for(Customer customer: customers){
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public Customer getCustomer(String cust_ID) {
        String strUserLogIn = "";
        String strCustomer = "";
        int countCustomer = 0;
        Customer gotCustomer = null;

        Iterable<Customer> customers = repositoryCustomer.findAll();
        for (Customer customer : customers) {
            if (customer.getIDNumber().equals(cust_ID))
            {
                countCustomer = countCustomer + 1;
                gotCustomer= customer;
            }

        }
        if (countCustomer != 0)
        {
            strCustomer = "Customer: Found";
        }
        else
        {
            strCustomer = "Customer: Not Found";
        }


        strUserLogIn = strCustomer + "\n";

        return gotCustomer;
    }

    @Override
    public String createCustomer(String idNumber, String firstNames, String lastName) {
        int count = 0;
        boolean blnCreateCustomer = false;


        Iterable<Customer> customers = repositoryCustomer.findAll();
        for (Customer customer : customers) {
            if (customer.getIDNumber().equals(idNumber))
            {
                count = count + 1;

            }

        }

        if (count == 0)
        {

            Customer newCustomer = CustomerFactory.createCustomer(idNumber, firstNames, lastName);
            repositoryCustomer.save(newCustomer);

            blnCreateCustomer = true;
        }
        else
        {
            blnCreateCustomer =false;
        }

        if(blnCreateCustomer==true)
            return "Customer created";
        else
            return "Customer not created";
    }

    @Override
    public String updateCustomer(Customer customerNewDetails) {

        boolean blnUpdateCustomer= false;
        Long customerID = 0L;
        Customer custOb = null;

        Iterable<Customer> customers = repositoryCustomer.findAll();
        for (Customer customer : customers) {
            if (customer.getIDNumber().equals(customerNewDetails.getIDNumber()))
            {
                customerID = customer.getID();
                custOb = customer;
                blnUpdateCustomer = true;
            }
        }

        if (blnUpdateCustomer == true)
        {


            Customer newCustomer = new Customer.Builder(custOb.getIDNumber())
                    .ID(customerID)
                    .customer_firstnames(customerNewDetails.getFirstnames())
                    .customer_lastname(customerNewDetails.getLastname())
                    .build();
            repositoryCustomer.save(newCustomer);
            return "Customer Updated";
        }else
            return "Customer not updated";

    }

    @Override
    public String PostNewCustomer(Customer newCustomer) {

        int count = 0;
        boolean blnCreateUser;
        Iterable<Customer> customers = repositoryCustomer.findAll();
        for (Customer customer : customers) {
            if (customer.getID().equals(newCustomer.getIDNumber()))
            {
                count = count + 1;
            }
        }

        if (count == 0)
        {
            Customer customer = CustomerFactory.createCustomer(newCustomer.getIDNumber(), newCustomer.getFirstnames(),newCustomer.getLastname());
            repositoryCustomer.save(customer);
            blnCreateUser = true;
        }
        else
        {
            blnCreateUser = false;
        }

        if(blnCreateUser==true)
            return "Customer saved";
        else
            return "Customer not saved";
    }

    @Override
    public String deleteCustomer(String idNumber) {
        Long customerID = 0L;
        boolean blnDeleteCustomer = false;

        Iterable<Customer> customers = repositoryCustomer.findAll();
        for (Customer customer : customers) {
            if(customer.getIDNumber().equals(idNumber))
            {
                customerID = customer.getID();
                blnDeleteCustomer = true;
            }
        }
        if(blnDeleteCustomer == true)
        {
            repositoryCustomer.deleteById(customerID);
        }

        if(blnDeleteCustomer==true)
            return "Customer deleted";
        else
            return "Customer cannot be deleted";
    }
}
