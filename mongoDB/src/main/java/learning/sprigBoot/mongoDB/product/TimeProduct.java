package learning.sprigBoot.mongoDB.product;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;


public class TimeProduct implements Serializable, Comparable<TimeProduct> {
	private static final long serialVersionUID = 0;
	private Product product;
	private Date startDate;

	public TimeProduct() {
	}

	public TimeProduct(Product product, Date startDate) {
		this.product = product;
		this.startDate = startDate;
	}

	public TimeProduct(TimeProduct tProduct) {
		this.product = tProduct.product;
		this.startDate = tProduct.startDate;
	}

//	public TimeProduct(String id) {
//		String pieces[] = id.trim().split(":");
//		if(pieces.length==2) {
//			this.productId = pieces[0];
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			try {
//				this.startDate = sdf.parse(pieces[1]);
//			} catch(ParseException e) {
//				this.startDate = null;
//				e.printStackTrace();
//			}
//		}
//	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "IST")
	public Date getStartDate() {
		return startDate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "IST")
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public int compareTo(TimeProduct tProd) {
		int compareProd = this.product.compareTo(tProd.product);
		if (compareProd == 0) {
			return this.startDate.compareTo(tProd.startDate);
		} else {
			return compareProd;
		}
	}

	@Override
	public String toString() {
		return "TimeProduct [product=" + product + ", startDate=" + startDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
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
		TimeProduct other = (TimeProduct) obj;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}
}
