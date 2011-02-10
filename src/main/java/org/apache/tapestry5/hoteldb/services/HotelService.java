package org.apache.tapestry5.hoteldb.services;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hoteldb.data.SearchCriteria;

public interface HotelService
{

    /**
     * Return the list of hotel matching the corresponding search criteria object.
     * 
     * @param criteria
     *            The criteria used to search against Hotel Database
     * @return The Tapestry Grid source on the resulting list of hotels
     */
    GridDataSource search(SearchCriteria criteria);

}
