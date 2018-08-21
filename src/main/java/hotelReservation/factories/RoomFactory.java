package hotelReservation.factories;

import hotelReservation.domain.Room;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class RoomFactory
{
    public static Room createRoom(int room_number,
                                  String room_type,
                                  String room_view,
                                  double room_price )
    {
        Room room = new Room
                .Builder( room_number )
                .room_type( room_type )
                .room_view( room_view )
                .room_price( room_price )
                .build();

        return room;
    }

}