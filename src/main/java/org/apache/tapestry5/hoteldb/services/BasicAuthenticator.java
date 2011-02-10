package org.apache.tapestry5.hoteldb.services;

import org.apache.tapestry5.hoteldb.dal.CrudServiceDAO;
import org.apache.tapestry5.hoteldb.dal.QueryParameters;
import org.apache.tapestry5.hoteldb.entities.User;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Session;

/**
 * Basic Security Realm implementation
 * 
 * @author karesti
 * @version 1.0
 */
public class BasicAuthenticator implements Authenticator
{

    public static final String AUTH_TOKEN = "authToken";

    @Inject
    private CrudServiceDAO crudService;

    @Inject
    private Request request;

    public void login(String username, String password) throws AuthenticationException
    {

        User user = crudService.findUniqueWithNamedQuery(User.BY_CREDENTIALS, QueryParameters.with(
                "username",
                username).and("password", password).parameters());

        if (user == null) { throw new AuthenticationException("The user doesn't exist"); }

        request.getSession(true).setAttribute(AUTH_TOKEN, user);
    }

    public boolean isLoggedIn()
    {
        Session session = request.getSession(false);
        if (session != null) { return session.getAttribute(AUTH_TOKEN) != null; }
        return false;
    }

    public void logout()
    {
        Session session = request.getSession(false);
        if (session != null)
        {
            session.setAttribute(AUTH_TOKEN, null);
            session.invalidate();
        }
    }

    public User getLoggedUser()
    {
        User user = null;

        if (isLoggedIn())
        {
            user = (User) request.getSession(true).getAttribute(AUTH_TOKEN);
        }
        else
        {
            throw new IllegalStateException("The user is not logged ! ");
        }
        return user;
    }

}
