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
    private String ID_number;
    private String customer_firstnames;
    private String customer_lastname;


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
        return customer_firstnames;
    }
    public String getLastname()
    {
        return customer_lastname;
    }

    private Customer(){}

    public Customer( Builder builder )
    {
        ID = builder.ID;
        ID_number = builder.ID_number;
        customer_firstnames = builder.customer_firstnames;
        customer_lastname = builder.customer_lastname;
    }


    public static class Builder
    {
        private Long ID;
        private String ID_number;
        private String customer_firstnames;
        private String customer_lastname;

        public Builder( String ID_number )
        {
            this.ID_number = ID_number;
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

}
