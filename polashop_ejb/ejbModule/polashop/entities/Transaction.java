package polashop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the transaction database table.
 * 
 */
@Entity
@Table(name="transaction")
@NamedQuery(name="Transaction.findAll", query="SELECT t FROM Transaction t")
public class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idtransaction;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="is_paid_for")
	private byte isPaidFor;

	@Column(name="is_send")
	private byte isSend;

	private double price;

	@Column(name="shipping_costs")
	private double shippingCosts;

	//bi-directional many-to-one association to ProductHasTransaction
	@OneToMany(mappedBy="transaction")
	private List<ProductHasTransaction> productHasTransactions;

	//bi-directional many-to-one association to ShippingDetail
	@ManyToOne
	@JoinColumn(name="shipping_details_idshipping_details")
	private ShippingDetail shippingDetail;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public Transaction() {
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

	public byte getIsPaidFor() {
		return this.isPaidFor;
	}

	public void setIsPaidFor(byte isPaidFor) {
		this.isPaidFor = isPaidFor;
	}

	public byte getIsSend() {
		return this.isSend;
	}

	public void setIsSend(byte isSend) {
		this.isSend = isSend;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getShippingCosts() {
		return this.shippingCosts;
	}

	public void setShippingCosts(double shippingCosts) {
		this.shippingCosts = shippingCosts;
	}

	public List<ProductHasTransaction> getProductHasTransactions() {
		return this.productHasTransactions;
	}

	public void setProductHasTransactions(List<ProductHasTransaction> productHasTransactions) {
		this.productHasTransactions = productHasTransactions;
	}

	public ProductHasTransaction addProductHasTransaction(ProductHasTransaction productHasTransaction) {
		getProductHasTransactions().add(productHasTransaction);
		productHasTransaction.setTransaction(this);

		return productHasTransaction;
	}

	public ProductHasTransaction removeProductHasTransaction(ProductHasTransaction productHasTransaction) {
		getProductHasTransactions().remove(productHasTransaction);
		productHasTransaction.setTransaction(null);

		return productHasTransaction;
	}

	public ShippingDetail getShippingDetail() {
		return this.shippingDetail;
	}

	public void setShippingDetail(ShippingDetail shippingDetail) {
		this.shippingDetail = shippingDetail;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}