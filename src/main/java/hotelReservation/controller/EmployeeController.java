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
            bindingResult.rejectValue("msg","Employee already exists!");
        }
        if(bindingResult.hasErrors()) {
            ModelAndView model = new ModelAndView("employee/add_employee");
            model.addObject("msg", "Employee already exists!");
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

/*
    @PostMapping(value ="/employee/update")
    public String updateEmployee ( @RequestBody Employee employee){
        Boolean created, created2;
        created = employeeService.updateEmployee(employee);
        if(created==true)
            return "Employee updated";
        else
            return "Employeenot updated";
    }

*/

}
