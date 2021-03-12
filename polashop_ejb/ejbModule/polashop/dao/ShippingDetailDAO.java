package polashop.dao;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpSession;

import polashop.entities.Product;
import polashop.entities.ShippingDetail;
import polashop.entities.User;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class ShippingDetailDAO {
	private final static String UNIT_NAME = "polashopPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	@EJB
	UserDAO userDAO;
	
	User user = new User();
	
	public void create(ShippingDetail shippingDetail) {
		em.persist(shippingDetail);
	}

	public ShippingDetail merge(ShippingDetail shippingDetail) {
		return em.merge(shippingDetail);
	}

	public void remove(ShippingDetail shippingDetail) {
		em.remove(em.merge(shippingDetail));
	}

	public ShippingDetail find(Object id) {
		return em.find(ShippingDetail.class, id);
	}
	public List<ShippingDetail> getFullList() {
		List<ShippingDetail> list = null;

		Query query = em.createQuery("select s from ShippingDetail s");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public List<ShippingDetail> getList() {
		List<ShippingDetail> list = null;
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Object atrValue;
		
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String atrName = enumeration.nextElement();
			if(atrName.equals("iduser")) {
				atrValue = session.getAttribute(atrName);
				user = userDAO.find(atrValue);
				
				Query query = em.createQuery("select s from ShippingDetail s where user = :user");
				query.setParameter("user", user);
				try {
					list = query.getResultList();
				} catch (Exception e) {
					e.printStackTrace();
				}

				return list;
				
			}
			        
		}
		
		
		return null;
		
		
	}

	
	

}
