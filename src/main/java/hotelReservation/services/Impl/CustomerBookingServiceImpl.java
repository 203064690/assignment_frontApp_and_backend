package hotelReservation.services.Impl;

import hotelReservation.domain.CustomerBooking;
import hotelReservation.repositories.CustomerBookingRepo;
import hotelReservation.services.CustomerBookingService;
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
public class CustomerBookingServiceImpl implements CustomerBookingService {
    @Autowired
    CustomerBookingRepo repositoryCustomerBooking;

    @Override
    public List<CustomerBooking> getAllCustomerBookings() {
        List<CustomerBooking> allCustomerBooking = new ArrayList<CustomerBooking>();

        Iterable<CustomerBooking> customerBookings = repositoryCustomerBooking.findAll();
        for (CustomerBooking customerBooking : customerBookings) {
            allCustomerBooking.add(customerBooking);
        }
        return allCustomerBooking;
    }

    @Override
    public boolean createCustomerBooking(String referenceNumber, String idNumber, String firstNames, String lastName) {
        int count = 0;
        boolean blnCreateCustomerBooking;

        Iterable<CustomerBooking> customerBookings = repositoryCustomerBooking.findAll();
        for (CustomerBooking customerBooking : customerBookings) {
            if (customerBooking.getReferenceNumber().equalsIgnoreCase(referenceNumber))
            {
                count = count + 1;
            }
        }
        if(count == 0)
        {
            CustomerBooking customerBooking = new CustomerBooking.Builder(referenceNumber)
                    .idNumber(idNumber)
                    .firstNames(firstNames)
                    .lastName(lastName)
                    .build();
            repositoryCustomerBooking.save(customerBooking);

            blnCreateCustomerBooking = true;
        }
        else
        {
            blnCreateCustomerBooking = false;
        }
        return blnCreateCustomerBooking;
    }

    @Override
    public boolean updateCustomerBooking(String referenceNumber) {
        boolean blnUpdateCustomerBooking = false;
        Long customerBookingID = 0L;
        CustomerBooking customerBookingUpdate = null;

        Iterable<CustomerBooking> customerBookings = repositoryCustomerBooking.findAll();
        for (CustomerBooking customerBooking : customerBookings) {
            if (customerBooking.getReferenceNumber().equalsIgnoreCase(referenceNumber))
            {
                customerBookingID = customerBooking.getId();
                customerBookingUpdate = customerBooking;
                blnUpdateCustomerBooking = true;
            }
        }
        if(blnUpdateCustomerBooking == true)
        {

            CustomerBooking newCustomerBooking = new CustomerBooking.Builder(customerBookingUpdate.getReferenceNumber())
                    .id(customerBookingID)
                    .build();
            repositoryCustomerBooking.save(newCustomerBooking);
        }
        return blnUpdateCustomerBooking;
    }

    @Override
    public boolean deleteCustomerBooking(String referenceNumber) {

        Long customerBookingID = 0L;
        boolean blnDeleteCustomerBooking = false;

        Iterable<CustomerBooking> customerBookings = repositoryCustomerBooking.findAll();
        for (CustomerBooking customerBooking : customerBookings) {
            if (customerBooking.getReferenceNumber().equalsIgnoreCase(referenceNumber))
            {
                customerBookingID = customerBooking.getId();
                blnDeleteCustomerBooking = true;
            }
        }
        if(blnDeleteCustomerBooking == true)
        {
            repositoryCustomerBooking.deleteById(customerBookingID);
        }
        return blnDeleteCustomerBooking;
    }
}
