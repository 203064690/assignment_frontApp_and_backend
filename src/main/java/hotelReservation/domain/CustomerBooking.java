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
public class CustomerBooking implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String idNumber;
    private String firstNames;
    private String lastName;
    private String referenceNumber;

    private CustomerBooking(){}

    public CustomerBooking(Builder builder)
    {
        id = builder.id;
        idNumber = builder.idNumber;
        firstNames = builder.firstNames;
        lastName = builder.lastName;
        referenceNumber = builder.referenceNumber;
    }

    public Long getId()
    {
        return this.id;
    }
    public String getIdNumber()
    {
        return this.idNumber;
    }
    public String getFirstNames()
    {
        return this.firstNames;
    }
    public String getLastName()
    {
        return this.lastName;
    }
    public String getReferenceNumber()
    {
        return this.referenceNumber;
    }

    public static class Builder
    {
        private Long id;
        private String idNumber;
        private String firstNames;
        private String lastName;
        private String referenceNumber;

        public Builder(String referenceNumber)
        {
            this.referenceNumber = referenceNumber;
        }
        public Builder id(Long value) {
            this.id=value;
            return this;
        }
        public Builder idNumber(String value) {
            this.idNumber = value;
            return this;
        }
        public Builder firstNames(String value) {
            this.firstNames = value;
            return this;
        }
        public Builder lastName(String value) {
            this.lastName = value;
            return this;
        }


        public CustomerBooking build(){
            return new CustomerBooking(this);
        }
    }

}
