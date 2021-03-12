package polashop_shopcart;

public class ShopcartProduct {
	private int idproduct;
	private String name;
	private double price;
	private int amount;
	private String description;
	
	public ShopcartProduct(int idproduct, String name, double price, int amount) {
		this.idproduct = idproduct;
		this.name = name;
		this.price = price;
		this.amount = amount;
	}
	
	public ShopcartProduct(int idproduct, String name, double price, String description) {
		this.idproduct = idproduct;
		this.name = name;
		this.price = price;
		this.amount = 1;
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getIdproduct() {
		return this.idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}
