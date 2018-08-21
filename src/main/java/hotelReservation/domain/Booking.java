package hotelReservation.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
@Entity
@Table(name="booking")
public class Booking implements Serializable
{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
   // @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    private String reference_number;
    @OneToMany(targetEntity=Room.class, mappedBy="booking")
    private List<Room> rooms;
    @OneToMany(targetEntity=ServicesAndAddOns.class, mappedBy="booking")
    private List<ServicesAndAddOns> services_and_addons;
    private Date hireDate;

    public Booking(){}

    public Long getID()
    {
        return this.ID;
    }
    public String getReferenceNumber()
    {
        return reference_number;
    }
    public List<Room> getRooms()
    {
        return rooms;
    }
    public List<ServicesAndAddOns> getServicesAndAddOns()
    {
        return services_and_addons;
    }
    public Date getDate()
    {
        return hireDate;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setReference_number(String reference_number) {
        this.reference_number = reference_number;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public void setServices_and_addons(List<ServicesAndAddOns> services_and_addons) {
        this.services_and_addons = services_and_addons;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public Booking(Builder builder )
    {
        ID = builder.ID;
        reference_number = builder.reference_number;
        rooms = builder.rooms;
        services_and_addons = builder.services_and_addons;
        hireDate = builder.hireDate;
    }

    public static class Builder
    {
        private Long ID;
        private String reference_number;
        private List<Room> rooms;
        private List<ServicesAndAddOns> services_and_addons;
        private Date hireDate;

        public Builder( String reference_number )
        {
            this.reference_number = reference_number;
        }

        public Builder ID(Long value)
        {
            this.ID=value;
            return this;
        }

        public Builder rooms( List<Room> value )
        {
            this.rooms = value;
            return this;
        }
        public Builder services_and_addons( List<ServicesAndAddOns> value )
        {
            this.services_and_addons = value;
            return this;
        }
        public Builder hireDate( Date value )
        {
            this.hireDate = value;
            return this;
        }


        public Booking build()
        {
            return new Booking( this );
        }
    }

    @Override
    public String toString() {
        return "Booking{" +
                "ID=" + ID +
                ", reference_number='" + reference_number + '\'' +
                ", rooms='" + rooms + '\'' +
                ", services_and_addons='" + services_and_addons + '\'' +
                ", hireDate=" + hireDate +
                '}';
    }
}
