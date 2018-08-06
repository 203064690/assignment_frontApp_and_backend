package hotelReservation.factories;

import hotelReservation.domain.Customer;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class CustomerFactory
{

    public static Customer createCustomer(String IDnumber,
                                          String firstnames,
                                          String lastname
                                          )
    {
        Customer customer = new Customer
                .Builder(IDnumber)
                .customer_firstnames( firstnames )
                .customer_lastname( lastname )
                .build();
        return customer;
    }
}
