package polashop.dao;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import polashop.entities.ShippingDetail;
import polashop.entities.User;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class UserDAO {
	private final static String UNIT_NAME = "polashopPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;

	public User find(Object id) {
		return em.find(User.class, id);
	}
	
	public User getUserFromDatabase(String login, String pass) {
		try {
			
			Query query = em.createQuery("Select u from User u where u.login=:login and u.password=:password");
		
			query.setParameter("login",login);
			query.setParameter("password", pass);
		
			User u = (User)query.getSingleResult();
	
			return u;
		} catch (javax.persistence.NoResultException e) {
			User u = null;
				
			return u;
		}
	}

	

}
