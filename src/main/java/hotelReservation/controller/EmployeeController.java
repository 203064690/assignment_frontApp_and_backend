package hotelReservation.controller;

import hotelReservation.domain.Employee;
import hotelReservation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 8203064690
 */
@RestController   // This means that this class is a Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value={"/view_employees"}, method = RequestMethod.GET)
    public ModelAndView getAllEmployees(){
        List<Employee> allEmployees = new ArrayList<>();
        Iterable<Employee> allEmployees2 = employeeService.getAllEmployees();
        for(Employee employee : allEmployees2){
            allEmployees.add(employee);
        }


        ModelAndView model = new ModelAndView();
        model.setViewName("reports/employ_report");
        model.addObject("reports", allEmployees);
        model.addObject("msg", "Employee");
        return model;
    }

    //To display login screen
    @RequestMapping(value= {"/add_employee"}, method =RequestMethod.GET)
    public ModelAndView customer(){

        ModelAndView model = new ModelAndView();
        model.setViewName("employee/add_employee");
        return model;
    }

    //To register user and return to Login screen
    @RequestMapping(value= {"/add_employee"}, method=RequestMethod.POST)
    public ModelAndView createEmployee(@ModelAttribute("Employee") Employee employee, BindingResult bindingResult) {

        Employee employeeExists = employeeService.getEmployee(employee.getIDNumber());
        boolean created;
        if(employeeExists != null) {
            bindingResult.rejectValue("ID_number","Employee already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("employee/add_employee");
            model.addObject("ID_number", "Employee already exists!");
            return model;
        } else {
            created = employeeService.createEmployee(employee);
            Employee employeeDetails = employeeService.getEmployee(employee.getIDNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Employee has been registered successfully!");
            model.addObject("employee", employeeDetails);
            return model;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value ="/employee/post")
    public String createEmployeePost(@RequestBody Employee employee){
        Boolean created;
        created = employeeService.createEmployee(employee);
        if(created==true)
            return "Employee created";
        else
            return "Employee not created";
    }

    //To view delete screen
    @RequestMapping(value= {"/delete_employee"}, method = RequestMethod.GET)
    public ModelAndView viewDelete(){

        ModelAndView model = new ModelAndView();
        model.setViewName("employee/delete_employee");
        return model;
    }

    //To delete room
    @RequestMapping(value= {"/delete_employee"}, method=RequestMethod.POST)
    public ModelAndView deleteEmployee(@ModelAttribute("Employee") Employee employee, BindingResult bindingResult) {

        Employee studentExists = employeeService.getEmployee(employee.getIDNumber());
        if(studentExists == null) {
            bindingResult.rejectValue("msg", "Employee does not exist");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("employee/delete_employee");
            model.addObject("msg", "Employee does not exist");
            return model;
        }

        else {

            employeeService.deleteEmployee(employee.getIDNumber());
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Employee was deleted!");
            return model;
        }

    }
    //To view edit room
    @RequestMapping(value= {"/edit_employee"}, method = RequestMethod.GET)
    public ModelAndView viewEditRoom(){

        ModelAndView model = new ModelAndView();
        model.setViewName("employee/edit_employee");
        return model;
    }

    //To update Room
    @RequestMapping(value= {"/edit_employee"}, method=RequestMethod.POST)
    public ModelAndView updateRoom(@ModelAttribute("Employee") Employee employee, BindingResult bindingResult) {
        boolean result;
        Employee roomExists = employeeService.getEmployee(employee.getIDNumber());
        if(roomExists == null) {
            bindingResult.rejectValue("msg", "Employee does not exist");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("employee/edit_employee");
            model.addObject("msg", "Employee does not exist");
            return model;
        }

        else {
            result = employeeService.updateEmployee(employee);
            ModelAndView model = new ModelAndView("home/home");
            model.addObject("msg", "Employee was udated!");
            System.out.println(result);
            return model;
        }

    }

}
