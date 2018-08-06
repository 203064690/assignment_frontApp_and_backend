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
    public static Room createRoom(int rm_num,
                                  String rm_type,
                                  String rm_view,
                                  double rm_price )
    {
        Room room = new Room
                .Builder( rm_num )
                .room_type( rm_type )
                .room_view( rm_view )
                .room_price( rm_price )
                .build();

        return room;
    }

}