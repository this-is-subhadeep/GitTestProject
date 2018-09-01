package learning.sprigBoot.mongoDB.product;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ProductDetails implements Comparable<ProductDetails> {

	@Id
	private TimeProduct id;
	private Date endDate;
	private Integer packageSize;
	private Float costPrice;
	private Float sellingPrice;

	public ProductDetails() {
		id = new TimeProduct();
	}

	public TimeProduct getId() {
		return id;
	}

	public void setId(TimeProduct id) {
		this.id = id;
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone="IST")
	public Date getEndDate() {
		return endDate;
	}

	@JsonFormat(pattern="yyyy-MM-dd",timezone="IST")
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public int compareTo(ProductDetails o) {
		return this.id.compareTo(o.id);
	}
	
	public void copyFrom(ProductDetails prodDet) {
		this.id = new TimeProduct(prodDet.id);
		this.endDate = (Date)prodDet.endDate.clone();
		this.packageSize = prodDet.packageSize.intValue();
		this.costPrice = prodDet.costPrice.floatValue();
		this.sellingPrice = prodDet.sellingPrice.floatValue();
	}

	@Override
	public String toString() {
		return "ProductDetails [id=" + id + ", endDate=" + endDate + ", packageSize=" + packageSize
				+ ", costPrice=" + costPrice + ", sellingPrice=" + sellingPrice + "]";
	}
}
