package polashop_history;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import polashop.dao.History;
import polashop.dao.ProductDAO;
import polashop.dao.TransactionDAO;
import polashop.entities.Product;
import polashop.entities.Transaction;

@Named
@RequestScoped
public class LazyView implements Serializable {

    

    private Product selectedProduct;

    @EJB
	TransactionDAO service;
    
    private LazyDataModel<History> lazyModel;
    private int idtransaction;
    @PostConstruct
    public void init() {
    	if(idtransaction>0) {
    		lazyModel = new HistoryDataModel(service,idtransaction);
    	}else {
    		lazyModel = new HistoryDataModel(service);
    	}
    }
    
    public int getIdtransaction() {
		return this.idtransaction;
	}
	
	public void setIdtransaction(int idtransaction) {
		lazyModel = new HistoryDataModel(service,idtransaction);
		this.idtransaction = idtransaction;
	}
    
    public LazyDataModel<History> getLazyModel() {
        return lazyModel;
    }

   
    public void setService(TransactionDAO service) {
        this.service = service;
    }

   
}
