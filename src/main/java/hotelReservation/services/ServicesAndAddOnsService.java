package hotelReservation.services;

import hotelReservation.domain.ServicesAndAddOns;

import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface ServicesAndAddOnsService {
    public List<ServicesAndAddOns> getAllServicesAndAddOns();
    public ServicesAndAddOns getServicesAndAddOns(int service_id);
    public boolean createServicesAndAddOns(ServicesAndAddOns servicesAndAddOns);
    public boolean updateServicesAndAddOns(ServicesAndAddOns servicesAndAddOns);
    public boolean deleteServicesAndAddOns(int serv_extras_id);
}
