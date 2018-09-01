package learning.sprigBoot.mongoDB.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	private ProductTypeRepository productTypeRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	public List<ProductType> getAllProductTypes() {
		List<ProductType> prodTypeList = productTypeRepository.findAll();
		Collections.sort(prodTypeList);
		return prodTypeList; 
	}

	public List<Product> getAllProducts() {
		List<Product> prodList = productRepository.findAll();
		Collections.sort(prodList);
		return prodList; 
	}

	public List<ProductDetails> getAllProductDetails() {
		List<ProductDetails> prodDetList = productDetailsRepository.findAll();
		Collections.sort(prodDetList);
		return prodDetList; 
	}

	public ProductType getProductType(String prodTypId) {
		if (prodTypId != null) {
			Optional<ProductType> prodTypContainer = productTypeRepository.findById(prodTypId);
			return prodTypContainer.isPresent() ? prodTypContainer.get() : null;
		}
		return null;
	}

	public Product getProduct(String prodId) {
		if (prodId != null) {
			Optional<Product> prodContainer = productRepository.findById(prodId);
			return prodContainer.isPresent() ? prodContainer.get() : null;
		}
		return null;
	}

	public ProductDetails getProductDetail(TimeProduct tProduct) {
		if (tProduct != null) {
			Optional<ProductDetails> prodContainer = productDetailsRepository.findById(tProduct);
			return prodContainer.isPresent() ? prodContainer.get() : null;
		}
		return null;
	}

	public ProductDetails getProductDetailInRange(TimeProduct tProduct) {
		if(tProduct!=null) {
			List<ProductDetails> prodDetList = getAllProductDetails();
			ProductDetails prodDetInRange=new ProductDetails();
			prodDetList.forEach(prodDet -> {
				if(prodDet.getId().getProduct().equals(tProduct.getProduct())
						&& prodDet.getId().getStartDate().compareTo(tProduct.getStartDate()) <= 0
						&& prodDet.getEndDate().compareTo(tProduct.getStartDate()) > 0) {
					prodDetInRange.copyFrom(prodDet);
				}
			});
			
			return prodDetInRange;
		} else {
			return null;
		}
	}

	//	public List<Product> getProductByType(String prodTypeId) {
//		if (prodTypeId != null) {
//			return repository.findByProductTypeId(prodTypeId);
//		}
//		return null;
//	}

	public void addProductType(ProductType prodTyp) {
		String prodId = prodTyp.getId();
		if (!productTypeRepository.findById(prodId).isPresent()) {
			productTypeRepository.save(prodTyp);
		}
	}

	public void addProduct(Product prod) {
		String prodId = prod.getId();
		if (!productRepository.findById(prodId).isPresent()) {
			productRepository.save(prod);
			addProductType(prod.getProductType());
		}
	}

	public void addProductDetails(ProductDetails prodDet) {
		TimeProduct prodDetId = prodDet.getId();
		if (!productDetailsRepository.findById(prodDetId).isPresent()) {
			productDetailsRepository.save(prodDet);
			addProduct(prodDet.getId().getProduct());
		}
	}

	public void updateProductType(ProductType prodTyp) {
		if (productTypeRepository.findById(prodTyp.getId()).isPresent()) {
			productTypeRepository.save(prodTyp);
		}
	}

	public void updateProduct(Product prod) {
		if (productRepository.findById(prod.getId()).isPresent()) {
			productRepository.save(prod);
			updateProductType(prod.getProductType());
		}
	}

	public void updateProductDetails(ProductDetails prodDet) {
		if (productDetailsRepository.findById(prodDet.getId()).isPresent()) {
			productDetailsRepository.save(prodDet);
			updateProduct(prodDet.getId().getProduct());
		}
	}

	public String getNextProductId() {
		String maxProdId = "itm000000";
		for (Product product : getAllProducts()) {
			if (product.getId().compareTo(maxProdId) > 0) {
				maxProdId = product.getId();
			}
		}
		int pidNumber = 0;
		try {
			pidNumber = Integer.parseInt(maxProdId.substring(3));
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		return maxProdId.substring(0, 3) + String.format("%06d", pidNumber + 1);
	}

	public List<CompleteProduct> getAllCompleteProducts(Date refDate) {
		List<CompleteProduct> compProdList = new ArrayList<>();
		if(refDate!=null) {
			getAllProductDetails().forEach(prodDet -> {
				if(refDate.compareTo(prodDet.getId().getStartDate()) >= 0
						&& refDate.compareTo(prodDet.getEndDate()) < 0) {
					CompleteProduct compProd = new CompleteProduct(prodDet.getId().getProduct());
					compProd.setPackageSize(prodDet.getPackageSize());
					compProd.setCostPrice(prodDet.getCostPrice());
					compProd.setSellingPrice(prodDet.getSellingPrice());
					compProdList.add(compProd);
				}
			});
		}
		return compProdList;
	}
	public void addCompleteProduct(CompleteProduct completeProduct, Date refDate) {
		addProduct(completeProduct.getProduct());
		ProductDetails prodDet = new ProductDetails();
		prodDet.setId(new TimeProduct(completeProduct.getProduct(), refDate));
		prodDet.setPackageSize(completeProduct.getPackageSize());
		prodDet.setCostPrice(completeProduct.getCostPrice());
		prodDet.setSellingPrice(completeProduct.getSellingPrice());
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			prodDet.setEndDate(sdf.parse("9999-12-31"));
		} catch (ParseException e) {
			e.printStackTrace(System.err);
		}
		addProductDetails(prodDet);
	}
	public void updateCompleteProduct(CompleteProduct completeProduct, Date refDate) {
		TimeProduct refTProduct = new TimeProduct(completeProduct.getProduct(), refDate);
		ProductDetails exactProdDet = getProductDetail(refTProduct);
		if(exactProdDet!=null && exactProdDet.getId()!=null && exactProdDet.getId().getProduct()!=null) {
			exactProdDet.setPackageSize(completeProduct.getPackageSize());
			exactProdDet.setCostPrice(completeProduct.getCostPrice());
			exactProdDet.setSellingPrice(completeProduct.getSellingPrice());
			updateProductDetails(exactProdDet);
		} else {
			ProductDetails prodDetInRange = getProductDetailInRange(refTProduct);
			if(prodDetInRange!=null && prodDetInRange.getId()!=null && prodDetInRange.getId().getProduct()!=null) {
				Date endDate = prodDetInRange.getEndDate();
				prodDetInRange.setEndDate(refDate);
				ProductDetails newProdDet = new ProductDetails();
				newProdDet.getId().setProduct(completeProduct.getProduct());
				newProdDet.getId().setStartDate(refDate);
				newProdDet.setEndDate(endDate);
				newProdDet.setPackageSize(completeProduct.getPackageSize());
				newProdDet.setCostPrice(completeProduct.getCostPrice());
				newProdDet.setSellingPrice(completeProduct.getSellingPrice());
				updateProductDetails(prodDetInRange);
				addProductDetails(newProdDet);
			} else {
				addCompleteProduct(completeProduct, refDate);
			}
		}
	}
}
