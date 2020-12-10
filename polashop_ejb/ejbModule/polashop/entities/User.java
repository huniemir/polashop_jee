package polashop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int iduser;

	private String email;

	private String login;

	private String password;

	private String role;

	//bi-directional many-to-one association to ShippingDetail
	@OneToMany(mappedBy="user")
	private List<ShippingDetail> shippingDetails;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="user")
	private List<Transaction> transactions;

	public User() {
	}

	public int getIduser() {
		return this.iduser;
	}

	public void setIduser(int iduser) {
		this.iduser = iduser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<ShippingDetail> getShippingDetails() {
		return this.shippingDetails;
	}

	public void setShippingDetails(List<ShippingDetail> shippingDetails) {
		this.shippingDetails = shippingDetails;
	}

	public ShippingDetail addShippingDetail(ShippingDetail shippingDetail) {
		getShippingDetails().add(shippingDetail);
		shippingDetail.setUser(this);

		return shippingDetail;
	}

	public ShippingDetail removeShippingDetail(ShippingDetail shippingDetail) {
		getShippingDetails().remove(shippingDetail);
		shippingDetail.setUser(null);

		return shippingDetail;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setUser(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setUser(null);

		return transaction;
	}

}