package polashop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shipping_details database table.
 * 
 */
@Entity
@Table(name="shipping_details")
@NamedQuery(name="ShippingDetail.findAll", query="SELECT s FROM ShippingDetail s")
public class ShippingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="idshipping_details")
	private int idshippingDetails;

	private String flat;

	private String name;

	private String street;

	private String surname;

	@Column(name="telephone_number")
	private int telephoneNumber;

	private String town;

	private String zipcode;

	//bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="shippingDetail")
	private List<Transaction> transactions;

	public ShippingDetail() {
	}

	public int getIdshippingDetails() {
		return this.idshippingDetails;
	}

	public void setIdshippingDetails(int idshippingDetails) {
		this.idshippingDetails = idshippingDetails;
	}

	public String getFlat() {
		return this.flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getTelephoneNumber() {
		return this.telephoneNumber;
	}

	public void setTelephoneNumber(int telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getTown() {
		return this.town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setShippingDetail(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setShippingDetail(null);

		return transaction;
	}

}