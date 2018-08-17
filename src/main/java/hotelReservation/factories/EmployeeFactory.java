package hotelReservation.factories;

import hotelReservation.domain.Employee;

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
                                          String hire_date){
        Employee employee = new Employee
                .Builder(ID_number)
                .employee_firstnames(employee_firstnames)
                .employee_lastname(employee_lastname)
                .hire_date(hire_date)
                .build();
        return employee;
    }
}
