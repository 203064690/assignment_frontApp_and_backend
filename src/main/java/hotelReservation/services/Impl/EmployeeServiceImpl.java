package hotelReservation.services.Impl;

import hotelReservation.domain.Employee;
import hotelReservation.factories.EmployeeFactory;
import hotelReservation.repositories.EmployeeRepo;
import hotelReservation.services.EmployeeService;
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
        for (Employee employee : employees) {
            if (employee.getIDNumber().equalsIgnoreCase(employeeNew.getIDNumber()))
            {
                count = count + 1;
            }
        }
        System.out.println(" " + count);
        System.out.println(" " + employeeNew.getIDNumber() + " " + employeeNew.getFirstnames() + " " + employeeNew.getLastname());

        if (count == 0)
        {
            Employee employee = EmployeeFactory.createEmployee(employeeNew.getIDNumber(), employeeNew.getFirstnames(), employeeNew.getLastname(), employeeNew.getHireDate());
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
    public Employee getEmployee(String ID_number) {
        boolean blnExistEmployee = false;
        Employee getEmployee = null;

        Iterable<Employee> employees = repositoryEmployee.findAll();
        for (Employee employee : employees) {
            if (employee.getIDNumber().equalsIgnoreCase(ID_number))
            {
                getEmployee = employee;
                blnExistEmployee = true;
            }
            else {
                blnExistEmployee = false;
                getEmployee =null;
            }
        }


        return getEmployee;
    }

    @Override
    public boolean updateEmployee(Employee employeeNew) {
        boolean blnEmployeeUpdate = false;
        Long ID = 0L;
        Employee employeeUpdate = null;
        Iterable<Employee> employees = repositoryEmployee.findAll();
        for (Employee employee : employees) {
            if (employee.getIDNumber().equals(employeeNew.getIDNumber()))
            {
                blnEmployeeUpdate = true;
                ID = employee.getID();
                employeeUpdate = employee;
            }
        }

        if (blnEmployeeUpdate == true)
        {
            Employee newRoom = new Employee.Builder(employeeUpdate.getIDNumber())
                    .ID(ID)
                    .employee_firstnames(employeeNew.getFirstnames())
                    .employee_lastname(employeeNew.getLastname())
                    .hire_date(employeeNew.getHireDate())
                    .build();
            repositoryEmployee.save(newRoom);
        }

        return blnEmployeeUpdate;
    }

    @Override
    public boolean deleteEmployee(String employeeNumber) {
        boolean blnRoomDelete = false;
        Long ID = 0L;
        Iterable<Employee> employees = repositoryEmployee.findAll();
        for (Employee employee : employees) {
            if (employee.getIDNumber().equalsIgnoreCase(employeeNumber))
            {
                blnRoomDelete = true;
                ID = employee.getID();
            }
        }

        if (blnRoomDelete == true)
        {
            repositoryEmployee.deleteById(ID);
        }

        return blnRoomDelete;
    }

}