package polashop_history;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;
import org.primefaces.model.filter.FilterConstraint;
import org.primefaces.util.LocaleUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import polashop.dao.History;
import polashop.dao.ProductDAO;
import polashop.dao.ShippingDetailDAO;
import polashop.dao.TransactionDAO;
import polashop.entities.Product;
import polashop.entities.Transaction;


public class HistoryDataModel extends LazyDataModel<History>{
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	TransactionDAO datasource;
	History h;
	
	
	int idtransaction = 0;
	    public HistoryDataModel(TransactionDAO datasource) {
	        this.datasource = datasource;
	    }
	    
	    public HistoryDataModel(TransactionDAO datasource, int idtransaction) {
	        this.datasource = datasource;
	        this.idtransaction = idtransaction;
	    }
	    
	    @Override
	    public List<History> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
	    	
	    	
	    	String where = "";
			String orderby = "";
			SortOrder unsorted = SortOrder.UNSORTED;
			for(SortMeta value : sortBy.values()) {
	            String order = value.getSortField();
	            if(!orderby.isEmpty()) break;
	            if(value.getSortOrder() != unsorted) {
	            	SortOrder so = value.getSortOrder();
	            	orderby += "order by t."+order+" ";
	            	if(so == SortOrder.ASCENDING) {
	            		orderby += "ASC ";
	            	}else if(so == SortOrder.DESCENDING){
	            		orderby += "DESC ";
	            	}
	            }
	            
	        }
			
			
			if(idtransaction>0) {
				where = "and t.idtransaction = "+idtransaction;
			}
	    	
	        List<polashop.dao.History> transactions = datasource.getLazyList(offset,pageSize,where,orderby);
	        
	        int rowCount = datasource.getRowsNumber(where,orderby);
	        setRowCount(rowCount);

	        return transactions;
	    }
	    
	    
	    
	    @Override
		public History getRowData(String rowKey) {
	    	
	    	Transaction t = datasource.find(Integer.parseInt(rowKey));
			
	    	History h = new History(t.getIdtransaction(),t.getDate(),t.getIsPaidFor(),t.getIsSend(),t.getPrice());
	    	
	    	
			return h;
		}
		
		@Override
		public String getRowKey(History object) {
			return String.valueOf(object.getIdtransaction());
		}
		
		

}
