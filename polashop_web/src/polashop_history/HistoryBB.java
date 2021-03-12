package polashop_history;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import polashop.dao.ProductDAO;
import polashop.dao.ShippingDetailDAO;
import polashop.dao.TransactionDAO;
import polashop.dao.UserDAO;
import polashop.entities.Product;
import polashop.entities.ShippingDetail;
import polashop.entities.Transaction;
import polashop.entities.User;
import polashop_shopcart.ShopcartProduct;

@Named
@RequestScoped
public class HistoryBB {
	@Inject
	FacesContext ctx;
	private static final String PAGE_STAY_AT_THE_SAME = null;

	List<History> list;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	TransactionDAO transactionDAO;
	
	@EJB
	UserDAO userDAO;
	
	@Inject
	FacesContext context;
	
	User user = new User();
	

	private History history = new History();
		
	
	public void setList(List<History> list) {
		this.list = list;
	}
	
	public History getHistory() {
		return history;
	}
	
	
	public List<History> getList(){
		List<Transaction> transactionlist = null;
		List<History> list = new LinkedList<History>();
		
		transactionlist = transactionDAO.getList();
		
		for(Transaction t : transactionlist) {
			History h = new History(t.getIdtransaction(),t.getDate(),t.getIsPaidFor(),t.getIsSend(),t.getPrice());
			list.add(h);
		}
		
		return list;
	}
		
	
}
