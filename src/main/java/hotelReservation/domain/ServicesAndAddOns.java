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
public class ServicesAndAddOns implements Serializable
{
    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    @ManyToOne
    private Booking booking;
    private int serv_extras_id;
    private String extra_name;
    private double price_added;

    public ServicesAndAddOns(){}

    public ServicesAndAddOns( Builder builder )
    {
        ID = builder.ID;
        serv_extras_id = builder.serv_extras_id;
        extra_name = builder.extra_name;
        price_added = builder.price_added;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setServ_extras_id(int serv_extras_id) {
        this.serv_extras_id = serv_extras_id;
    }

    public void setExtra_name(String extra_name) {
        this.extra_name = extra_name;
    }

    public void setPrice_added(double price_added) {
        this.price_added = price_added;
    }

    public Long getID()
    {
        return ID;
    }
    public int getServExtraID()
    {
        return serv_extras_id;
    }
    public String getExtraName()
    {
        return extra_name;
    }
    public double getPriceAdded()
    {
        return price_added;
    }

    public static class Builder
    {
        private Long ID;
        private int serv_extras_id;
        private String extra_name;
        private double price_added;

        public Builder( int serv_extras_id )
        {
            this.serv_extras_id = serv_extras_id;
        }
        public Builder ID( Long value )
        {
            this.ID = value;
            return this;
        }
        public Builder extra_name( String value )
        {
            this.extra_name = value;
            return this;
        }
        public Builder price_added( double value )
        {
            this.price_added = value;
            return this;
        }


        public ServicesAndAddOns build()
        {
            return new ServicesAndAddOns( this );
        }
    }

    @Override
    public String toString() {
        return "Service{" +
                "ID=" + ID +
                ", serv_extras_id='" + serv_extras_id + '\'' +
                ", extra_name='" + extra_name + '\'' +
                ", price_added='" + price_added + '\'' +
                '}';
    }

}
