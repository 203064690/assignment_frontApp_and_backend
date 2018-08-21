package hotelReservation.controller;

import hotelReservation.domain.ServicesAndAddOns;
import hotelReservation.services.ServicesAndAddOnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@RestController    // This means that this class is a Controller
public class ServicesAndAddOnsController {

    @Autowired
    private ServicesAndAddOnsService servicesAndAddOnsService;

    @RequestMapping("/services/all")
    public @ResponseBody
    Iterable<ServicesAndAddOns> getAllservices()
    {
        return servicesAndAddOnsService.getAllServicesAndAddOns();
    }

    //To display login screen
    @RequestMapping(value= {"/add_ons"}, method =RequestMethod.GET)
    public ModelAndView add_ons(){

        ModelAndView model = new ModelAndView();
        model.setViewName("addOn/add_ons");
        return model;
    }

    //To register user and return to Login screen
    @RequestMapping(value= {"/add_ons"}, method=RequestMethod.POST)
    public ModelAndView createService(@ModelAttribute("ServicesAndAddOns") ServicesAndAddOns servicesAndAddOns, BindingResult bindingResult) {
        ServicesAndAddOns serviceExists = servicesAndAddOnsService.getServicesAndAddOns(servicesAndAddOns.getServExtraID());
        boolean created;
        if(serviceExists != null) {
            bindingResult.rejectValue("serv_extras_id","Service already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("addOn/add_ons");
            model.addObject("serv_extras_id", "Service already exists!");
            return model;
        } else {
            created = servicesAndAddOnsService.createServicesAndAddOns(servicesAndAddOns);
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Service has been added!");
            return model;
        }
    }

}
