package hotelReservation.domain;


import javax.persistence.*;
import java.io.Serializable;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Entity
public class Customer implements Serializable
{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String cust_ID;
    private String customer_firstnames;
    private String customer_lastname;


    public Long getID()
    {
        return ID;
    }
    public String getIDNumber()
    {
        return cust_ID;
    }
    public String getFirstnames()
    {
        return customer_firstnames;
    }
    public String getLastname()
    {
        return customer_lastname;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setCust_ID(String cust_ID) {
        this.cust_ID = cust_ID;
    }

    public void setCustomer_firstnames(String customer_firstnames) {
        this.customer_firstnames = customer_firstnames;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    public Customer(){}

    public Customer( Builder builder )
    {
        ID = builder.ID;
        cust_ID = builder.cust_ID;
        customer_firstnames = builder.customer_firstnames;
        customer_lastname = builder.customer_lastname;
    }


    public static class Builder
    {
        private Long ID;
        private String cust_ID;
        private String customer_firstnames;
        private String customer_lastname;

        public Builder( String cust_ID )
        {
            this.cust_ID = cust_ID;
        }
        public Builder ID( Long value )
        {
            this.ID = value;
            return this;
        }
        public Builder customer_firstnames( String value )
        {
            this.customer_firstnames = value;
            return this;
        }
        public Builder customer_lastname( String value )
        {
            this.customer_lastname = value;
            return this;
        }


        public Customer build()
        {
            return new Customer( this );
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "ID=" + ID +
                ", Customer_ID='" + cust_ID + '\'' +
                ", Name='" + customer_firstnames + '\'' +
                ", LastName='" + customer_lastname + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        return !(ID != null ? !ID.equals(customer.ID) : customer.ID != null);
    }
    @Override
    public int hashCode()
    {
        return ID != null ? ID.hashCode() : 0;
    }
}
