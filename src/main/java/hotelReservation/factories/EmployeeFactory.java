package hotelReservation.factories;

import hotelReservation.domain.Employee;

import java.util.Date;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class EmployeeFactory {
    public static Employee createEmployee(String ID_number,
                                          String employee_firstnames,
                                          String employee_lastname,
                                          Date hire_date)
    {
        Employee employee = new Employee
                .Builder(ID_number)
                .employee_firstnames(employee_firstnames)
                .employee_lastname(employee_lastname)
                .hire_date(hire_date)
                .build();
        return employee;
    }
}
