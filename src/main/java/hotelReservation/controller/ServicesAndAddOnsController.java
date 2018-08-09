package hotelReservation.controller;

import hotelReservation.domain.ServicesAndAddOns;
import hotelReservation.services.ServicesAndAddOnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@Controller    // This means that this class is a Controller
public class ServicesAndAddOnsController {

    @Autowired
    private ServicesAndAddOnsService servicesAndAddOnsService;

    @RequestMapping("/services/all")
    public @ResponseBody
    Iterable<ServicesAndAddOns> getAllservices()
    {
        return servicesAndAddOnsService.getAllServicesAndAddOns();
    }

    @PostMapping(path = "/services/services/add")
    public @ResponseBody String createServicesAndAddOns (@RequestBody ServicesAndAddOns servicesAndAddOns){
        boolean created;
        created = servicesAndAddOnsService.createServicesAndAddOns(servicesAndAddOns);
        if(created==true)
            return "ServicesAndAddOns added";
        else
            return "ServicesAndAddOns not not added";
    }

    @PostMapping("/services/update")
    public @ResponseBody String updateServicesAndAddOns ( @RequestBody ServicesAndAddOns servicesAndAddOns){
        boolean created, created2;
        created = servicesAndAddOnsService.updateServicesAndAddOns(servicesAndAddOns);

        if(created==true)
            return "ServicesAndAddOns updated";
        else
            return "ServicesAndAddOns not updated";
    }

    @RequestMapping(path = "/services/delete/{id}")
    public String deleteServices (@PathVariable int serv_extras_id){
        boolean created;
        created = servicesAndAddOnsService.deleteServicesAndAddOns(serv_extras_id);
        if(created==true)
            return "ServicesAndAddOns Deleted";
        else
            return "ServicesAndAddOns not deleted";
    }
}
