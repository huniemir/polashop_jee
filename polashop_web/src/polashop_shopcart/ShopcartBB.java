package polashop_shopcart;

import java.time.LocalDate;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import polashop.dao.ProductDAO;
import polashop.dao.ProductHasTransactionDAO;
import polashop.dao.TransactionDAO;
import polashop.dao.UserDAO;
import polashop.entities.Product;
import polashop.entities.ProductHasTransaction;
import polashop.entities.ProductHasTransactionPK;
import polashop.entities.ShippingDetail;
import polashop.entities.Transaction;
import polashop.entities.User;
@Named
@RequestScoped
public class ShopcartBB {
	
	@EJB ProductDAO dao;
	
	@EJB
	UserDAO userDAO;
	
	@EJB
	TransactionDAO transactionDAO;
	
	@EJB
	ProductHasTransactionDAO productHasTransactionDAO;
	
	@Inject
	FacesContext ctx;
	
	private final double SHIPPINGCOSTS = 0;
	
	private int amount;
	private ShopcartProduct product;
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getAmount() {
		return this.amount;
	}
	
	public void setProduct(ShopcartProduct product) {
		this.product = product;
	}
	
	public ShopcartProduct getProduct() {
		return this.product;
	}
	
	
	public List<ShopcartProduct> show(){
		//pobranie obiektu żądania
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		List<ShopcartProduct> list = new LinkedList<ShopcartProduct>();
		String productname;
		
		//pętla po ciastkach
		Cookie cookies[] = req.getCookies();
	    for (Cookie c:cookies) {
	    	if(c.getName().matches("product.*")) { 
		        //dodanie ciacha do wyświetlenia
	    		productname = c.getName();
	    		productname = productname.substring(7);
		    	Product product = dao.find(Integer.valueOf(productname));
		    	ShopcartProduct scproduct = new ShopcartProduct(product.getIdproduct(),product.getName(),product.getPrice(),Integer.valueOf(c.getValue()));
		        list.add(scproduct);
	    	}
	    }
        
		return list;
	}
	
	public String increaseAmount(ShopcartProduct product){
		int amount = product.getAmount()+1;
		if(amount == 100) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Osiągnięto maksymalną liczbe produktów",null));
			return null;
		}
		HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		Cookie productCookie = new Cookie("product".concat(String.valueOf(product.getIdproduct())), String.valueOf(amount));
		resp.addCookie(productCookie);
		
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zwiekszono ilość",null));
		return "/shopcart?faces-redirect=true";
		
	}
	public String delete(ShopcartProduct product) {
		HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Cookie cookies[] = req.getCookies();
		
	    for (Cookie c:cookies) {
	    	if(c.getName().matches("product".concat(String.valueOf(product.getIdproduct())))) { 
	    		Cookie cookie = new Cookie(c.getName(), "");
	            cookie.setMaxAge(0);
	            resp.addCookie(cookie);
	            ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usunięto ".concat(product.getName()).concat(" z koszyka"),null));
	    	}
	    }
	    return "/shopcart?faces-redirect=true";
		
        
	}
	
	public double getSummary() {
		double summary = 0;
		//pobranie obiektu żądania
				HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				List<ShopcartProduct> list = new LinkedList<ShopcartProduct>();
				String productname;
				
				//pętla po ciastkach
				Cookie cookies[] = req.getCookies();
			    for (Cookie c:cookies) {
			    	if(c.getName().matches("product.*")) { 
			    	   productname = c.getName();
			    	   productname = productname.substring(7);
			    	   double price = dao.find(Integer.valueOf(productname)).getPrice();
				       summary += Integer.valueOf(c.getValue())*price;
			    	}
			    }
		
		return summary;
	}
	
	public void buy(ShippingDetail s) {
		User user = new User();
		Date date = new Date();
		Transaction transaction = new Transaction();
		transaction.setDate(date);
		transaction.setIsPaidFor((byte)0);
		transaction.setIsSend((byte)0);
		transaction.setShippingDetail(s);
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		Object atrValue;
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String atrName = enumeration.nextElement();
			if(atrName.equals("iduser")) {
				atrValue = session.getAttribute(atrName);
				
				user = userDAO.find(atrValue);
				transaction.setUser(user);
				
			}			        
		}
		double summary = getSummary();
		transaction.setPrice(summary);
		transaction.setShippingCosts(SHIPPINGCOSTS);
		transactionDAO.create(transaction);
		int idtransaction = transactionDAO.findId(date,user,summary);
		transaction = transactionDAO.find(idtransaction);
		
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		List<ShopcartProduct> list = new LinkedList<ShopcartProduct>();
		String productname;
		ProductHasTransaction productHasTransaction;
		ProductHasTransactionPK productHasTransactionId = new ProductHasTransactionPK();
		//pętla po ciastkach
		Cookie cookies[] = req.getCookies();
	    for (Cookie c:cookies) {
	    	if(c.getName().matches("product.*")) { 
		        //dodanie ciacha do wyświetlenia
	    		productname = c.getName();
	    		productname = productname.substring(7);
		    	Product product = dao.find(Integer.valueOf(productname));
		    	productHasTransaction = new ProductHasTransaction();
		    	productHasTransaction.setProduct(product);
		    	productHasTransaction.setTransaction(transaction);
		    	productHasTransactionId.setProductIdproduct(product.getIdproduct());
		    	productHasTransactionId.setTransactionIdtransaction(transaction.getIdtransaction());
		    	productHasTransaction.setId(productHasTransactionId);
		    	productHasTransaction.setAmount(Integer.valueOf(c.getValue()));
		    	productHasTransactionDAO.create(productHasTransaction);
		    	Cookie cookie = new Cookie(c.getName(), "");
	            cookie.setMaxAge(0);
	            resp.addCookie(cookie);
	    	}
	    }
	    ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zamówienie zostało zrealizowane",null));
		
	}
	
	
	
}
