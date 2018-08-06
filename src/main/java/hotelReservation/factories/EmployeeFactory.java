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
    public static Employee createEmployee(String employee_ID,
                                          String firstnames,
                                          String lastname,
                                          Date hire_date)
    {
        Employee employee = new Employee
                .Builder(employee_ID)
                .employee_firstnames(firstnames)
                .employee_lastname(lastname)
                .hire_date(hire_date)
                .build();
        return employee;
    }
}
