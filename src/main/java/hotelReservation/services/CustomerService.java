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
    public String updateCustomer(Customer customer);
    public String saveCustomer(Customer newCustomer);
    public String deleteCustomer(String idNumber);
}
