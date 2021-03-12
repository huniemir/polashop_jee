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
import polashop.entities.ProductHasTransaction;
import polashop.entities.ShippingDetail;
import polashop.entities.Transaction;
import polashop.entities.User;

//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class ProductHasTransactionDAO {
	private final static String UNIT_NAME = "polashopPU";

	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	
	public void create(ProductHasTransaction productHasTransaction) {
		em.persist(productHasTransaction);
	}

	public ProductHasTransaction merge(ProductHasTransaction productHasTransaction) {
		return em.merge(productHasTransaction);
	}

	public void remove(ProductHasTransaction productHasTransaction) {
		em.remove(em.merge(productHasTransaction));
	}

	public ProductHasTransaction find(Object id) {
		return em.find(ProductHasTransaction.class, id);
	}


	
	

}
