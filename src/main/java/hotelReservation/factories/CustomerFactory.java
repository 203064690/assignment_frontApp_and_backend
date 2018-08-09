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

    public static Customer createCustomer(String cust_ID,
                                          String customer_firstnames,
                                          String customer_lastname
                                          )
    {
        Customer customer = new Customer
                .Builder(cust_ID)
                .customer_firstnames( customer_firstnames )
                .customer_lastname( customer_lastname )
                .build();
        return customer;
    }
}
