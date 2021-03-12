package polashop.dao;

import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import polashop.entities.Product;
import polashop.entities.ShippingDetail;
import polashop.entities.Transaction;
import polashop.entities.User;


//DAO - Data Access Object for Person entity
//Designed to serve as an interface between higher layers of application and data.
//Implemented as stateless Enterprise Java bean - server side code that can be invoked even remotely.

@Stateless
public class TransactionDAO {
	private final static String UNIT_NAME = "polashopPU";

	@EJB
	UserDAO userDAO;
	
	User user = new User();
	
	
	// Dependency injection (no setter method is needed)
	@PersistenceContext(unitName = UNIT_NAME)
	protected EntityManager em;
	
	
	public void create(Transaction transaction) {
		em.persist(transaction);
	}

	public Transaction merge(Transaction transaction) {
		return em.merge(transaction);
	}

	public void remove(Transaction transaction) {
		em.remove(em.merge(transaction));
	}

	public Transaction find(Object id) {
		return em.find(Transaction.class, id);
	}

	public int findId(Date date, User user, double price) {
		Transaction transaction;
		Query query = em.createQuery("select t.idtransaction from Transaction t where date = :date and user = :user and price = :price");
		query.setParameter("date", date);
		query.setParameter("user", user);
		query.setParameter("price", price);
		try {

			return (int)query.getSingleResult();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}
	
	public List<Transaction> getFullList() {
		List<Transaction> list = null;

		Query query = em.createQuery("select t from Transaction t");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public List<Transaction> getList() {
		List<Transaction> list = null;
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Object atrValue;
		
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String atrName = enumeration.nextElement();
			if(atrName.equals("iduser")) {
				atrValue = session.getAttribute(atrName);
				user = userDAO.find(atrValue);
				
				Query query = em.createQuery("select t from Transaction t where user = :user");
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
	
	public List<History> getLazyList(int first, int max, String where, String orderby)  {
		List<Transaction> transactionlist = null;
		List<History> list = new LinkedList<History>();
		
        
        
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		
		Object atrValue;
		
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String atrName = enumeration.nextElement();
			if(atrName.equals("iduser")) {
				atrValue = session.getAttribute(atrName);
				user = userDAO.find(atrValue);
				
				Query query = em.createQuery("select t from Transaction t where user = :user "+where+" "+orderby);
				query.setParameter("user", user);
				query.setMaxResults(max);
				query.setFirstResult(first);
				try {
					transactionlist = query.getResultList();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
				
		}

		for(Transaction t : transactionlist) {
			History h = new History(t.getIdtransaction(),t.getDate(),t.getIsPaidFor(),t.getIsSend(),t.getPrice());
			list.add(h);
		}

		return list;
	}
	
	public int getRowsNumber(String where, String orderby)  {

		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		int rowsNumber = 0;
		
		Object atrValue;
		
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String atrName = enumeration.nextElement();
			if(atrName.equals("iduser")) {
				atrValue = session.getAttribute(atrName);
				user = userDAO.find(atrValue);
				
				TypedQuery<Long> query = (TypedQuery<Long>)em.createQuery("select count(t) from Transaction t where user = :user "+where+" "+orderby);
				query.setParameter("user", user);

				try {
					rowsNumber = query.getSingleResult().intValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
				
		}


		return rowsNumber;
	}
	

	

}
