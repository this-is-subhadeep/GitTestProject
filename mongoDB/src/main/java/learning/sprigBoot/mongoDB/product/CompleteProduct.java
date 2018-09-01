package learning.sprigBoot.mongoDB.product;

public class CompleteProduct {
	private Product product;
	private Integer packageSize;
	private Float costPrice;
	private Float sellingPrice;
	public CompleteProduct() {
	}
	public CompleteProduct(Product product) {
		this.product = product;
	}
	public CompleteProduct(Product product, Integer packageSize, Float costPrice, Float sellingPrice) {
		this.product = product;
		this.packageSize = packageSize;
		this.costPrice = costPrice;
		this.sellingPrice = sellingPrice;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getPackageSize() {
		return packageSize;
	}
	public void setPackageSize(Integer packageSize) {
		this.packageSize = packageSize;
	}
	public Float getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(Float costPrice) {
		this.costPrice = costPrice;
	}
	public Float getSellingPrice() {
		return sellingPrice;
	}
	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
	@Override
	public String toString() {
		return "CompleteProduct [product=" + product + ", packageSize=" + packageSize + ", costPrice=" + costPrice
				+ ", sellingPrice=" + sellingPrice + "]";
	}
}
