package hotelReservation.services;

import hotelReservation.domain.Customer;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface CustomerService {
    public Iterable<Customer> getAllCustomers();
    public String createCustomer(String idNumber, String firstNames, String lastName);
    public String updateCustomer(Customer customer);
    public String PostNewCustomer(Customer newCustomer);
    public String deleteCustomer(String idNumber);
}
