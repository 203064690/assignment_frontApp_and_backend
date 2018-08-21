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
@Table(name="room")
public class Room implements Serializable {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long ID;
    @ManyToOne
    private Booking booking;


    private int room_number;
    private String room_type;
    private String room_view;
    private double room_price;

    public Room() {}


    public Long getID()
    {
        return ID;
    }

    public int getRoomNumber() {
        return room_number;
    }

    public String getRoomType() {
        return room_type;
    }

    public String getRoomView() {
        return room_view;
    }

    public double getRoomPrice() {
        return room_price;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public void setRoom_view(String room_view) {
        this.room_view = room_view;
    }

    public void setRoom_price(double room_price) {
        this.room_price = room_price;
    }

    public Room(Builder builder){
        ID = builder.ID;
        room_number = builder.room_number;
        room_type = builder.room_type;
        room_view = builder.room_view;
        room_price = builder.room_price;
    }

    public static class Builder
    {
        private Long ID;
        private int room_number;
        private String room_type;
        private String room_view;
        private double room_price;

        public Builder( int room_number )
        {
            this.room_number = room_number;
        }
        public Builder ID( Long value )
        {
            this.ID = value;
            return this;
        }
        public Builder room_type( String value )
        {
            this.room_type = value;
            return this;
        }
        public Builder room_view( String value )
        {
            this.room_view = value;
            return this;
        }
        public Builder room_price( double value )
        {
            this.room_price = value;
            return this;
        }


        public Room build()
        {
            return new Room( this );
        }
    }

    @Override
    public String toString() {
        return "Room{" +
                "ID=" + ID +
                ", room_number='" + room_number + '\'' +
                ", room_type='" + room_type + '\'' +
                ", room_view='" + room_view + '\'' +
                ", room_price=" + room_price +
                '}';
    }

}
