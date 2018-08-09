package hotelReservation.controller;

import hotelReservation.domain.Employee;
import hotelReservation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping("/employee/all")
    public @ResponseBody Iterable<Employee> getAllEmployee()
    {
        return employeeService.getAllEmployees();
    }

    @GetMapping(path = "/employee/get/{ID_number}")
    public String getEmployee (@PathVariable String ID_number){
        Employee getEmp = null;
        String created;
        getEmp =employeeService.getEmployee(ID_number);
        created =getEmp.toString();
        return created;
    }
/*
    @GetMapping(path = "/employee/create")
    public @ResponseBody String createEmployee_get (@RequestParam String name, @RequestParam String lastName, @RequestParam String ID_number){
        Boolean created;
        created = employeeService.createEmployeeGet(name, lastName, ID_number);
        if(created==true)
            return "Employee created";
        else
            return "Employee not created";
    }
*/
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
