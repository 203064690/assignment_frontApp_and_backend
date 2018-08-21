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
    public boolean createEmployee(Employee employeeNew);
    public boolean updateEmployee(Employee employee);
    public Employee getEmployee(String ID_number);
    public boolean deleteEmployee(String employeeNumber);
}
