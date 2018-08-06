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
    public static ServicesAndAddOns createServicesAndAddOns(int serv_ext_id,
                                                            String ext_name,
                                                            double pr_added )
    {
        ServicesAndAddOns services_and_addons = new ServicesAndAddOns
                .Builder( serv_ext_id )
                .extra_name( ext_name )
                .price_added( pr_added )
                .build();

        return services_and_addons;
    }
}
