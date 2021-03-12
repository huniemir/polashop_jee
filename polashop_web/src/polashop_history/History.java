package polashop_history;

import java.util.Date;

public class History {
	private int idtransaction;
	private Date date;
	private String isPaidFor;
	private String isSend;
	private double price;
	
	
	public History(int idtransaction, Date date, int isPaidFor, int isSend, double price){
		this.idtransaction = idtransaction;
		this.date = date;

		if(isPaidFor>0) {
			this.isPaidFor = "Opłacona";
		}else {
			this.isPaidFor = "Nieopłacona";
		}
		
		if(isSend>0) {
			this.isSend = "Wysłana";
		}else {
			this.isSend = "Niewysłana";
		}
		
		this.price = price;
	}
	public History(){
		
	}
	public int getIdtransaction() {
		return this.idtransaction;
	}
	
	public void setIdtransaction(int idtransaction) {
		this.idtransaction = idtransaction;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getIsPaidFor() {
		return this.isPaidFor;
	}
	
	public void setIsPaidFor(String isPaidFor) {
		this.isPaidFor = isPaidFor;
	}
	
	public String getIsSend() {
		return this.isSend;
	}
	
	public void setIsSend(String isSend) {
		this.isSend = isSend;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
