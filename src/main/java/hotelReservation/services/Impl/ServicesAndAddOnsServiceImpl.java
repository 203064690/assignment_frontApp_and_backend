package hotelReservation.services.Impl;

import hotelReservation.domain.ServicesAndAddOns;
import hotelReservation.factories.ServicesAndAddOnsFactory;
import hotelReservation.repositories.ServicesAndAddOnsRepo;
import hotelReservation.services.ServicesAndAddOnsService;
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
public class ServicesAndAddOnsServiceImpl implements ServicesAndAddOnsService {
    @Autowired
    ServicesAndAddOnsRepo repositoryServicesAndAddOns;

    @Override
    public List<ServicesAndAddOns> getAllServicesAndAddOns() {
        List<ServicesAndAddOns> allServicesAndAddOns = new ArrayList<ServicesAndAddOns>();

        Iterable<ServicesAndAddOns> servicesAndAddOnses = repositoryServicesAndAddOns.findAll();
        for (ServicesAndAddOns servicesAndAddOn : servicesAndAddOnses) {
            allServicesAndAddOns.add(servicesAndAddOn);
        }
        return allServicesAndAddOns;
    }

    @Override
    public boolean createServicesAndAddOns(ServicesAndAddOns servicesAndAddOnsNew) {
        int count = 0;
        boolean blnCreateServicesAndAddOn ;
        Iterable<ServicesAndAddOns> servicesAndAddOnses = repositoryServicesAndAddOns.findAll();
        for (ServicesAndAddOns servicesAndAddOn : servicesAndAddOnses) {
            if (servicesAndAddOn.getServExtraID() == servicesAndAddOnsNew.getServExtraID())
            {
                count = count + 1;
            }
        }
        if (count == 0)
        {
            ServicesAndAddOns servicesAndAddOns = ServicesAndAddOnsFactory.createServicesAndAddOns(0, "Mini Bar", 200);
            repositoryServicesAndAddOns.save(servicesAndAddOns);
            blnCreateServicesAndAddOn = true;
        }
        else
        {
            //False is generated if the room exists already
            blnCreateServicesAndAddOn = false;
        }
        return blnCreateServicesAndAddOn;
    }

    @Override
    public boolean updateServicesAndAddOns(ServicesAndAddOns servicesAndAddOnsNew) {
        boolean blnServicesAndAddOnsUpdate = false;
        Long ID = 0L;
        ServicesAndAddOns servicesAndAddOnUpdate = null;

        Iterable<ServicesAndAddOns> servicesAndAddOnses = repositoryServicesAndAddOns.findAll();
        for (ServicesAndAddOns servicesAndAddOn : servicesAndAddOnses) {
            if (servicesAndAddOn.getServExtraID() == servicesAndAddOnsNew.getServExtraID())
            {
                blnServicesAndAddOnsUpdate = true;
                ID = servicesAndAddOn.getID();

            }
        }

        if (blnServicesAndAddOnsUpdate == true)
        {
            ServicesAndAddOns newServicesAndAddOns = new ServicesAndAddOns.Builder(servicesAndAddOnUpdate.getServExtraID())
                    .ID(ID)
                    .extra_name(servicesAndAddOnsNew.getExtraName())
                    .price_added(servicesAndAddOnsNew.getPriceAdded())
                    .build();
            repositoryServicesAndAddOns.save(newServicesAndAddOns);
        }

        return blnServicesAndAddOnsUpdate;
    }

    @Override
    public boolean deleteServicesAndAddOns(int serv_extras_id) {
        boolean blnServicesAndAddOnsDelete = false;
        Long ID = 0L;

        Iterable<ServicesAndAddOns> servicesAndAddOnses = repositoryServicesAndAddOns.findAll();
        for (ServicesAndAddOns servicesAndAddOn : servicesAndAddOnses) {
            if (servicesAndAddOn.getServExtraID() == serv_extras_id)
            {
                blnServicesAndAddOnsDelete = true;
                ID = servicesAndAddOn.getID();
            }
        }

        if (blnServicesAndAddOnsDelete == true)
        {
            repositoryServicesAndAddOns.deleteById(ID);
        }

        return false;
    }
}
