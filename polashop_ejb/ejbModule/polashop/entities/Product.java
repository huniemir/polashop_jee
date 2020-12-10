package polashop.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product database table.
 * 
 */
@Entity
@Table(name="product")
@NamedQuery(name="Product.findAll", query="SELECT p FROM Product p")
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idproduct;

	@Lob
	private String description;

	private String image;

	private String name;

	private double price;

	private String producer;

	//bi-directional many-to-one association to ProductHasTransaction
	@OneToMany(mappedBy="product")
	private List<ProductHasTransaction> productHasTransactions;

	public Product() {
	}

	public int getIdproduct() {
		return this.idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getProducer() {
		return this.producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public List<ProductHasTransaction> getProductHasTransactions() {
		return this.productHasTransactions;
	}

	public void setProductHasTransactions(List<ProductHasTransaction> productHasTransactions) {
		this.productHasTransactions = productHasTransactions;
	}

	public ProductHasTransaction addProductHasTransaction(ProductHasTransaction productHasTransaction) {
		getProductHasTransactions().add(productHasTransaction);
		productHasTransaction.setProduct(this);

		return productHasTransaction;
	}

	public ProductHasTransaction removeProductHasTransaction(ProductHasTransaction productHasTransaction) {
		getProductHasTransactions().remove(productHasTransaction);
		productHasTransaction.setProduct(null);

		return productHasTransaction;
	}

}