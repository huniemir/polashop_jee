package polashop_shippingdetail;

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
import polashop.dao.UserDAO;
import polashop.entities.Product;
import polashop.entities.ShippingDetail;
import polashop.entities.User;
import polashop_shopcart.ShopcartProduct;

@Named
@RequestScoped
public class ShippingDetailBB {
	@Inject
	FacesContext ctx;
	private static final String PAGE_STAY_AT_THE_SAME = null;

	List<ShippingDetail> list;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	ShippingDetailDAO shippingDetailDAO;
	
	@EJB
	UserDAO userDAO;
	
	@Inject
	FacesContext context;
	
	User user = new User();
	

	private ShippingDetail shippingDetail = new ShippingDetail(); 
		
	
	public void setList(List<ShippingDetail> list) {
		this.list = list;
	}
	
	public ShippingDetail getShippingDetail() {
		return shippingDetail;
	}
	
	
	public List<ShippingDetail> getList(){
		List<ShippingDetail> list = null;
		
		list = shippingDetailDAO.getList();
		
		return list;
	}
	
	public void delete(ShippingDetail s) {
		s.setUser(null);
		shippingDetailDAO.merge(s);
	}
	
	public String saveData() {
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				
		Object atrValue;
				
		Enumeration<String> enumeration = session.getAttributeNames();
		while (enumeration.hasMoreElements()) {
			String atrName = enumeration.nextElement();
			if(atrName.equals("iduser")) {
				atrValue = session.getAttribute(atrName);
				user = userDAO.find(atrValue);
				shippingDetail.setUser(user);
			}
			        
		}

		try {
			
			shippingDetailDAO.create(shippingDetail);
			
		} catch (Exception e) {
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "wystąpił błąd podczas zapisu", null));
			return PAGE_STAY_AT_THE_SAME;
		}

		return "shopcart?faces-redirect=true";
	}
}
