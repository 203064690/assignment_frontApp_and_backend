package hotelReservation.services;

import hotelReservation.domain.Customer;

import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface CustomerService {
    public List<Customer> getAllCustomers();
    public Customer getCustomer(String id);
    public String createCustomer(String idNumber, String firstNames, String lastName);
    public String updateCustomer(Customer customer);
    public String PostNewCustomer(Customer newCustomer);
    public String deleteCustomer(String idNumber);
}
