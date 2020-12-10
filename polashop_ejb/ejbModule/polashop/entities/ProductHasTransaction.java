package polashop.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the product_has_transaction database table.
 * 
 */
@Entity
@Table(name="product_has_transaction")
@NamedQuery(name="ProductHasTransaction.findAll", query="SELECT p FROM ProductHasTransaction p")
public class ProductHasTransaction implements Serializable {	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ProductHasTransactionPK id;

	private int amount;

	private double price;

	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="idproduct", insertable=false, updatable=false)
	private Product product;

	//bi-directional many-to-one association to Transaction
	@ManyToOne
	@JoinColumn(name="idtransaction", insertable=false, updatable=false)
	private Transaction transaction;

	public ProductHasTransaction() {
	}

	public ProductHasTransactionPK getId() {
		return this.id;
	}

	public void setId(ProductHasTransactionPK id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}