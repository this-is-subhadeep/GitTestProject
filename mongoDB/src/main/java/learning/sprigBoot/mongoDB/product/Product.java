package learning.sprigBoot.mongoDB.product;

import org.springframework.data.annotation.Id;

public class Product implements Comparable<Product> {
	@Id
	private String id;
	private String name;
	private ProductType productType;

	public Product() {
	}

	public Product(String id, String name, ProductType type) {
		this.id = id;
		this.name = name;
		this.productType = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public int compareTo(Product ref) {
		int compareType = this.productType.compareTo(ref.productType);
		if (compareType == 0) {
			int compareName = this.name.compareTo(ref.name);
			if (compareName == 0) {
				return this.id.compareTo(ref.id);
			} else {
				return compareName;
			}
		} else {
			return compareType;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((productType == null) ? 0 : productType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (productType == null) {
			if (other.productType != null)
				return false;
		} else if (!productType.equals(other.productType))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", productType=" + productType + "]";
	}
}
