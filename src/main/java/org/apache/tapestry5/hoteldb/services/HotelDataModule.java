package org.apache.tapestry5.hoteldb.services;

import org.apache.tapestry5.hoteldb.dal.DataModule;
import org.apache.tapestry5.hoteldb.dal.HibernateModule;
import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.ServiceBinder;
import org.apache.tapestry5.ioc.annotations.SubModule;

/**
 * This module is automatically included as part of the Tapestry IoC Registry, it's a good place to
 * configure and extend Tapestry, or to place your own service definitions.
 */
@SubModule(
{ HibernateModule.class, DataModule.class })
public class HotelDataModule
{
    public static void bind(ServiceBinder binder)
    {
        binder.bind(Authenticator.class, BasicAuthenticator.class);
        binder.bind(HotelService.class, HotelServiceImpl.class);
    }

    public static void contributeHibernateEntityPackageManager(Configuration<String> configuration)
    {
      configuration.add("org.apache.tapestry5.hoteldb.entities");
    }
    
}
