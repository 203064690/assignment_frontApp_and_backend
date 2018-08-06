package hotelReservation.controller;

import hotelReservation.domain.Employee;
import hotelReservation.services.EmployeeService;
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
@RequestMapping(path="/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/all")
    public @ResponseBody
    Iterable<Employee> getAllcustomerBooking()
    {
        return employeeService.getAllEmployees();
    }

    @PostMapping(path = "/add")
    public @ResponseBody String createEmployee (@RequestBody Employee employee){
        Boolean created;
        created = employeeService.createEmployee(employee);
        if(created==true)
            return "Employee created";
        else
            return "Employee not created";
    }
    @GetMapping(path = "/add")
    public @ResponseBody String createEmployee_get (@RequestParam String name, @RequestParam String lastName, @RequestParam String ID_number){
        Boolean created;
        created = employeeService.createEmployeeGet(name, lastName, ID_number);
        if(created==true)
            return "Employee created";
        else
            return "Employee not created";
    }

    @RequestMapping("/update")
    public @ResponseBody String updateCustomer ( @RequestBody Employee employee){
        Boolean created, created2;
        created = employeeService.updateEmployee(employee);
        if(created==true)
            return "Customer Booking updated";
        else
            return "Customer Booking not updated";
    }

}
