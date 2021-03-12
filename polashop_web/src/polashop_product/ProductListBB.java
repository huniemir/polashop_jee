package polashop_product;

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
import polashop.entities.Product;
import polashop_shopcart.ShopcartProduct;

@Named
@RequestScoped
public class ProductListBB {
	@Inject
	FacesContext ctx;
	private static final String PAGE_STAY_AT_THE_SAME = null;

	private String name;
	
	List<ShopcartProduct> list;
		
	@Inject
	ExternalContext extcontext;
	
	@Inject
	Flash flash;
	
	@EJB
	ProductDAO productDAO;
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}


	public List<Product> getFullList(){
		return productDAO.getFullList();
	}

	public List<ShopcartProduct> getList(){
		List<Product> productlist = null;
		List<ShopcartProduct> list = new LinkedList<ShopcartProduct>();
		
		//1. Prepare search params
		Map<String,Object> searchParams = new HashMap<String, Object>();
		
		if (name != null && name.length() > 0){
			searchParams.put("name", name);
		}
		
		//2. Get list
		
		productlist = productDAO.getList(searchParams);
		
		for(Product p : productlist) {
			ShopcartProduct s = new ShopcartProduct(p.getIdproduct(),p.getName(),p.getPrice(),p.getDescription());
			list.add(s);
		}
		return list;
	}
	public void setList(List<ShopcartProduct> list) {
		this.list = list;
	}
	
	public String add(ShopcartProduct product){
		
		HttpServletResponse resp = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		Cookie productCookie = new Cookie("product".concat(String.valueOf(product.getIdproduct())), "1");
		resp.addCookie(productCookie);
		
		ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Dodano produkt do koszyka",null));
		return null;
	}

	public String deleteProduct(Product product){
		productDAO.remove(product);
		return PAGE_STAY_AT_THE_SAME;
	}
}
