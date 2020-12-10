package polashop.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the product_has_transaction database table.
 * 
 */
@Embeddable
public class ProductHasTransactionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="product_idproduct", insertable=false, updatable=false)
	private int productIdproduct;

	@Column(name="transaction_idtransaction", insertable=false, updatable=false)
	private int transactionIdtransaction;

	public ProductHasTransactionPK() {
	}
	public int getProductIdproduct() {
		return this.productIdproduct;
	}
	public void setProductIdproduct(int productIdproduct) {
		this.productIdproduct = productIdproduct;
	}
	public int getTransactionIdtransaction() {
		return this.transactionIdtransaction;
	}
	public void setTransactionIdtransaction(int transactionIdtransaction) {
		this.transactionIdtransaction = transactionIdtransaction;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ProductHasTransactionPK)) {
			return false;
		}
		ProductHasTransactionPK castOther = (ProductHasTransactionPK)other;
		return 
			(this.productIdproduct == castOther.productIdproduct)
			&& (this.transactionIdtransaction == castOther.transactionIdtransaction);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.productIdproduct;
		hash = hash * prime + this.transactionIdtransaction;
		
		return hash;
	}
}