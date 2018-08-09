package hotelReservation.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Entity
public class Employee implements Serializable
{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String ID_number;
    private String employee_firstnames;
    private String employee_lastname;
    private Date hire_date;

    public Long getID()
    {
        return ID;
    }
    public String getIDNumber()
    {
        return ID_number;
    }
    public String getFirstnames()
    {
        return employee_firstnames;
    }
    public String getLastname()
    {
        return employee_lastname;
    }
    public Date getHireDate()
    {
        return hire_date;
    }

    private Employee(){}

    public Employee( Builder builder )
    {
        ID = builder.ID;
        ID_number = builder.ID_number;
        employee_firstnames = builder.employee_firstnames;
        employee_lastname = builder.employee_lastname;
        hire_date = builder.hire_date;

    }

    public static class Builder
    {
        private Long ID;
        private String ID_number;
        private String employee_firstnames;
        private String employee_lastname;
        private Date hire_date;


        public Builder( String ID_number )
        {
            this.ID_number = ID_number;
        }
        public Builder ID( Long value )
        {
            this.ID = value;
            return this;
        }
        public Builder employee_firstnames( String value )
        {
            this.employee_firstnames = value;
            return this;
        }
        public Builder employee_lastname( String value )
        {
            this.employee_lastname = value;
            return this;
        }

        public Builder hire_date( Date value )
        {
            this.hire_date = value;
            return this;
        }


        public Employee build()
        {
            return new Employee( this );
        }
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", ID_number='" + ID_number + '\'' +
                ", employee_firstnames='" + employee_firstnames + '\'' +
                ", employee_lastname='" + employee_lastname + '\'' +
                ", hire_date=" + hire_date +
                '}';
    }

}
