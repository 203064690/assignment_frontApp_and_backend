package hotelReservation.services.Impl;

import hotelReservation.domain.Employee;
import hotelReservation.repositories.EmployeeRepo;
import hotelReservation.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepo repositoryEmployee;

    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> allEmployees = new ArrayList<Employee>();

        Iterable<Employee> employees = repositoryEmployee.findAll();
        for (Employee employee : employees) {
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean createEmployee(Employee employeeNew) {
        int count = 0;
        boolean blnCreateEmployee;

        Iterable<Employee> employees = repositoryEmployee.findAll();

        System.out.println(" " + count);
        System.out.println(" " + employeeNew.getIDNumber() + " " + employeeNew.getFirstnames() + " " + employeeNew.getLastname());

        if (count == 0)
        {
            Date dateTemp = new Date();

            Employee employee = new Employee.Builder(employeeNew.getIDNumber())
                    .employee_firstnames(employeeNew.getFirstnames())
                    .employee_lastname(employeeNew.getLastname())
                    .hire_date(dateTemp)
                    .build();
            repositoryEmployee.save(employee);
            blnCreateEmployee = true;
        }
        else
        {
            blnCreateEmployee = false;
        }

        return blnCreateEmployee;
    }

    @Override
    public boolean createEmployeeGet(String name, String lastName, String ID_number) {
        int count = 0;
        boolean blnCreateEmployee;

        Iterable<Employee> employees = repositoryEmployee.findAll();



        if (count == 0)
        {
            Date dateTemp = new Date();

            Employee employee = new Employee.Builder(ID_number)
                    .employee_firstnames(name)
                    .employee_lastname(lastName)
                    .hire_date(dateTemp)
                    .build();
            repositoryEmployee.save(employee);
            blnCreateEmployee = true;
        }
        else
        {
            blnCreateEmployee = false;
        }

        return blnCreateEmployee;
    }

    @Override
    public boolean updateEmployee(Employee employeeNew) {
        boolean blnUpdateEmployee= false;
        Long employeeID = 0L;
        Employee employeeUpdate = null;

        Iterable<Employee> employees = repositoryEmployee.findAll();
        for (Employee employee : employees) {
            if (employee.getIDNumber().equalsIgnoreCase(employeeNew.getIDNumber()))
            {
                employeeID = employee.getID();
                employeeUpdate = employee;
                blnUpdateEmployee = true;
            }
        }

        if (blnUpdateEmployee == true)
        {
            Employee newEmployee = new Employee.Builder(employeeUpdate.getIDNumber())
                    .ID(employeeID)
                    .employee_firstnames("John")
                    .employee_lastname("Dave")
                    .build();
            repositoryEmployee.save(newEmployee);
        }
        return blnUpdateEmployee;
    }
}