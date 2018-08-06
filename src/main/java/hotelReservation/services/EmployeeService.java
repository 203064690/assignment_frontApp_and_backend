package hotelReservation.services;

import hotelReservation.domain.Employee;

import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public boolean createEmployee(Employee employee);
    public boolean updateEmployee(Employee employee);
    public boolean createEmployeeGet(String name, String lastName, String ID_number);
}
