package org.apache.tapestry5.hoteldb.services;

import org.apache.tapestry5.grid.GridDataSource;
import org.apache.tapestry5.hibernate.HibernateGridDataSource;
import org.apache.tapestry5.hoteldb.data.SearchCriteria;
import org.apache.tapestry5.hoteldb.entities.Hotel;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class HotelServiceImpl implements HotelService
{

    private final Session session;

    public HotelServiceImpl(Session session)
    {
        super();
        this.session = session;
    }

    /**
     * This datasource is used by Tapestry 5 Grid to search and paginate
     * 
     * @author ccordenier
     */
    private final class HotelDataSource extends HibernateGridDataSource
    {
        private SearchCriteria criteria;

        public HotelDataSource(SearchCriteria criteria)
        {
            super(session, Hotel.class);
            this.criteria = criteria;
        }

        @Override
        public int getAvailableRows()
        {
            return criteria.getSearchPattern() == null ? 0 : super.getAvailableRows();
        }

        @Override
        protected void applyAdditionalConstraints(Criteria crit)
        {
            crit.add(Restrictions.ilike("name", criteria.getSearchPattern()));
        }
    }

    public GridDataSource search(SearchCriteria criteria)
    {
        return new HotelDataSource(criteria);
    }

}
