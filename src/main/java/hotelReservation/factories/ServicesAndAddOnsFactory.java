package hotelReservation.factories;

import hotelReservation.domain.ServicesAndAddOns;

/**
 * Assignment 6
 * Domain Driven Design
 * Dylan Baadjies
 * 203064690.
 */
public class ServicesAndAddOnsFactory
{
    public static ServicesAndAddOns createServicesAndAddOns(int serv_extras_id,
                                                            String extra_name,
                                                            double price_added )
    {
        ServicesAndAddOns services_and_addons = new ServicesAndAddOns
                .Builder( serv_extras_id )
                .extra_name( extra_name )
                .price_added( price_added )
                .build();

        return services_and_addons;
    }
}
